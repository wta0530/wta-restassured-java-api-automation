package com.demo.tests.testSuite;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.requests.RequestFactory;
import com.demo.tests.TestBase;
import com.demo.utils.RestUtils;

import io.restassured.response.Response;

public class Demo_GET_API extends TestBase {

	RequestFactory requests = new RequestFactory();
	Response response = null;

	ExtentTest log;
	boolean flag = false;

			
	@Test(priority = 0)
	public void getAPIRunning() throws IOException, JSONException {

		setScenarioName("Verify GET API");
		
		response = (Response) requests.getmethod("/api/users/2", extentTest.get());
		extentTest.get().log(Status.INFO, "Response : " + response.body().prettyPrint());

		log = extentTest.get().createNode("To Verify Key from response body");
		
		RestUtils.verifyNode(response, "id", log);
		RestUtils.verifyNode(response, "email", log);
		RestUtils.verifyNode(response, "first_name", log);
		RestUtils.verifyNode(response, "last_name", log);

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
