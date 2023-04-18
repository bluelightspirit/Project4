import java.util.Random;

public class Battle {
//does all the logic for a pokemon attack
    public static int attack(Pokemon attacker, Pokemon defender, Move move) {
        int damage = 0;
        if(hitOrMiss(move) && move.getPP() > 0) {
            double effectiveness = defender.getWeakness(move.getMoveType());
            int D = 0;
            int A = 0;
            double burn = 1;
            if (move.getAttackType().equals("Physical")) {
                D = defender.getDefense();
                A = attacker.getAttack();
                if (attacker.getBurned()) {
                    burn = .5;
                }
            } else if (move.getAttackType().equals("Special")) {
                D = defender.getSpecialDefense();
                A = attacker.getSpecialAttack();
            }
            int level = attacker.getLevel();
            int power = move.getPower();
            boolean crit = (Math.random() * (16 - 1) + 1) == 1;
            double critHit = 1;
            double random = (Math.random() * (100 - 85) + 85) / 100;
            if (crit) {
                critHit = 1.5;
            }
            double STAB = 1;
            if (move.getMoveType().equals(attacker.getType1()) || move.getMoveType().equals(attacker.getType2())) {
                STAB = 1.5;
            }
            damage = (int) ((((((2 * level) / 5) + 2) * power * A / D) / 50) * burn * critHit * random * STAB * effectiveness);
            defender.damage(damage);
        }
        move.usePP();
        return damage;
    }
//calculates if a move hits or misses
    public static boolean hitOrMiss(Move move) {
        return (Math.random()*100 < move.getAccuracy());
    }


    // broken
//    public Pokemon switch() {
//        Pokemon poke = new Pokemon(2);
//        return poke;
//    }
//determines who goes first
    public static boolean priority(Pokemon player, Pokemon ai) {
        return (player.getSpeed() > ai.getSpeed());
    }

}
