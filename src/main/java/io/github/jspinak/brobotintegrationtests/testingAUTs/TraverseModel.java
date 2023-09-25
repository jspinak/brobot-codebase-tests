package io.github.jspinak.brobotintegrationtests.testingAUTs;

import io.github.jspinak.brobot.testingAUTs.ElasticClient;
import io.github.jspinak.brobot.testingAUTs.IndexTemplateCreator;
import io.github.jspinak.brobot.testingAUTs.StateTraversalService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TraverseModel {

    private StateTraversalService stateTraversalService;
    private IndexTemplateCreator indexTemplateCreator;
    private ElasticClient elasticClient;

    public TraverseModel(StateTraversalService stateTraversalService,
                         IndexTemplateCreator indexTemplateCreator,
                         ElasticClient elasticClient) {
        this.stateTraversalService = stateTraversalService;
        this.indexTemplateCreator = indexTemplateCreator;
        this.elasticClient = elasticClient;
    }

    public void run() {
        elasticClient.init();
        try {
            indexTemplateCreator.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //stateTraversalService.traverseAllStates();
        try {
            elasticClient.sendLogsToElasticsearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
