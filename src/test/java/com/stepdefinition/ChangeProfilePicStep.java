package com.stepdefinition;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.pojo.ChangeProfilePic_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import io.restassured.http.Header;
import io.restassured.response.Response;



public class ChangeProfilePicStep extends BaseClass{
	
	Response response;
@Given("User add headers and bearer authorization and multipart\\/form-data for accessing changeprofilepic endpoints")
public void userAddHeadersAndBearerAuthorizationAndMultipartFormDataForAccessingChangeprofilepicEndpoints() throws FileNotFoundException, IOException {
	List<Header> h = new ArrayList<>();
	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Authorization", "Bearer "+LoginStep.logtoken);
	Header h3 = new Header("Content-Type", "multipart/form-data");
	h.add(h1);
	h.add(h2);
	h.add(h3);
	addHeaders(h);
	formData(getPropertyFileValue("path"));
	System.out.println(getPropertyFileValue("path"));
   
}

@Given("User send {string} request for changeprofilepic")
public void userSendRequestForChangeprofilepic(String string) {
response = requestType(string, EndPoints.CHANGEPROFILEPIC);
   
}

@Then("User verify the changeprofilepic response message matches {string}")
public void userVerifyTheChangeprofilepicResponseMessageMatches(String expProfilePicMsg) {
ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
	
	String message = changeProfilePic_Output_Pojo.getMessage();
	Assert.assertEquals("Verify Profile updated successfully", expProfilePicMsg,message);
	
	
	
    
}	
	

}
