package com.example.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import java.util.List;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.api",
    plugin = {
        "pretty",
        "html:target/cucumber-html-report",
        "json:target/cucumber.json"
    },
    monochrome = true
)
public class RunCucumberTestNG extends AbstractTestNGCucumberTests {
} 