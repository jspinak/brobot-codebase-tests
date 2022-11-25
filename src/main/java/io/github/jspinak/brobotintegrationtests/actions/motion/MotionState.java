package io.github.jspinak.brobotintegrationtests.actions.motion;

import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MotionState {

    public enum Name implements StateEnum {
        MOTION
    }

    private StateImageObject screen1 = new StateImageObject.Builder()
            .withImage("screen1")
            .build();

    private StateImageObject screen2 = new StateImageObject.Builder()
            .withImage("screen2")
            .build();

    private State state = new State.Builder(Name.MOTION)
            //.withImages(screen1, screen2)
            .build();

    public MotionState(StateService stateService) { stateService.save(state); }

}
