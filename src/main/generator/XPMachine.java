package main.generator;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>XPMachine</h1>
 * The XPMachine class takes a list of encounters and a list of party members
 * in a dungeon and distributes experience points to the party.
 * <p>
 *     It allows for the manual addition of specific encounters as well.
 */

@SuppressWarnings("ALL") public class XPMachine {

    String header = "XP Summary \n ----------------";

    ArrayList<Encounter> encounters;
    ArrayList<Member> party;
    int total;

    /**
     * Constructor for an XPMachine object.
     * @param p An array of party members
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2") public XPMachine (ArrayList<Member> p) {
        encounters = new ArrayList<Encounter>();
        party = p;
        total = 0;
    }

    /**
     * Overloaded constructor for an XPMachine object.
     * @param e An array of encounters
     * @param p An array of party members
     */
    @SuppressFBWarnings({ "EI_EXPOSE_REP2", "EI_EXPOSE_REP2" }) public XPMachine (ArrayList<Encounter> e, ArrayList<Member> p) {
        encounters = e;
        party = p;
        total = 0;
    }

    /**
     * This method adds an encounter to the list to be factored in for xp gain.
     * Meant to be done before using giveXP().
     * @param e The encounter to add
     */
    public void addEncounter(Encounter e) {
        encounters.add(e);
    }

    /**
     * This method blasts out xp to all the party members.
     */
    public void giveXP() {
        totalXP();

        for (int i = 0; i < party.size(); i++) {
            party.get(i).addXP(total / party.size());
        }

    }
    /**
     * Helper for giveXP(): totals the xp gained from each encounter in the list.
     */
    private void totalXP() {
        for (int i = 0; i < encounters.size(); i++) {
            total += encounters.get(i).getXP();
        }

    }

    /**
     * Returns a list of the party members involved.
     * @return party
     */
    @SuppressFBWarnings("EI_EXPOSE_REP") public ArrayList<Member> getParty() {
        ArrayList<Member> party = this.party;
        return party;
    }

    /**
     * Returns a printout for the XPMachine object.
     * Includes xp and level gain stats for each party member.
     * @return this as a String
     */
    public String toString() {
        String output = header;
        output += party.get(0).getProgress() + " ";
        output += party.get(0).getName().toUpperCase() + " has " + party.get(0).getXP()  + " XP (LEVEL " + party.get(0).getLevel() + ")" + "\n";


        return output;
    }

}
