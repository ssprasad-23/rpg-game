package main.parser;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main.parser.DungeonParser class converts information on a dungeon document into data.
 * It assumes that the dungeon document is formatted correctly.
 */


@SuppressWarnings("ALL") public class DungeonParser implements DungeonParserBuilder {

    String dungeon;
    int floors;
    int roomsMin;
    int roomsMax;
    int trapsMax;
    int[] trapChances;
    int trapsNum;
    ChanceObject[] trapTypes;
    int monsterHouseChance;
    int bigRoomChance;
    int monsterNum;
    String[] monster;
    int levelMin;
    int levelMax;
    int itemsMax;
    int[] itemChances;
    int itemsNum;
    ChanceObject[] itemTypes;
    int pokeMin;
    int pokeMax;
    int geoPebblesMax;

    /**
     * Constructor for a main.parser.DungeonParser object. Automatically conducts the parse.
     * @param d The name of the dungeon document
     */
    public DungeonParser(String d) {
        dungeon = d;

        parse();
    }

    /**
     * This method reads a dungeon document and extract information about the dungeon.
     * It requires that the specified file exists and is formatted correctly.
     */
    @SuppressFBWarnings("DM_DEFAULT_ENCODING") public void parse() {
        // initialize scanner
        Scanner scan = null;
        try {
            scan = new Scanner(new File("dg_" + dungeon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // parse text
        int[] temp;

        assert scan != null;
        scan.nextLine();
        floors = parseSingleInt(8,scan);

        temp = parseRange(17, scan);
        roomsMin = temp[0];
        roomsMax = temp[1];

        trapsMax = parseSingleInt(7,scan);
        trapChances = parseChanceList(trapsMax, 12, scan);
        trapsNum = parseSingleInt(11, scan);
        trapTypes = parseChanceObjects(trapsNum, scan);

        monsterHouseChance = parseSingleInt(15, scan);
        bigRoomChance = parseSingleInt(17, scan);

        monsterNum = parseSingleInt(17, scan);
        monster = scan.nextLine().split(", ");
        monster[0] = monster[0].trim();

        temp = parseRange(13, scan);
        levelMin = temp[0];
        levelMax = temp[1];

        itemsMax = parseSingleInt(7,scan);
        itemChances = parseChanceList(itemsMax, 12, scan);
        itemsNum = parseSingleInt(11, scan);
        itemTypes = parseChanceObjects(itemsNum, scan);

        temp = parseRange(12, scan);
        pokeMin = temp[0];
        pokeMax = temp[1];

        geoPebblesMax = parseSingleInt(13, scan);

    }


    /**
     * Helper for parse(): This method parses a line containing a single number.
     * @param index The starting position of the actual number
     * @param scan The scanner used by the main.parser.DungeonParser
     * @return The integer in the line
     */
    public int parseSingleInt(int index, Scanner scan) {
        return Integer.parseInt(scan.nextLine().substring(index));
    }
    /**
     * This method parses a line containing two numbers.
     * @param index The starting position of the first number
     * @param scan The scanner used by the main.parser.DungeonParser
     * @return The integers in the line
     */
    public int[] parseRange(int index, Scanner scan) {
        String[] range = scan.nextLine().substring(index).split("-");
        int[] numbers = {Integer.parseInt(range[0]), Integer.parseInt(range[1])};
        return numbers;
    }
    /**
     * This method parses a list of lines containing count percentages.
     * @param size The number of lines to scan
     * @param index The starting position of the percent
     * @param scan The scanner used by the main.parser.DungeonParser
     * @return The percentages in the list
     */
    public int[] parseChanceList(int size, int index, Scanner scan) {
        int[] output = new int[size];
        for (int a = 0; a < size; a++) {
            output[a] = Integer.parseInt(scan.nextLine().substring(index));
        }
        return output;
    }
    /**
     * This method parses a list of lines containing ranges of dice rolls.
     * @param size The number of lines to scan
     * @param scan The scanner used by the main.parser.DungeonParser
     * @return The ChanceObjects containing the ranges
     */
    public ChanceObject[] parseChanceObjects(int size, Scanner scan) {
        ChanceObject[] output = new ChanceObject[size];
        for (int b = 0; b < size; b++) {
            String[] data = scan.nextLine().split("-");
            int l = Integer.parseInt(data[0].trim());
            int h = Integer.parseInt(data[1]);
            String n = data[2];
            output[b] = new ChanceObject(l, h, n);
        }
        return output;
    }



    public int getFloors() {
        return floors;
    }
    public int getRoomsMin() { return roomsMin; }
    public int getRoomsMax() { return roomsMax; }

    public int getTrapsMax() { return trapsMax; }
    public int getTrapChance(int num) {
        if (num > trapChances.length) return 0;
        return trapChances[num-1];
    }
    public int getTrapsNum() { return trapsNum; }
    public ChanceObject getTrapType(int num) {
        if (num > trapTypes.length) return null;
        return trapTypes[num-1];
    }

    public int getMonsterHouseChance() { return monsterHouseChance; }
    public int getBigRoomChance() { return bigRoomChance; }

    public int getMonsterNum() { return monsterNum; }
    public String getMonster(int num) {
        if (num > monster.length) return "";
        return monster[num-1];
    }
    public int getLevelMin() { return levelMin; }
    public int getLevelMax() { return levelMax; }

    public int getItemsMax() { return itemsMax; }
    public int getItemChance(int num) {
        if (num > itemChances.length) return 0;
        return itemChances[num-1];
    }
    public int getItemsNum() { return itemsNum; }
    public ChanceObject getItemType(int num) {
        if (num > itemTypes.length) return null;
        return itemTypes[num-1];
    }

    public int getPokeMin() { return pokeMin; }
    public int getPokeMax() { return pokeMax; }

    public int getGeoPebblesMax() { return geoPebblesMax; }

}
