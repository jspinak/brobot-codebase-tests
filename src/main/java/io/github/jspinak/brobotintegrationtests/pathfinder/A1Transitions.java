package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.A1State.Enum.A1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B1State.Enum.B1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B2State.Enum.B2_STATE;

@Component
public class A1Transitions {

    private CommonActions commonActions;
    private A1State a1State;

    private StateTransitions transitions;

    public A1Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, A1State a1State) {
        this.commonActions = commonActions;
        this.a1State = a1State;
        transitions = new StateTransitions.Builder(A1_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openB1, B1_STATE)
                .addTransition(this::openB2, B2_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, A1_STATE);
    }

    public boolean openB1() {
        return commonActions.click(1, a1State.getX());
    }

    public boolean openB2() {
        return commonActions.click(1, a1State.getX());
    }
}
