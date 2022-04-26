package main.generator;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Encounter class holds details for a particular dungeon encounter.
 *It stores the name and level of the Monster and calculates the given xp.
 */

public class Encounter {

    private String name;
    private int level;
    private int xp;

    /**
     * Constructor for an Encounter object. Automatically calculates given xp.
     * @param n The name of the Monster
     * @param l The level of the Monster
     */
    public Encounter(String n, int l) {
        name = n;
        level = l;

        calculateXP();
    }

    /**
     * This method calculates the given xp of the encounter. It requires that
     * the file "xpguide" exists and contains the Monster's stats.
     */
    @SuppressFBWarnings({ "NP_NULL_ON_SOME_PATH_EXCEPTION", "DM_DEFAULT_ENCODING" }) private void calculateXP() {
        // set up scanner
        Scanner scan = null;
        try {
            scan = new Scanner(new File("xpguide"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // extract data
        String[] stats = null;
        while (true) {
            assert scan != null;
            if (!scan.hasNext())
                break;
            stats = scan.nextLine().split(" ");
            if (stats[0].equals(name)) break;
        }

        xp = (int)Math.floor(Double.parseDouble(stats[1]) * level / 7.0);

    }

    /**
     * Returns the given xp count of the encounter.
     * @return xp
     */
    public int getXP() {
        return xp;
    }

    /**
     * Returns a printout for the Encounter object.
     * Includes the name and the level of the Monster.
     * @return this as a String
     */
    public String toString() {
        return name + "LV" + level;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
