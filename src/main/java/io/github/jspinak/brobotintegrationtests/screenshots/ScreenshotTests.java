package io.github.jspinak.brobotintegrationtests.screenshots;

import io.github.jspinak.brobot.imageUtils.CaptureScreenshots;
import org.springframework.stereotype.Component;

@Component
public class ScreenshotTests {

    private CaptureScreenshots captureScreenshots;

    public ScreenshotTests(CaptureScreenshots captureScreenshots) {
        this.captureScreenshots = captureScreenshots;
    }

    public void getScreenshots() {
        captureScreenshots.capture(2, .5);
    }

}
