import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;


public class PokeGUI extends JFrame implements ActionListener {
    // essential variables
    private JButton move1, move2, move3, move4, swap;

    // all accessible variables (would make more sense to have in Player or AI classes)

    // player
    private String playerPokemonName;
    private int playerTempHp;
    private int playerMaxHp;
    private int playerLevel = 20;

    // enemy
    private String enemyPokemonName;
    private int enemyTempHp;
    private int enemyMaxHp;
    private int enemyLevel = 50;

    public String getPlayerPokemonName() {
        return playerPokemonName;
    }

    public String getEnemyPokemonName() {
        return enemyPokemonName;
    }

    public int getPlayerTempHp() {
        return playerTempHp;
    }

    public int getPlayerMaxHp() {
        return playerMaxHp;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getEnemyTempHp() {
        return enemyTempHp;
    }

    public int getEnemyMaxHp() {
        return enemyMaxHp;
    }

    public int getEnemyLevel() {
        return enemyLevel;
    }

    PokeGUI() {

        // set title
        this.setTitle("Pokémane Battle Simulator");

        // can't close or resize
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // set icon
        BufferedImage urlImage = getImage("https://cdn.discordapp.com/avatars/262317813396537345/7f5d50988b402fbd1c853e37ab3c27a1?size=1024");
        ImageIcon image = new ImageIcon(urlImage);
        this.setIconImage(image.getImage());

        // player panel & variables
        JPanel playerPokemonPanel = new JPanel();
        playerTempHp = 76;
        playerMaxHp = 150;
        playerPokemonName = "charizard";
        String playerPokemonNameCapsFirst = playerPokemonName.substring(0,1).toUpperCase().concat(playerPokemonName.substring(1));

        // player label
        JLabel playerLabel = new JLabel();
        playerLabel.setText("<html>".concat(playerPokemonNameCapsFirst + "<br />Level: " + playerLevel + "<br />" + getPlayerTurn() + "</html>"));
        playerLabel.setForeground(Color.white);
        playerLabel.setIcon(new ImageIcon(getSprite(playerPokemonName)));

        // set text color to always be white & background outside of bar's highlight to be black
        UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
        UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
        UIManager.put("ProgressBar.background", Color.BLACK);
        // foreground is changed depending on progressbar circumstances now to red, orange, or green
        // UIManager.put("ProgressBar.foreground", Color.GREEN);

        // player hp bar
        JProgressBar playerHpBar = new JProgressBar();
        playerHpBar.setMinimum(0);
        playerHpBar.setMaximum(playerMaxHp);
        playerHpBar.setValue(playerTempHp);
        playerHpBar.setPreferredSize(new Dimension(150, 15));
        playerHpBar.setStringPainted(true);

        // round progress bar percent using BigDecimal
        BigDecimal playerPercentHp = new BigDecimal(playerHpBar.getPercentComplete());
        MathContext precision = new MathContext(4, RoundingMode.HALF_UP);
        BigDecimal roundedPlayerPercentHp = playerPercentHp.round(precision).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
        playerHpBar.setString("HP: " + playerTempHp + " / " + playerMaxHp + " (" + roundedPlayerPercentHp + "%)");

        // set color based on HP percentage
        // > 25% - 50%: dark orange
        if (playerPercentHp.doubleValue() <= .50 && playerPercentHp.doubleValue() > .25) {
            playerHpBar.setForeground(new Color(255,140,0));
        // 0% - 25%: red / crimson
        } else if (playerPercentHp.doubleValue() <= .25) {
            playerHpBar.setForeground(new Color(220,20,60));
        // > 50%: malachite / green
        } else {
            playerHpBar.setForeground(new Color(11,218,81));
        }

        playerPokemonPanel.add(playerHpBar, BorderLayout.WEST);

        // enemy panel & variables
        JPanel enemyPokemonPanel = new JPanel();
        enemyTempHp = 30;
        enemyMaxHp = 150;
        String enemyPokemonName = "pikachu";
        String enemyPokemonNameCapsFirst = enemyPokemonName.substring(0,1).toUpperCase().concat(enemyPokemonName.substring(1));

        // enemy label
        JLabel enemyLabel = new JLabel();
        enemyLabel.setText("<html>".concat(enemyPokemonNameCapsFirst + "<br />Level: " + enemyLevel + "<br />" + getEnemyTurn() + "</html>"));
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
        BigDecimal roundedEnemyPercentHp = enemyPercentHp.round(precision).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
        enemyHpBar.setString("HP: " + enemyTempHp + " / " + enemyMaxHp + " (" + roundedEnemyPercentHp + "%)");

        // set color based on HP percentage
        // > 25% - 50%: dark orange
        if (enemyPercentHp.doubleValue() <= .50 && enemyPercentHp.doubleValue() > .25) {
            enemyHpBar.setForeground(new Color(255,140,0));
        // 0% - 25%: red / crimson
        } else if (enemyPercentHp.doubleValue() <= .25) {
            enemyHpBar.setForeground(new Color(220,20,60));
        // > 50%: malachite / green
        } else {
            enemyHpBar.setForeground(new Color(11,218,81));
        }
        
        // add labels to panels
        playerPokemonPanel.add(playerLabel);
        playerPokemonPanel.setBackground(Color.blue);
        enemyPokemonPanel.add(enemyLabel);
        enemyPokemonPanel.setBackground(Color.red);
        enemyPokemonPanel.add(enemyHpBar, BorderLayout.EAST);

        // move buttons
        move1 = new JButton("Move 1");
        move2 = new JButton("Move 2");
        move3 = new JButton("Move 3");
        move4 = new JButton("Move 4");
        swap = new JButton("Swap");

        // actionListeners
        move1.addActionListener(this);
        move2.addActionListener(this);
        move3.addActionListener(this);
        move4.addActionListener(this);
        swap.addActionListener(this);

        // add buttons to movesPanel
        JPanel movesPanel = new JPanel();
        movesPanel.add(move1); movesPanel.add(move2); movesPanel.add(move3); movesPanel.add(move4); movesPanel.add(swap);
        movesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        movesPanel.setBackground(Color.cyan);
        movesPanel.setSize(movesPanel.getWidth(), 36);

        // add label for log
        JPanel logPanel = new JPanel();
        JLabel log = new JLabel();
        log.setText("Charizard launched fireball! (-12 HP for Pikachu)");
        logPanel.add(log);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // add panels to frame & set layout
        mainPanel.add(enemyPokemonPanel);
        mainPanel.add(playerPokemonPanel);
        mainPanel.add(movesPanel);
        mainPanel.add(logPanel);

        // pack all panels & make it visible
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);

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

    // get image from Pokémon name given
    public static BufferedImage getSprite(String pokemonName) {
        String link = "https://img.pokemondb.net/sprites/sword-shield/icon/".concat(pokemonName + ".png");
        return getImage(link);
    }

    public String getPlayerTurn() {
        return "<p style=\"color: #fdfd96\">WAITING...</p>";
    }

    public String getEnemyTurn() {
        return "<p style=\"color: #76ff7a\">It's pikachu's turn!</p>";
    }

    public static void main(String[] args) {
        // base frame for all panels
        new PokeGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource(); // choice/button selected
        if (src == move1) {
            System.out.println("move 1 clicked!");
        } else if (src == move2) {
            System.out.println("move 2 clicked!");
        } else if (src == move3) {
            System.out.println("move 3 clicked!");
        } else if (src == move4) {
            System.out.println("move 4 clicked!");
        } else if (src == swap) {
            System.out.println("swap clicked!");
        }
    }
}
