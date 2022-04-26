package main.spaces;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.generator.Encounter;
import main.generator.Pickup;
import main.parser.DungeonParser;

import java.util.Random;

/**
 * The Room class holds details for a particular dungeon room.
 * It stores the type of room and generates the looted items,
 * the activated traps, and the encounters.
 */

@SuppressWarnings("ALL") public class Room extends Dungeon{

    Random random;
    boolean big;
    boolean monsterhouse;
    int itemNum = 0;
    int trapNum = 0;
    int encounterNum = 1;
    Pickup[] items;
    Pickup[] traps;
    Encounter[] encounters;

    /**
     * Constructor for a Room object.
     * The main.parser.DungeonParser being used
     * Whether the room is a big room
     * Whether the room is a monster house
     */
    public Room(DungeonParser d, boolean b, boolean m) {
        super(d);
        big = b;
        monsterhouse = m;
        random = new Random();
    }

    /**
     * This method generates the items, traps, and encounters for the room.
     * It grabs data from the main.parser.DungeonParser and rolls random numbers.
     */
    public void populate() {
        int max;

        // ITEM GEN
        max = dungeon.getItemsMax();
        int itemroll = random.nextInt(100);
        for (int i = max; i > 0; i--) {
            if (itemroll < dungeon.getItemChance(i)) {
                itemNum = i;
                break;
            }
        }
        if (monsterhouse)
            itemNum += 3;
        if (big)
            itemNum *= 2;

        items = new Pickup[itemNum];
        for (int i = 0; i < itemNum; i++) {
            itemroll = random.nextInt(100);
            for (int a = 1; a <= dungeon.getItemsNum(); a++) {
                if (dungeon.getItemType(a).getInRange(itemroll)) {
                    items[i] = new Pickup(dungeon, dungeon.getItemType(a).getName());
                    break;
                }
            }

        }
        max = dungeon.getItemsMax();
        int traproll = random.nextInt(100);
        for (int t = max; t > 0; t--) {
            if (traproll < dungeon.getTrapChance(t)) {
                trapNum = t;
                break;
            }
        }
        if (big)
            trapNum *= 2;

        traps = new Pickup[trapNum];
        for (int t = 0; t < trapNum; t++) {
            traproll = random.nextInt(100);
            for (int a = 1; a <= dungeon.getTrapsNum(); a++) {
                if (dungeon.getTrapType(a).getInRange(traproll)) {
                    traps[t] = new Pickup(dungeon, dungeon.getTrapType(a).getName());
                    break;
                }
            }

        }
        if (monsterhouse)
            encounterNum = random.nextInt(5) + 6;
        else if (random.nextInt(100) < 10)
            encounterNum++;
        if (big)
            encounterNum *= 2;

        encounters = new Encounter[encounterNum];
        for (int e = 0; e < encounterNum; e++) {
            int encounterroll = random.nextInt(dungeon.getMonsterNum()) + 1;
            int range = dungeon.getLevelMax() - dungeon.getLevelMin() + 1;
            int levelroll = random.nextInt(range) + dungeon.getLevelMin();

            encounters[e] = new Encounter(dungeon.getMonster(encounterroll), levelroll);
        }
    }


    /**
     * Returns the array of items found in the room.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") public Pickup[] getItems() {
        Pickup[] items = this.items;
        return items;
    }
    /**
     * Returns the array of traps activated in the room.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") public Pickup[] getTraps() {
        Pickup[] traps = this.traps;
        return traps;
    }
    /**
     * Returns the array of encounters in the room.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") public Encounter[] getEncounters() {
        Encounter[] encounters = this.encounters;
        return encounters;
    }

    /**
     * Returns a printout for the Room object.
     * Includes the items, traps, encounters, and room type.
     */
    public String toString() {

        String output = "Room  ||  ITEMS" + printItems() + "\n" +
                        "      ||  TRAPS" + printTraps() + "\n" +
                        "      ||  ENCOUNTERS" + printEncounters();

        if (big || monsterhouse) {
            output += "\n" + "      ||  ";
            if (big) output += ">>  BIG  ";
            if (monsterhouse) output += ">>  M.HOUSE  ";
        }

        return output;

    }

    /**
     * Helper for toString(): returns the items printout
     */
    private String printItems() {
        String print = "[";
        for (int a = 0; a < itemNum; a++) {
            print += items[a];
            if (a != itemNum - 1)
                print += ", ";
        }
        return print + "]";
    }
    /**
     * Helper for toString(): returns the traps printout
     */
    private String printTraps() {
        String print = "[";
        for (int a = 0; a < trapNum; a++) {
            print += traps[a];
            if (a != trapNum - 1)
                print += ", ";
        }
        return print + "]";
    }
    /**
     * Helper for toString(): returns the encounters printout
     */
    private String printEncounters() {
        String print = "[";
        for (int a = 0; a < encounterNum; a++) {
            print += encounters[a];
            if (a != encounterNum - 1)
                print += ", ";
        }
        return print + "]";
    }
}
