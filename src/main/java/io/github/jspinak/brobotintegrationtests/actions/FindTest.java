package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.datatypes.primitives.region.Region;
import io.github.jspinak.brobot.reports.TestOutput;
import io.github.jspinak.brobotintegrationtests.stateStructure.definedSearchRegions.StateWithDefinedSearchRegions;
import org.springframework.stereotype.Component;

@Component
public class FindTest {

    private final Action action;
    private final TestState testState;
    private final StateWithDefinedSearchRegions stateWithDefinedSearchRegions;

    public FindTest(Action action, TestState testState,
                    StateWithDefinedSearchRegions stateWithDefinedSearchRegions) {
        this.action = action;
        this.testState = testState;
        this.stateWithDefinedSearchRegions = stateWithDefinedSearchRegions;
    }

    public void run() {
        simpleFindTest();
        stateWithDefinedSearchRegions.getState().setProbabilityExists(100);
        searchRegionTest();
    }

    private void simpleFindTest() {
        System.out.println("\n__Find Tests__");
        Matches matches = action.perform(new ActionOptions(), testState.getTopLeft());
        TestOutput.assertTrue("success", true, matches.isSuccess());
        matches.getBestMatch().ifPresent(
                m -> TestOutput.assertTrue("Image match", 20,
                        m.getMatch().x, 20, m.getMatch().y, 50, m.getMatch().w, 100, m.getMatch().h));
    }

    private void searchRegionTest() {
        System.out.println("\n__SearchRegion Tests__");
        ActionOptions findAll = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.ALL)
                .build();
        Matches matches = action.perform(
                findAll, stateWithDefinedSearchRegions.getVariableLocationImage());
        TestOutput.assertTrue("success", true, matches.isSuccess());
        Region searchReg = stateWithDefinedSearchRegions.getVariableLocationImage().getSearchRegion();
        matches.getMatches().forEach(match ->
                TestOutput.assertTrue("in searchRegion", true, searchReg.contains(match)));
    }
}
