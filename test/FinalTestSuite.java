
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestUserNameInput.class,
        TestAtomPlacement.class,
        TestUserPlacedAtoms.class,
        TestRayReaction.class,
        TestScoreCalculation.class,
        TestTUI.class
})

public class FinalTestSuite {
}
