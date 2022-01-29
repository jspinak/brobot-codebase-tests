package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.location.Location;
import io.github.jspinak.brobot.database.primitives.match.Matches;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

@Component
public class MoveMouseTest {

    private Action action;
    private TestState testState;

    private ActionOptions actionOptions = new ActionOptions.Builder()
            .setAction(ActionOptions.Action.MOVE)
            .setFind(ActionOptions.Find.EACH) // leaving Find.FIRST will only move to 1 Location
            .setMaxWait(5.0)
            .build();

    public MoveMouseTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    public void run() {
        System.out.println("\n__Move Mouse Tests__");
        Matches matches = action.perform(actionOptions, testState.getObjectCollection());
        TestOutput.assertTrue("success", true, matches.isSuccess());
        TestOutput.assertTrue("number of locations to move to", 4,
                matches.getMatchLocations().size());
        if (matches.getMatchLocations().size() < 4) return;
        Location m1 = matches.getMatchLocations().get(0);
        Location m2 = matches.getMatchLocations().get(1);
        Location m3 = matches.getMatchLocations().get(2);
        Location m4 = matches.getMatchLocations().get(3);
        TestOutput.assertTrue("move the mouse to these locations", 44,
                m1.getX(), 69, m1.getY(), 524, m2.getX(), 69, m2.getY(),
                44, m3.getX(), 549, m3.getY(), 524, m4.getX(), 549, m4.getY());
        testState.getTopLeft().getMatchHistory().print();
    }

}
