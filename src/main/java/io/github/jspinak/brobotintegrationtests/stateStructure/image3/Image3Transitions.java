package io.github.jspinak.brobotintegrationtests.stateStructure.image3;

import static io.github.jspinak.brobotintegrationtests.stateStructure.image3.Image3State.Name.IMAGE3;

import io.github.jspinak.brobot.actions.customActions.CommonActions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

@Component
public class Image3Transitions {
  private final CommonActions commonActions;

  private final Image3State image3;

  public Image3Transitions(CommonActions commonActions, Image3State image3,
      StateTransitionsRepository stateTransitionsRepository) {
    this.commonActions = commonActions;
    this.image3 = image3;
    stateTransitionsRepository.add(
        new StateTransitions.Builder(IMAGE3)
        .addTransitionFinish(this::finishTransition)
        .build());
  }

  private boolean finishTransition() {
    return commonActions.findState(1, IMAGE3);
  }
}
