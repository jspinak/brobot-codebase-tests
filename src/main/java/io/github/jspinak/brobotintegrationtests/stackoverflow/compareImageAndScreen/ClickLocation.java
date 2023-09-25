package io.github.jspinak.brobotintegrationtests.stackoverflow.compareImageAndScreen;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.location.Location;
import io.github.jspinak.brobot.datatypes.primitives.match.Matches;
import io.github.jspinak.brobot.datatypes.primitives.region.Region;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Action.CLICK;
import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Action.FIND;
import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Find.COLOR;

@Component
public class ClickLocation {

    private final Action action;
    private final RedRegionState redRegionState;
    private final NotRedState notRedState;

    public ClickLocation(Action action, RedRegionState redRegionState, NotRedState notRedState) {
        this.action = action;
        this.redRegionState = redRegionState;
        this.notRedState = notRedState;
    }

    public Matches find() {
        ActionOptions actionOptions = new ActionOptions.Builder()
                .setAction(FIND)
                .setFind(COLOR)
                .build();
        return action.perform(actionOptions, redRegionState.getRedArea());
    }

    public boolean click() {
        if (find().isSuccess())
            return action.perform(CLICK, redRegionState.getClickLocation().asObjectCollection()).isSuccess();
        return action.perform(CLICK, notRedState.getClickLocation().asObjectCollection()).isSuccess();
    }
}
