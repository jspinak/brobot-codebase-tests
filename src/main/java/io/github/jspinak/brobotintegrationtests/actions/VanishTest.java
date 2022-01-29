package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.match.MatchObject;
import io.github.jspinak.brobot.database.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.database.primitives.match.Matches;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VanishTest {

    private final Action action;
    private final TestState testState;

    public VanishTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    /**
     * Vanish should produce Match objects up until the duration of the mock is reached.
     */
    public void run() {
        System.out.println("\n__Vanish Tests__");
        Matches matches = action.perform(
                new ActionOptions.Builder().setAction(ActionOptions.Action.VANISH).build(),
                testState.getTopRight());
        Optional<MatchSnapshot> optSnapshot =
                testState.getTopRight().getMatchHistory().getRandomSnapshot(
                new ActionOptions.Builder().setAction(ActionOptions.Action.VANISH).build());
        if (optSnapshot.isPresent()) {
            TestOutput.assertTrue("Vanish Snapshot duration", 1.1, optSnapshot.get().getDuration());
            TestOutput.assertTrue("success", true, matches.isSuccess());
            for (MatchObject matchObject : matches.getMatchObjects()) {
                TestOutput.assertTrue("Match duration", true,
                        matchObject.getDuration() < optSnapshot.get().getDuration());
            }
        }
    }
}
