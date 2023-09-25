package io.github.jspinak.brobotintegrationtests.probabilities;

import io.github.jspinak.brobot.datatypes.primitives.location.Position;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * This State is similar to the TestState but doesn't contain Snapshots.
 * All matches will be found using probabilities.
 */
@Component
@Getter
public class ProbabilityState {

    public enum Enum implements StateEnum {
        PROBABILITY_STATE
    }

    private StateImageObject topLeft = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.TOPLEFT, Position.Name.BOTTOMLEFT)
            .build();
    private StateImageObject topRight = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.TOPRIGHT, Position.Name.BOTTOMRIGHT)
            .build();
    private StateImageObject bottomLeft = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.BOTTOMLEFT, Position.Name.TOPLEFT)
            .build();
    private StateImageObject bottomRight = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.BOTTOMRIGHT, Position.Name.TOPRIGHT)
            .build();

    private State state = new State.Builder(Enum.PROBABILITY_STATE)
            .withImages(topLeft, topRight, bottomLeft, bottomRight)
            .build();

    private ObjectCollection objectCollection = new ObjectCollection.Builder()
            .withImages(topLeft, topRight, bottomLeft, bottomRight)
            .build();

    public ProbabilityState(StateService stateService) { stateService.save(state); }
}
