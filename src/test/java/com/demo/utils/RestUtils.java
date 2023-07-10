package com.demo.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demo.tests.TestBase;
import com.demo.utils.RestUtils;

import io.restassured.response.Response;

public class RestUtils extends TestBase {

	public static String getDataFromResponse(Response response, String xpath) {
		return response.getBody().jsonPath().get(xpath).toString();
	}

	public static void setResponseDetailInExtentReport(String responseKey, String responseValue, ExtentTest extentLog) {
		String keyValueStr = "<b> " + responseKey + "  ::" + "<b>" + responseValue;
		Markup color = MarkupHelper.createLabel(keyValueStr, ExtentColor.GREEN);
		extentLog.log(Status.INFO, color);
	}

	public static void verifyActualExpectedResult(String expectedResult, String actualResult, String str,
			ExtentTest extentLog) {
		String actualExpectedLogStr = "<b> Expected/Actual:: " + expectedResult + "/" + actualResult + " <b>";
		if (expectedResult.equals(actualResult)) {
			passedLog(actualExpectedLogStr, "Successfully verify " + str + "", extentLog);
		} else {
			failedLog(actualExpectedLogStr, "Not able to verify " + str + "", extentLog);
		}
	}

	public static void verifyActualExpectedResult(int expectedResult, int actualResult, String str,
			ExtentTest extentLog) {
		String actualExpectedLogStr = "<b> Expected/Actual:: " + expectedResult + "/" + actualResult + " <b>";
		if (expectedResult == actualResult) {
			passedLog(actualExpectedLogStr, "Successfully verify " + str + "", extentLog);
		} else {
			failedLog(actualExpectedLogStr, "Not able to verify " + str + "", extentLog);
		}
	}

	public static void verifyActualExpectedResult(Boolean expectedResult, Boolean actualResult, String str,
			ExtentTest extentLog) {
		String actualExpectedLogStr = "<b> Expected/Actual:: " + expectedResult + "/" + actualResult + " <b>";
		if (expectedResult.equals(actualResult)) {
			passedLog(actualExpectedLogStr, "Successfully verify " + str + "", extentLog);
		} else {
			failedLog(actualExpectedLogStr, "Not able to verify " + str + "", extentLog);
		}
	}

	public static void passedLog(String ActualvsExpectedLogText, String passedMessage, ExtentTest log) {
		Markup m = MarkupHelper.createLabel(ActualvsExpectedLogText, ExtentColor.GREEN);
		log.log(Status.INFO, passedMessage);
		log.log(Status.PASS, m);
	}

	public static void failedLog(String ActualvsExpectedLogText, String failedMessage, ExtentTest log) {
		Markup m = MarkupHelper.createLabel(ActualvsExpectedLogText, ExtentColor.RED);
		log.log(Status.FAIL, m);
		log.log(Status.INFO, failedMessage);
	}

	
	public static String getCreatedid(Response response) {
		created_id = getDataFromResponse(response, "id");
		return created_id;
	}
	// Node verification method
		public static void verifyNode(Response response, String expectedNode, ExtentTest log) {

			String node = response.getBody().asString();
			boolean actualNode = node.contains(expectedNode);
			String logText = "To Verify ::" + expectedNode + " Key from response body ";
			if (actualNode == true) {
				
				verifyActualExpectedResult(true, true, "'"+expectedNode+"'" +" Key from response body ", log);

			} else {
				
				verifyActualExpectedResult(true, false, "'"+expectedNode+"'"+ " Key from response body", log);
			}
		}
	
	
}

