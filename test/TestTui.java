

import TUI.Board;
import TUI.Util;
import objects.Atom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
public class TestTui {

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
        ArrayList<String> atomizedBoard = Board.getAtomizedBoard(atoms);
        StringBuilder compare = new StringBuilder();

        for(String line : atomizedBoard) {
        	compare.append(line.replaceAll("[0-9]", " ")).append("\n");
        }

        String result = compare.toString();
        assertEquals(test_str, result);
        }
    }

