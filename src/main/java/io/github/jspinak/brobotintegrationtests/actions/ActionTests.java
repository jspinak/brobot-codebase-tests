package io.github.jspinak.brobotintegrationtests.actions;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobot.reports.Report;
import io.github.jspinak.brobotintegrationtests.actions.classify.ClassifyTest;
import io.github.jspinak.brobotintegrationtests.actions.findColor.ColorTest;
import io.github.jspinak.brobotintegrationtests.actions.motion.MotionTest;
import org.springframework.stereotype.Component;

@Component
public class ActionTests {

    private TestState testState;
    private DefineTest defineTest;
    private FindTest findTest;
    private ClickTest clickTest;
    private DragTest dragTest;
    private MoveMouseTest moveMouseTest;
    private GetTextTest getTextTest;
    private VanishTest vanishTest;
    private ClassifyTest classifyTest;
    private MotionTest motionTest;
    private ColorTest colorTest;
    private XmlTest xmlTest;

    /**
     * Tests basic and composite actions.
     *
     * @param testState is the State used for the tests.
     * @param defineTest DEFINE
     * @param findTest FIND
     * @param clickTest CLICK
     * @param dragTest DRAG
     * @param moveMouseTest MOVE
     * @param getTextTest GET_TEXT
     * @param vanishTest VANISH
     */
    public ActionTests(TestState testState, DefineTest defineTest, FindTest findTest, ClickTest clickTest,
                       DragTest dragTest, MoveMouseTest moveMouseTest, GetTextTest getTextTest,
                       VanishTest vanishTest, ClassifyTest classifyTest, MotionTest motionTest,
                       ColorTest colorTest, XmlTest xmlTest) {
        this.testState = testState;
        this.defineTest = defineTest;
        this.findTest = findTest;
        this.clickTest = clickTest;
        this.dragTest = dragTest;
        this.moveMouseTest = moveMouseTest;
        this.getTextTest = getTextTest;
        this.vanishTest = vanishTest;
        this.classifyTest = classifyTest;
        this.motionTest = motionTest;
        this.colorTest = colorTest;
        this.xmlTest = xmlTest;
    }

    public void run() {
        //xmlTest.writeXml();
        //motionTest.test();
        //colorTest.test();
        //classifyTest.classify();
        BrobotSettings.mock = true;
        if (!BrobotSettings.mock) Report.println("Mocking needs to be turned on.");
        testState.getState().setProbabilityExists(100);
        defineTest.run();
        findTest.run();
        clickTest.run();
        dragTest.run();
        moveMouseTest.run();
        getTextTest.run();
        vanishTest.run();




    }
}
