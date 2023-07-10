package com.demo.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;
import java.util.Date;

public class ExtentManager {
	private static ExtentReports extent;
	// private static Platform platform;
	private static String qaEnv = "QA Server", devEnv = "Dev Server";
	private static String qaURL = "http://116.206.148.197:91", dveURL = "http://116.206.148.197:91";
	
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		// platform = getCurrentPlatform();
		String fileName = getReportFileName();
		String directory = System.getProperty("user.dir") + "/reports/";
		new File(directory).mkdir();
		String path = directory + fileName;
		ExtentSparkReporter spark = new ExtentSparkReporter(path).viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY }).apply();
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Demo API Project Automation Report");
		spark.config().setEncoding("utf-8");
		spark.config().setReportName("Demo API Project Automation Test Results");
		spark.config().setTheme(Theme.DARK);
//        ((AnalysisTypeConfigurable) spark).setAnalysisStrategy(AnalysisStrategy.TEST);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Demo");
		extent.setSystemInfo("Application", "Demo");
		extent.setSystemInfo("Environment", qaEnv);
		extent.setSystemInfo("URL", qaURL);
		extent.setSystemInfo("User Name", "Demo");
		extent.attachReporter(spark);

		return extent;
	}

	public static String getReportFileName() {
		Date d = new Date();
		String fileName = "Demo_Report" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}

}
