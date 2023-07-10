package com.demo.requests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.pojo.Post;
import com.demo.requests.RestClient;
import com.demo.tests.TestBase;

import io.restassured.response.Response;

public class RequestFactory extends TestBase {

	RestClient restClient = new RestClient();

	
	public Response getmethod(String url, ExtentTest test) {

		Response response = restClient.doGetRequest(url, test);
		return response;
	}
	public Response doPost(String url,String name, String job, ExtentTest test) {

		Post payload=new Post(name, job);
		Response response = restClient.doPostRequest(url, payload, test);
		setRequestParameterInExtentReport("Username", name, test);
		setRequestParameterInExtentReport("Job", job, test);
		return response;
	}
	public Response doPut(String url,String updated_name, String updated_job, ExtentTest test) {

		Post payload=new Post(updated_name, updated_job);
		Response response = restClient.doPutRequest(url, payload, test);
		setRequestParameterInExtentReport("Username", updated_name, test);
		setRequestParameterInExtentReport("Job", updated_job, test);
		return response;
	}

	public Response doDelete(String url, ExtentTest test) {
		Response response = restClient.doDeleteRequest(url, test);
		return response;
	}
	public static void setRequestParameterInExtentReport(String key, String value, ExtentTest log) {
		String params = "<b> " + key + " ::" + "<b>" + value;
		log.log(Status.INFO, params);
	}


}
