package com.example.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/Features/." },
        monochrome = true,
        glue = {"com.example.features.steps"},
        dryRun = false,
        plugin = { "pretty",
                "html:target/onefile_cucumber_report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        publish = true
)
public class LoginRunner
{
}
