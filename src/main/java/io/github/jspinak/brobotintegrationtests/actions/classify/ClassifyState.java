package io.github.jspinak.brobotintegrationtests.actions.classify;

import io.github.jspinak.brobot.datatypes.primitives.dynamicImage.DynamicImage;
import io.github.jspinak.brobot.datatypes.state.state.State;
import io.github.jspinak.brobot.datatypes.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClassifyState {

    public enum Name implements StateEnum {
        CLASSIFY
    }

    private StateImageObject grass = new StateImageObject.Builder()
            .called("grass")
            .setDynamicImage(
                    new DynamicImage.Builder()
                            .addInsideImages("grass1","grass2","grass3")
                            .build())
            .build();

    private StateImageObject kobold = new StateImageObject.Builder()
            .called("kobold")
            .setDynamicImage(
                    new DynamicImage.Builder()
                            .addInsideImages("kobold1","kobold2","kobold3")
                            .build())
            .build();

    private StateImageObject scene1 = new StateImageObject.Builder()
            .called("scene1")
            .withImage("kobolds1")
            .build();

    private StateImageObject scene2 = new StateImageObject.Builder()
            .called("scene2")
            .withImage("kobolds2")
            .build();

    private State state = new State.Builder(Name.CLASSIFY)
            .withImages(grass, kobold)
            .build();

    public ClassifyState(StateService stateService) { stateService.save(state); }

}
