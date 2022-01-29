package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateService;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobot.manageStates.UnknownState.Enum.UNKNOWN;
import static io.github.jspinak.brobotintegrationtests.pathfinder.A1State.Enum.A1_STATE;

@Component
public class UnknownStateTransitions {

    private StateService stateService;

    private StateTransitions transitions;

    public UnknownStateTransitions(StateTransitionsRepository stateTransitionsRepository,
                                   StateService stateService) {
        this.stateService = stateService;
        transitions = new StateTransitions.Builder(UNKNOWN)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openA1, A1_STATE)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    private boolean finishTransition() {
        return true;
    }

    private boolean openA1() {
        //if (do something to open A1_STATE == false) return false;
        stateService.findByName(A1_STATE).ifPresent(state -> state.setProbabilityExists(100));
        return true;
    }

}
