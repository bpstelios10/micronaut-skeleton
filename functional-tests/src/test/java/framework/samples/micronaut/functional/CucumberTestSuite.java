package framework.samples.micronaut.functional;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.CucumberOptions;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@CucumberOptions(features = "features", glue = "net.serenitybdd.cucumber.actors framework.samples.micronaut.functional.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "io.cucumber.core.plugin.SerenityReporter")
public class CucumberTestSuite {
}
