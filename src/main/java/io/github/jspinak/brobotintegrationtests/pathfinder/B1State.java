package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.datatypes.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.A1State.Enum.A1_STATE;

@Component
@Getter
public class B1State {

    public enum Enum implements StateEnum {
        B1_STATE
    }

    private StateImageObject x = new StateImageObject.Builder()
            .withImage("X")
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(20, 20, 50, 100)
                    .build())
            .build();

    private State state = new State.Builder(Enum.B1_STATE)
            .withImages(x)
            .canHide(A1_STATE)
            .build();

    public B1State(StateService stateService) { stateService.save(state); }
}
