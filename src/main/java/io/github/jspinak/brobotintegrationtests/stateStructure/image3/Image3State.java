package io.github.jspinak.brobotintegrationtests.stateStructure.image3;

import static io.github.jspinak.brobotintegrationtests.stateStructure.image3.Image3State.Name.IMAGE3;

import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.datatypes.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Image3State {
  private final StateImageObject image3 = new StateImageObject.Builder()
  .withImages("image3")
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(96, 537, 74, 39)
  	.addMatch(213, 537, 74, 39)
  	.build())
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(96, 515, 74, 39)
  	.addMatch(213, 515, 74, 39)
  	.build())
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(96, 493, 74, 39)
  	.addMatch(213, 493, 74, 39)
  	.build())
  .build();

  private final State state = new State.Builder(IMAGE3)
  	.withImages(image3)
  	.build();

  public Image3State(StateService stateService) {
    stateService.save(state);
  }

  public enum Name implements StateEnum {
    IMAGE3
  }
}
