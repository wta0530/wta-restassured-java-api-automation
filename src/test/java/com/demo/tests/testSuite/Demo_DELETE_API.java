package com.demo.tests.testSuite;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.requests.RequestFactory;
import com.demo.tests.TestBase;
import com.demo.utils.Configuration;
import com.demo.utils.RestUtils;

import io.restassured.response.Response;

public class Demo_DELETE_API extends TestBase {

	RequestFactory requests = new RequestFactory();
	Response response = null;

	ExtentTest log, log1, log2,log3;
	boolean flag = false;

	@Test(priority = 0)	
	public void deleteAPIRunning() throws IOException, JSONException {

		setScenarioName("Verify DELETE API");
		response = (Response) requests.doDelete("/api/users/"+created_id, extentTest.get());
		extentTest.get().log(Status.INFO, "Response : " + response.body().prettyPrint());
	}


	public void setScenarioName(String scenario) {
		if (test == null) {
			test = extent.createTest(scenario);
			extentTest.set(test);
			flag = true;
		}
		log = extentTest.get().log(Status.INFO, scenario);
	}

	public void displayValueOnReport(String value, ExtentTest log) {
		log.log(Status.INFO, value);
	}

	public ExtentTest log(String message) {
		log = extentTest.get().createNode(message);
		return log;
	}

	@AfterClass
	public void end() {
		if (flag)
			extent.flush();
	}

}
