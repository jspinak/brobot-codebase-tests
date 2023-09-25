package io.github.jspinak.brobotintegrationtests.northgard;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.state.ObjectCollection;
import org.springframework.stereotype.Component;

@Component
public class FindPeople {

    private Action action;
    private NorthgardState northgardState;

    public FindPeople(Action action, NorthgardState northgardState) {
        this.action = action;
        this.northgardState = northgardState;
    }

    public void findPeople() {
        BrobotSettings.saveHistory = true;
        BrobotSettings.mock = true;
        BrobotSettings.screenshots.add("screen16.png");

        ActionOptions findPeople = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.FIND)
                .setFind(ActionOptions.Find.COLOR)
                .setMaxMatchesToActOn(5)
                .build();
        ObjectCollection people = new ObjectCollection.Builder()
                .withImages(northgardState.getPerson())
                .build();
        ObjectCollection land = new ObjectCollection.Builder()
                .withImages(northgardState.getLand(), northgardState.getBuilding())
                .build();
        ObjectCollection building = new ObjectCollection.Builder()
                .withImages(northgardState.getBuilding())
                .build();
        action.perform(findPeople, people, land, building);
    }
}
