import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;

public class PokeFrame extends JFrame {
    PokeFrame() {
        this.setTitle("Pokémane Battle Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setSize(500,500);

        // full screen
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);

        this.setVisible(true);
        BufferedImage urlImage = getImage("https://img.pokemondb.net/sprites/sword-shield/icon/charizard.png");

        ImageIcon image = new ImageIcon(urlImage);
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel();
        label.setText("test");
        ImageIcon icon = new ImageIcon(getImage("https://img.pokemondb.net/sprites/sword-shield/icon/venusaur.png"));
        label.setIcon(icon);

        this.add(label);
    }

    // get image from link given
    public static BufferedImage getImage(String url) {
        BufferedImage urlImage;
        try {
            urlImage = ImageIO.read(new URL(url));
            return urlImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new PokeFrame();

        JLabel label = new JLabel();
        label.setText("test");
        ImageIcon icon = new ImageIcon(getImage("https://img.pokemondb.net/sprites/sword-shield/icon/venusaur.png"));
        label.setIcon(icon);

        frame.setVisible(true);
        frame.add(label);
    }
}
