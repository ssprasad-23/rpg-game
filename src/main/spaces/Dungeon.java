package main.spaces;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.parser.DungeonParser;

import java.util.Random;

public class Dungeon {
    DungeonParser dungeon;
    Random random;
    String name;

    public Dungeon(String d) {
        name = d;
        dungeon = new DungeonParser(d);
        random = new Random();
    }

    /**
     * Constructor
     * @param d
     */
    public Dungeon(DungeonParser d) {
        dungeon = d;
    }

    public DungeonParser getDungeon() {
        return dungeon;
    }

    public void setDungeon(DungeonParser dungeon) {
        this.dungeon = dungeon;
    }

     @SuppressFBWarnings("EI_EXPOSE_REP") public Random getRandom() {
         Random random = this.random;
         return random;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2") public void setRandom(Random random) {
        this.random = random;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
