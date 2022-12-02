package io.github.jspinak.brobotintegrationtests.actions.findColor;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.region.Region;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.SearchRegions;
import io.github.jspinak.brobotintegrationtests.actions.classify.ClassifyState;
import org.springframework.stereotype.Component;

@Component
public class ColorTest {

    private Action action;
    private ColorState colorState;
    private ClassifyState classifyState;

    public ColorTest(Action action, ColorState colorState, ClassifyState classifyState) {
        this.action = action;
        this.colorState = colorState;
        this.classifyState = classifyState;
    }

    public void test() {
        SearchRegions searchRegions = new SearchRegions();
        Region reg = colorState.getMinimapRegion().getSearchRegion();
        Region reg2 = new Region(reg);
        reg2.setX(reg.getX() + 200);
        reg2.setW(reg.getW() + 300);
        reg2.setY(reg.getY() + 100);
        reg2.setH(reg.getH() + 300);
        searchRegions.addSearchRegions(reg);
        ActionOptions findColor = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.COLOR)
                .setColor(ActionOptions.Color.MU)
                .setSearchRegions(searchRegions)
                //.setMinScore(0.4)
                .setMaxArea(4)
                .setMaxMatchesToActOn(25)
                //.setPauseBeforeBegin(2)
                .build();

        BrobotSettings.saveHistory = true;

        BrobotSettings.mock = true;
        BrobotSettings.screenshot = "kobolds1.png";

        ActionOptions findBar = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.ALL)
                .addFind(ActionOptions.Find.COLOR)
                //.setMinSimilarity(0.20)
                .setMinScore(.7)
                //.keepLargerMatches(true)
                //.setPauseBeforeBegin(2)
                .build();
        ActionOptions findClass = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLASSIFY)
                .setMaxMatchesToActOn(1)
                .setKmeans(4)
                .build();
        ActionOptions histogramFind = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.HISTOGRAM)
                .setMaxMatchesToActOn(5)
                .setMinScore(.70)
                .build();
        //action.perform(findColor, colorState.getRedDot());
        //action.perform(findBar, colorState.getYellowBar());
        //action.perform(histogramFind, colorState.getPartOfScene());

        ObjectCollection target = new ObjectCollection.Builder()
                .withImages(classifyState.getKobold())
                .build();
        ObjectCollection additional = new ObjectCollection.Builder()
                .withImages(classifyState.getGrass())
                .build();
        action.perform(findClass, target, additional);

        //action.perform(findBar, colorState.getYellowBar());

        ActionOptions moveBar = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLICK)
                .setAddX2(100)
                .setAddY2(100)
                //.setOffsetLocation(400, 500)
                .build();
        //action.perform(moveBar, colorState.getYellowBar());
    }
}
