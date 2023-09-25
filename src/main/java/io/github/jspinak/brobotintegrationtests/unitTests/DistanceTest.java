package io.github.jspinak.brobotintegrationtests.unitTests;

import io.github.jspinak.brobot.analysis.Distance;
import io.github.jspinak.brobot.datatypes.primitives.location.Location;
import io.github.jspinak.brobot.reports.Report;
import io.github.jspinak.brobot.reports.TestOutput;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DistanceTest {

    private Distance distance;

    public DistanceTest(Distance distance) {
        this.distance = distance;
    }

    public void run() {
        Report.println("\n__Distance Tests__");
        convertAndUnconvert();
        basicAngleFunctions();
    }

    private void convertAndUnconvert() {
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(90.0);
        doubleList.add(160.0);
        doubleList.add(180.0);
        doubleList.add(-170.0);
        doubleList.add(-10.0);
        List<Double> convertedList = distance.convertAnglesToSameScale(doubleList);
        System.out.println(convertedList);
        TestOutput.assertTrue("converted correctly", convertedList.contains(190.0), true);
        convertedList = distance.undueConversion(convertedList);
        System.out.println(convertedList);
        TestOutput.assertTrue("unconverted correctly", convertedList.contains(-170.0), true);
    }

    private void basicAngleFunctions() {
        Location zero = new Location(0,0);
        Location loc90 = new Location(0, -1);
        double angle90 = distance.getAngle(zero, loc90);
        TestOutput.assertTrue("90 degrees", 90.0, angle90);
        Location standard90 = distance.getStandardLocation(90.0);
        TestOutput.assertTrue("standard location",
                standard90.getX(), 0, standard90.getY(), -1000);
        //median angle
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(-10.0);
        doubleList.add(-20.0);
        double medianAngle = distance.getMedianAngle(doubleList);
        TestOutput.assertTrue("median angle: ", Math.round(medianAngle), 0.0);
        double medianAngle2 = distance.getMedianAngle(30.0, 120.0);
        TestOutput.assertTrue("median angle", 75.0, Math.round(medianAngle2));
    }
}
