package io.github.jspinak.brobotintegrationtests.actions;

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
                       VanishTest vanishTest) {
        this.testState = testState;
        this.defineTest = defineTest;
        this.findTest = findTest;
        this.clickTest = clickTest;
        this.dragTest = dragTest;
        this.moveMouseTest = moveMouseTest;
        this.getTextTest = getTextTest;
        this.vanishTest = vanishTest;
    }

    public void run() {
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
