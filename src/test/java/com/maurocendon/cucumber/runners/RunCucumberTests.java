package com.maurocendon.cucumber.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"com.maurocendon.cucumber.steps"}, features = "src/test/resources/com/maurocendon/cucumber/features", plugin = { "pretty", "html:target/site/cucumber-pretty",
		"json:target/cucumber.json" })
public class RunCucumberTests {
}