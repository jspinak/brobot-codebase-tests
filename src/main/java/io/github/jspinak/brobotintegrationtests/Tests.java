package io.github.jspinak.brobotintegrationtests;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobotintegrationtests.actions.ActionTests;
import io.github.jspinak.brobotintegrationtests.northgard.NorthgardTest;
import io.github.jspinak.brobotintegrationtests.pathfinder.PathfinderTests;
import io.github.jspinak.brobotintegrationtests.probabilities.ProbabilityTests;
import io.github.jspinak.brobotintegrationtests.screenshots.ScreenshotTests;
import io.github.jspinak.brobotintegrationtests.testingAUTs.TraverseModel;
import io.github.jspinak.brobotintegrationtests.text.TextTests;
import io.github.jspinak.brobotintegrationtests.unitTests.DistanceTest;
import org.springframework.stereotype.Component;

@Component
public class Tests {

    private ActionTests actionTests;
    private PathfinderTests pathfinderTests;
    private ProbabilityTests probabilityTests;
    private TextTests textTests;
    private ScreenshotTests screenshotTests;
    private DistanceTest distanceTest;
    private TraverseModel traverseModel;
    private final NorthgardTest northgardTest;

    public Tests(ActionTests actionTests, PathfinderTests pathfinderTests, ProbabilityTests probabilityTests,
                 TextTests textTests, ScreenshotTests screenshotTests, DistanceTest distanceTest,
                 TraverseModel traverseModel, NorthgardTest northgardTest) {
        this.actionTests = actionTests;
        this.pathfinderTests = pathfinderTests;
        this.probabilityTests = probabilityTests;
        this.textTests = textTests;
        this.screenshotTests = screenshotTests;
        this.distanceTest = distanceTest;
        this.traverseModel = traverseModel;
        this.northgardTest = northgardTest;
    }

    public void run() {
        BrobotSettings.mock = false;
        //runReal();
        BrobotSettings.mock = true;
        runMock();
    }

    /*
    Tests screenshot retrieval for the StateStructure builder
     */
    private void runReal() {
        northgardTest.run();
        //screenshotTests.getScreenshots();
        //screenshotTests.getStateImageObjects();
        //screenshotTests.build();
    }

    /*
    Tests actions and other functionality using mocks
     */
    private void runMock() {
        //textTests.run();
        //actionTests.run();
        pathfinderTests.run();
        /*probabilityTests.run();
        distanceTest.run();

         */
        traverseModel.run();
    }
}
