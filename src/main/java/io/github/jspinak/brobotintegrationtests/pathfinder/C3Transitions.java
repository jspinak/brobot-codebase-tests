package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobotintegrationtests.pathfinder.C3State.Enum.C3_STATE;

@Component
public class C3Transitions {

    private CommonActions commonActions;

    private StateTransitions transitions;

    public C3Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions) {
        this.commonActions = commonActions;
        transitions = new StateTransitions.Builder(C3_STATE)
                .addTransitionFinish(this::finishTransition)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, C3_STATE);
    }

}
