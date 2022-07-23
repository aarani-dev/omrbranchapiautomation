package com.runner;

import org.junit.runner.RunWith;

import com.reports.Reporting;

import org.junit.AfterClass;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {}, snippets = SnippetType.CAMELCASE, strict = true, dryRun = false, monochrome = true, plugin = {"pretty", "json:target/output.json"}, features = {"C:\\Users\\user\\eclipse-workspace\\OMRBranchAPIAutomation\\src\\test\\resources\\Features"}, glue = {"com.stepdefinition"})

public class TestRunnerClass {
	@AfterClass
	public static void afterClass() {
		Reporting.generatesJVMReport("C:\\Users\\user\\eclipse-workspace\\OMRBranchAPIAutomation\\target\\output.json");
		
		}
	
	
	
	
	
	
	
	
	
	


		
}
