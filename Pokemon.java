import java.io.ObjectInputFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pokemon
{
    private String name;
    private int hp;
    private int attack;
    private int specialAttack;
    private int defense;
    private int number;
    private String type1;
    private String type2;

    // this had no variable type?
    private int specialDefense;

    private int speed;
    private int level; //figure out later

    // intellij said to import ObjectInputFilter
    private ObjectInputFilter.Status status;

    //private Ability ability;//probably get rid of
    // private ArrayList<Move> = new ArrayList<>();
   // private Item heldItem;//probably get rid of
    private double againstBug;
    private double againstDark;
    private double againstDragon;
    private double againstElectric;
    private double againstFairy;
    private double againstFight;
    private double againstFire;
    private double againstFlying;
    private double againstGhost;
    private double againstGrass;
    private double againstGround;
    private double againstIce;
    private double againstNormal;
    private double againstPoison;
    private double againstPsychic;
    private double againstRock;
    private double againstSteel;
    private double againstWater;
    public Pokemon(int number)//makes pokemon from number
    {
        int numberForFile = number -1;
        this.number = number;
        this.level = 50; //set level
        //sets name
        try {
            String names = "";
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("name.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                names =  fIn.nextLine();
                count++;}
            this.name= names;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets type1
        try {
            String types = "";
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("type1.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                types =  fIn.nextLine();
                count++;}
            this.type1= types;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets type2
        try {
            String types = "";
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("type2.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                types =  fIn.nextLine();
                count++;}
            this.type2= types;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets special defense
        try {
            int damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("specialDefense.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextInt();
                count++;}
            this.specialDefense = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets speed
        try {
            int damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("speed.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextInt();
                count++;}
            this.speed = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets hp
        try {
            int damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("hp.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextInt();
                count++;}
            this.hp = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets attack
        try {
            int damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("attack.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextInt();
                count++;}
            this.attack = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets special attack
        try {
            int damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("specialAttack.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextInt();
                count++;}
            this.specialAttack = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //sets defense
        try {
            int damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("defense.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextInt();
                count++;}
            this.defense = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        //gets damage against types
        try { // bug
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstBug.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
               damage =  fIn.nextDouble();
               count++;}
            this.againstBug = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // dark
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstDark.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstDark = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // dragon
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstDragon.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstDragon = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // electric
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstElectric.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstElectric = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // fairy
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstFairy.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstFairy = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // fight
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstFight.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstFight = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // fire
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstFire.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstFire = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // flying
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstFlying.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstFlying = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // ghost
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstGhost.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstGhost = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // grass
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstGrass.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstGrass = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // ground
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstGround.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstGround = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // ice
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstIce.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstIce = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // normal
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstNormal.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstNormal = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // poison
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstPoison.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstPoison = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // psychic
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstPyschic.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstPsychic = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // rock
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstRock.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstRock = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // steel
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstSteel.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstSteel = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
        try { // water
            double damage = 0;
            int count = 0;
            Scanner fIn = new Scanner(new FileInputStream("againstWater.txt"));
            while(fIn.hasNextDouble()&&count < numberForFile) {
                damage =  fIn.nextDouble();
                count++;}
            this.againstWater = damage;
        }catch(Exception e) {
            System.out.println("error getting data");}
    }
    public int getNumber()
    {
        return number;
    }


}
