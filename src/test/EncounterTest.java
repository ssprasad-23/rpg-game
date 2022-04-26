package test;
import main.generator.Encounter;
import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;

public class EncounterTest {
    Encounter encounter = new Encounter("Cat", 133);

    @Test
    public void testEncounterGettersAndSetters() throws NoSuchFieldException, IllegalAccessException {
        //Constructor check
        assert encounter.getName() == "Cat" && encounter.getLevel() == 133;
        //Properties
        encounter.setName("TEST");
        encounter.setLevel(1);
        assert encounter.getName() == "TEST" && encounter.getLevel() == 1;
        encounter.setXp(10);
        assert encounter.getXP() ==10;


        final Encounter encounter1 = new Encounter("Cat", 133);
        //when
        encounter1.setName("Cat");
        //then
        final Field field = encounter1.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(encounter1), "Cat");
    }

}
