package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.pojo.Login_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LoginStep extends BaseClass {

	static Response response;
	static String logtoken;

	@Given("User add header")
	public void userAddHeader() {
		addHeader("accept", "application/json");

	}

	@Given("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {
		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));

	}

	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String string) {
		response = requestType(string, EndPoints.POSTMANBASICAUTHLOGIN);

	}

	@Then("User verify the login response body firstName present as {string} and get the logtoken")
	public void userVerifyTheLoginResponseBodyFirstNamePresentAsAndGetTheLogtoken(String expFirstName) {
		Login_Output_Pojo output_pojo = response.as(Login_Output_Pojo.class);
		logtoken = output_pojo.getData().getLogtoken();
		System.out.println(logtoken);
		String first_name = output_pojo.getData().getFirst_name();
		Assert.assertEquals("verify first Name", expFirstName, first_name);

	}

}
