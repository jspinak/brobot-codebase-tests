package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.datatypes.primitives.region.Region;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

@Component
public class DefineTest {

    private Action action;
    private TestState testState;

    private ActionOptions actionOptions = new ActionOptions.Builder()
            .setAction(ActionOptions.Action.DEFINE)
            .setDefineAs(ActionOptions.DefineAs.INSIDE_ANCHORS)
            .build();

    public DefineTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    public void run() {
        System.out.println("\n__Define Tests__");
        Matches matches = action.perform(actionOptions, testState.getObjectCollection());
        TestOutput.assertTrue("snapshots", 2,
                testState.getTopLeft().getMatchHistory().getSnapshots().size());
        testState.getTopLeft().getMatchHistory().print();
        TestOutput.assertTrue("Action success", true, matches.isSuccess());
        Region def = matches.getDefinedRegion();
        TestOutput.assertTrue("the defined Region xywh",
                20, def.x, 119, def.y, 530, def.w, 382, def.h);
    }

}
