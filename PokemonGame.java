import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
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
    private static ArrayList<Pokemon> playerPokemonArrayList = new ArrayList<>();
    private static ArrayList<Pokemon> enemyPokemonArrayList = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of game file");
        System.out.println("");
        String input = in.nextLine();
        File file =new File(".idea/PokemonData/");
        input = input.replace(" ", "_");
        input = input.toLowerCase();
        String fileName = ".idea/PokemonData/"+ input + ".txt";
        // why does this exist
        // String filename = input + ".txt";
        System.out.println("Game file " + fileName + "was made");
        PrintWriter print = new PrintWriter(fileName);
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
      // while(count < 6) {
           // while(count < 6) {
        for(int ii = 0; ii < 6; ii++) {
            //if (count < 3) {
                System.out.println("Please enter name of pokemon to be added first 3 are your team next three are enemy. Or type R to let RNG decide pokemon");
           // } else {
               // System.out.println("Please enter name of pokemon to be added to enemy team. Or type R to let RNG decide pokemon");
            //}
            String response = in.next();

            if (response.equals("R"))
            {//rng decide
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
                // break;
            }
            else
            {
                while (!names.contains(response) && !response.equals("R")) {
                    if (count < 3) {
                        System.out.println("Please enter name of pokemon to be added to your team");
                        response = in.nextLine();
                    } else {
                        System.out.println("Please enter name of pokemon to be added to enemy team");
                        response = in.nextLine();
                    }
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
                        // break;
                    } /*else {
                        response = in.next();
                    }*/
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
                //System.out.println("Enter name of move to be added or type R to let RNG decide");
                //response = in.next();
                int count2 = 0;
              /*  if (response.equals("R"))
                {//rng decide
                    int mov1 = (int) (Math.random() * 502);
                    int mov2 = (int) (Math.random() * 502);
                    int mov3 = (int) (Math.random() * 502);
                    int mov4 = (int) (Math.random() * 502);
                    print.println(moveNames.get(mov1));
                    print.println(moveNames.get(mov2));
                    print.println(moveNames.get(mov3));
                    print.println(moveNames.get(mov4));
                    count++;
                   //  break;
                }*/
                while (!moveNames.contains(response) && count2 < 4)
                {
                    System.out.println("Enter name of move to be added or type R to let RNG decide");
                    response = in.next();
                    if (moveNames.contains(response)) {
                        print.println(response);
                        count2++;
                        response = "";
                    }
                    else if (response.equals("R"))
                    {//rng decide
                        int mov1 = (int) (Math.random() * 502);
                        int mov2 = (int) (Math.random() * 502);
                        int mov3 = (int) (Math.random() * 502);
                        int mov4 = (int) (Math.random() * 502);
                        if(count2 == 0)
                        {
                            print.println(moveNames.get(mov1));
                            count2++;
                        }
                        else if(count2 == 1)
                        {
                            print.println(moveNames.get(mov2));
                            count2++;

                        }
                        else if(count2 == 2)
                        {
                            print.println(moveNames.get(mov3));
                            count2++;
                        }
                        else if(count2 == 3)
                        {
                            print.println(moveNames.get(mov4));
                            count2++;
                        }
                       // print.println(moveNames.get(mov1));
                       // print.println(moveNames.get(mov2));
                        //print.println(moveNames.get(mov3));
                        //print.println(moveNames.get(mov4));
                        //count++;
                        //  break;
                    }
                }
                count++;
                 // break;
                // }
            }

            }
       // }
        print.close();



        try {
            Scanner fIn = new Scanner(new FileInputStream(fileName));
            int i = 1;
            while (fIn.hasNext()) {
                // get pokemon name & pokemon level
                String pokemonName = fIn.nextLine();
                int pokemonLevel = Integer.parseInt(fIn.nextLine());
                Pokemon pokemon = new Pokemon(pokemonName, pokemonLevel);

                // get moves
                pokemon.setMove(new Move(fIn.nextLine()), 1);
                pokemon.setMove(new Move(fIn.nextLine()), 2);
                pokemon.setMove(new Move(fIn.nextLine()), 3);
                pokemon.setMove(new Move(fIn.nextLine()), 4);

                // add to arraylist
                if (i > 3) {
                    enemyPokemonArrayList.add(pokemon);
                } else {
                    playerPokemonArrayList.add(pokemon);
                }
                i++;
            }
            fIn.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + fileName + " not found");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.err.println("Error: file" + fileName + " has no more line elements to be read???");
        }

        System.out.println(playerPokemonArrayList.get(0).getName());
        System.out.println(playerPokemonArrayList.get(1).getName());
        System.out.println(playerPokemonArrayList.get(2).getName());

        System.out.println(enemyPokemonArrayList.get(0).getName());
        System.out.println(enemyPokemonArrayList.get(1).getName());
        System.out.println(enemyPokemonArrayList.get(2).getName());

        PokeGUI.setEnemyPokemon(enemyPokemonArrayList.get(0));
        PokeGUI.setPlayerPokemon(playerPokemonArrayList.get(0));
        JFrame pokeGUI = new PokeGUI();

//        ((PokeGUI) pokeGUI).setPlayerPokemonName(playerPokemonArrayList.get(0).getName());
//        ((PokeGUI) pokeGUI).updatePlayerLabel("WAITING");

//        ((PokeGUI) pokeGUI).setPlayerPokemon(playerPokemonArrayList.get(0));
//        ((PokeGUI) pokeGUI).setEnemyPokemon(enemyPokemonArrayList.get(0));
    }

    // determines if enemy team is fully fainted or not
    public static boolean getEnemyTeamFainted() {
        int x = 1;
        for (int i = 0; i <= 2; i++) {
            if (enemyPokemonArrayList.get(i).getFainted() == true) {
                x++;
            }
        }
        if (x == 4) {
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    public static Pokemon swapEnemyPokemon(Pokemon enemyPoke) {
        if (enemyPoke.getFainted() == true && getEnemyTeamFainted() == false) {
            for (int i = 0; i <= 2; i++) {
                System.out.println(i);
                if (enemyPokemonArrayList.get(i).getFainted() == false) {
                    System.out.println("Switching to " + enemyPokemonArrayList.get(i).getName());
                    return enemyPokemonArrayList.get(i);
                }
            }
        }
        System.out.println("All enemy pokemon was found fainted :(");
        return enemyPoke;
    }
}
