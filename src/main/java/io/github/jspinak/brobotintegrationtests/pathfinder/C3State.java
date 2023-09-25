package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.datatypes.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class C3State {

    public enum Enum implements StateEnum {
        C3_STATE
    }

    private StateImageObject x = new StateImageObject.Builder()
            .withImage("X")
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(20, 20, 50, 100)
                    .build())
            .build();

    private State state = new State.Builder(Enum.C3_STATE)
            .withImages(x)
            .build();

    public C3State(StateService stateService) { stateService.save(state); }
}
