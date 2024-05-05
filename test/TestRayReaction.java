import TUI.Util;
import objects.Atom;
import objects.Box;
import objects.Ray;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//test valid and invalid ray inputs
//also use Util.ray response to test string output for valid and invalid

public class TestRayReaction {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Box box;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
}
