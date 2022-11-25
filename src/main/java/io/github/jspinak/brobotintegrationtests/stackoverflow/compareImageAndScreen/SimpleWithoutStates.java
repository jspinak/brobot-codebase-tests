package io.github.jspinak.brobotintegrationtests.stackoverflow.compareImageAndScreen;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.location.Location;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.datatypes.primitives.region.Region;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;

import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Action.CLICK;
import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Action.FIND;
import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Find.COLOR;

public class SimpleWithoutStates {

    private Action action;
    private RedRegionState redRegionState;

    public SimpleWithoutStates(Action action, RedRegionState redRegionState) {
        this.action = action;
        this.redRegionState = redRegionState;
    }

    public Matches find() {
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(FIND)
                .setFind(COLOR)
                .addSearchRegion(new Region(1342,212,175,21))
                .build();
        return action.perform(actionOptions, redRegionState.getRedArea());
    }

    public void click2() {
        int x = find().isSuccess() ? 3406 : 23;
        ObjectCollection clickLocation = new ObjectCollection.Builder()
                .withLocations(new Location(x, 1420))
                .build();
        action.perform(CLICK, clickLocation);
    }

    public void click() {
        int x = find().isSuccess() ? 3406 : 23;
        action.perform(CLICK, new Location(x, 1420).asObjectCollection());
    }

}
