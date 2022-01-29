package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.reports.Report;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobot.manageStates.StateMemory.Enum.PREVIOUS;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B1State.Enum.B1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C1State.Enum.C1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C2State.Enum.C2_STATE;

@Component
public class B1Transitions {

    private CommonActions commonActions;
    private B1State b1State;

    private StateTransitions transitions;

    public B1Transitions(StateTransitionsRepository stateTransitionsRepository,
                         CommonActions commonActions, B1State b1State) {
        this.commonActions = commonActions;
        this.b1State = b1State;
        transitions = new StateTransitions.Builder(B1_STATE)
                .addTransitionFinish(this::finishTransition)
                .addTransition(this::openC1, C1_STATE)
                .addTransition(this::openC2, C2_STATE)
                .addTransition(this::closeState, PREVIOUS)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    public boolean finishTransition() {
        return commonActions.findState(1, B1_STATE);
    }

    public boolean openC1() {
        return commonActions.click(1, b1State.getX());
    }

    public boolean openC2() {
        return commonActions.click(1, b1State.getX());
    }

    public boolean closeState() {
        //do something else to close the state
        Report.println("close B1");
        return true;
    }
}
