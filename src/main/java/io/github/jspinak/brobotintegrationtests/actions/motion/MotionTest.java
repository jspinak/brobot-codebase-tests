package io.github.jspinak.brobotintegrationtests.actions.motion;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import org.springframework.stereotype.Component;

@Component
public class MotionTest {

    private Action action;
    private MotionState motionState;

    public MotionTest(Action action, MotionState motionState) {
        this.action = action;
        this.motionState = motionState;
    }

    public void test() {
        /*
        ActionOptions motion = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.MOTION)
                .setMaxMatchesToActOn(10)
                .setMinArea(300)
                .build();
        ObjectCollection screenshots = new ObjectCollection.Builder()
                .withImages(motionState.getScreen1(), motionState.getScreen2())
                .build();
        action.perform(motion, screenshots);

         */
    }
}
