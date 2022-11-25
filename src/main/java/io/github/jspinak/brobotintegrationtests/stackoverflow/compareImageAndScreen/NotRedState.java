package io.github.jspinak.brobotintegrationtests.stackoverflow.compareImageAndScreen;

import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.otherStateObjects.StateLocation;
import io.github.jspinak.brobot.datatypes.state.stateObject.otherStateObjects.StateRegion;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NotRedState {

    public enum Name implements StateEnum {
        NOT_RED
    }

    private StateLocation clickLocation = new StateLocation.Builder()
            .withLocation(23,1420)
            .build();

    private State state = new State.Builder(Name.NOT_RED)
            .withLocations(clickLocation)
            .build();

    public NotRedState(StateService stateService) { stateService.save(state); }
}
