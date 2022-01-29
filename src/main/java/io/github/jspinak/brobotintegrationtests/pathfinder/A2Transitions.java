package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.A2State.Enum.A2_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B1State.Enum.B1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B2State.Enum.B2_STATE;

@Component
public class A2Transitions {

    private CommonActions commonActions;
    private A2State a2State;

    private StateTransitions transitions;

    public A2Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, A2State a2State) {
        this.commonActions = commonActions;
        this.a2State = a2State;
        transitions = new StateTransitions.Builder(A2_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openB1, B1_STATE)
                .addTransition(this::openB2, B2_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, A2_STATE);
    }

    public boolean openB1() {
        return commonActions.click(1, a2State.getX());
    }

    public boolean openB2() {
        return commonActions.click(1, a2State.getX());
    }
}
