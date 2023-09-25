package io.github.jspinak.brobotintegrationtests.stateStructure.imageX;

import static io.github.jspinak.brobotintegrationtests.stateStructure.imageX.ImageXState.Name.IMAGEX;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

@Component
public class ImageXTransitions {
  private final CommonActions commonActions;

  private final ImageXState imageX;

  public ImageXTransitions(CommonActions commonActions, ImageXState imageX,
      StateTransitionsRepository stateTransitionsRepository) {
    this.commonActions = commonActions;
    this.imageX = imageX;
    stateTransitionsRepository.add(
        new StateTransitions.Builder(IMAGEX)
        .addTransitionFinish(this::finishTransition)
        .build());
  }

  private boolean finishTransition() {
    return commonActions.findState(1, IMAGEX);
  }
}
