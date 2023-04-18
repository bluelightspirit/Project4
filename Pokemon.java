import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Pokemon
{
    private final String name, type1, type2;
    private int hp, tempHp, attack, defense, specialAttack, specialDefense, speed, level, number;
    private Move move1,move2,move3,move4;
    // intellij said to import ObjectInputFilter

    //private Ability ability;//probably get rid of
    // private ArrayList<Move> = new ArrayList<>();
   // private Item heldItem;//probably get rid of
    private final double againstBug, againstDark, againstDragon, againstElectric, againstFairy,
            againstFight, againstFire, againstFlying, againstGhost, againstGrass, againstGround,
            againstIce, againstNormal, againstPoison, againstPsychic, againstRock, againstSteel,
            againstWater;
    private final int IV = 31;
    private final int EV = 0;
    private boolean burned, fainted;
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

        hp = (((2*hp+IV)*level)/100)+level+10;
        tempHp = hp;
        attack = (((2*attack+IV)*level)/100)+5;
        specialAttack = (((2*specialAttack+IV)*level)/100)+5;
        defense = (((2*defense+IV)*level)/100)+5;
        specialDefense = (((2*specialDefense+IV)*level)/100)+5;
        speed = (((2*speed+IV)*level)/100)+5;

        burned = false;
        fainted = false;

        move1 = null;
        move2 = null;
        move3 = null;
        move4 = null;
    }
    public Pokemon(String pokeName, int level)//makes pokemon from name
    {
        ArrayList<String> names = new ArrayList<String>();
        try {
            Scanner fIn = new Scanner(new FileInputStream("name.txt"));
            while(fIn.hasNext()) {
                names.add(fIn.nextLine());
            }
        }
        catch(Exception e) {
            System.out.println("error getting data");
        }
        int numberForFile = names.indexOf(pokeName)-1;
        this.number = numberForFile;
        this.level = level; //set level
        //sets name
        this.name = pokeName;
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
        tempHp = hp;
        attack = ((2*attack*IV+(EV/4)*level)/100)+5;
        specialAttack = ((2*specialAttack*IV+(EV/4)*level)/100)+5;
        defense = ((2*defense*IV+(EV/4)*level)/100)+5;
        specialDefense = ((2*specialDefense*IV+(EV/4)*level)/100)+5;
        speed = ((2*speed*IV+(EV/4)*level)/100)+5;

        burned = false;
        fainted = false;

        move1 = null;
        move2 = null;
        move3 = null;
        move4 = null;
    }
//grabs the data from the specified file on the correct line
    public String getDataFromFile(String fileName, int number) {
        try {
            String line;
            try (Stream<String> lines = Files.lines(Paths.get(".idea/".concat(fileName)))) {
                line = lines.skip(number).findFirst().get();
                return line;
            }
        } catch (Exception e) {
            System.out.println("Error getting data (within Pokemon.java)");
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

    public int getTempHp() {
        return tempHp;
    }

    public Move getMove1() {
        return move1;
    }

    public Move getMove2() {
        return move2;
    }

    public Move getMove3() {
        return move3;
    }
    public Move getMove4() {
        return move4;
    }
//returns the corresponding weakness value based on string.
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
//damages pokemon+
    public boolean damage(int damage) {
        tempHp -= damage;
        if (tempHp <= 0) {
            fainted = true;
            tempHp = 0;
        }
        return fainted;
    }

    public boolean getFainted() {
        return fainted;
    }
//sets the moves of the pokemon
    public void setMove(Move move, int moveNumber)
    {
        if(moveNumber == 1)
        {
            move1 = move;
        }
        else if(moveNumber == 2)
        {
            move2 = move;
        }
        else if(moveNumber == 3)
        {
            move3 = move;
        }
        else if(moveNumber == 4)
        {
            move4 = move;
        }
        else
        {
            return;
        }
    }

    public void setFainted() {
        fainted = true;
    }

    public  Move decideMove() {
        int choice = (int)(Math.random()*(4)+1);
        switch (choice) {
            case 1 -> {
                return move1;
            }
            case 2 -> {
                return move2;
            }
            case 3 -> {
                return move3;
            }
            case 4 -> {
                return move4;
            }
            default -> {
                System.out.println("AI class | decideMoveDefault error");
                return move1;
            }
        }
    }

}
