package Interfaces;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author JorgeLPR
 */
public class ImagenDeFondo extends JFrame{

    Panel panel = new Panel();

    public ImagenDeFondo(){

        this.setDefaultCloseOperation(ImagenDeFondo.EXIT_ON_CLOSE);
        this.setTitle("Ventana con Imagen");
        this.setSize(new Dimension(700, 700));
        this.setLocationRelativeTo(null);

        this.add(panel, BorderLayout.CENTER);
    }

    public static void main(String [] args){
        ImagenDeFondo frame = new ImagenDeFondo();
        frame.setVisible(true);
    }
}
