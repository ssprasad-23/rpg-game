package main.spaces;

import main.parser.DungeonParser;

public interface DungeonGeneratorInterface {
    @SuppressWarnings("CheckStyle") public void generateRooms();
    @SuppressWarnings("CheckStyle") public void populateRooms(int r, int c, int m, DungeonParser dungeon);
}
