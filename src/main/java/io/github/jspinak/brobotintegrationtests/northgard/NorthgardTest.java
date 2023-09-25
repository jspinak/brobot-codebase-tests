package io.github.jspinak.brobotintegrationtests.northgard;

import org.springframework.stereotype.Component;

@Component
public class NorthgardTest {

    private final InitNorthgard initNorthgard;
    private final FindPeople findPeople;

    public NorthgardTest(InitNorthgard initNorthgard, FindPeople findPeople) {
        this.initNorthgard = initNorthgard;
        this.findPeople = findPeople;
    }

    public void run() {
        findPeople.findPeople();
        //initNorthgard.capture();
    }
}
