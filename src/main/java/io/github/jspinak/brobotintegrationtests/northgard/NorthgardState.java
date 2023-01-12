package io.github.jspinak.brobotintegrationtests.northgard;

import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NorthgardState {

    public enum Name implements StateEnum {
        NORTHGARD
    }

    private StateImageObject person = new StateImageObject.Builder()
            .withImage("person1", "person2", "person3")
            .build();

    private StateImageObject land = new StateImageObject.Builder()
            .withImage("land1", "land2", "land3")
            .build();

    private StateImageObject building = new StateImageObject.Builder()
            .withImage("building1", "building2", "building3")
            .build();

    private State state = new State.Builder(Name.NORTHGARD)
            .withImages(person, land, building)
            .build();

    public NorthgardState(StateService stateService) { stateService.save(state); }
}
