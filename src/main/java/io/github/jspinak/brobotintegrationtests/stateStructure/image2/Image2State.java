package io.github.jspinak.brobotintegrationtests.stateStructure.image2;

import static io.github.jspinak.brobotintegrationtests.stateStructure.image2.Image2State.Name.IMAGE2;

import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Image2State {
  private final StateImageObject image2 = new StateImageObject.Builder()
  .withImages("image2")
  .addSnapshot(184, 493, 155, 52)
  .addSnapshot(184, 471, 155, 52)
  .build();

  private final State state = new State.Builder(IMAGE2)
  	.withImages(image2)
  	.build();

  public Image2State(StateService stateService) {
    stateService.save(state);
  }

  public enum Name implements StateEnum {
    IMAGE2
  }
}
