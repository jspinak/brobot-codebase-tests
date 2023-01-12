package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.actions.methods.basicactions.captureAndReplay.capture.CaptureScenesAndInputs;
import io.github.jspinak.brobot.actions.methods.basicactions.captureAndReplay.findClosestScene.FindClosestAndReplay;
import io.github.jspinak.brobot.datatypes.primitives.image.Image;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobotintegrationtests.actions.findColor.ColorState;
import org.springframework.stereotype.Component;

@Component
public class XmlTest {

    private CaptureScenesAndInputs captureScenesAndInputs;
    private FindClosestAndReplay findClosestAndReplay;
    private Action action;
    private ColorState colorState;

    public XmlTest(CaptureScenesAndInputs captureScenesAndInputs, FindClosestAndReplay findClosestAndReplay,
                   Action action, ColorState colorState) {
        this.captureScenesAndInputs = captureScenesAndInputs;
        this.findClosestAndReplay = findClosestAndReplay;
        this.action = action;
        this.colorState = colorState;
    }

    public void writeXml() {
        BrobotSettings.saveHistory = false;
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.COLOR)
                .keepLargerMatches(true)
                .setMaxWait(5)
                .build();
        StateImageObject remb = new StateImageObject.Builder().withImage(new Image("rembrandt", "rembrandt2")).build();
        for (int i=0; i<100; i++)
        findClosestAndReplay.findClosestAndPerformActions(actionOptions, colorState.getYellowBar());
        //captureScenesAndInputs.captureAndFindObjects(actionOptions, colorState.getYellowBar());
    }
}
