import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends JPanel {
    private BufferedImage image;
    int x;
    int y;

    public Image(String path, int X, int Y) {
        try {
            image = ImageIO.read(new File(path));
            x = X;
            y = Y;
        } catch (IOException ex) {
            System.out.println("Could not open map");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}
