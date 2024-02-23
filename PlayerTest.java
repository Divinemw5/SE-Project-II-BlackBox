import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
class PlayerTest {
    @Test
    public void testConstructorWithInvalidName_Null() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null));
    }

    @Test
    public void testConstructorWithInvalidName_Empty() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null));
    }

    @Test
    public void testConstructorWithInvalidName_Large(){
        assertThrows(IllegalArgumentException.class, () -> new Player("fggfkjnkjngdskgbfjbhs"));
    }

    @Test
    public void testConstructorWithInvalidName_Small(){
        assertThrows(IllegalArgumentException.class, () -> new Player("a"));
    }
}