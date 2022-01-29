package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.B3State.Enum.B3_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C3State.Enum.C3_STATE;

@Component
public class B3Transitions {

    private CommonActions commonActions;
    private B3State b3State;

    private StateTransitions transitions;

    public B3Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, B3State b3State) {
        this.commonActions = commonActions;
        this.b3State = b3State;
        transitions = new StateTransitions.Builder(B3_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openC3, C3_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, B3_STATE);
    }


    public boolean openC3() {
        return commonActions.click(1, b3State.getX());
    }
}
