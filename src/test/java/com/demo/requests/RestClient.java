package com.demo.requests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demo.tests.TestBase;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class RestClient extends TestBase {

    public Response doGetRequest(String requestPath,ExtentTest test) {
    	setRequestURIinExtentReport(requestPath, test);
        Response response =	given()
                            .when()
                                .get(requestPath);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON);
        return response;
    }

    public Response doPostRequest(String uri, Object body, ExtentTest test) {
    	setRequestURIinExtentReport(uri, test);
        Response response = given()
                                 .contentType(ContentType.JSON)
                             .when()
                                .body(body)
                                .post(uri);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON);
        return response;
    }
    public Response doPutRequest(String uri, Object body, ExtentTest test) {
    	setRequestURIinExtentReport(uri, test);
        Response response = given()
                                 .contentType(ContentType.JSON)
                             .when()
                                .body(body)
                                .put(uri);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON);
        return response;
    }
    
    public Response doPostRequest(String uri, ExtentTest test) {
    	setRequestURIinExtentReport(uri, test);
        Response response = given()
                                 .contentType(ContentType.JSON)
                             .when()
                                .post(uri);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON);
        return response;
    }
    
    public Response doPostWithBodyWithToken(String uri, Object body, String token)  {
    	setRequestURIinExtentReport(uri, test);
    	setRequestHeaderinExtentReport(token, test);
        Response response = given()
                                .contentType(ContentType.JSON)
                                .header(new Header("Authorization","Bearer " + token))
                            .when()
                                .body(body)
                                .post(uri);
        return response;
    }
    
    public Response doPutWithBodyWithToken(String uri, String token, Object body, ExtentTest test)  {
    	setRequestURIinExtentReport(uri, test);
    	setRequestHeaderinExtentReport(token, test);
        Response response = given()
                                .contentType(ContentType.JSON)
                                .header(new Header("Authorization","Bearer " + token))
                            .when()
                                .body(body)
                                .put(uri);
        return response;
    }

    public Response doPostWithToken(String uri, String token, ExtentTest test)  {
    	setRequestURIinExtentReport(uri, test);
    	setRequestHeaderinExtentReport(token, test);
        Response response = given()
                                .contentType(ContentType.JSON)
                                .header(new Header("Authorization", "Bearer " + token))
                            .when()
                                .post(uri);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON+","+"Authorization :: "+"Bearer " + token);
        return response;
    }

    public static Response doGetWithToken(String url, String token, ExtentTest test)  {
    	setRequestURIinExtentReport(url, test);
    	setRequestHeaderinExtentReport(token, test);
        Response response = given()
                                .contentType(ContentType.JSON)
                                .header(new Header("Authorization", "Bearer " + token))
                            .when()
                                .get(url);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON+","+token);
        return response;
    }
    
    public Response doGetWithBodyAndToken(String url, String token, Object body, ExtentTest test)  {
    	setRequestURIinExtentReport(url, test);
    	setRequestHeaderinExtentReport(token, test);
        Response response = given()
                                .contentType(ContentType.JSON)
                                .header(new Header("Authorization", "Bearer " + token))
                            .when()
                            .body(body)
                                .get(url);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON+","+token);
        return response;
    }
    
    public Response doGetRequestWithQueryParamToken(String res, Map<String, String> params,String token) {
        Response response = given()
				        		.contentType(ContentType.JSON)
				                .header(new Header("Authorization", "Bearer " + token))
                                .queryParams(params)
                            .when()
                                .get(res);
        return response;
    }
    public Response doGetRequestWithQueryParam(String res, Map<String, String> params) {
        Response response = given()
                                .queryParams(params)
                            .when()
                                .get(res);
        return response;
    }
    public Response doPostRequestWithQueryParam(String res, Map<String, String> params) {
        Response response = given()
                                .queryParams(params)
                            .when()
                                .post(res);
        return response;
    }

    public Response doGetRequestWithHeader(String res, Map<String, String> headers) {
        Response response = given()
                                .headers(headers)
                            .when()
                                .get(res);
        return response;
    }

    public Response doPutRequest(String res , Object body) {
        Response response = given()
                            .when()
                                .body(body)
                                .put(res);
        return response;
    }

    public Response doPatchRequestWithToken(String res , String token, Object body, ExtentTest test) {
        Response response = given()
                            .contentType(ContentType.JSON)
                            .header(new Header("Authorization", "Bearer " + token))
                            .when()
                                .body(body)
                                .patch(res);
        test.log(Status.INFO,"Headers are :: "+ContentType.JSON+","+token);
        return response;
    }
    
    public Response doDeleteRequest(String url, ExtentTest test) {
    	setRequestURIinExtentReport(url, test);
        Response response = given()
                            .when()
                                .delete(url);
        return response;

    }
    
    public Response doDeleteRequestWithToken(String url,String token, ExtentTest test) {
    	setRequestURIinExtentReport(url, test);
        Response response = given()
                            .when()
                            .header(new Header("Authorization", "Bearer " + token))
                                .delete(url);
        return response;

    }
    
    public static void setRequestURIinExtentReport(String requestPayload, ExtentTest log) {
		String reqURI = "<b> Request URI ::" + "<b>" + baseUrl + requestPayload;
		Markup m = MarkupHelper.createLabel(reqURI, ExtentColor.TRANSPARENT);
		log.log(Status.INFO, m);
	}
    
    public static void setRequestHeaderinExtentReport(String header, ExtentTest log) {
		String reqHeader = "<b> Request Header ::" + "<b>" + "accesstoken : "+header;
		Markup m = MarkupHelper.createLabel(reqHeader, ExtentColor.TRANSPARENT);
		log.log(Status.INFO, m);
	}
    
    
}
