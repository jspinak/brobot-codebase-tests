package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.match.Matches;
import io.github.jspinak.brobot.database.primitives.region.Region;
import io.github.jspinak.brobot.reports.TestOutput;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public class FindTest {

    private final Action action;
    private final TestState testState;

    public FindTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    public void run() {
        System.out.println("\n__Find Tests__");
        Matches matches = action.perform(new ActionOptions(), testState.getTopLeft());
        TestOutput.assertTrue("success", true, matches.isSuccess());
        matches.getBestMatch().ifPresent(
                m -> TestOutput.assertTrue("Image match", 20,
                        m.getMatch().x, 20, m.getMatch().y, 50, m.getMatch().w, 100, m.getMatch().h));
        ActionOptions keyDown = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.KEY_DOWN)
                .setModifiers(Key.SHIFT)
                .build();
        new Region().type("hi", Key.SHIFT);
    }
}
