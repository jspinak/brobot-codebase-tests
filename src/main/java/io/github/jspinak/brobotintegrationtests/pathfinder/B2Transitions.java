package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.A1State.Enum.A1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B2State.Enum.B2_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C2State.Enum.C2_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C3State.Enum.C3_STATE;

@Component
public class B2Transitions {

    private CommonActions commonActions;
    private B2State b2State;

    private StateTransitions transitions;

    public B2Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, B2State b2State) {
        this.commonActions = commonActions;
        this.b2State = b2State;
        transitions = new StateTransitions.Builder(B2_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openC2, C2_STATE)
                .addTransition(this::openC3, C3_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, A1_STATE);
    }

    public boolean openC2() {
        return commonActions.click(1, b2State.getX());
    }

    public boolean openC3() {
        return commonActions.click(1, b2State.getX());
    }
}
