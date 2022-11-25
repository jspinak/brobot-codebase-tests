package io.github.jspinak.brobotintegrationtests;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobot.services.Init;
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
        BrobotSettings.initProfilesForDynamicImages = false;
        BrobotSettings.initProfilesForStaticfImages = false;
        Init init = context.getBean(Init.class);
        init.setBundlePathAndPreProcessImages("images.sikuli");
        //ImagePath.setBundlePath(System.getProperty("user.dir")+"/images.sikuli");
        //ImagePath.add("screenshots");
        //System.out.println("bundle path: "+ org.sikuli.script.ImagePath.getBundlePath());
        BrobotSettings.mock = false;
        BrobotSettings.saveHistory = true;

        Tests tests = context.getBean(Tests.class);
        tests.run();
    }

}
