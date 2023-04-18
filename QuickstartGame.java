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

public class QuickstartGame {

        private static ArrayList<Pokemon> playerPokemonArrayList = new ArrayList<>();
        private static ArrayList<Pokemon> enemyPokemonArrayList = new ArrayList<>();

        public static void main (String[]args) throws FileNotFoundException {
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


            try {
                Scanner fIn = new Scanner(new FileInputStream("quickStart.txt"));
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
                System.err.println("Error: " + "quickStart.txt" + " not found");
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                System.err.println("Error: file" + "quickStart.txt" + " has no more line elements to be read???");
            }

            System.out.println(playerPokemonArrayList.get(0).getName());
            System.out.println(playerPokemonArrayList.get(1).getName());
            System.out.println(playerPokemonArrayList.get(2).getName());

            System.out.println(enemyPokemonArrayList.get(0).getName());
            System.out.println(enemyPokemonArrayList.get(1).getName());
            System.out.println(enemyPokemonArrayList.get(2).getName());

            PokeGUI2.setEnemyPokemon(enemyPokemonArrayList.get(0));
            PokeGUI2.setPlayerPokemon(playerPokemonArrayList.get(0));
            JFrame pokeGUI2 = new PokeGUI2();

        }

        // determines if enemy team is fully fainted or not
        public static boolean getEnemyTeamFainted () {
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

        public static Pokemon swapEnemyPokemon (Pokemon enemyPoke){
            for (int i = 0; i <= 2; i++) {
                System.out.println(i);
                if (enemyPokemonArrayList.get(i).getFainted() == false) {
                    System.out.println("Switching to " + enemyPokemonArrayList.get(i).getName());
                    return enemyPokemonArrayList.get(i);
                }
            }
            System.out.println("All enemy pokemon were found fainted... You somehow ended up to this print statement too! :(");
            return enemyPoke;
        }

        // determines if player team is fully fainted or not
        public static boolean getPlayerTeamFainted () {
            int x = 1;
            for (int i = 0; i <= 2; i++) {
                if (playerPokemonArrayList.get(i).getFainted() == true) {
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

        public static Pokemon swapPlayerPokemon (Pokemon playerPoke){
            for (int i = 0; i <= 2; i++) {
                System.out.println(i);
                if (playerPokemonArrayList.get(i).getFainted() == false) {
                    System.out.println("Switching to " + playerPokemonArrayList.get(i).getName());
                    return playerPokemonArrayList.get(i);
                }
            }
            System.out.println("All player pokemon were found fainted... You somehow ended up to this print statement too! :(");
            return playerPoke;
        }

        public static ArrayList<Pokemon> getPlayerPokemonArrayList () {
            return playerPokemonArrayList;
        }
        public static ArrayList<Pokemon> getEnemyPokemonArrayList () {
            return enemyPokemonArrayList;
        }
    }
//}


