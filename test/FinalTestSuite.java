
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestUserNameInput.class,
        //add test for round control
        TestAtomPlacement.class,
        TestRayReaction.class,
        TestScoreCalculation.class
})

public class FinalTestSuite {
}
