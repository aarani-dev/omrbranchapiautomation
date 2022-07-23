package com.reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting {

	public static void generatesJVMReport(String jsfile) {

		File file = new File("C:\\Users\\user\\eclipse-workspace\\OMRBranchAPIAutomation\\target");
		Configuration configuration = new Configuration(file, "APIAutomation");
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Project", "APIAutomationReport");
		configuration.addClassifications("OS", "Windows 11");
		
		List<String> jsonFiles =new ArrayList<String>();
		jsonFiles.add(jsfile);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
		reportBuilder.generateReports();
		
}
}
