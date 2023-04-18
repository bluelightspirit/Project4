// randomize enemy moves math.random then return that move
public class AI {

    public static Move decideMove(Pokemon enemyPokemon) {
        int randomNum = (int) (1 + (Math.random() * 4));
        Move move = new Move(randomNum);
        System.out.println(randomNum);
        return move;
    }

    public static void main(String[] args) {
        decideMove(new Pokemon(1));
    }
}
