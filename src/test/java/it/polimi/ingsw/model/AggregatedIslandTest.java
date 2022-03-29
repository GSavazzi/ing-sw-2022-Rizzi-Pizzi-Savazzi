package it.polimi.ingsw.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

public class AggregatedIslandTest extends TestCase {

    public void testGetInfluenceDefault() {
        AggregatedIsland isle = new AggregatedIsland(new Isle(),new Isle());
        isle.setTower(Faction.Black);
        ArrayList<Student> s = new ArrayList<Student>();
        int[] numStudent = {1,2,3,4,5};
        for(Colour c:Colour.values())
            for(int i = 0; i < numStudent[c.ordinal()];i++)
                s.add(new Student(c));
        isle.addStudents(s);
        HashMap<Colour,Player> professors = new HashMap<Colour,Player>();
        Player p1 = new Player(1,"pippo");
        p1.createBoard(9);
        p1.assignFaction(Faction.Black);
        Player p2 = new Player(2,"pippo");
        p2.createBoard(9);
        p2.assignFaction(Faction.Grey);
        Player p3 = new Player(3,"pippo");
        p3.createBoard(9);
        p3.assignFaction(Faction.Black);
        Team t = new Team();
        t.addPlayer(p1);
        t.addPlayer(p3);
        t.assignFaction(Faction.Black);
        professors.put(Colour.Gnomes,p1);
        professors.put(Colour.Dragons,p1);
        professors.put(Colour.Frogs,p2);
        professors.put(Colour.Fairies,p2);
        professors.put(Colour.Unicorns,p3);
        assertEquals(numStudent[Colour.Gnomes.ordinal()]+numStudent[Colour.Dragons.ordinal()]+isle.getSize(),isle.getInfluence(p1,professors));
        assertEquals(numStudent[Colour.Frogs.ordinal()]+numStudent[Colour.Fairies.ordinal()],isle.getInfluence(p2,professors));
        assertEquals(numStudent[Colour.Unicorns.ordinal()]+numStudent[Colour.Gnomes.ordinal()]+numStudent[Colour.Dragons.ordinal()]+isle.getSize(),isle.getInfluence(t,professors));
    }

    public void testGetInfluenceNoTower() {
        AggregatedIsland isle = new AggregatedIsland(new Isle(),new Isle());
        isle.setInfStrategy(new noTowersStrategy());
        isle.setTower(Faction.Black);
        ArrayList<Student> s = new ArrayList<Student>();
        int[] numStudent = {1,2,3,4,5};
        for(Colour c:Colour.values())
            for(int i = 0; i < numStudent[c.ordinal()];i++)
                s.add(new Student(c));
        isle.addStudents(s);
        HashMap<Colour,Player> professors = new HashMap<Colour,Player>();
        Player p1 = new Player(1,"pippo");
        p1.createBoard(9);
        p1.assignFaction(Faction.Black);
        Player p2 = new Player(2,"pippo");
        p2.createBoard(9);
        p2.assignFaction(Faction.Grey);
        Player p3 = new Player(3,"pippo");
        p3.createBoard(9);
        p3.assignFaction(Faction.Black);
        Team t = new Team();
        t.addPlayer(p1);
        t.addPlayer(p3);
        t.assignFaction(Faction.Black);
        professors.put(Colour.Gnomes,p1);
        professors.put(Colour.Dragons,p1);
        professors.put(Colour.Frogs,p2);
        professors.put(Colour.Fairies,p2);
        professors.put(Colour.Unicorns,p3);
        assertEquals(numStudent[Colour.Gnomes.ordinal()]+numStudent[Colour.Dragons.ordinal()],isle.getInfluence(p1,professors));
        assertEquals(numStudent[Colour.Frogs.ordinal()]+numStudent[Colour.Fairies.ordinal()],isle.getInfluence(p2,professors));
        assertEquals(numStudent[Colour.Unicorns.ordinal()]+numStudent[Colour.Gnomes.ordinal()]+numStudent[Colour.Dragons.ordinal()],isle.getInfluence(t,professors));
    }

    public void testGetInfluencePlusInf() {
        AggregatedIsland isle = new AggregatedIsland(new Isle(),new Isle());
        isle.setInfStrategy(new PlusInfStrategy());
        isle.setTower(Faction.Black);
        ArrayList<Student> s = new ArrayList<Student>();
        int[] numStudent = {1,2,3,4,5};
        for(Colour c:Colour.values())
            for(int i = 0; i < numStudent[c.ordinal()];i++)
                s.add(new Student(c));
        isle.addStudents(s);
        HashMap<Colour,Player> professors = new HashMap<Colour,Player>();
        Player p1 = new Player(1,"pippo");
        p1.createBoard(9);
        p1.assignFaction(Faction.Black);
        Player p2 = new Player(2,"pippo");
        p2.createBoard(9);
        p2.assignFaction(Faction.Grey);
        Player p3 = new Player(3,"pippo");
        p3.createBoard(9);
        p3.assignFaction(Faction.Black);
        Team t = new Team();
        t.addPlayer(p1);
        t.addPlayer(p3);
        t.assignFaction(Faction.Black);
        professors.put(Colour.Gnomes,p1);
        professors.put(Colour.Dragons,p1);
        professors.put(Colour.Frogs,p2);
        professors.put(Colour.Fairies,p2);
        professors.put(Colour.Unicorns,p3);
        assertEquals(numStudent[Colour.Gnomes.ordinal()]+numStudent[Colour.Dragons.ordinal()]+2+isle.getSize(),isle.getInfluence(p1,professors));
        assertEquals(numStudent[Colour.Frogs.ordinal()]+numStudent[Colour.Fairies.ordinal()]+2,isle.getInfluence(p2,professors));
        assertEquals(numStudent[Colour.Unicorns.ordinal()]+numStudent[Colour.Gnomes.ordinal()]+numStudent[Colour.Dragons.ordinal()]+2+isle.getSize(),isle.getInfluence(t,professors));
    }
    public void testGetInfluenceNoColour() {
        AggregatedIsland isle = new AggregatedIsland(new Isle(),new Isle());
        isle.setTower(Faction.Black);
        ArrayList<Student> s = new ArrayList<Student>();
        int[] numStudent = {1,2,3,4,5};
        for(Colour c:Colour.values())
            for(int i = 0; i < numStudent[c.ordinal()];i++)
                s.add(new Student(c));
        isle.addStudents(s);
        HashMap<Colour,Player> professors = new HashMap<Colour,Player>();
        Player p1 = new Player(1,"pippo");
        p1.createBoard(9);
        p1.assignFaction(Faction.Black);
        Player p2 = new Player(2,"pippo");
        p2.createBoard(9);
        p2.assignFaction(Faction.Grey);
        Player p3 = new Player(3,"pippo");
        p3.createBoard(9);
        p3.assignFaction(Faction.Black);
        Team t = new Team();
        t.addPlayer(p1);
        t.addPlayer(p3);
        t.assignFaction(Faction.Black);
        professors.put(Colour.Gnomes,p1);
        professors.put(Colour.Dragons,p1);
        professors.put(Colour.Frogs,p2);
        professors.put(Colour.Fairies,p2);
        professors.put(Colour.Unicorns,p3);
        assertEquals(numStudent[Colour.Gnomes.ordinal()]+isle.getSize(),isle.getInfluenceNoColour(p1,Colour.Dragons,professors));
        assertEquals(numStudent[Colour.Frogs.ordinal()]+numStudent[Colour.Fairies.ordinal()],isle.getInfluenceNoColour(p2,Colour.Dragons,professors));
        assertEquals(numStudent[Colour.Unicorns.ordinal()]+numStudent[Colour.Gnomes.ordinal()]+isle.getSize(),isle.getInfluenceNoColour(t,Colour.Dragons,professors));
    }

    public void testJoin() {
        AggregatedIsland isle = new AggregatedIsland(new Isle(),new Isle());
        assertEquals(2,isle.getSize());
        Isle isle1 = new Isle();
        assertFalse(isle.getJoinedIsle().contains(isle1));
        isle.join(isle1);
        assertEquals(3,isle.getSize());
        assertTrue(isle.getJoinedIsle().contains(isle1));
    }

    public void testTestJoin() {
        AggregatedIsland isle = new AggregatedIsland(new Isle(),new Isle());
        assertEquals(2,isle.getSize());
        Isle isle1 = new Isle();
        Isle isle2 = new Isle();
        AggregatedIsland isle3 = new AggregatedIsland(isle1,isle2);
        assertFalse(isle.getJoinedIsle().contains(isle1));
        assertFalse(isle.getJoinedIsle().contains(isle2));
        isle.join(isle3);
        assertEquals(4,isle.getSize());
        assertTrue(isle.getJoinedIsle().contains(isle1));
        assertTrue(isle.getJoinedIsle().contains(isle2));
    }

    public void testGetJoinedIsle() {
        Isle isle1 = new Isle();
        Isle isle2 = new Isle();
        AggregatedIsland isle = new AggregatedIsland(isle1,isle2);
        assertEquals(isle1,isle.getJoinedIsle().get(0));
        assertEquals(isle2,isle.getJoinedIsle().get(1));
    }
}