package Interfaces;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{

    @Override
    public void paint(Graphics g){
        Dimension dimension = this.getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/back.jpg"));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        super.paintChildren(g);
    }

    public void tomarFoto() throws IOException {
        // "componente" es el elemento gráfico que deseamos guardar
        //Creamos una Imagen con el tamaño del componente
        BufferedImage imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //Hacemos que el componente se pinte en el Graphics de la imagen
        this.paint(imagen.getGraphics());
        //Guardamos la imagen y listo
        ImageIO.write(imagen, "png", new File("fichero.png"));
        System.out.print("Se tomo la foto del panel");
    }

}
