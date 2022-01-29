package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.database.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.database.state.state.State;
import io.github.jspinak.brobot.database.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class A1State {

    public enum Enum implements StateEnum {
        A1_STATE
    }

    private StateImageObject x = new StateImageObject.Builder()
            .withImage("X")
            .addSnapshot(new MatchSnapshot.Builder()
                    .addMatch(20, 20, 50, 100)
                    .build())
            .build();

    private State state = new State.Builder(Enum.A1_STATE)
            .withImages(x)
            .build();

    public A1State(StateService stateService) { stateService.save(state); }
}
