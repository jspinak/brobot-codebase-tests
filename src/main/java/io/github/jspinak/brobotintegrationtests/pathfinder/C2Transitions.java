package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.A2State.Enum.A2_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C2State.Enum.C2_STATE;

@Component
public class C2Transitions {

    private CommonActions commonActions;
    private C2State c2State;

    private StateTransitions transitions;

    public C2Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, C2State c2State) {
        this.commonActions = commonActions;
        this.c2State = c2State;
        transitions = new StateTransitions.Builder(C2_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openA2, A2_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, C2_STATE);
    }


    public boolean openA2() {
        return commonActions.click(1, c2State.getX());
    }
}
