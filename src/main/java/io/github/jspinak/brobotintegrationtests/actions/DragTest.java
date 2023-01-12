package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.datatypes.primitives.region.Region;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

@Component
public class DragTest {

    private Action action;
    private TestState testState;

    private ActionOptions actionOptions = new ActionOptions.Builder()
            .setAction(ActionOptions.Action.DRAG)
            .build();

    public DragTest(Action action, TestState testState) {
        this.action = action;
        this.testState = testState;
    }

    public void run() {
        System.out.println("\n__Drag Tests__");
        Matches matches = action.perform(actionOptions, testState.getObjectCollection());
        /* 2 Obj Coll is for Drag, not for DragSimple
        Matches matches = action.perform(actionOptions,
                testState.getTopLeft().asObjectCollection(),
                testState.getTopRight().asObjectCollection());
         */
        TestOutput.assertTrue("success", true, matches.isSuccess());
        /* for Drag, not DragSimple
        Region def = matches.getDefinedRegion();
        TestOutput.assertTrue("drag locations", 44, def.x, 69, def.y,
                524, def.getX2(), 69, def.getY2());
         */
        TestOutput.assertTrue("drag locations", 45, matches.getMatches().get(0).getTarget().x,
                70, matches.getMatches().get(0).getTarget().y,
                525, matches.getMatches().get(1).getTarget().x,
                70, matches.getMatches().get(1).getTarget().y);
    }

}
