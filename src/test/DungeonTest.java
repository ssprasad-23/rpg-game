package test;
import main.generator.Encounter;
import main.spaces.Dungeon;
import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;

public class DungeonTest {

    Dungeon dungeon = new Dungeon("waterfallcave");

    @Test
    public void testDungeon() {
        assertEquals("Fields didn't match", dungeon.getName(), "waterfallcave");
    }
}
