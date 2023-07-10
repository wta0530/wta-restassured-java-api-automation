package com.demo.tests.testSuite;

import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.io.IOException;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.requests.RequestFactory;
import com.demo.tests.TestBase;
import com.demo.utils.RestUtils;

import io.restassured.response.Response;

public class Demo_POST_API extends TestBase {

	RequestFactory requests = new RequestFactory();
	Response response = null;
	ExtentTest log, log1;
	boolean flag = false;

	public void getAPIRunning() throws IOException, JSONException {

		setScenarioName("Verify POST API");
		
		response = (Response) requests.getmethod("/api/users/2", extentTest.get());
		extentTest.get().log(Status.INFO, "Response : " + response.body().prettyPrint());
		

	}
	@Test(priority = 0)
	public void postAPIRunning() throws IOException, JSONException {

		getAPIRunning();

		response = (Response) requests.doPost("/api/users/165", name, job, extentTest.get());
		extentTest.get().log(Status.INFO, "Response : " + response.body().prettyPrint());

		log = extentTest.get().createNode("To Verify Key from response body");
		log1 = extentTest.get().createNode("To Verify Key from response body");

		RestUtils.verifyNode(response, "name", log1);
		RestUtils.verifyNode(response, "job", log1);
		RestUtils.verifyNode(response, "id", log1);
		RestUtils.getCreatedid(response);

	}

	public void setScenarioName(String scenario) {
		if (test == null) {
			test = extent.createTest(scenario);
			extentTest.set(test);
			flag = true;
		}
		log = extentTest.get().log(Status.INFO, scenario);
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
