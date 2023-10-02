package io.github.jspinak.brobotintegrationtests.testingAUTs;

import io.github.jspinak.brobot.testingAUTs.ActionLogSender;
import io.github.jspinak.brobot.testingAUTs.IndexTemplateCreator;
import io.github.jspinak.brobot.testingAUTs.RestClientConnection;
import io.github.jspinak.brobot.testingAUTs.StateTraversalService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TraverseModel {

    private StateTraversalService stateTraversalService;
    private IndexTemplateCreator indexTemplateCreator;
    private final ActionLogSender actionLogSender;
    private final RestClientConnection restClientConnection;

    public TraverseModel(StateTraversalService stateTraversalService,
                         IndexTemplateCreator indexTemplateCreator,
                         ActionLogSender actionLogSender,
                         RestClientConnection restClientConnection) {
        this.stateTraversalService = stateTraversalService;
        this.indexTemplateCreator = indexTemplateCreator;
        this.actionLogSender = actionLogSender;
        this.restClientConnection = restClientConnection;
    }

    public void run() {
        stateTraversalService.traverseAllStates();
    }

    private void previous() {
        try {
            indexTemplateCreator.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stateTraversalService.traverseAllStates();
        try {
            actionLogSender.sendLogsToElasticsearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        restClientConnection.close();
    }
}
