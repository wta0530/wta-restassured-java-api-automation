package com.demo.tests.testSuite;

import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.io.IOException;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.requests.RequestFactory;
import com.demo.tests.TestBase;
import com.demo.utils.Configuration;
import com.demo.utils.RestUtils;

import io.restassured.response.Response;

public class Demo_PUT_API extends TestBase {

	RequestFactory requests = new RequestFactory();
	Response response = null;

	ExtentTest log, log1, log2;
	boolean flag = false;

	@Test(priority = 0)
	public void putAPIRunning() throws IOException, JSONException {

		setScenarioName("Verify PUT API");

		response = (Response) requests.doPut("/api/users/"+created_id, updated_name, updated_job, extentTest.get());
		extentTest.get().log(Status.INFO, "Response : " + response.body().prettyPrint());

		log = extentTest.get().createNode("To Verify Key from response body");
	

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
