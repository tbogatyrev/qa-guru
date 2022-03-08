package hw17.tests;

import hw17.api.ra.steps.ApiSteps;
import hw17.data.TestData;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static ApiSteps apiSteps;
    static TestData testData;

    @BeforeAll
    public static void init() {
        apiSteps = new ApiSteps();
        testData = new TestData();
    }
}
