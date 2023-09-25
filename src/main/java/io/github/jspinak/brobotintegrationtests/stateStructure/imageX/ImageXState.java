package io.github.jspinak.brobotintegrationtests.stateStructure.imageX;

import static io.github.jspinak.brobotintegrationtests.stateStructure.imageX.ImageXState.Name.IMAGEX;

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
public class ImageXState {
  private final StateImageObject imageX = new StateImageObject.Builder()
  .withImages("imageX")
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(614, 188, 11, 13)
  	.addMatch(367, 709, 11, 13)
  	.build())
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(614, 188, 11, 13)
  	.addMatch(367, 709, 11, 13)
  	.build())
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(614, 188, 11, 13)
  	.addMatch(367, 709, 11, 13)
  	.build())
  .addSnapshot(new MatchSnapshot.Builder()
  	.setActionOptions(ActionOptions.Find.ALL)
  	.addMatch(614, 188, 11, 13)
  	.addMatch(367, 709, 11, 13)
  	.build())
  .build();

  private final StateImageObject image1 = new StateImageObject.Builder()
  .withImages("image1")
  .addSnapshot(347, 538, 75, 39)
  .addSnapshot(347, 516, 75, 39)
  .addSnapshot(347, 494, 75, 39)
  .addSnapshot(347, 472, 75, 39)
  .build();

  private final State state = new State.Builder(IMAGEX)
  	.withImages(imageX, image1)
  	.build();

  public ImageXState(StateService stateService) {
    stateService.save(state);
  }

  public enum Name implements StateEnum {
    IMAGEX
  }
}
