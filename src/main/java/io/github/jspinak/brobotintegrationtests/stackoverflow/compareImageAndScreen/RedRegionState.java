package io.github.jspinak.brobotintegrationtests.stackoverflow.compareImageAndScreen;

import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.otherStateObjects.StateLocation;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RedRegionState {

    public enum Name implements StateEnum {
        RED
    }

    private StateImageObject redArea = new StateImageObject.Builder()
            .withImage("negatif")
            .withSearchRegion(1342,212,175,21)
            .build();

    private StateLocation clickLocation = new StateLocation.Builder()
            .withLocation(3406,1420)
            .build();

    private State state = new State.Builder(Name.RED)
            //.withImages(redArea)
            .withLocations(clickLocation)
            .build();

    public RedRegionState(StateService stateService) { stateService.save(state); }
}
