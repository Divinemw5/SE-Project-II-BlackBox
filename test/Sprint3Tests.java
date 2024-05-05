
import TUI.Board;
import TUI.Util;
import math.Coordinate;
import objects.*;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class Sprint3Tests
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // objects.Box with a hexagon @----- with barrier level two
    Atom[] TestAtoms = {
            new Atom(4,6,2),//0,2,-2
            new Atom(4,5,3), //0,1,-1
            new Atom(4,4,4),//0,0,0
            new Atom(3,3,6),//-1,-1,2
            new Atom(3,1,8),
            new Atom(4,0,8)
    };
    Atom[] TestAtoms2 = {
            new Atom(4,6,2),//0,2,-2
            new Atom(4,5,3), //0,1,-1
            new Atom(4,4,4),//0,0,0
            new Atom(3,3,6),//-1,-1,2
            new Atom(3,4,5),//-1,0,1
            new Atom(4,0,8)
    };
    Box box = new Box(TestAtoms);

    Box box1 = new Box(TestAtoms2);

    @Test
    public void RayResponse()
    {
        Ray ray = new Ray(35, box1);
        Util.printRayResponse(ray);
        assertTrue(outContent.toString().toLowerCase().contains("reflected"));
    }

    @Test
    public void TestUserPlacedAtoms()
    {
        Atom[] atoms = new Atom[]{Util.getAtom(6,1), Util.getAtom(51,46), Util.getAtom(37,42), Util.getAtom(10,30), Util.getAtom(26,25), Util.getAtom(19,7)};

        Box box1 = new Box(atoms);
        Hexagon hex = box1.getHexagonByCoordinate(new Coordinate(4,6,2));
        Hexagon hex1 = box1.getHexagonByCoordinate(new Coordinate(6,4,2));

        assertTrue(hex.checkHasAtom());
        assertTrue(hex1.checkHasAtom());

       //box1.getHexagonByCoordinate(math.Coordinate());
    }

    @Test
    public void RayMarkerTUITest()
    {
        char[] pairMarkers = Board.pairMarkers;

        Ray ray1 = new Ray(35, box1);
        Ray ray2 = new Ray(3,box1);
        Ray ray3 = new Ray(44,box1);

        ArrayList<Ray> rays = new ArrayList<>();
        rays.add(ray1);
        rays.add(ray2);
        rays.add(ray3);
        rays.add(new Ray(29,box1));

        ArrayList<String> board = Board.appendRayMarkers(rays, Board.getAtomizedBoard(TestAtoms));
        int count =0;
        for(String str : board) {
            char[] charArray = str.toCharArray(); // Convert the String to a char array
            for(char ch : charArray) {
                for(char marker : pairMarkers) {
                    if(ch == marker) {
                        count++;
                        break;
                    }
                }
            }
        }

        int count2=0;
        for(String str : board) {
            char[] charArray = str.toCharArray(); // Convert the String to a char array
            for(char ch : charArray) {
                if(ch == 'R' || ch == 'A') { // Check if any character in the array is 'R'
                    count2++; // Increment the count if 'R' is found
                }
            }
        }

        assertEquals(count,4);
        assertEquals(count2,2);
    }


}



