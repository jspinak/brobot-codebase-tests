package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.match.Matches;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

@Component
public class GetTextTest {

    private Action action;
    private TestState testState;

    private ActionOptions actionOptions = new ActionOptions.Builder()
            .setAction(ActionOptions.Action.GET_TEXT)
            .build();

    public GetTextTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    public void run() {
        System.out.println("\n__Get Text Tests__");
        Matches matches = action.perform(actionOptions, testState.getTopLeft());
        TestOutput.assertTrue("success", true, matches.isSuccess());
        matches.print();
        TestOutput.assertTrue("number of Strings in Text",1,
                matches.getText().size());
        TestOutput.assertTrue("text retrieved", "Top Left Text",
                matches.getSelectedText());
    }

}
