package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.pojo.AddAdress_Output_Pojo;
import com.pojo.Address_Input_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.GetAddress_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddressStep extends BaseClass {
	String logtoken= LoginStep.logtoken;
	public static String address_id;
	static Response response;

	@Given("User add headers and bearer authorization for accessing address endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingAddressEndpoints() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

	}

	@When("User add request body for Add new address {string},{string},{string},{string},{string},{string},{string},{string},{string}and{string}")
	public void userAddRequestBodyForAddNewAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		Integer state1 = Integer.valueOf(state);
		Integer city1 = Integer.valueOf(city);
		Integer country1 = Integer.valueOf(country);
		Address_Input_Pojo addAddress = new Address_Input_Pojo(first_name, last_name, mobile, apartment, state1, city1,
				country1, zipcode, address, address_type);
		addBody(addAddress);

	}

	@When("User send {string} request for add new address")
	public void userSendRequestForAddNewAddress(String string) {
		 response = requestType("POST", EndPoints.ADDUSERADDRESS);

	}

	@Then("User verify the create address response message matches {string}")
	public void userVerifyTheCreateAddressResponseMessageMatches(String expAddressMsg) {
		AddAdress_Output_Pojo addAdress_Output_Pojo = response.as(AddAdress_Output_Pojo.class);
		int id = addAdress_Output_Pojo.getAddress_id();
		address_id = String.valueOf(id);
		System.out.println(address_id);
		String message = addAdress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Address successfully", expAddressMsg, message);

	}

	@When("User add request body for update existing address{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}and{string}")
	public void userAddRequestBodyForUpdateExistingAddressAnd(String addressid, String first_name, String last_name,
			String mobile, String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {

		{
			Integer state2 = Integer.valueOf(state);
			Integer city2 = Integer.valueOf(city);
			Integer country2 = Integer.valueOf(country);
			UpdateAddress_Input_Pojo updateAddress_input_pojo = new UpdateAddress_Input_Pojo(address_id, first_name, last_name, mobile, apartment, state2, city2,
					country2, zipcode, address, address_type);
			addBody(updateAddress_input_pojo);

		}
	}

	@When("User send {string} request to update the existing address")
	public void userSendRequestToUpdateTheExistingAddress(String string) {
		 response = requestType("PUT", EndPoints.UPDATEADDRESS);

	}

	@Then("User verify the update address response message matches {string}")
	public void userVerifyTheUpdateAddressResponseMessageMatches(String expUpdateMsg) {
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify UpdateAddress successfully", expUpdateMsg,message);

	}

	@Given("User send {string} request to delete the address")
	public void userSendRequestToDeleteTheAddress(String string) {
		 response = requestType("DELETE", EndPoints.DELETEADDRESS);

	}

	@When("User send request body to delete address")
	public void userSendRequestBodyToDeleteAddress() {
		DeleteAddress_Input_Pojo deleteAddress_input_pojo = new DeleteAddress_Input_Pojo(address_id);
		addBody(deleteAddress_input_pojo);

	}

	@Then("User verify the delete address response message matches {string}")
	public void userVerifyTheDeleteAddressResponseMessageMatches(String expDeleteAddressMsg) {
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify DeleteAddress successfully", expDeleteAddressMsg,message);
	}

	@When("User send the {string} request to get the existing address")
	public void userSendTheRequestToGetTheExistingAddress(String string) {
		 response = requestType("GET", EndPoints.GETADDRESS);

	}

	@Then("User verify the get address response message matches {string}")
	public void userVerifyTheGetAddressResponseMessageMatches(String expGetAddressMsg) {
		GetAddress_Output_Pojo getAddress_Output_Pojo = response.as(GetAddress_Output_Pojo.class);
		String message = getAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify GetAddress successfully", expGetAddressMsg,message);
		

	}

}
