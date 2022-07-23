package com.stepdefinition;


import org.junit.Assert;

import com.base.BaseClass;

import cucumber.api.java.en.Then;



public class CommonStep extends BaseClass {
	
	@Then("User verify the status code is {int}")
	public void userVerifyTheStatusCodeIs(int responseCode) {
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals("Verify first name", responseCode,statusCode);
		
		
}
	

}
