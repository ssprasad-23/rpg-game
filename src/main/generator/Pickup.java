package main.generator;

import main.parser.DungeonParser;

import java.util.Random;

/**
 * The Pickup class holds details for a particular dungeon pickup.
 * It stores the name of the Pickup and the amount.
 */

@SuppressWarnings("ALL") public class Pickup implements AbstractFactory<String>{

    public static String[] magicOrbs = {"Light","Arcane","Mana","Vitality","Dark"};
    static String[] potions = {"Black","Blue","Brown","Clear","Gold", "Green","Orange","Pink","Purple","Red"};

    private DungeonParser dungeon;
    private Random random;
    public String name;
    private String out = "";
    private int count = 1;

    /**
     * Constructor for a Pickup object. Automatically rolls for its type.
     * @param d The main.parser.DungeonParser being used
     * @param n The name of the pickup
     */

    public Pickup(DungeonParser d, String n) {
        dungeon = d;
        name = n;
        random = new Random();

        setOut();
    }

    /**
     * This method uses the name of the object to generate any special
     * information about that particular type of object.
     */
    @Override
    public String setOut() {
        if (name.equals("Orb")) {
            int roll = random.nextInt(magicOrbs.length);
            out = magicOrbs[roll] + " " + name;
        }
        else if (name.equals("Potion")) {
            int stat = random.nextInt(100);
            if (stat < 20) {
                int roll = random.nextInt(potions.length);
                out = potions[roll] + " " + name;
            } else if (stat < 50) {
                out = "Unfavorable " + name;
            } else {
                out = "Favorable " + name;
            }
        }
        else if (name.equals("Poke")) {
            int range = dungeon.getPokeMax() - dungeon.getPokeMin() + 1;
            count = random.nextInt(range) + dungeon.getPokeMin();
            out = name;
        }
        else if (name.equals("Geo Pebble") || name.equals("Gravelrock") || name.equals("Iron Thorn")) {
            count = random.nextInt(dungeon.getGeoPebblesMax()) + 1;
            out = name;
        }
        else {
            out = name;
        }
        return "";
    }

    /**
     * Returns the name of the pickup.
     */
    public String getName() { return name; }
    /**
     * Returns the amount of the pickup.
     */
    public int getCount() { return count; }
    /**
     * Returns the full, specialized name of the pickup.
     */
    public String toString() {
        return out;
    }

    public String[] getPotions() {
        return this.potions;
    }
}
