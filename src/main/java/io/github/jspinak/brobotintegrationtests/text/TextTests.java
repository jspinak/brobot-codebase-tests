package io.github.jspinak.brobotintegrationtests.text;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.match.Matches;
import io.github.jspinak.brobot.database.state.ObjectCollection;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

/**
 * Check the output to see if it was correctly typed.
 * TYPE returns a successful but empty Matches object.
 */
@Component
public class TextTests {

    private Action action;

    public TextTests(Action action) {
        this.action = action;
    }

    public void run() {
        ActionOptions type = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.TYPE)
                .setModifiers(Key.SHIFT)
                .build();
        ObjectCollection objColl = new ObjectCollection.Builder()
                .withStrings("hi")
                .build();

        System.out.println("\n__Type Tests__");
        action.perform(type, objColl);
    }
}
