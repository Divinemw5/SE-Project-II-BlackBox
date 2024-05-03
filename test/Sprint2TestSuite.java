

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A feature that allows the user to end a round or send a ray into the box
 * A feature that implements the edge positions around the board    DONE
 * A feature that implements a ray that encounters no atom and travels across the board     DONE
 * A feature that implements a ray that makes a direct hit and the setter responds with “Absorbed” - [when the ray is travelling in the same direction as the atom and encounters it]   DONE
 * A feature that implements a ray that hits a hexagon with a barrier level of 1 and is deflected by 1 axis (60 degrees)    DONE
 * A feature that implements a ray that hits a hexagon with barrier level 1 before it enters the box and the setter announces “Reflected” DONE (NEED TO CHECK ANNOUNCE)
 * A feature that implements a ray that hits an atom directly on entering the board is the setter announces “Absorbed” DONE
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestSideNumbers.class,
        TestRayTravel.class

})

public class Sprint2TestSuite {

}