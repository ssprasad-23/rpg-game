package main.generator;

import main.spaces.DungeonGenerator;

import java.util.ArrayList;

@SuppressWarnings("CheckStyle") public class DungeonGeneratorDriver {

    @SuppressWarnings("CheckStyle") public static void main(String args[]) {

        DungeonGenerator dungeon = new DungeonGenerator("waterfallcave");

        dungeon.generateRooms();
        System.out.println(dungeon);

        ArrayList<Member> peeps = new ArrayList<Member>();
        peeps.add(new Member("George"));
        XPMachine xp = new XPMachine(dungeon.getEncounters(), peeps);
        xp.giveXP();
        System.out.println(xp);


    }
}
