package io.github.jspinak.brobotintegrationtests.stateStructure.image2;

import static io.github.jspinak.brobotintegrationtests.stateStructure.image2.Image2State.Name.IMAGE2;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

@Component
public class Image2Transitions {
  private final CommonActions commonActions;

  private final Image2State image2;

  public Image2Transitions(CommonActions commonActions, Image2State image2,
      StateTransitionsRepository stateTransitionsRepository) {
    this.commonActions = commonActions;
    this.image2 = image2;
    stateTransitionsRepository.add(
        new StateTransitions.Builder(IMAGE2)
        .addTransitionFinish(this::finishTransition)
        .build());
  }

  private boolean finishTransition() {
    return commonActions.findState(1, IMAGE2);
  }
}
