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

import static java.awt.Color.RGBtoHSB;

// main GUI class to handle everything GUI related, like JPanel, JFrame, buttons, & ActionListener
public class PokeGUI extends JFrame implements ActionListener {

    // main method to run constructor
    public static void main(String[] args) {
        // base frame for all panels
        new PokeGUI();
    }

    // essential variables
    private final JButton move1;
    private final JButton move2;
    private final JButton move3;
    private final JButton move4;
    private final JButton swap;

    // color object variables based on type of move
    private final Color bug = new Color(59, 153, 80);
    private final Color dark = new Color(90, 89, 121);
    private final Color dragon = new Color(97, 202, 217);
    private final Color electric = new Color(251, 251, 114);
    private final Color fairy = new Color(234, 19, 105);
    private final Color fighting = new Color(239, 97, 56);
    private final Color fire = new Color(253, 76, 90);
    private final Color flying = new Color(147, 178, 199);
    private final Color ghost = new Color(144, 103, 144);
    private final Color grass = new Color(39, 203, 79);
    private final Color ground = new Color(110, 73, 31);
    private final Color ice = new Color(216, 240, 248);
    private final Color normal = new Color(202, 152, 167);
    private final Color poison = new Color(155, 105, 217);
    private final Color psychic = new Color(248, 28, 145);
    private final Color rock = new Color(139, 62, 33);
    private final Color steel = new Color(66, 189, 148);
    private final Color water = new Color(134, 168, 252);


    // all accessible variables (would make more sense to have in Player or AI classes)

    // player variables
    private String playerPokemonName;
    private int playerTempHp;
    private int playerMaxHp;
    private int playerLevel = 20;

    // enemy variables
    private String enemyPokemonName;
    private int enemyTempHp;
    private int enemyMaxHp;
    private int enemyLevel = 50;

    // gets player's pokemon name
    public String getPlayerPokemonName() {
        return playerPokemonName;
    }

    // gets enemy's pokemon name
    public String getEnemyPokemonName() {
        return enemyPokemonName;
    }

    // gets player's temporary (current) hp
    public int getPlayerTempHp() {
        return playerTempHp;
    }

    // gets player's max hp
    public int getPlayerMaxHp() {
        return playerMaxHp;
    }

    // gets player's level
    public int getPlayerLevel() {
        return playerLevel;
    }

    // gets enemy's temporary (current) hp
    public int getEnemyTempHp() {
        return enemyTempHp;
    }

    // gets enemy's max hp
    public int getEnemyMaxHp() {
        return enemyMaxHp;
    }

    // gets enemy's level
    public int getEnemyLevel() {
        return enemyLevel;
    }

    // constructs multiple panels and merges them into one main panel using BoxLayout
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

        // player panel & variable initialization
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

        // move buttons so buttons exist
        move1 = new JButton("Move 1");
        move2 = new JButton("Move 2");
        move3 = new JButton("Move 3");
        move4 = new JButton("Move 4");
        swap = new JButton("Swap");

        // actionListeners so clicking does something
        move1.addActionListener(this);
        move2.addActionListener(this);
        move3.addActionListener(this);
        move4.addActionListener(this);
        swap.addActionListener(this);

        // set color for buttons
        move1.setBackground(bug);
        move2.setBackground(dark);
        move3.setBackground(dragon);
        move4.setBackground(electric);
        swap.setBackground(Color.LIGHT_GRAY);

        // set text colors for buttons
        setTextColors();

        // add MouseListeners on hover
        mouseHover();

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
        String link = "https://img.pokemondb.net/artwork/vector/".concat(pokemonName + ".png");
        // smaller sprite
        // String link = "https://img.pokemondb.net/sprites/sword-shield/icon/".concat(pokemonName + ".png");
        return getImage(link);
    }

    // get string of player waiting or not
    public String getPlayerTurn() {
        return "<p style=\"color: #fdfd96\">WAITING...</p>";
    }

    // get string of enemy waiting or not
    public String getEnemyTurn() {
        return "<p style=\"color: #76ff7a\">It's pikachu's turn!</p>";
    }

    // https://docs.oracle.com/en/java/javase/18/docs/api/java.desktop/java/awt/Color.html
    // gets color brightness using RGBtoHSB from Color
    public float getColorBrightness(Color color) {
        float[] hsbArr = RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return hsbArr[2];
    }

    // on button click do something
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

    // on mouse hover of button do something
    public void mouseHover() {
        // on hover change button color and text color
        move1.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color originalBackground = move1.getBackground();
            final Color originalForeground = move1.getForeground();

            // when button is hovered
            public void mouseEntered(java.awt.event.MouseEvent e) {
                
                // set button background to brighter color if brightness <= 75%
                if (getColorBrightness(originalBackground) <= .75) {
                    move1.setBackground(originalBackground.brighter());
                    // else set it darker
                } else {
                    move1.setBackground(originalBackground.darker());
                }

                // set button text to opposite of previous colored text
                if (originalForeground.equals(Color.white)) {
                    move1.setForeground(Color.black);
                } else {
                    move1.setForeground(Color.white);
                }
            }

            // after button is hovered
            public void mouseExited(java.awt.event.MouseEvent e) {
                
                // set button background to original
                move1.setBackground(originalBackground);
                // set button text to original
                move1.setForeground(originalForeground);
            }
        });

        move2.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color originalBackground = move2.getBackground();
            final Color originalForeground = move2.getForeground();

            // when button is hovered
            public void mouseEntered(java.awt.event.MouseEvent e) {
                
                // set button background to brighter color if brightness <= 75%
                if (getColorBrightness(originalBackground) <= .75) {
                    move2.setBackground(originalBackground.brighter());
                    // else set it darker
                } else {
                    move2.setBackground(originalBackground.darker());
                }

                // set button text to opposite of previous colored text
                if (originalForeground.equals(Color.white)) {
                    move2.setForeground(Color.black);
                } else {
                    move2.setForeground(Color.white);
                }
            }

            // after button is hovered
            public void mouseExited(java.awt.event.MouseEvent e) {
                
                // set button background to original
                move2.setBackground(originalBackground);
                // set button text to original
                move2.setForeground(originalForeground);
            }
        });

        move3.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color originalBackground = move3.getBackground();
            final Color originalForeground = move3.getForeground();

            // when button is hovered
            public void mouseEntered(java.awt.event.MouseEvent e) {
                
                // set button background to brighter color if brightness <= 75%
                if (getColorBrightness(originalBackground) <= .75) {
                    move3.setBackground(originalBackground.brighter());
                    // else set it darker
                } else {
                    move3.setBackground(originalBackground.darker());
                }

                // set button text to opposite of previous colored text
                if (originalForeground.equals(Color.white)) {
                    move3.setForeground(Color.black);
                } else {
                    move3.setForeground(Color.white);
                }
            }

            // after button is hovered
            public void mouseExited(java.awt.event.MouseEvent e) {
                
                // set button background to original
                move3.setBackground(originalBackground);
                // set button text to original
                move3.setForeground(originalForeground);
            }
        });

        move4.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color originalBackground = move4.getBackground();
            final Color originalForeground = move4.getForeground();

            // when button is hovered
            public void mouseEntered(java.awt.event.MouseEvent e) {
                
                // set button background to brighter color if brightness <= 75%
                if (getColorBrightness(originalBackground) <= .75) {
                    move4.setBackground(originalBackground.brighter());
                    // else set it darker
                } else {
                    move4.setBackground(originalBackground.darker());
                }

                // set button text to opposite of previous colored text
                if (originalForeground.equals(Color.white)) {
                    move4.setForeground(Color.black);
                } else {
                    move4.setForeground(Color.white);
                }
            }

            // after button is hovered
            public void mouseExited(java.awt.event.MouseEvent e) {
                
                // set button background to original
                move4.setBackground(originalBackground);
                // set button text to original
                move4.setForeground(originalForeground);
            }
        });

        swap.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color originalBackground = swap.getBackground();
            final Color originalForeground = swap.getForeground();

            // when button is hovered
            public void mouseEntered(java.awt.event.MouseEvent e) {
                
                // set button background to brighter color if brightness <= 75%
                if (getColorBrightness(originalBackground) <= .75) {
                    swap.setBackground(originalBackground.brighter());
                    // else set it darker
                } else {
                    swap.setBackground(originalBackground.darker());
                }

                // set button text to opposite of previous colored text
                if (originalForeground.equals(Color.white)) {
                    swap.setForeground(Color.black);
                } else {
                    swap.setForeground(Color.white);
                }
            }

            // after button is hovered
            public void mouseExited(java.awt.event.MouseEvent e) {
                
                // set button background to original
                swap.setBackground(originalBackground);
                // set button text to original
                swap.setForeground(originalForeground);
            }
        });
    }

    // set original text colors based on background brightness
    public void setTextColors() {
        if (getColorBrightness(move1.getBackground()) <= .50) {
            move1.setForeground(Color.white);
        }
        if (getColorBrightness(move2.getBackground()) <= .50) {
            move2.setForeground(Color.white);
        }
        if (getColorBrightness(move3.getBackground()) <= .50) {
            move3.setForeground(Color.white);
        }
        if (getColorBrightness(move4.getBackground()) <= .50) {
            move4.setForeground(Color.white);
        }
        if (getColorBrightness(swap.getBackground()) <= .50) {
            swap.setForeground(Color.white);
        }
    }
}
