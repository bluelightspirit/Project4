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

    // this had no variable type?
    private int specialDefense;

    private int speed;
    private int level; //figure out later

    // intellij said to import ObjectInputFilter
    private ObjectInputFilter.Status status;

    // Doesn't exist..
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
        this.number = number;
        this.level = 50; //set level
        //sets name

        //sets type1

        //sets type2

        //sets special defense

        //sets speed

        //sets hp

        //sets attack

        //sets special attack

        //sets defense


        int numberForFile = number -1;
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
