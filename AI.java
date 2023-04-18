// randomize enemy moves math.random then return that move
public class AI {

    public static Move decideMove(Pokemon enemyPokemon) {
        Move move = new Move((int) Math.random()*4 + 1);
        return move;
    }
}
