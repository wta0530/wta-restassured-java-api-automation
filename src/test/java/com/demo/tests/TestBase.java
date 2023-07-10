package com.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.demo.extent.ExtentManager;
import com.demo.utils.Configuration;
import com.demo.utils.PropertyReader;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestBase {

	public static PropertyReader prop=PropertyReader.getInstance();
	public static String baseUrl = prop.getProperty("baseUrl");
	/*--------------Create API ----------------------*/
	public static String name = "testing";
    public static String  job= "AutimationTest";
    public static String  created_id;
    public static String  updated_name="Updated_testingName";
    public static String  updated_job="Updated_Auto_test_job";
    

    public ExtentReports extent = ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;
	/*
	 * @BeforeClass public static void initUrl() { prop =
	 * PropertyReader.getInstance(); RestAssured.baseURI =
	 * prop.getProperty("baseUrl"); }
	 */
	@BeforeClass
	public static void initUrl() {
		try {
			RestAssured.baseURI =Configuration.applicationMap("baseUrl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static Map<String,String> getData(){
		Map<String,String> fakeDataMap = new HashMap<String,String>();
		Faker data = new Faker();

		fakeDataMap.put("username", data.name().username());
		fakeDataMap.put("mobileNo", data.numerify("+91975#######"));

		return fakeDataMap;
	}
}
