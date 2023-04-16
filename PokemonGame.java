import java.io.ObjectInputFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
public class PokemonGame
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of game file");
        System.out.println("");
        String input = in.nextLine();
        File file =new File("./PokemonData/");
        input = input.replace(" ", "_");
        input = input.toLowerCase();
        String fileName = "./PokemonData/"+ input + ".txt";
        System.out.println("Game file " + fileName + "was made");
        int count = 0;
        //get list of pokemon and list of moves
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> moveNames = new ArrayList<String>();
        try
        {
            Scanner fIn = new Scanner(new FileInputStream("name.txt"));
            while(fIn.hasNext())
            {
                names.add(fIn.nextLine());
            }
        }
        catch(Exception e)
        {
            System.out.println("error getting data");
        }
        try
        {
            Scanner fIn = new Scanner(new FileInputStream("moveNames.txt"));
            while(fIn.hasNext())
            {
                moveNames.add(fIn.nextLine());
            }
        }
        catch(Exception e)
        {
            System.out.println("error getting data");
        }



        while(count < 6)
        {
            if(count < 3) //player pokemon
            {

            }
            else //enemy pokemon
            {

            }
        }







        //have it create file
        //pokemon must be selected by their pokedex number
        //user will then be prompted to enter moves for pokemon
        //moves will be accepted if they are a type that the pokemon is
        //in file it will have the 6 player pokemon with their moves
        /* example file entry for sylveon and vaporeon
            700
            50
            Charm
            Baby-Doll Eyes
            Draining Kiss
            Moonblast

            134
            50
           Tackle
            Water Pulse
            Hydro Pump
            Baby-Doll Eyes
        */


        // file will also have 6 randomly generated pokemon with random moves for the enemy

    }
}
