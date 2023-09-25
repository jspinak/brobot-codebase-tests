package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

@Component
public class ClickTest {

    private final Action action;
    private final TestState testState;

    public ClickTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    public void run() {
        System.out.println("\n__Click Tests__");
        Matches matches = action.perform(
                new ActionOptions.Builder().setAction(ActionOptions.Action.CLICK).build(),
                testState.getTopLeft());
        TestOutput.assertTrue("success", true, matches.isSuccess());
        matches.getBestMatch().ifPresent(m -> {
            TestOutput.assertTrue("match location",20,  m.getMatch().x,
                    20, m.getMatch().y, 50, m.getMatch().w, 100, m.getMatch().h);
            TestOutput.assertTrue("middle point should be clicked",
                    45, m.getLocation().getX(), 70, m.getLocation().getY());
            TestOutput.assertTrue("anchor position", 0,
                    m.getAnchorList().get(0).getLocationInMatch().getPercentW(),
                    1, m.getAnchorList().get(0).getLocationInMatch().getPercentH());
        });
    }
}
