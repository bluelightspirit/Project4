import java.io.ObjectInputFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Pokemon
{
    private String name, type1, type2;
    private int hp, attack, defense, specialAttack, specialDefense, speed, level, number;
    // intellij said to import ObjectInputFilter
    private ObjectInputFilter.Status status;

    //private Ability ability;//probably get rid of
    // private ArrayList<Move> = new ArrayList<>();
   // private Item heldItem;//probably get rid of
    private double againstBug, againstDark, againstDragon, againstElectric, againstFairy,
            againstFight, againstFire, againstFlying, againstGhost, againstGrass, againstGround,
            againstIce, againstNormal, againstPoison, againstPsychic, againstRock, againstSteel,
            againstWater;
    private final int IV = 15;
    private final int EV = 0;
    private boolean burned;
    public Pokemon(int number)//makes pokemon from number
    {
        int numberForFile = number -1;
        this.number = number;
        this.level = 50; //set level
        //sets name
        this.name = getDataFromFile("name.txt", numberForFile);
        //sets types
        this.type1 = getDataFromFile("type1.txt", numberForFile);
        this.type2 = getDataFromFile("type2.txt", numberForFile);
        //sets stats
        this.hp = Integer.parseInt(getDataFromFile("hp.txt", numberForFile));
        this.attack = Integer.parseInt(getDataFromFile("attack.txt", numberForFile));
        this.defense = Integer.parseInt(getDataFromFile("defense.txt", numberForFile));
        this.specialAttack = Integer.parseInt(getDataFromFile("specialAttack.txt", numberForFile));
        this.specialDefense = Integer.parseInt(getDataFromFile("specialDefense.txt", numberForFile));
        this.speed = Integer.parseInt(getDataFromFile("speed.txt", numberForFile));
        //gets damage against types
        this.againstBug = Double.parseDouble(getDataFromFile("againstBug.txt", numberForFile));
        this.againstDark = Double.parseDouble(getDataFromFile("againstDark.txt", numberForFile));
        this.againstDragon = Double.parseDouble(getDataFromFile("againstDragon.txt", numberForFile));
        this.againstElectric = Double.parseDouble(getDataFromFile("againstElectric.txt", numberForFile));
        this.againstFairy = Double.parseDouble(getDataFromFile("againstFairy.txt", numberForFile));
        this.againstFight = Double.parseDouble(getDataFromFile("againstFight.txt", numberForFile));
        this.againstFire = Double.parseDouble(getDataFromFile("againstFire.txt", numberForFile));
        this.againstFlying = Double.parseDouble(getDataFromFile("againstFlying.txt", numberForFile));
        this.againstGhost = Double.parseDouble(getDataFromFile("againstGhost.txt", numberForFile));
        this.againstGrass = Double.parseDouble(getDataFromFile("againstGrass.txt", numberForFile));
        this.againstGround = Double.parseDouble(getDataFromFile("againstGround.txt", numberForFile));
        this.againstIce = Double.parseDouble(getDataFromFile("againstIce.txt", numberForFile));
        this.againstNormal = Double.parseDouble(getDataFromFile("againstNormal.txt", numberForFile));
        this.againstPoison = Double.parseDouble(getDataFromFile("againstPoison.txt", numberForFile));
        this.againstPsychic = Double.parseDouble(getDataFromFile("againstPsychic.txt", numberForFile));
        this.againstRock = Double.parseDouble(getDataFromFile("againstRock.txt", numberForFile));
        this.againstSteel = Double.parseDouble(getDataFromFile("againstSteel.txt", numberForFile));
        this.againstWater = Double.parseDouble(getDataFromFile("againstWater.txt", numberForFile));

        hp = ((2*hp*IV+(EV/4)*level)/100)+level+10;
        attack = ((2*attack*IV+(EV/4)*level)/100)+5;
        specialAttack = ((2*specialAttack*IV+(EV/4)*level)/100)+5;
        defense = ((2*defense*IV+(EV/4)*level)/100)+5;
        specialDefense = ((2*specialDefense*IV+(EV/4)*level)/100)+5;
        speed = ((2*speed*IV+(EV/4)*level)/100)+5;

        burned = false;

    }

    public String getDataFromFile(String fileName, int number) {
        try {
            String line;
            try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
                line = lines.skip(number).findFirst().get();
                return line;
            }
        } catch (Exception e) {
            System.out.println("Error getting data");
            System.exit(0);
        }
        return "";
    }
    public int getNumber() {
        return number;
    }
    public String getName()
    {
        return name.toLowerCase();
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp(){
        return hp;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getLevel() {
        return level;
    }

    public boolean getBurned() {
        return burned;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public double getWeakness(String type) {
        switch (type) {
            case "bug":
                return againstBug;
            case "dark":
                return againstDark;
            case "dragon":
                return againstDragon;
            case "electric":
                return againstElectric;
            case "fairy":
                return againstFairy;
            case "fight":
                return againstFight;
            case "fire":
                return againstFire;
            case "flying":
                return againstFlying;
            case "ghost":
                return againstGhost;
            case "grass":
                return againstGrass;
            case "ground":
                return againstGround;
            case "ice":
                return againstIce;
            case "normal":
                return againstNormal;
            case "poison":
                return againstPoison;
            case "psychic":
                return againstPsychic;
            case "rock":
                return againstRock;
            case "steel":
                return againstSteel;
            case "water":
                return againstWater;
            default:
                return 1;
        }
    }

}
