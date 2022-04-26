package main.parser;

import java.util.Scanner;

public interface DungeonParserBuilder {
    void parse();
    int parseSingleInt(int index, Scanner scan);
    int[] parseRange(int index, Scanner scan);
    int[] parseChanceList(int size, int index, Scanner scan);
    ChanceObject[] parseChanceObjects(int size, Scanner scan);

}