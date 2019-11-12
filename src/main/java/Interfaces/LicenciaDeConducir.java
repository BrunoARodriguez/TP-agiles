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

    public LicenciaDeConducir() {
        imprimir.addActionListener(actionEvent -> {
            try {
                tomarFoto();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                imprimirLicencia();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    public void tomarFoto() throws IOException {
        // "componente" es el elemento gráfico que desaemos guardar
//Creamos una Imagen con el tamaño del componente
        BufferedImage imagen = new BufferedImage(this.panelLicencia.getWidth(), this.panelLicencia.getHeight(), BufferedImage.TYPE_INT_ARGB);
//Hacemos que el componente se pinte en el Graphics de la imagen
        this.panelLicencia.paint(imagen.getGraphics());
//Guardamos la imagen y listo
        ImageIO.write(imagen, "png", new File("fichero.png"));

        System.out.print("Se ejecuto");
    }

    public void imprimirLicencia() throws FileNotFoundException, MalformedURLException {
// Creating a PdfWriter
        String dest = "D:/addingImage.pdf";
        PdfWriter writer = new PdfWriter(dest);
        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        // Creating a Document
        Document document = new Document(pdfDoc);

        // Creating an ImageData object
        String imageFile = "C:\\Users\\Ciro Riquelme\\IdeaProjects\\TP-agiles\\fichero.png";
        ImageData data = ImageDataFactory.create(imageFile);

        // Creating an Image object
        Image img = new Image(data);

        // Adding image to the document
        document.add(img);

        // Closing the document
        document.close();

        System.out.println("Image added");

    }

    public JPanel getPane() {
        return rootPane;
    }
}
