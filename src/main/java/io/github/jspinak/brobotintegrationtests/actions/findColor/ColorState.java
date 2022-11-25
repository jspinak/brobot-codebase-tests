package io.github.jspinak.brobotintegrationtests.actions.findColor;

import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.otherStateObjects.StateRegion;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ColorState {

    public enum Name implements StateEnum {
        COLOR_STATE
    }

    private StateRegion minimapRegion = new StateRegion.Builder()
            .withSearchRegion(1020,100,200,160)
            .build();

    private StateImageObject redDot = new StateImageObject.Builder()
            .withImage("redDot1","redDot2")
            .isDynamic()
            .build();

    private StateImageObject yellowBar = new StateImageObject.Builder()
            .withImage("yellowBar")
            //.isDynamic()
            .build();

    private StateImageObject partOfScene = new StateImageObject.Builder()
            .withImage("hist")
            .build();

    private State state = new State.Builder(Name.COLOR_STATE)
            .withImages(redDot, yellowBar, partOfScene)
            .build();

    public ColorState(StateService stateService) { stateService.save(state); }

}
