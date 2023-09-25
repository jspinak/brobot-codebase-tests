package io.github.jspinak.brobotintegrationtests.probabilities;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.reports.Report;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tests mocking functionality when using probabilities and not Match Histories.
 */
@Component
public class ProbabilityTests {

    private final Action action;
    private final ProbabilityState probabilityState;

    public ProbabilityTests(Action action, ProbabilityState probabilityState) {
        this.action = action;
        this.probabilityState = probabilityState;
    }

    public void run() {
        System.out.println("\n__Probability Tests__");
        probabilityState.getState().setProbabilityExists(100);
        Matches matches = action.perform(new ActionOptions(), probabilityState.getTopLeft());
        TestOutput.assertTrue("100% probability match is found", true, matches.isSuccess());
        probabilityState.getTopLeft().setProbabilityExists(0);
        matches = action.perform(new ActionOptions(), probabilityState.getTopLeft());
        TestOutput.assertTrue("0% probability match is not found", false, matches.isSuccess());
        probabilityState.getTopLeft().setProbabilityExists(50);
        List<Matches> matchesList = new ArrayList<>();
        for (int i=0; i<10; i++) matchesList.add(action.perform(new ActionOptions(), probabilityState.getTopLeft()));
        AtomicInteger successes = new AtomicInteger();
        matchesList.forEach(m -> { if (m.isSuccess()) successes.getAndIncrement(); });
        boolean successCondition = successes.get() > 0 && successes.get() < 10;
        Report.println("# of successes out of 10 tries = "+ successes.get());
        // it's unlikely to get 0% or 100% in 10 tries but still possible
        TestOutput.assertTrue("50% probability. results are between no matches and all matches.",true, successCondition);
    }
}
