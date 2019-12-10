package Interfaces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import LogicaDeNegocios.DTOs.CarnetDTO;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;


public class LicenciaDeConducir {
    private JPanel rootPane;
    private Panel formatoLicencia;
    private JPanel panelLicencia;
    private JButton imprimir;

    private JLabel tfNroLicencia;
    private JLabel tfApellido;
    private JLabel tfNombre;
    private JLabel tfDomicilio;
    private JLabel tfFechaNacimiento;
    private JLabel tfFechaAlta;
    private JLabel tfTipoDeClases;
    private JLabel tfFechaVencimiento;
    private JLabel tfEsDonante;
    private JLabel tfGrupoYFactor;
    private JLabel tfObservaciones;
    private JButton imprimirTodasButton;
    private JButton verSiguienteButton;
    private JButton cancelarButton;
    private JButton comprobanteButton;

    private ArrayList<CarnetDTO> carnetDTOS = new ArrayList<>();
    private CarnetDTO carnetDTOActual;
    Integer cantidadLicencias;
    Integer cantidadImpresas = 0;
    Integer indiceLicenciaActual=0;

    public LicenciaDeConducir(final MainFrame frame, ArrayList<CarnetDTO> carnetDTOS)   {

        frame.setSize(1200,400);
        frame.setLocationRelativeTo(null);

        this.carnetDTOS =carnetDTOS;
        for(CarnetDTO l : this.carnetDTOS){
            System.out.println(l.toString());
        }

        cantidadLicencias = this.carnetDTOS.size();
        carnetDTOActual = this.carnetDTOS.get(indiceLicenciaActual);
        setearDatosLicencia(carnetDTOActual);



        imprimir.addActionListener(actionEvent -> {
                try {

                    if(imprimirLicencia(carnetDTOActual)){
                        JOptionPane.showMessageDialog(frame, "Impresion exitosa", "Imprimir licencia", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(frame, "No puede imprimir una licencia NO VIGENTE", "Imprimir Licencia", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        });

        verSiguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                indiceLicenciaActual++;
                if(indiceLicenciaActual>=cantidadLicencias){
                    JOptionPane.showMessageDialog(frame, "No quedan licencia mas en la cola", "Imprimir Licencia", JOptionPane.ERROR_MESSAGE);
                }else{
                    carnetDTOActual = LicenciaDeConducir.this.carnetDTOS.get(indiceLicenciaActual);
                    setearDatosLicencia(carnetDTOActual);
                }

            }
        });
        imprimirTodasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(CarnetDTO l : LicenciaDeConducir.this.carnetDTOS){
                    setearDatosLicencia(l);
                    try {
                        if(imprimirLicencia(l)){

                        }else{
                            JOptionPane.showMessageDialog(frame, "La licencia "+ l.getIdLicencia()+ " no se encuentra vigente", "Imprimir Licencia", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(frame, "Impresion exitosa", "Imprimir licencia", JOptionPane.INFORMATION_MESSAGE);
                frame.cambiarPanel(MainFrame.PANE_MENU_OPERADOR);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialogCancelar c = new JDialogCancelar(frame);
                if(c.fueCancelado()) {
                    frame.cambiarPanel(MainFrame.PANE_MENU_OPERADOR);
                }
            }
        });

        comprobanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(carnetDTOActual.getComprobante()!=null){
                    frame.cambiarPanelComprobante(carnetDTOActual);
                }else{
                    System.out.println("No existe comprobante en el sistema");
                    JOptionPane.showMessageDialog(frame, "No existe comprobante en el sistema", "Imprimir comprobante", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private Boolean imprimirLicencia(CarnetDTO licenciaDTO) throws IOException {
        if(validarEstadoLicencia(licenciaDTO)){
            tomarFoto();
            generarPdf(licenciaDTO.getIdLicencia().toString());
            return true;
        }else{
            System.out.println("La licencia con id " + licenciaDTO.getIdLicencia() + " no esta vigente");
            return false;
        }
    }

    private void setearDatosLicencia(CarnetDTO licenciaDTO) {
        //TODO faltan campos
        tfNroLicencia.setText(licenciaDTO.getIdLicencia().toString());
        tfNombre.setText(licenciaDTO.getNombre());
        tfApellido.setText(licenciaDTO.getApellido());
        tfDomicilio.setText(licenciaDTO.getDomicilio());
        tfFechaNacimiento.setText(licenciaDTO.getFechaDeNacimiento().toLocalDate().toString());
        tfFechaAlta.setText(licenciaDTO.getFechaAltaLicencia().toLocalDate().toString());
        tfFechaVencimiento.setText(licenciaDTO.getFechaVencimientoLicencia().toLocalDate().toString());
        String clases=" ";
        for(String clase : licenciaDTO.getClasesLicencia()){
            clases=clases.concat(" - " + clase);
        }
        tfTipoDeClases.setText(clases);

        tfEsDonante.setText(licenciaDTO.getEsDonante().toString());
        tfGrupoYFactor.setText(licenciaDTO.getTipoSangre().toString());
        tfObservaciones.setText(licenciaDTO.getObservacionesLicencia());
    }

    public void tomarFoto() throws IOException {
        // "componente" es el elemento gráfico que desaemos guardar
        //Creamos una Imagen con el tamaño del componente
        BufferedImage imagen = new BufferedImage(this.panelLicencia.getWidth(), this.panelLicencia.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //Hacemos que el componente se pinte en el Graphics de la imagen
        this.panelLicencia.paint(imagen.getGraphics());
        //Guardamos la imagen y listo
        ImageIO.write(imagen, "png", new File("src/fichero.png"));

        System.out.println("Se tomo la foto del panel");
    }

    public void generarPdf(String idLicencia) throws FileNotFoundException, MalformedURLException {
    // Creating a PdfWriter

        //TODO Se debe usar una ruta que pueda ser utilizada por todos.
        //C:/Users/usuario/IdeaProjects/TP-agiles
        String dest = "C:\\Users\\Public\\LicenciaDeConducir-"+idLicencia+".pdf";
        PdfWriter writer = new PdfWriter(dest);
        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        // Creating a Document
        Document document = new Document(pdfDoc);

        // Creating an ImageData object
        //TODO Se debe usar una ruta que pueda ser utilizada por todos.
       // String imageFile = "C:\\Users\\Ciro Riquelme\\IdeaProjects\\TP-agiles\\fichero.png";

        String imageFile  = "src/fichero.png";
        ImageData data = ImageDataFactory.create(imageFile);

        // Creating an Image object
        Image img = new Image(data);

        // Adding image to the document
        document.add(img);

        // Closing the document
        document.close();

        System.out.println("Se genero el pdf de la licencia " + idLicencia + " en la ruta "+ dest);
    }

    private Boolean validarEstadoLicencia(CarnetDTO licencia){
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaVencimientoLicencia = licencia.getFechaVencimientoLicencia();
        if (fechaActual.isBefore(fechaVencimientoLicencia)){
            return true;
        }else{
            return false;
        }
    }

    public JPanel getPane() {
        return rootPane;
    }
}
