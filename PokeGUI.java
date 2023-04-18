import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.Color.RGBtoHSB;
import static java.awt.Color.getColor;

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
    private final JLabel log;
    private final JProgressBar playerHpBar;
    private final JProgressBar enemyHpBar;
    private final JLabel playerLabel;
    private final JLabel enemyLabel;
    private final MathContext precision = new MathContext(4, RoundingMode.HALF_UP);
    private static Pokemon playerPokemon = new Pokemon(1);
    private static Pokemon enemyPokemon = new Pokemon(1);
    private String playerPokemonNameCapsFirst;
    private String enemyPokemonNameCapsFirst;
    private boolean gameRunning = true;
    private boolean gameOverEnemyWin = false;
    private boolean gameOverPlayerWin = false;

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
    private static HashMap<String, Color> colorsHashMap = new HashMap<>();


    /// all accessible variables

    // player variables
    private String playerPokemonName = "charizard";
    private int playerTempHp;
    private int playerMaxHp;
    private int playerLevel = 20;
    private int playerNumber;

    // enemy variables
    private String enemyPokemonName = "pikachu";
    private int enemyTempHp;
    private int enemyMaxHp;
    private int enemyLevel = 50;
    private int enemyNumber;

    /// get methods (would make more sense to have in Player or AI classes)
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

    //// set methods that can be accessed by other classes USEFULLY (example: mouseHover() is irrelevant to be accessed by other classes)
    // set log/console text on bottom panel
    public void setLogText(String data) {
        long delay = 0;
        // animation
//        javax.swing.Timer slowFpsTimer = new javax.swing.Timer((int) delay, null);
//        slowFpsTimer.addActionListener(new ActionListener() {
//            int i = 0;
//            // set log text
//            public void actionPerformed(ActionEvent e) {
//                i++;
//                if (i == data.length()) {
//                    slowFpsTimer.stop();
//                } else {
//                    log.setText(data.substring(0, i + 1));
//                }
//            }
//        });

        log.setText(data);
        log.setHorizontalAlignment(SwingConstants.CENTER);
        log.setVerticalAlignment(SwingConstants.CENTER);
//        slowFpsTimer.restart();
    }
    /// player
    // set player pokemon name
    public void setPlayerPokemonName(String pokeName) {
        playerPokemonName = pokeName;
    }

    // set player level
    public void setPlayerLevel(int pokeLevel) {
        playerLevel = pokeLevel;
    }

    // set player max hp
    public void setPlayerMaxHp(int pokeHp) {
        playerMaxHp = pokeHp;
    }

    // set player temp hp
    public void setPlayerTempHp(int pokeHp) {
        playerTempHp = pokeHp;
    }

    // set player pokemon name
    public void setEnemyPokemonName(String pokeName) {
        enemyPokemonName = pokeName;
    }

    /// enemy
    // set enemy level
    public void setEnemyLevel(int pokeLevel) {
        enemyLevel = pokeLevel;
    }

    // set enemy max hp
    public void setEnemyMaxHp(int pokeHp) {
        enemyMaxHp = pokeHp;
    }

    // set enemy temp hp
    public void setEnemyTempHp(int pokeHp) {
        enemyTempHp = pokeHp;
    }

    // constructs multiple panels and merges them into one main panel using BoxLayout
    // new addition: uses 2 Pokemon objects
    PokeGUI() {
//        if (gameRunning) {
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
        playerMaxHp = playerPokemon.getHp();
        playerTempHp = playerPokemon.getTempHp();
        playerPokemonName = playerPokemon.getName();
        playerNumber = playerPokemon.getNumber();
        playerLevel = playerPokemon.getLevel();

        // player label
        playerLabel = new JLabel();
        updatePlayerLabel();

        // set text color to always be white & background outside of bar's highlight to be black
        UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
        UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
        UIManager.put("ProgressBar.background", Color.BLACK);
        // foreground is changed depending on progressbar circumstances now to red, orange, or green
        // UIManager.put("ProgressBar.foreground", Color.GREEN);

        // player hp bar
        playerHpBar = new JProgressBar();
        playerHpBar.setMinimum(0);
        playerHpBar.setMaximum(playerMaxHp);
        updatePlayerHpBar();

        playerPokemonPanel.add(playerHpBar, BorderLayout.EAST);

        // enemy panel & variables
        JPanel enemyPokemonPanel = new JPanel();
        enemyMaxHp = enemyPokemon.getHp();
        enemyTempHp = enemyPokemon.getTempHp();
        enemyPokemonName = enemyPokemon.getName();
        enemyNumber = enemyPokemon.getNumber();
        enemyLevel = enemyPokemon.getLevel();

        // enemy label
        enemyLabel = new JLabel();
        updateEnemyLabel();

        // enemy hp bar
        enemyHpBar = new JProgressBar();
        enemyHpBar.setMinimum(0);
        enemyHpBar.setMaximum(enemyMaxHp);
        updateEnemyHpBar();

        // add labels to panels
        playerPokemonPanel.add(playerLabel);
        playerPokemonPanel.setBackground(Color.blue);
        enemyPokemonPanel.add(enemyLabel);
        enemyPokemonPanel.setBackground(Color.red);
        enemyPokemonPanel.add(enemyHpBar, BorderLayout.EAST);

        // move buttons so buttons exist
        move1 = new JButton("<html><head><style> div {text-align: center;}</style></head><body><div>Move 1<br />" + playerPokemon.getMove1().getMoveName() + "</div></html>");
        move2 = new JButton("<html><head><style> div {text-align: center;}</style></head><body><div>Move 2<br />" + playerPokemon.getMove2().getMoveName() + "</div></html>");
        move3 = new JButton("<html><head><style> div {text-align: center;}</style></head><body><div>Move 3<br />" + playerPokemon.getMove3().getMoveName() + "</div></html>");
        move4 = new JButton("<html><head><style> div {text-align: center;}</style></head><body><div>Move 4<br />" + playerPokemon.getMove4().getMoveName() + "</div></html>");
        swap = new JButton("<html><head><style> div {text-align: center;}</style></head><body><div>Swap<br />Pokémon</div></html>");

        // actionListeners so clicking does something
        move1.addActionListener(this);
        move2.addActionListener(this);
        move3.addActionListener(this);
        move4.addActionListener(this);
        swap.addActionListener(this);

        setColorsHashMap();
        move1.setBackground(getPokemonMoveTypeColor(playerPokemon.getMove1().getMoveType()));
        move2.setBackground(getPokemonMoveTypeColor(playerPokemon.getMove2().getMoveType()));
        move3.setBackground(getPokemonMoveTypeColor(playerPokemon.getMove3().getMoveType()));
        move4.setBackground(getPokemonMoveTypeColor(playerPokemon.getMove4().getMoveType()));
        swap.setBackground(Color.LIGHT_GRAY);

        // set text colors for buttons
        setTextColors();

        // add MouseListeners on hover
        mouseHover();

        // change cursor on hover
        setCursor();

        // add buttons to movesPanel
        JPanel movesPanel = new JPanel();
        movesPanel.add(move1);
        movesPanel.add(move2);
        movesPanel.add(move3);
        movesPanel.add(move4);
        movesPanel.add(swap);
        movesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        movesPanel.setBackground(Color.cyan);
        movesPanel.setSize(movesPanel.getWidth(), 36);

        // add label for log
        JPanel logPanel = new JPanel();
        log = new JLabel();

        // download font
        downloadFont();

        // set log panel text & add that to the panel
        //setLogText("Welcome to Pokémane Battle Simulator!");
        logPanel.add(log);

        // main panel BoxLayout before adding all panels in
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
//            } else {
//            move1 = null;
//            move2 = null;
//            move3 = null;
//            move4 = null;
//            swap = null;
//            playerHpBar = null;
//            enemyHpBar = null;
//            playerLabel = null;
//            enemyLabel = null;
//
//            // add label for log
//            JPanel logPanel = new JPanel();
//            log = new JLabel();
//
//            // download font
//            downloadFont();
//
//            // set log panel text & add that to the panel
//            setLogText("GAME OVER");
//            logPanel.add(log);
//
//            this.setContentPane(logPanel);
//            this.pack();
//        }
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
    public static BufferedImage getSprite(int pokemonNumber) {
        // PokeAPI sprite (https://github.com/PokeAPI/sprites)
        String link = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/".concat((pokemonNumber+2) + ".png");
        // bigger sprite
        //String link = "https://img.pokemondb.net/artwork/vector/".concat(pokemonName + ".png");
        // smaller sprite
        // String link = "https://img.pokemondb.net/sprites/sword-shield/icon/".concat(pokemonName + ".png").replace(' ', '-');
        return getImage(link);
    }

    // get string of player waiting or not
    public String getPlayerTurn() {
        return "<p style=\"color: #fdfd96\">WAITING...</p>";
    }

    // get string of enemy waiting or not
    public String getEnemyTurn() {
        return "<p style=\"color: #76ff7a\">MY TURN!</p>";
    }

    // https://docs.oracle.com/en/java/javase/18/docs/api/java.desktop/java/awt/Color.html
    // gets color brightness using RGBtoHSB from Color
    public float getColorBrightness(Color color) {
        float[] hsbArr = RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return hsbArr[2];
    }

    public static void setPlayerPokemon(Pokemon pokemon) {
        playerPokemon = pokemon;
    }

    public static void setEnemyPokemon(Pokemon pokemon) {
        enemyPokemon = pokemon;
    }


    // on button click or press do something
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource(); // choice/button selected
        if (src == move1) {
            int damage = 0;
            // if playerPokemon priority is true, have player pokemon go first
            if (Battle.priority(playerPokemon, enemyPokemon)) {
                // player attacks first
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove1());
                System.out.println("move 1 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;

                // seems to break the game if not only after
                updateEnemyHpBar();
                if (enemyTempHp == 0) {
                   // enemyPokemon.setFainted();
                    // TODO adam thinks issue is here most likely so far 10:49 AM
                    if(PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(1).getName().concat(" took " + damage + " damage!</p></html>")))));
                       // System.out.println("Problem 3?");
                        enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                       // System.out.println("problem 4?");
                        enemyPokemon.setFainted();
                    }
                }
                else if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                   // setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                    System.out.println("Problem 5?");
                    if(PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(1).getName().concat(" took " + damage + " damage!</p></html>")))));
                      //  System.out.println("Problem 8?");
                       // enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                       // System.out.println("problem 7?");
                        //enemyPokemon.setFainted();
                    }
                }
                updateEnemyHpBar();

                // enemy attacks second
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 1 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;

                // seems to break the game if not only after
                updatePlayerHpBar();
                // TODO fix log text
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                else if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updatePlayerHpBar();

            // if enemy has priority
            } else {
                // enemy attacks first
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 1 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                }
                // TODO adam thinks this is an issue 10:50 AM
                if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText("<html><p style=\"text-align:center\"><html>" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                }
                updatePlayerHpBar();

                // player attacks second
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove1());
                System.out.println("move 1 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;
                if (enemyTempHp == 0) {
                    enemyPokemon.setFainted();
                }
                if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updateEnemyHpBar();
            }

            // will need StringBuilder possibly, this is too long if Green and Blue are also needed...
            // System.out.println(getPokemonMoveTypeColor(playerPokemon.getMove1().getMoveType()).getRed());
            // can do "<style> p { text-align:center; color:rgb(".concat(playerPokemon.getMove1.getMoveType().getRGB).concat(")") } </style>"
            // then "<p>text</p>"
            this.pack();
            updatePlayerLabel();
            updateEnemyLabel();

        } else if (src == move2) {

            int damage = 0;
            // if playerPokemon priority is true, have player pokemon go first
            if (Battle.priority(playerPokemon, enemyPokemon)) {
                // player attacks first
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove1());
                System.out.println("move 1 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;

                // seems to break the game if not only after
                updateEnemyHpBar();
                if (enemyTempHp == 0) {
                    // enemyPokemon.setFainted();
                    // TODO adam thinks issue is here most likely so far 10:49 AM
                    if(PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(1).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("Problem 3?");
                        enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("problem 4?");
                        enemyPokemon.setFainted();
                    }
                }
                else if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    // setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                    System.out.println("Problem 5?");
                    if(PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(1).getName().concat(" took " + damage + " damage!</p></html>")))));
                        //  System.out.println("Problem 8?");
                        // enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(1).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("problem 7?");
                        //enemyPokemon.setFainted();
                    }
                }
                updateEnemyHpBar();

                // enemy attacks second
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 1 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;

                // seems to break the game if not only after
                updatePlayerHpBar();
                // TODO fix log text
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                else if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updatePlayerHpBar();

                // if enemy has priority
            } else {
                // enemy attacks first
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 1 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                }
                // TODO adam thinks this is an issue 10:50 AM
                if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText("<html><p style=\"text-align:center\"><html>" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                }
                updatePlayerHpBar();

                // player attacks second
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove1());
                System.out.println("move 1 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;
                if (enemyTempHp == 0) {
                    enemyPokemon.setFainted();
                }
                if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove1().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updateEnemyHpBar();
            }

            // will need StringBuilder possibly, this is too long if Green and Blue are also needed...
            // System.out.println(getPokemonMoveTypeColor(playerPokemon.getMove1().getMoveType()).getRed());
            // can do "<style> p { text-align:center; color:rgb(".concat(playerPokemon.getMove1.getMoveType().getRGB).concat(")") } </style>"
            // then "<p>text</p>"
            this.pack();
            updatePlayerLabel();
            updateEnemyLabel();
        } else if (src == move3) {

            int damage = 0;
            // if playerPokemon priority is true, have player pokemon go first
            if (Battle.priority(playerPokemon, enemyPokemon)) {
                // player attacks first
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove3());
                System.out.println("move 3 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;

                // seems to break the game if not only after
                updateEnemyHpBar();
                if (enemyTempHp == 0) {
                    // enemyPokemon.setFainted();
                    // TODO adam thinks issue is here most likely so far 30:49 AM
                    if(PokemonGame.getEnemyPokemonArrayList().get(3).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove3().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(3).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("Problem 3?");
                        enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(3).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove3().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("problem 4?");
                        enemyPokemon.setFainted();
                    }
                }
                else if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    // setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove3().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                    System.out.println("Problem 5?");
                    if(PokemonGame.getEnemyPokemonArrayList().get(3).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove3().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(3).getName().concat(" took " + damage + " damage!</p></html>")))));
                        //  System.out.println("Problem 8?");
                        // enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(3).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove3().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("problem 7?");
                        //enemyPokemon.setFainted();
                    }
                }
                updateEnemyHpBar();

                // enemy attacks second
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 3 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;

                // seems to break the game if not only after
                updatePlayerHpBar();
                // TODO fix log text
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                else if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updatePlayerHpBar();

                // if enemy has priority
            } else {
                // enemy attacks first
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 3 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                }
                // TODO adam thinks this is an issue 30:50 AM
                if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText("<html><p style=\"text-align:center\"><html>" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                }
                updatePlayerHpBar();

                // player attacks second
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove3());
                System.out.println("move 3 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;
                if (enemyTempHp == 0) {
                    enemyPokemon.setFainted();
                }
                if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove3().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updateEnemyHpBar();
            }

            // will need StringBuilder possibly, this is too long if Green and Blue are also needed...
            // System.out.println(getPokemonMoveTypeColor(playerPokemon.getMove3().getMoveType()).getRed());
            // can do "<style> p { text-align:center; color:rgb(".concat(playerPokemon.getMove3.getMoveType().getRGB).concat(")") } </style>"
            // then "<p>text</p>"
            this.pack();
            updatePlayerLabel();
            updateEnemyLabel();
        } else if (src == move4) {

            int damage = 0;
            // if playerPokemon priority is true, have player pokemon go first
            if (Battle.priority(playerPokemon, enemyPokemon)) {
                // player attacks first
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove4());
                System.out.println("move 4 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;

                // seems to break the game if not only after
                updateEnemyHpBar();
                if (enemyTempHp == 0) {
                    // enemyPokemon.setFainted();
                    // TODO adam thinks issue is here most likely so far 40:49 AM
                    if(PokemonGame.getEnemyPokemonArrayList().get(4).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove4().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(4).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("Problem 3?");
                        enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(4).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove4().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("problem 4?");
                        enemyPokemon.setFainted();
                    }
                }
                else if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    // setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove4().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                    System.out.println("Problem 5?");
                    if(PokemonGame.getEnemyPokemonArrayList().get(4).getTempHp() == 0) {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove4().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(4).getName().concat(" took " + damage + " damage!</p></html>")))));
                        //  System.out.println("Problem 8?");
                        // enemyPokemon.setFainted();
                    }
                    else if(PokemonGame.getEnemyPokemonArrayList().get(0).getTempHp() == 0 && PokemonGame.getEnemyPokemonArrayList().get(4).getTempHp() > 0)
                    {
                        setLogText("<html><p style=\"text-align:center\"><html>" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove4().getMoveName().concat("!<br />".concat(PokemonGame.getEnemyPokemonArrayList().get(0).getName().concat(" took " + damage + " damage!</p></html>")))));
                        // System.out.println("problem 7?");
                        //enemyPokemon.setFainted();
                    }
                }
                updateEnemyHpBar();

                // enemy attacks second
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 4 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;

                // seems to break the game if not only after
                updatePlayerHpBar();
                // TODO fix log text
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                else if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updatePlayerHpBar();

                // if enemy has priority
            } else {
                // enemy attacks first
                Move enemyMoveChoice = enemyPokemon.decideMove();
                damage = Battle.attack(enemyPokemon, playerPokemon, enemyMoveChoice);
                System.out.println("move 4 clicked! -" + damage + " for " + playerPokemon.getName() + "!");
                playerTempHp = playerTempHp - damage;
                if (playerTempHp == 0) {
                    playerPokemon.setFainted();
                }
                // TODO adam thinks this is an issue 40:50 AM
                if (swapPlayerPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText("<html><p style=\"text-align:center\"><html>" + enemyPokemonNameCapsFirst.concat(" casted " + enemyMoveChoice.getMoveName().concat("!<br />".concat(playerPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>")))));
                }
                updatePlayerHpBar();

                // player attacks second
                damage = Battle.attack(playerPokemon, enemyPokemon, playerPokemon.getMove4());
                System.out.println("move 4 clicked! -" + damage + " for " + enemyPokemon.getName() + "!");
                enemyTempHp = enemyTempHp - damage;
                if (enemyTempHp == 0) {
                    enemyPokemon.setFainted();
                }
                if (swapEnemyPokemon() == true && PokemonGame.getPlayerTeamFainted() == false && PokemonGame.getEnemyTeamFainted() == false) {
                    setLogText(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst.concat(" casted " + playerPokemon.getMove4().getMoveName().concat("!<br />".concat(enemyPokemonNameCapsFirst.concat(" took " + damage + " damage!</p></html>"))))));
                }
                updateEnemyHpBar();
            }

            // will need StringBuilder possibly, this is too long if Green and Blue are also needed...
            // System.out.println(getPokemonMoveTypeColor(playerPokemon.getMove4().getMoveType()).getRed());
            // can do "<style> p { text-align:center; color:rgb(".concat(playerPokemon.getMove4.getMoveType().getRGB).concat(")") } </style>"
            // then "<p>text</p>"
            this.pack();
            updatePlayerLabel();
            updateEnemyLabel();
        } else if (src == swap) {
            showOptionPane();
            System.out.println("swap clicked!");
        }
    }

    // set original text colors based on background brightness
    public void setTextColors() {
        if (getColorBrightness(move1.getBackground()) <= .60) {
            move1.setForeground(Color.white);
        }
        if (getColorBrightness(move2.getBackground()) <= .60) {
            move2.setForeground(Color.white);
        }
        if (getColorBrightness(move3.getBackground()) <= .60) {
            move3.setForeground(Color.white);
        }
        if (getColorBrightness(move4.getBackground()) <= .60) {
            move4.setForeground(Color.white);
        }
        if (getColorBrightness(swap.getBackground()) <= .60) {
            swap.setForeground(Color.white);
        }
    }

    // set cursor to hand cursor on hover
    public void setCursor() {
        Cursor handCursor = new Cursor(HAND_CURSOR);
        move1.setCursor(handCursor);
        move2.setCursor(handCursor);
        move3.setCursor(handCursor);
        move4.setCursor(handCursor);
        swap.setCursor(handCursor);
    }

    // update temp hp in playerHpBar
    public void updatePlayerHpBar() {
        // playerTempHp = new player hp to aim for
        playerHpBar.setMaximum(playerMaxHp);

        // fix initial hp set and when hp is 100%
        if (playerHpBar.getValue() == 0) {
            if (playerTempHp > playerHpBar.getValue()) {
                playerHpBar.setValue(1);
            }
        } else if (playerHpBar.getPercentComplete() == 1) {
            if (playerTempHp < playerHpBar.getValue()) {
                playerHpBar.setValue(playerHpBar.getValue()-1);
            }
        }

        // set delay
        long delay = 0;
        int abs = Math.abs(playerHpBar.getValue() - playerTempHp);
        if (abs != 0) {
            delay = (1000 / abs);
        }

        // remove some repetition in actionPerformed
        playerHpBar.setPreferredSize(new Dimension(150, 15));
        playerHpBar.setStringPainted(true);

        // animation
        javax.swing.Timer slowFpsTimer = new javax.swing.Timer((int) delay, null);
        slowFpsTimer.addActionListener(new ActionListener() {
            // if temp hp = max hp or 0, force playerTempHp to stay the same
            public void actionPerformed(ActionEvent e) {
                int playerAnimatedTempHp = playerHpBar.getValue();
                // if temp hp = max hp or 0, force playerTempHp to stay the same
                if (playerAnimatedTempHp == playerMaxHp || playerAnimatedTempHp == 0) {
                    playerTempHp = playerAnimatedTempHp;
                    slowFpsTimer.stop();
                }

                if (playerAnimatedTempHp > playerTempHp) {
                    playerAnimatedTempHp--;
                } else if (playerAnimatedTempHp < playerTempHp) {
                    playerAnimatedTempHp++;
                }
                playerHpBar.setValue(playerAnimatedTempHp);

                // round progress bar percent using BigDecimal
                BigDecimal playerPercentHp = new BigDecimal(playerHpBar.getPercentComplete());
                BigDecimal roundedPlayerPercentHp = playerPercentHp.round(precision).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
                playerHpBar.setString("HP: " + playerAnimatedTempHp + " / " + playerMaxHp + " (" + roundedPlayerPercentHp + "%)");

                // set color based on HP percentage
                // > 25% - 50%: dark orange
                if (playerPercentHp.doubleValue() <= .50 && playerPercentHp.doubleValue() > .25) {
                    playerHpBar.setForeground(new Color(255, 140, 0));
                    // 0% - 25%: red / crimson
                } else if (playerPercentHp.doubleValue() <= .25) {
                    playerHpBar.setForeground(new Color(220, 20, 60));
                    // > 50%: malachite / green
                } else {
                    playerHpBar.setForeground(new Color(11, 218, 81));
                }
            }
        });

        slowFpsTimer.start();

    }

    // update temp hp in enemyHpBar
    public void updateEnemyHpBar() {
        if (!swapEnemyPokemon()) {
            gameRunning = false;
        }
        // enemyTempHp = new enemy hp to aim for
        enemyHpBar.setMaximum(enemyMaxHp);

        // fix initial hp set and when hp is 100%
        if (enemyHpBar.getValue() == 0) {
            if (enemyTempHp > enemyHpBar.getValue()) {
                enemyHpBar.setValue(1);
            }
        } else if (enemyHpBar.getPercentComplete() == 1) {
            if (enemyTempHp < enemyHpBar.getValue()) {
                enemyHpBar.setValue(enemyHpBar.getValue()-1);
            }
        }

        // set delay
        long delay = 0;
        int abs = Math.abs(enemyHpBar.getValue() - enemyTempHp);
        if (abs != 0) {
            delay = (1000 / abs);
        }

        // remove some repetition in actionPerformed
        enemyHpBar.setPreferredSize(new Dimension(150, 15));
        enemyHpBar.setStringPainted(true);

        // animation
        javax.swing.Timer slowFpsTimer = new javax.swing.Timer((int) delay, null);
        slowFpsTimer.addActionListener(new ActionListener() {
            // if temp hp = max hp or 0, force enemyTempHp to stay the same
            public void actionPerformed(ActionEvent e) {
                int enemyAnimatedTempHp = enemyHpBar.getValue();
                // if temp hp = max hp or 0, force enemyTempHp to stay the same
                if (enemyAnimatedTempHp == enemyMaxHp || enemyAnimatedTempHp == 0) {
                    enemyTempHp = enemyAnimatedTempHp;
                    slowFpsTimer.stop();
                }

                if (enemyAnimatedTempHp > enemyTempHp) {
                    enemyAnimatedTempHp--;
                } else if (enemyAnimatedTempHp < enemyTempHp) {
                    enemyAnimatedTempHp++;
                }
                enemyHpBar.setValue(enemyAnimatedTempHp);

                // round progress bar percent using BigDecimal
                BigDecimal enemyPercentHp = new BigDecimal(enemyHpBar.getPercentComplete());
                BigDecimal roundedenemyPercentHp = enemyPercentHp.round(precision).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
                enemyHpBar.setString("HP: " + enemyAnimatedTempHp + " / " + enemyMaxHp + " (" + roundedenemyPercentHp + "%)");

                // set color based on HP percentage
                // > 25% - 50%: dark orange
                if (enemyPercentHp.doubleValue() <= .50 && enemyPercentHp.doubleValue() > .25) {
                    enemyHpBar.setForeground(new Color(255, 140, 0));
                    // 0% - 25%: red / crimson
                } else if (enemyPercentHp.doubleValue() <= .25) {
                    enemyHpBar.setForeground(new Color(220, 20, 60));
                    // > 50%: malachite / green
                } else {
                    enemyHpBar.setForeground(new Color(11, 218, 81));
                }
            }
        });

        slowFpsTimer.restart();
    }

    // update label for player pokemon
    public void updatePlayerLabel() {//String turnInfo) {
        playerPokemonNameCapsFirst = playerPokemonName.substring(0, 1).toUpperCase().concat(playerPokemonName.substring(1));
        playerLabel.setText("<html>".concat(playerPokemonNameCapsFirst + "<br />Level: " + playerLevel + "</html>"));
        playerLabel.setForeground(Color.white);
        playerLabel.setIcon(new ImageIcon(getSprite(playerNumber)));
    }

    public String setCapsFirst(String pokeName) {
        return pokeName.substring(0, 1).toUpperCase().concat(pokeName.substring(1));
    }

    // update label for enemy/AI pokemon
    public void updateEnemyLabel() {//String turnInfo) {
        enemyPokemonNameCapsFirst = enemyPokemonName.substring(0, 1).toUpperCase().concat(enemyPokemonName.substring(1));
        enemyLabel.setText("<html>".concat(enemyPokemonNameCapsFirst + "<br />Level: " + enemyLevel + "</html>"));
        enemyLabel.setForeground(Color.white);
        enemyLabel.setIcon(new ImageIcon(getSprite(enemyNumber)));
    }

    public void setColorsHashMap() {
        colorsHashMap.put("bug", bug);
        colorsHashMap.put("dark", dark);
        colorsHashMap.put("dragon", dragon);
        colorsHashMap.put("electric", electric);
        colorsHashMap.put("fairy", fairy);
        colorsHashMap.put("fighting", fighting);
        colorsHashMap.put("fire", fire);
        colorsHashMap.put("flying", flying);
        colorsHashMap.put("ghost", ghost);
        colorsHashMap.put("grass", grass);
        colorsHashMap.put("ground", ground);
        colorsHashMap.put("ice", ice);
        colorsHashMap.put("normal", normal);
        colorsHashMap.put("poison", poison);
        colorsHashMap.put("psychic", psychic);
        colorsHashMap.put("rock", rock);
        colorsHashMap.put("steel", steel);
        colorsHashMap.put("water", water);
    }

    public Color getPokemonMoveTypeColor(String givenColor){
        return colorsHashMap.get(givenColor.toLowerCase());
    }

    public void downloadFont() {
        GraphicsEnvironment ge;
        try {
            // Load font file from local file system
            File fontFile = new File(".idea/fonts/northrup-regular.ttf");

            // Create Font object from the file
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(96f);

            // Register the font with the GraphicsEnvironment
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            log.setFont(font.deriveFont(48f));
            enemyLabel.setFont(font.deriveFont(24f));
            playerLabel.setFont(font.deriveFont(24f));
            enemyHpBar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            playerHpBar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        } catch (Exception e) {
            System.out.println("couldn't download font");
        }
    }

    // updates both enemy and player labels
//    public void updateBothLabels() {
//        updateEnemyLabel();
//        updatePlayerLabel();
//    }

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

            // after button is not hovered
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

            // after button is not hovered
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

            // after button is not hovered
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

            // after button is not hovered
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

            // after button is not hovered
            public void mouseExited(MouseEvent e) {
                // set button background to original
                swap.setBackground(originalBackground);
                // set button text to original
                swap.setForeground(originalForeground);
            }
        });
    }

    // swaps enemy pokemon and updates GUI
    public boolean swapEnemyPokemon() {
        if (enemyPokemon.getFainted()) {
            if (PokemonGame.getEnemyTeamFainted() == false) {
                enemyPokemon = PokemonGame.swapEnemyPokemon(enemyPokemon);
                System.out.println(enemyPokemon.getName());
                // need giant set method taking in enemyPokemon and resetting the variables at the top
                // like this:
                enemyMaxHp = enemyPokemon.getHp();
                enemyTempHp = enemyPokemon.getTempHp();
                enemyPokemonName = enemyPokemon.getName();
                enemyNumber = enemyPokemon.getNumber();
                enemyLevel = enemyPokemon.getLevel();
                updateEnemyLabel();
                this.repaint();
                // true = success
                return true;
            } else {
                // false = couldn't swap, enemy lost
                if (gameOverPlayerWin == false) {
                    System.out.println("Enemy lost!!!");
                    System.out.println(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
                    this.repaint();
                    gameOverPlayerWin = true;
                    }
                    // was causing duplicate faint in logText output
//                } else if (gameOverEnemyWin == false) {
//                    System.out.println("player lost!!!");
//                    System.out.println(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
//                    setLogText(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
//                    this.repaint();
//                    gameOverEnemyWin = true;
//                }
                return false;
            }
        }
        return true;
    }

    public boolean swapPlayerPokemon() {
        if (playerPokemon.getFainted()) {
            if (PokemonGame.getPlayerTeamFainted() == false) {
                playerPokemon = PokemonGame.swapPlayerPokemon(playerPokemon);
                System.out.println(playerPokemon.getName());
                // need giant set method taking in playerPokemon and resetting the variables at the top
                // like this:
                playerMaxHp = playerPokemon.getHp();
                playerTempHp = playerPokemon.getTempHp();
                playerPokemonName = playerPokemon.getName();
                playerNumber = playerPokemon.getNumber();
                playerLevel = playerPokemon.getLevel();
                // move buttons so buttons exist
                move1.setText("<html><head><style> div {text-align: center;}</style></head><body><div>Move 1<br />" + playerPokemon.getMove1().getMoveName() + "</div></html>");
                move2.setText("<html><head><style> div {text-align: center;}</style></head><body><div>Move 2<br />" + playerPokemon.getMove2().getMoveName() + "</div></html>");
                move3.setText("<html><head><style> div {text-align: center;}</style></head><body><div>Move 3<br />" + playerPokemon.getMove3().getMoveName() + "</div></html>");
                move4.setText("<html><head><style> div {text-align: center;}</style></head><body><div>Move 4<br />" + playerPokemon.getMove4().getMoveName() + "</div></html>");
                updatePlayerLabel();
                this.repaint();
                // true = success
                return true;
            } else {
                // false = couldn't swap, player lost
                if (gameOverEnemyWin == false) {
                    System.out.println("player lost!!!");
                    System.out.println(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
                    setLogText(log.getText().replace("</p></html>", "<br />" + playerPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
                    this.repaint();
                    gameOverEnemyWin = true;
                }
                    // was causing duplicate faint in logText output
//                } else if (gameOverPlayerWin == false) {
//                    System.out.println("Enemy lost!!!");
//                    System.out.println(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
//                    setLogText(log.getText().replace("</p></html>", "<br />" + enemyPokemonNameCapsFirst + " fainted and lost, GG!</p></html>"));
//                    this.repaint();
//                    gameOverPlayerWin = true;
//                }
                return false;
            }
        }
        return true;
    }

    public void showOptionPane() {
        ArrayList<Pokemon> playerPokemons = new ArrayList<>();
        playerPokemons = PokemonGame.getPlayerPokemonArrayList();
        String[] responses = {playerPokemons.get(0).getName(), playerPokemons.get(1).getName(), playerPokemons.get(2).getName()};
        // double check if user wants to move onto next question
        int response = JOptionPane.showOptionDialog(null,"What Pokémon would you like to swap to?", "Pokémon Swapper", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, responses, 0);
        switch (response){

            case 0: // also can be JOptionPane.YES_OPTION
                playerPokemon = playerPokemons.get(0);
                break;
            case 1:
                playerPokemon = playerPokemons.get(1);
                break;
            case 2:
                playerPokemon = playerPokemons.get(2);
                break;

        }
        playerMaxHp = playerPokemon.getHp();
        playerTempHp = playerPokemon.getTempHp();
        playerPokemonName = playerPokemon.getName();
        playerNumber = playerPokemon.getNumber();
        playerLevel = playerPokemon.getLevel();
        updatePlayerLabel();
        updatePlayerHpBar();
        this.repaint();
    }

    public String getLogText() {
        return log.getText();
    }
}
