import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.TileObserver;
import java.io.IOException;
import java.net.URL;
import javax.swing.border.TitledBorder;
import java.nio.Buffer;
import java.awt.LayoutManager;
import java.io.Serializable;


public class PokeFrame extends JFrame {
    PokeFrame() {
        this.setTitle("Pokémane Battle Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        // full screen
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);

//        this.setVisible(true);
//        BufferedImage urlImage = getImage("https://cdn.discordapp.com/avatars/262317813396537345/7f5d50988b402fbd1c853e37ab3c27a1?size=1024");
//
//        ImageIcon image = new ImageIcon(urlImage);
//        this.setIconImage(image.getImage());
//        this.getContentPane().setBackground(new Color(255, 255, 255));
//
//        JLabel label = new JLabel();
//        label.setText("test");
//        ImageIcon icon2 = new ImageIcon(getImage("https://img.pokemondb.net/sprites/sword-shield/icon/venusaur.png"));
//        label.setIcon(icon2);

        // this.add(label);
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

    // get image from pokemon name given
    public static BufferedImage getSprite(String pokemonName) {
        BufferedImage urlImage;
        String link = "https://img.pokemondb.net/sprites/sword-shield/icon/".concat(pokemonName + ".png");
        return getImage(link);
    }

    public static void main(String[] args) {
        JFrame frame = new PokeFrame();

        JPanel playerPokemonPanel = new JPanel();
        JLabel playerLabel = new JLabel();
        int tempHp = 0;
        playerLabel.setText("<html>Charizard <br />HP: " + tempHp + "</html>");
        playerLabel.setForeground(Color.white);
        ImageIcon icon = new ImageIcon(getSprite("charizard"));
        playerLabel.setIcon(icon);
        playerPokemonPanel.add(playerLabel);
        playerPokemonPanel.setBackground(Color.blue);


        JPanel enemyPokemonPanel = new JPanel();
        JLabel enemyLabel = new JLabel();
        enemyLabel.setText("<html>Pikachu<br />HP: " + tempHp + "</html>");
        enemyLabel.setForeground(Color.white);
        icon = new ImageIcon(getSprite("pikachu"));
        enemyLabel.setIcon(icon);
        enemyPokemonPanel.add(enemyLabel);
        enemyPokemonPanel.setBackground(Color.red);


        JButton move1 = new JButton("Move 1");
        JButton move2 = new JButton("Move 2");
        JButton move3 = new JButton("Move 3");
        JButton move4 = new JButton("Move 4");
        JButton swap = new JButton("Swap");

        JPanel movesPanel = new JPanel();
        movesPanel.add(move1); movesPanel.add(move2); movesPanel.add(move3); movesPanel.add(move4); movesPanel.add(swap);
        movesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        movesPanel.setBackground(Color.cyan);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(enemyPokemonPanel);
        frame.add(playerPokemonPanel);
        frame.add(movesPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
