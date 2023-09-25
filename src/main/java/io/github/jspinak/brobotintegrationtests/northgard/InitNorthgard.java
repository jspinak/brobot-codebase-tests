package io.github.jspinak.brobotintegrationtests.northgard;

import io.github.jspinak.brobot.imageUtils.CaptureScreenshots;
import org.springframework.stereotype.Component;

@Component
public class InitNorthgard {

    private CaptureScreenshots captureScreenshots;

    public InitNorthgard(CaptureScreenshots captureScreenshots) {
        this.captureScreenshots = captureScreenshots;
    }

    public void capture() {
        captureScreenshots.capture(10 * 60, 1);
    }
}
