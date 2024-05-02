
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import objects.Player;
import org.junit.Test;

public class TestUserNameInput {
    @Test
    public void testConstructorWithInvalidName_Null() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null));
    }

    @Test
    public void testConstructorWithInvalidName_Empty() {
        assertThrows(IllegalArgumentException.class, () -> new Player("    "));
    }

    @Test
    public void testConstructorWithInvalidName_Large(){
        assertThrows(IllegalArgumentException.class, () -> new Player("fggfkjnkjngdskgbfjbhs"));
    }

    @Test
    public void testConstructorWithInvalidName_Small(){
        assertThrows(IllegalArgumentException.class, () -> new Player("a"));
    }

    @Test
    public void testStoresPlayerName(){
        Player player = new Player("John");
        assertEquals(player.getName(), "John");
    }
}