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
import java.util.random.*;
import java.lang.Math;
public class PokemonGame
{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of game file");
        System.out.println("");
        String input = in.nextLine();
        File file =new File("./PokemonData/");
        input = input.replace(" ", "_");
        input = input.toLowerCase();
        String fileName = "./PokemonData/"+ input + ".txt";
        String filename = input + ".txt";
        System.out.println("Game file " + fileName + "was made");
        PrintWriter print = new PrintWriter(filename);
        int count = 0;
        //get list of pokemon and list of moves
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> moveNames = new ArrayList<String>();
        try {
            Scanner fIn = new Scanner(new FileInputStream("name.txt"));
            while(fIn.hasNext()) {
                names.add(fIn.nextLine());
            }
        }
        catch(Exception e) {
            System.out.println("error getting data");
        }
        try {
            Scanner fIn = new Scanner(new FileInputStream("moveName.txt"));
            while(fIn.hasNext()) {
                moveNames.add(fIn.nextLine());
            }
        }
        catch(Exception e) {
            System.out.println("error getting data");
        }
        //adds pokemon
        while(count < 6) {
            while(count < 6) {
                if (count < 3) {
                    System.out.println("Please enter name of pokemon to be added to your team. Or type R to let RNG decide pokemon");
                } else {
                    System.out.println("Please enter name of pokemon to be added to enemy team. Or type R to let RNG decide pokemon");
                }
                String response = in.next();

                if (response.equals("R")) {//rng decide
                    int i = (int) (Math.random() * 801);
                    int mov1 = (int) (Math.random() * 502);
                    int mov2 = (int) (Math.random() * 502);
                    int mov3 = (int) (Math.random() * 502);
                    int mov4 = (int) (Math.random() * 502);
                    print.println(names.get(i));
                    print.println(50);
                    print.println(moveNames.get(mov1));
                    print.println(moveNames.get(mov2));
                    print.println(moveNames.get(mov3));
                    print.println(moveNames.get(mov4));
                    count++;
                    break;
                } else {
                    while (!names.contains(response)) {
                        if (count < 3) {
                            System.out.println("Please enter name of pokemon to be added to your team");
                        } else {
                            System.out.println("Please enter name of pokemon to be added to enemy team");
                        }
                        response = in.next();
                    }
                    print.println(response);
                    System.out.println("Enter level of pokemon between 1-100");
                    int a = in.nextInt();
                    if (a > 0 && a <= 100) {
                        print.println(a);
                    } else {
                        print.println(50);
                    }
                    // add response
                    System.out.println("Enter name of move to be added or type R to let RNG decide");
                    response = in.next();
                    int count2 = 0;
                    if (response.equals("R")) {//rng decide
                        int mov1 = (int) (Math.random() * 502);
                        int mov2 = (int) (Math.random() * 502);
                        int mov3 = (int) (Math.random() * 502);
                        int mov4 = (int) (Math.random() * 502);
                        print.println(moveNames.get(mov1));
                        print.println(moveNames.get(mov2));
                        print.println(moveNames.get(mov3));
                        print.println(moveNames.get(mov4));
                        count++;
                        break;
                    }
                    while (!moveNames.contains(response) && count2 < 4) {
                        System.out.println("Enter name of move to be added");
                        response = in.next();
                        if (moveNames.contains(response)) {
                            print.println(response);
                            count2++;
                        }
                    }
                    count++;
                    break;
                }
            }

        }
        print.close();
    }
}
