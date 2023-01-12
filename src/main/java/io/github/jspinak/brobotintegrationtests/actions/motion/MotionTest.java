package io.github.jspinak.brobotintegrationtests.actions.motion;

import io.github.jspinak.brobot.actions.BrobotSettings;
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
        BrobotSettings.saveHistory = true;
        BrobotSettings.mock = false;
        ActionOptions findMotion = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.MOTION)
                .setMaxMatchesToActOn(10)
                .setMinArea(50)
                .setMaxMovement(100)
                .build();
        action.perform(findMotion);

        BrobotSettings.screenshots.add("screen15.png");
        BrobotSettings.screenshots.add("screen16.png");
        BrobotSettings.screenshots.add("screen17.png");

        ObjectCollection screenshots = new ObjectCollection.Builder()
                .withScenes(motionState.getScreen1(), motionState.getScreen2())
                .build();
    }
}
