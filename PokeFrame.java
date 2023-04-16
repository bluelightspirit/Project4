import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;


public class PokeFrame extends JFrame {
    PokeFrame() {

        // set title
        this.setTitle("Pokémane Battle Simulator");

        // can't close or resize
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // set icon
        BufferedImage urlImage = getImage("https://cdn.discordapp.com/avatars/262317813396537345/7f5d50988b402fbd1c853e37ab3c27a1?size=1024");
        ImageIcon image = new ImageIcon(urlImage);
        this.setIconImage(image.getImage());

        // full screen
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);

//        this.setVisible(true);
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
        String link = "https://img.pokemondb.net/sprites/sword-shield/icon/".concat(pokemonName + ".png");
        return getImage(link);
    }

    public static void main(String[] args) {
        // base frame for all panels
        JFrame frame = new PokeFrame();

        // player panel & variables
        JPanel playerPokemonPanel = new JPanel();
        int playerTempHp = 86;
        int playerMaxHp = 150;
        String playerPokemonName = "charizard";
        String playerPokemonNameCapsFirst = playerPokemonName.substring(0,1).toUpperCase().concat(playerPokemonName.substring(1));

        // player label
        JLabel playerLabel = new JLabel();
        playerLabel.setText("<html>".concat(playerPokemonNameCapsFirst + "<br />Level: " + "Joey" + "</html>"));
        playerLabel.setForeground(Color.white);
        playerLabel.setIcon(new ImageIcon(getSprite(playerPokemonName)));

        // player hp bar
        JProgressBar playerHpBar = new JProgressBar();
        playerHpBar.setMinimum(0);
        playerHpBar.setMaximum(playerMaxHp);
        playerHpBar.setValue(playerTempHp);
        playerHpBar.setPreferredSize(new Dimension(150, 15));
        playerHpBar.setStringPainted(true);

        // round progress bar percent using BigDecimal
        BigDecimal playerPercentHp = new BigDecimal(playerHpBar.getPercentComplete());
        MathContext precision = new MathContext(4);
        BigDecimal roundedPlayerPercentHp = playerPercentHp.round(precision).multiply(BigDecimal.valueOf(100)).setScale(2).stripTrailingZeros();
        playerHpBar.setString("HP: " + playerTempHp + " / " + playerMaxHp + " (" + roundedPlayerPercentHp + "%)");
        playerPokemonPanel.add(playerHpBar, BorderLayout.WEST);

        // enemy panel & variables
        JPanel enemyPokemonPanel = new JPanel();
        int enemyTempHp = 54;
        int enemyMaxHp = 150;
        String enemyPokemonName = "pikachu";
        String enemyPokemonNameCapsFirst = enemyPokemonName.substring(0,1).toUpperCase().concat(enemyPokemonName.substring(1));

        // enemy label
        JLabel enemyLabel = new JLabel();
        enemyLabel.setText("<html>".concat(enemyPokemonNameCapsFirst + "<br />Level: " + "2" + "</html>"));
        enemyLabel.setForeground(Color.white);
        enemyLabel.setIcon(new ImageIcon(getSprite(enemyPokemonName)));

        // enemy hp bar
        JProgressBar enemyHpBar = new JProgressBar();
        enemyHpBar.setMinimum(0);
        enemyHpBar.setMaximum(enemyMaxHp);
        enemyHpBar.setValue(enemyTempHp);
        enemyHpBar.setPreferredSize(new Dimension(150, 15));
        enemyHpBar.setStringPainted(true);

        // round progress bar percent using BigDecimal
        BigDecimal enemyPercentHp = new BigDecimal(enemyHpBar.getPercentComplete());
        BigDecimal roundedEnemyPercentHp = enemyPercentHp.round(precision).multiply(BigDecimal.valueOf(100)).setScale(2).stripTrailingZeros();
        enemyHpBar.setString("HP: " + enemyTempHp + " / " + enemyMaxHp + " (" + roundedEnemyPercentHp + "%)");

        // add labels to panels
        playerPokemonPanel.add(playerLabel);
        playerPokemonPanel.setBackground(Color.blue);
        enemyPokemonPanel.add(enemyLabel);
        enemyPokemonPanel.setBackground(Color.red);
        enemyPokemonPanel.add(enemyHpBar, BorderLayout.EAST);

        // move buttons
        JButton move1 = new JButton("Move 1");
        JButton move2 = new JButton("Move 2");
        JButton move3 = new JButton("Move 3");
        JButton move4 = new JButton("Move 4");
        JButton swap = new JButton("Swap");

        // add buttons to movesPanel
        JPanel movesPanel = new JPanel();
        movesPanel.add(move1); movesPanel.add(move2); movesPanel.add(move3); movesPanel.add(move4); movesPanel.add(swap);
        movesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        movesPanel.setBackground(Color.cyan);

        // add panels to frame & set layout
        frame.setLayout(new GridLayout(3, 1));
        frame.add(enemyPokemonPanel);
        frame.add(playerPokemonPanel);
        frame.add(movesPanel);

        // pack all panels & make it visible
        frame.pack();
        frame.setVisible(true);
    }
}
