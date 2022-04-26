package test;

import main.generator.Member;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class MemberTest {

    Member member = new Member("TEST", 1, 1, "TEST");
    @Test
    public void testEncounterGettersAndSetters() {
        //Constructor check
        assert member.getName().equals("TEST") && member.getLevel() == 1 && member.getXPspeed().equals("TEST") && member.getXP() == 1;
        //Properties
        member.setName("TEST1");
        member.setLevel(2);
        member.setXp(2);
        member.setXpspeed("TEST1");
        assert member.getName().equals("TEST1") && member.getLevel() == 2 && member.getXPspeed().equals("TEST1") && member.getXP() == 2;
    }


}
