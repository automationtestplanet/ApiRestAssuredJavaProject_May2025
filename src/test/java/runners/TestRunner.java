package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "Features",
        glue = "step.definitions",
        plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        dryRun = false,
//        tags = "@GetAllUsers",
        snippets = CucumberOptions.SnippetType.CAMELCASE

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
