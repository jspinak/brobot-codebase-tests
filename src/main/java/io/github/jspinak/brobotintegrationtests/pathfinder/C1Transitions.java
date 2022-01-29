package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.C1State.Enum.C1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C3State.Enum.C3_STATE;

@Component
public class C1Transitions {

    private CommonActions commonActions;
    private C1State c1State;

    private StateTransitions transitions;

    public C1Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, C1State c1State) {
        this.commonActions = commonActions;
        this.c1State = c1State;
        transitions = new StateTransitions.Builder(C1_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openC3, C3_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, C1_STATE);
    }


    public boolean openC3() {
        return commonActions.click(1, c1State.getX());
    }
}
