package gestores;

import LogicaDeNegocios.Entidades.Comprobante;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

public abstract class GestorImpresion {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static Boolean imprimirComprobante(Comprobante comprobante){

        String dest = "C:\\Users\\Public\\comprobante_"+comprobante.hashCode()+".pdf";
        try{
            PdfWriter pdfWriter = new PdfWriter(dest);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A6);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);

            String paragraphS = "Municipalidad Santa Fe.";
            Paragraph paragraph = new Paragraph(paragraphS);
            paragraph.setTextAlignment(TextAlignment.CENTER);

            document.add(paragraph);

            paragraphS = "Comprobante de tramite de licencia de conducir.";
            paragraph = new Paragraph(paragraphS);
            paragraph.setTextAlignment(TextAlignment.CENTER);

            document.add(paragraph);

            paragraphS = "\n"+comprobante.getObservaciones();
            paragraph = new Paragraph(paragraphS);
            paragraph.setFontSize(10.0F);

            document.add(paragraph);

            paragraphS = "Costo del tramite: $"+comprobante.getCostoTramite();
            paragraph = new Paragraph(paragraphS);
            paragraph.setFontSize(10.0F);

            document.add(paragraph);

            paragraphS = "Fecha y hora del tramite: "+dateTimeFormatter.format(comprobante.getFechaCreacion());
            paragraph = new Paragraph(paragraphS);
            paragraph.setFontSize(10.0F);

            document.add(paragraph);

            document.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


        return true;

    }



}
