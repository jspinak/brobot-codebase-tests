package io.github.jspinak.brobotintegrationtests.pathfinder;

import io.github.jspinak.brobot.manageStates.*;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.reports.Report;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.jspinak.brobot.manageStates.UnknownState.Enum.UNKNOWN;
import static io.github.jspinak.brobotintegrationtests.pathfinder.A1State.Enum.A1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.B1State.Enum.B1_STATE;
import static io.github.jspinak.brobotintegrationtests.pathfinder.C3State.Enum.C3_STATE;

/**
 * Tests the PathFinder, including Hidden States, on the following State structure:
 *
 * Transitions:
 * UNKNOWN -> A1
 * A1 -> B1,B2
 * A2 -> B1,B2
 * B1 -> C1,C2,PREVIOUS (this can be either A1 or A2)
 * B2 -> C2,C3
 * B3 -> C3
 * C1 -> C3
 * C2 -> A2
 * C3 ->|
 *
 */
@Component
public class PathfinderTests {

    private PathFinder pathFinder;
    private StateMemory stateMemory;
    private StateTransitionsManagement stateTransitionsManagement;
    private B1State b1State;

    public PathfinderTests(PathFinder pathFinder, StateMemory stateMemory,
                           StateTransitionsManagement stateTransitionsManagement,
                           B1State b1State) {
        this.pathFinder = pathFinder;
        this.stateMemory = stateMemory;
        this.stateTransitionsManagement = stateTransitionsManagement;
        this.b1State = b1State;
    }

    public void run() {
        System.out.println("\n__Pathfinder Tests__");
        Set<StateEnum> startStates = new HashSet<>();
        startStates.add(UNKNOWN);
        Paths pathsFound = pathFinder.getPathsToState(startStates, C3_STATE);
        TestOutput.assertTrue("paths found", 4, pathsFound.getPaths().size());
        Path path1 = pathsFound.getPaths().get(0);
        if (path1.getScore() == 4) {
            TestOutput.assertTrue("path #1", "UNKNOWN", path1.getPath().get(0).toString(),
                    "A1_STATE", path1.getPath().get(1).toString(), "B2_STATE", path1.getPath().get(2).toString(),
                    "C3_STATE", path1.getPath().get(3).toString());
        }
        testScores(pathsFound);
        testPreviousState();
    }

    private void testPreviousState() {
        Report.println("\n__Previous State Tests__");
        // should be UNKNOWN -> A1 -> B1
        Paths toB1 = pathFinder.getPathsToState(Collections.singleton(UNKNOWN), B1_STATE);
        TestOutput.assertTrue("Path A1->B1 exists", true, !toB1.isEmpty());
        // there should be no Path to A1
        Paths B1toA1 = pathFinder.getPathsToState(Collections.singleton(B1_STATE), A1_STATE);
        TestOutput.assertTrue("Path B1->A1 does not exist", true, B1toA1.isEmpty());
        // mock going to B1, then find a path to A1. there should now be a Path through PREVIOUS
        stateMemory.removeInactiveState(UNKNOWN);
        stateMemory.addActiveState(A1_STATE, true);
        stateTransitionsManagement.openState(B1_STATE);
        TestOutput.assertTrue("B1_STATE is active", true, stateMemory.getActiveStates().contains(B1_STATE));
        Set<StateEnum> b1Prev = b1State.getState().getHidden();
        TestOutput.assertTrue("# of hidden States", 1, b1Prev.size());
        if (b1Prev.size() > 0) TestOutput.assertTrue("hidden State", A1_STATE, b1Prev.iterator().next());
        // there should now be a path from B1 to A1
        B1toA1 = pathFinder.getPathsToState(Collections.singleton(B1_STATE), A1_STATE);
        TestOutput.assertTrue("Path B1->A1 exists", true, !B1toA1.isEmpty());
        stateTransitionsManagement.openState(A1_STATE);
        // After we leave B1, B1 should no longer have any hidden States.
        TestOutput.assertTrue("no hidden States", true, b1State.getState().getHidden().isEmpty());
    }

    /**
     * for State C1, the Score is set to 5 instead of the standard 1
     * this will give any Path that contains C1 a higher score
     * Paths will higher scores will be visited after Paths with lower scores
     */
    private void testScores(Paths paths) {
        List<Path> pathsFound = paths.getPaths();
        TestOutput.assertTrue("path #1 score", 4, pathsFound.get(0).getScore());
        TestOutput.assertTrue("path #2 score", 7, pathsFound.get(1).getScore());
        TestOutput.assertTrue("path #3 score", 9, pathsFound.get(2).getScore());
        TestOutput.assertTrue("path #4 score", 12, pathsFound.get(3).getScore());
    }
}
