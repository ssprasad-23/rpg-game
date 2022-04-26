package test;

import org.junit.Assert;
import main.generator.Member;
import main.generator.Pickup;
import main.parser.DungeonParser;
import main.spaces.DungeonGenerator;
import org.junit.Test;

public class PickupTest {
    DungeonParser dungeonParser = new DungeonParser("waterfallcave");
    Pickup pickup = new Pickup(dungeonParser, "TEST");
    @Test
    public void testEncounterGettersAndSetters() {
        //Constructor check
        assert pickup.getName().equals("TEST");
        //Properties
        pickup.name = "Orb";
        pickup.setOut();
        String[] magicOrbs = pickup.magicOrbs;
        Assert.assertEquals(magicOrbs[0], "Light");
        Assert.assertEquals(magicOrbs[1], "Arcane");
        Assert.assertEquals(magicOrbs[2], "Mana");
        Assert.assertEquals(magicOrbs[3], "Vitality");
        Assert.assertEquals(magicOrbs[4], "Dark");

        pickup.magicOrbs = new String[] {"TestOrb"};
        Assert.assertEquals(pickup.magicOrbs[0], "TestOrb");
        pickup.name = "Potion";
        Assert.assertEquals(pickup.getName(), "Potion");
        pickup.setOut();
        String[] potions = pickup.getPotions();
        Assert.assertEquals(potions[0], "Black");
        Assert.assertEquals("Blue", potions[1]);
        Assert.assertEquals("Brown", potions[2]);
        Assert.assertEquals("Clear", potions[3]);
        Assert.assertEquals("Gold", potions[4]);
        Assert.assertEquals("Green", potions[5]);
        Assert.assertEquals("Orange", potions[6]);
        Assert.assertEquals("Pink", potions[7]);
        Assert.assertEquals("Purple", potions[8]);
        Assert.assertEquals("Red", potions[9]);
    }
}
