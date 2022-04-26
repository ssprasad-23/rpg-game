package main.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



@SuppressWarnings("ALL") public class Member {

    String name;
    String xpspeed;
    int level;
    int xp;

    /**
     * Constructor for a Member object. Automatically fetches other stats.
     * @param n The name of the Monster
     */
    public Member(String n) {
        name = n;
        getStats();
    }

    /**
     * Overloaded constructor for a Member object which takes all stats.
     * @param n The name of the Monster
     * @param l The level of the Monster
     * @param x The total xp of the Monster
     * @param s The leveling speed of the Monster
     */
    public Member(String n, int l, int x, String s) {
        name = n;
        level = l;
        xp = x;
        xpspeed = s;
    }

    /**
     * This method reads a file to find data for other stats with the Monster's name.
     * It requires that the file "rpers" exists and contains the Monster's stats.
     */
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({ "NP_NULL_ON_SOME_PATH_EXCEPTION", "DM_DEFAULT_ENCODING" }) private void getStats() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("rpers"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] stats = null;
        while (scan.hasNext()) {
            stats = scan.nextLine().split(" ");
            if (stats[0].equals(name)) break;
        }
        level = Integer.parseInt(stats[1]);
        xp = Integer.parseInt(stats[2]);
        xpspeed = stats[3];

    }

    /**
     * Returns the name of the party member.
     * @return name
     */
    public String getName() { return name; }
    /**
     * Returns the level of the party member.
     * @return level
     */
    public int getLevel() { return level; }
    /**
     * Returns the total xp of the party member.
     * @return xp
     */
    public int getXP() { return xp; }
    /**
     * Returns the leveling speed of the party member.
     * @return xpspeed
     */
    public String getXPspeed() { return xpspeed; }

    public void setName(String name) {
        this.name = name;
    }

    public void setXpspeed(String xpspeed) {
        this.xpspeed = xpspeed;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * This method calculates relevant numbers displaying the progress of the
     * party member through a certain level and returns a String displaying this information.
     * @return A proportion and a progress bar
     */
    public String getProgress() {
        String output = "";
        int progress = getXP() - levelReq(getXPspeed(), getLevel());
        int needed = levelReq(getXPspeed(), getLevel()+1) - levelReq(getXPspeed(), getLevel());

        int barlength = (int)(progress * 75.0 / (double)needed);
        for (int i = 0; i < 75; i++) {
            if (i < barlength)
                output += "#";
            else
                output += "-";
        }
        output += " (" + progress + "/" + needed + ") ";

        return output;
    }


    /**
     * This method adds a given amount of xp to the party member's total xp.
     * It also uses that number to recalculate the party member's level.
     * @param a The amount of xp to add
     */
    public void addXP(int a) {
        xp += a;

        // GET LEVEL
        for (int i = level; i < 100; i++) {
            if (levelReq(xpspeed, i) > xp) {
                level = i-1;
                break;
            }
        }
    }

    /**
     * This method calculates the amount of xp needed to get a certain level.
     * <p>
     *     Details on the formulas can be found on the Bulbapedia page on Experience.
     * </p>
     * @param speed The leveling speed of the Monster
     * @param level The level in question
     * @return The amount of xp needed to reach the level in question
     */
    private int levelReq(String speed, int level) {
        int output = (int)Math.pow(level, 3);
        if (speed.equals("fast")) {
            output *= 4.0/5.0;
        }
        else if (speed.equals("slow")) {
            output *= 5.0/4.0;
        }
        else if (speed.equals("mediumslow")) {
            double temp = output * 6.0 / 5.0;
            output = (int)(temp - (15 * (int)Math.pow(level, 2)) + (100 * level) - 140);
        }

        return output;
    }

}
