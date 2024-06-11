package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",plugin ="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"}, tags = "@LoginDisableallPOJO")
public class TestRunner {
//tags= {"@DeletePlace"}  compile test verify
}
