package io.github.jspinak.brobotintegrationtests;

import io.github.jspinak.brobotintegrationtests.actions.ActionTests;
import io.github.jspinak.brobotintegrationtests.northgard.FindPeople;
import io.github.jspinak.brobotintegrationtests.northgard.InitNorthgard;
import io.github.jspinak.brobotintegrationtests.pathfinder.PathfinderTests;
import io.github.jspinak.brobotintegrationtests.probabilities.ProbabilityTests;
import io.github.jspinak.brobotintegrationtests.text.TextTests;
import org.springframework.stereotype.Component;

@Component
public class Tests {

    private ActionTests actionTests;
    private PathfinderTests pathfinderTests;
    private ProbabilityTests probabilityTests;
    private TextTests textTests;
    private InitNorthgard initNorthgard;
    private FindPeople findPeople;

    public Tests(ActionTests actionTests, PathfinderTests pathfinderTests, ProbabilityTests probabilityTests,
                 TextTests textTests, InitNorthgard initNorthgard, FindPeople findPeople) {
        this.actionTests = actionTests;
        this.pathfinderTests = pathfinderTests;
        this.probabilityTests = probabilityTests;
        this.textTests = textTests;
        this.initNorthgard = initNorthgard;
        this.findPeople = findPeople;
    }

    public void run() {
        //textTests.run();
        actionTests.run();
        //pathfinderTests.run();
        //probabilityTests.run();
    }

    public void runNorthgard() {
        findPeople.findPeople();
        //initNorthgard.capture();
    }
}
