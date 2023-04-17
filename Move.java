import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
public class Move
{
    private String moveName,moveType,attackType;
    private int power, pp, accuracy;
    private boolean cannotMiss, variablePower;
    public Move(int number)
    {
        int numberForFile = number -1;
        String tempPower = getDataFromFile("power.txt", numberForFile);
        if (tempPower.equals("-")) {
            this.power = 1;
            variablePower = true;
        } else {
            this.power = Integer.parseInt(tempPower);
            variablePower = false;
        }
        this.pp =  Integer.parseInt(getDataFromFile("pp.txt", numberForFile));
        String tempAccuracy = getDataFromFile("accuracy.txt", numberForFile);
        if (tempAccuracy.equals("-")) {
            this.power = 100;
            cannotMiss = true;
        } else {
            this.power = Integer.parseInt(tempAccuracy);
            cannotMiss = false;
        }
        this.moveName = getDataFromFile("moveName.txt", numberForFile);
        this.moveType = getDataFromFile("moveType.txt", numberForFile);
        this.attackType = getDataFromFile("attackType.txt", numberForFile);

    }
    public Move(String name)
    {
        int numberForFile = 0;
        try {
            Scanner in = new Scanner(new FileReader("moveName.txt"));
            String findName = "";
            while(in.hasNext() && !findName.equals(name))
            {
                findName = in.nextLine();
                if(!findName.equals(name))
                {
                    numberForFile++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("Error finding move name");
        }
        String tempPower = getDataFromFile("power.txt", numberForFile);
        // note: weird dash for power.txt
        if (tempPower.equals("—")) {
            this.power = 1;
            variablePower = true;
        } else {
            this.power = Integer.parseInt(tempPower);
            variablePower = false;
        }
        this.pp =  Integer.parseInt(getDataFromFile("pp.txt", numberForFile));
        String tempAccuracy = getDataFromFile("accuracy.txt", numberForFile);
        // note: weird dash for accuracy.txt
        if (tempAccuracy.equals("—")) {
            this.power = 10000;
            cannotMiss = true;
        } else {
            this.power = Integer.parseInt(tempAccuracy);
            cannotMiss = false;
        }
        this.moveName = getDataFromFile("moveName.txt", numberForFile);
        this.moveType = getDataFromFile("moveType.txt", numberForFile);
        this.attackType = getDataFromFile("attackType.txt", numberForFile);
    }
    public String getDataFromFile(String fileName, int number) {
        try {
            String line;
            try (Stream<String> lines = Files.lines(Paths.get(".idea/".concat(fileName)))) {
                line = lines.skip(number).findFirst().get();
                return line;
            }
        } catch (Exception e) {
            System.out.println("Error getting data (within Move.java)");
            System.exit(0);
        }
        return "";
    }
    public int getAccuracy()
    {
        return accuracy;
    }
    public int getPower()
    {
        return power;
    }
    public int getPP()
    {
        return pp;
    }
    public String getMoveName()
    {
        return moveName;
    }
    public String getMoveType()
    {
        return moveType;
    }
    public String getAttackType()
    {
        return attackType;
    }
}