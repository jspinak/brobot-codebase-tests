package io.github.jspinak.brobotintegrationtests.stateStructure.definedSearchRegions;

import io.github.jspinak.brobot.datatypes.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Find.ALL;

@Component
@Getter
public class StateWithDefinedSearchRegions {

    private final StateImageObject variableLocationImage = new StateImageObject.Builder()
            .withImages("imageX")
            .isFixed(false)
            .isShared(true)
            .withSearchRegion(962, 53, 299, 261)
            .addSnapshot(new MatchSnapshot.Builder()
                    .setActionOptions(ALL)
                    .addMatch(1123,163, 7, 7)
                    .addMatch(1127,175, 7, 7)
                    .addMatch(930, 392, 7, 7) //this is outside the search region
                    .build())
            .build();

    private final State state = new State.Builder(Name.SEARCH)
            .withImages(variableLocationImage)
            .build();

    public StateWithDefinedSearchRegions(StateService stateService) {
        stateService.save(state);
    }

    public enum Name implements StateEnum {
        SEARCH
    }
}
