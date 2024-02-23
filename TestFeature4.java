import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
public class TestFeature4 {

    @Test
    public void testTUIShowHiddenAtoms()
    {
        String test_str =
"                    ░█      ░█      ░█      ░█      ░█\n"+
"                  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"                ░█  ╔╗  ░█      ░█      ░█      ░█      ░█\n"+
"                ░█  ░░  ░█      ░█      ░█      ░█      ░█\n"+
"                ░█  ╚╝  ░█      ░█      ░█      ░█      ░█\n"+
"              ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"            ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"            ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"            ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"          ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"        ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"        ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"        ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"      ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"    ░█  ╔╗  ░█      ░█  ╔╗  ░█      ░█      ░█  ╔╗  ░█      ░█      ░█\n"+
"    ░█  ░░  ░█      ░█  ░░  ░█      ░█      ░█  ░░  ░█      ░█      ░█\n"+
"    ░█  ╚╝  ░█      ░█  ╚╝  ░█      ░█      ░█  ╚╝  ░█      ░█      ░█\n"+
"  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"░█      ░█      ░█      ░█      ░█  ╔╗  ░█      ░█      ░█      ░█      ░█\n"+
"░█      ░█      ░█      ░█      ░█  ░░  ░█      ░█      ░█      ░█      ░█\n"+
"░█      ░█      ░█      ░█      ░█  ╚╝  ░█      ░█      ░█      ░█      ░█\n"+
"  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"    ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"    ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"    ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"      ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"        ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"        ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"        ░█      ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"          ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"            ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"            ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"            ░█      ░█      ░█      ░█      ░█      ░█      ░█\n"+
"              ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"                ░█      ░█      ░█      ░█      ░█  ╔╗  ░█\n"+
"                ░█      ░█      ░█      ░█      ░█  ░░  ░█\n"+
"                ░█      ░█      ░█      ░█      ░█  ╚╝  ░█\n"+
"                  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█\n"+
"                    ░█      ░█      ░█      ░█      ░█\n";

        Atom[] atoms  ={new Atom(4,4,4),new Atom(4,8,0),new Atom(4,0,8),new Atom(6,3,3),new Atom(3,6,3),new Atom(1,8,3)};
        ArrayList<String> atomizedBoard = Util.getAtomizedBoard(atoms);
        StringBuilder compare = new StringBuilder();

        for(String line : atomizedBoard) {
        	compare.append(line).append("\n");
        }
        assertEquals(compare.toString(), test_str);
        }
    }

