package io.github.jspinak.brobotintegrationtests;

import io.github.jspinak.brobot.actions.BrobotSettings;
import org.sikuli.script.ImagePath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BrobotIntegrationTestsApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BrobotIntegrationTestsApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        // setup brobot
        ImagePath.setBundlePath("images.sikuli");
        BrobotSettings.mock = true;

        Tests tests = context.getBean(Tests.class);
        tests.run();
    }

}
