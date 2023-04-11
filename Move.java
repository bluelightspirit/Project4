import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
public class Move
{
    private String moveName,moveType,attackType;
    private int power, pp, accuracy;
    public Move(int number)
    {
        int numberForFile = number -1;
        this.power = Integer.parseInt(getDataFromFile("power.txt", numberForFile));
        this.pp =  Integer.parseInt(getDataFromFile("pp.txt", numberForFile));
        this.accuracy = Integer.parseInt(getDataFromFile("accuracy.txt", numberForFile));
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
        this.power = Integer.parseInt(getDataFromFile("power.txt", numberForFile));
        this.pp =  Integer.parseInt(getDataFromFile("pp.txt", numberForFile));
        this.accuracy = Integer.parseInt(getDataFromFile("accuracy.txt", numberForFile));
        this.moveName = getDataFromFile("moveName.txt", numberForFile);
        this.moveType = getDataFromFile("moveType.txt", numberForFile);
        this.attackType = getDataFromFile("attackType.txt", numberForFile);
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