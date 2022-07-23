package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	static RequestSpecification reqSpec;
	public static Response response;

	public  void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);

	}

	public void pathParam(String key, String value) {

		reqSpec = reqSpec.pathParam(key, value);
	}

	public void queryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);

	}

	public  Response requestType(String reqType, String endPoint) {

		switch (reqType) {
		case "GET":
			response = reqSpec.log().all().get(endPoint);

			break;
		case "POST":
			response = reqSpec.log().all().post(endPoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endPoint);

			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endPoint);
			break;

		default:
			break;
		}
		return response;

	}

	public static int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}

	public ResponseBody getResBody(Response response) {

		ResponseBody body = response.getBody();
		return body;

	}

	public String resBodyAsString(Response response) {
		String asString = getResBody(response).asString();
		return asString;

	}

	public String getResBodyAsPretty() {
		String asPrettyString = getResBody(response).asPrettyString();
		return asPrettyString;

	}

	public void basicAuth(String username, String password) {

		reqSpec = reqSpec.auth().preemptive().basic(username, password);

	}

	public String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "/config.properties"));
		String value = (String) properties.get(key);
		return value;

	}

	// public static String getPropertyFileValue(String key) throws IOException {
	// FileInputStream stream = new FileInputStream(System.getProperty("user.dir") +
	// "/config.properties");
	// Properties properties = new Properties();
	// properties.load(stream);
	// Object name = properties.get(key);
	// String value = (String) name;
	// return value;
	//
	// }
	public void addHeaders(List<Header> h) {
		Headers headers = new Headers(h);
		reqSpec = RestAssured.given().headers(headers);

	}

	public void addBody(Object payload) {
		reqSpec = reqSpec.body(payload);

	}
	
	public void formData(String imagePath) {
		reqSpec = reqSpec.multiPart("profile_picture", new File(imagePath));

	}
	
	
	
	
	
}

