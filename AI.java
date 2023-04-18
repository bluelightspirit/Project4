// randomize enemy moves math.random then return that move
public class AI {
    //picks the move at random
    public static Move decideMove(Pokemon pokemon) {
        int choice = (int)(Math.random()*(4)+1);
        switch (choice) {
            case 1 -> {
                return pokemon.getMove1();
            }
            case 2 -> {
                return pokemon.getMove2();
            }
            case 3 -> {
                return pokemon.getMove3();
            }
            case 4 -> {
                return pokemon.getMove4();
            }
            default -> {
                System.out.println("AI class | decideMoveDefault error");
                return pokemon.getMove1();
            }
        }
    }

    public static void main(String[] args) {
        decideMove(new Pokemon(1));
    }
}
