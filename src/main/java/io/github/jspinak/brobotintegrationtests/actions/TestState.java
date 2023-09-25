package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.location.Position;
import io.github.jspinak.brobot.datatypes.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.actions.TestState.Enum.TEST_STATE;

@Component
@Getter
public class TestState {

    public enum Enum implements StateEnum {
        TEST_STATE
    }

    private StateImageObject topLeft = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.TOPLEFT, Position.Name.BOTTOMLEFT)
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(20, 20, 50, 100)
                    .build())
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(20, 20, 50, 100)
                    .addString("Top Left Text")
                    .build())
            .build();
    private StateImageObject topRight = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.TOPRIGHT, Position.Name.BOTTOMRIGHT)
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(500, 20, 50, 100)
                    .build())
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(500, 20, 50, 100)
                    .setActionOptions(ActionOptions.Action.VANISH)
                    .setDuration(1.1)
                    .build())
            .build();
    private StateImageObject bottomLeft = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.BOTTOMLEFT, Position.Name.TOPLEFT)
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(20, 500, 50, 100)
                    .build())
            .build();
    private StateImageObject bottomRight = new StateImageObject.Builder()
            .withImage("X")
            .addAnchor(Position.Name.BOTTOMRIGHT, Position.Name.TOPRIGHT)
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(500, 500, 50, 100)
                    .build())
            .build();

    private State state = new State.Builder(TEST_STATE)
            .withImages(topLeft, topRight, bottomLeft, bottomRight)
            .build();

    private ObjectCollection objectCollection = new ObjectCollection.Builder()
            .withImages(topLeft, topRight, bottomLeft, bottomRight)
            .build();

    public TestState(StateService stateService) { stateService.save(state); }
}
