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
import java.util.ArrayList;

import LogicaDeNegocios.DTOs.LicenciaDTO;
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

    private  ArrayList<LicenciaDTO> licenciaDTOS = new ArrayList<>();
    private LicenciaDTO licenciaDTOActual;
    Integer cantidadLicencias;
    Integer cantidadImpresas = 0;
    Integer indiceLicenciaActual=0;



  /*  public LicenciaDeConducir(final MainFrame frame) {


        imprimir.addActionListener(actionEvent -> {
            try {
                tomarFoto();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                generarPdf();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }*/

    public LicenciaDeConducir(final MainFrame frame, ArrayList<LicenciaDTO> licenciaDTOS)   {


        this.licenciaDTOS=licenciaDTOS;
        for(LicenciaDTO l : this.licenciaDTOS){
            System.out.println(l.toString());
        }

        cantidadLicencias = this.licenciaDTOS.size();
        licenciaDTOActual= this.licenciaDTOS.get(indiceLicenciaActual);
        setearDatosLicencia(licenciaDTOActual);

        imprimir.addActionListener(actionEvent -> {
                try {
                    imprimirLicencia(licenciaDTOActual);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            JOptionPane.showMessageDialog(frame, "Impresion exitosa", "Imprimir licencia", JOptionPane.INFORMATION_MESSAGE);

        });
        verSiguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                indiceLicenciaActual++;
                if(indiceLicenciaActual>=cantidadLicencias){
                    JOptionPane.showMessageDialog(frame, "No quedan licencia mas en la cola", "Imprimir Licencia", JOptionPane.ERROR_MESSAGE);
                }else{
                    licenciaDTOActual = LicenciaDeConducir.this.licenciaDTOS.get(indiceLicenciaActual);
                    setearDatosLicencia(licenciaDTOActual);
                }

            }
        });
        imprimirTodasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(LicenciaDTO l : LicenciaDeConducir.this.licenciaDTOS){
                    setearDatosLicencia(l);
                    try {
                        imprimirLicencia(l);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(frame, "Impresion exitosa", "Imprimir licencia", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void imprimirLicencia(LicenciaDTO licenciaDTO) throws IOException {
        tomarFoto();
        generarPdf(licenciaDTO.getIdLicencia().toString());
    }

    private void setearDatosLicencia(LicenciaDTO licenciaDTO) {
        //TODO faltan campos
        tfNroLicencia.setText(licenciaDTO.getIdLicencia().toString());
        tfFechaAlta.setText(licenciaDTO.getFechaAltaLicencia().toLocalDate().toString());
        tfFechaVencimiento.setText(licenciaDTO.getFechaVencimientoLicencia().toLocalDate().toString());
        tfEsDonante.setText(licenciaDTO.getEsDonante().toString());
    }

    public void tomarFoto() throws IOException {
        // "componente" es el elemento gráfico que desaemos guardar
        //Creamos una Imagen con el tamaño del componente
        BufferedImage imagen = new BufferedImage(this.panelLicencia.getWidth(), this.panelLicencia.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //Hacemos que el componente se pinte en el Graphics de la imagen
        this.panelLicencia.paint(imagen.getGraphics());
        //Guardamos la imagen y listo
        ImageIO.write(imagen, "png", new File("fichero.png"));

        System.out.println("Se tomo la foto del panel");
    }

    public void generarPdf(String idLicencia) throws FileNotFoundException, MalformedURLException {
    // Creating a PdfWriter

        //TODO Se debe usar una ruta que pueda ser utilizada por todos.
        String dest = "D:/LicenciaDeConducir-"+idLicencia+".pdf";
        PdfWriter writer = new PdfWriter(dest);
        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        // Creating a Document
        Document document = new Document(pdfDoc);

        // Creating an ImageData object
        //TODO Se debe usar una ruta que pueda ser utilizada por todos.
        String imageFile = "C:\\Users\\Ciro Riquelme\\IdeaProjects\\TP-agiles\\fichero.png";
        ImageData data = ImageDataFactory.create(imageFile);

        // Creating an Image object
        Image img = new Image(data);

        // Adding image to the document
        document.add(img);

        // Closing the document
        document.close();

        System.out.println("Se genero el pdf de la licencia " + idLicencia + " en la ruta "+ dest);

    }

    public JPanel getPane() {
        return rootPane;
    }
}
