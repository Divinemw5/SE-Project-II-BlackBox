

import TUI.Board;
import TUI.Util;
import objects.Atom;
import objects.Box;
import objects.Ray;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestTUI
{
    Atom atoms[];
    Box box;
    ArrayList<Ray> rays;

    @Before
    public void setup()
    {
        atoms = Atom.generateAtoms(6);
        box = new Box(atoms);
        rays = new ArrayList<>();

        for (int i = 1; i < 54; i++)
        {
            rays.add(new Ray(i, box));
            rays.add(new Ray(i, box));
            rays.add(new Ray(i, box));
        }
    }

    @Test
    public void testTUIThrowsNoExceptionEmptyBoard()
    {
        try
        {
            Board.appendRayMarkers(rays, Board.getEmptyBoard());
        }
        catch (Exception ex)
        {
            fail();
            ex.printStackTrace();
        }
    }

    @Test
    public void testTUIThrowsNoExceptionAtomizedBoard()
    {
        try
        {
            Board.appendRayMarkers(rays, Board.getAtomizedBoard(atoms));
        }
        catch (Exception ex)
        {
            fail();
            ex.printStackTrace();
        }
    }
}
