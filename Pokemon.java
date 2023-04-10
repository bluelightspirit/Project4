import java.io.ObjectInputFilter;
import java.util.ArrayList;

public class Pokemon {
    private String name;
    private int hp;
    private int attack;
    private int specialAttack;
    private int defense;

    // this had no variable type?
    private int specialDefense;

    private int speed;
    private int level;

    // intellij said to import ObjectInputFilter
    private ObjectInputFilter.Status status;

    // Doesn't exist..
    private Ability ability;
    //private ArrayList<Move> = new ArrayList<Move>();
    private Item heldItem;
}
