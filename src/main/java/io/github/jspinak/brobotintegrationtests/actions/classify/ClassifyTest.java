package io.github.jspinak.brobotintegrationtests.actions.classify;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassifyTest {

    private Action action;
    private ClassifyState classifyState;

    public ClassifyTest(Action action, ClassifyState classifyState) {
        this.action = action;
        this.classifyState = classifyState;
    }

    public void classify() {
        List<StateImageObject> toShow = new ArrayList<>();
        toShow.add(classifyState.getGrass());
        toShow.add(classifyState.getKobold());
        //showColorProfile.drawImagesAndProfiles(toShow);
        ObjectCollection toClassify = new ObjectCollection.Builder()
                .withImages(classifyState.getGrass(), classifyState.getKobold())
                .build();
        ObjectCollection additionalImages = new ObjectCollection.Builder().build();
        ObjectCollection scenes = new ObjectCollection.Builder()
                .withImages(classifyState.getScene1(), classifyState.getScene2())
                .build();
        ActionOptions options = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLASSIFY)
                .setMinScore(0.7)
                .setKmeans(2)
                .setMaxMatchesToActOn(10)
                .build();
        action.perform(options, toClassify, additionalImages, scenes);
    }
}
