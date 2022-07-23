package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.EndPoints;
import com.pojo.AddAdress_Output_Pojo;
import com.pojo.Address_Input_Pojo;
import com.pojo.ChangeProfilePic_Output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.GetAddress_Output_Pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class OMRBranchClub extends BaseClass {
	String logtoken;
	public static String address_id;

	@Test(priority = 1)
	public void createUser() throws FileNotFoundException, IOException {
		String propertyFileValues = getPropertyFileValue("username");
		System.out.println(propertyFileValues);
		addHeader("accept", "application/json");
		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));
		Response response = requestType("POST", EndPoints.POSTMANBASICAUTHLOGIN);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Login_Output_Pojo output_pojo = response.as(Login_Output_Pojo.class);
		String first_name = output_pojo.getData().getFirst_name();
		System.out.println(first_name);
		logtoken = output_pojo.getData().getLogtoken();
		Assert.assertEquals(statusCode, 200, "verify status code");
		Assert.assertEquals(first_name, "Jeni", "verify first Name");

	}

	@Test(priority = 2)
	public void addAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);
		// Add payload
		Address_Input_Pojo addAddress = new Address_Input_Pojo("jeni", "s", "7418438762", "19/23 appartment", 35, 3659,
				101, "600089", "chennai nagar", "home");
		addBody(addAddress);
		Response response = requestType("POST", EndPoints.ADDUSERADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		AddAdress_Output_Pojo addAdress_Output_Pojo = response.as(AddAdress_Output_Pojo.class);
		String message = addAdress_Output_Pojo.getMessage();
		int id = addAdress_Output_Pojo.getAddress_id();
	   address_id = String.valueOf(id);
		
		Assert.assertEquals(statusCode, 200, "verify status code");
		Assert.assertEquals(message, "Address added successfully", "verify Address added succesfully");

	}
@Test(priority=4)
		public void updateAddress() {
			List<Header> h = new ArrayList<>();
			Header h1 = new Header("accept", "application/json");
			Header h2 = new Header("Authorization", "Bearer "+logtoken);
			Header h3 = new Header("Content-Type", "application/json");
			h.add(h1);
			h.add(h2);
			h.add(h3);
			addHeaders(h);
			UpdateAddress_Input_Pojo updateAddress_input_pojo = new UpdateAddress_Input_Pojo(address_id, "jeni", "s", "7418438762", "19/23 appartment", 35, 3659, 101, "600089", "chennai nagar", "home");
			addBody(updateAddress_input_pojo);
			Response response = requestType("PUT", EndPoints.UPDATEADDRESS);
			int statusCode = getStatusCode(response);
			System.out.println(statusCode);
			Assert.assertEquals(statusCode,200, "verify status code");
			UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
			String message = updateAddress_Output_Pojo.getMessage();
			Assert.assertEquals(message, "Address updated successfully", "verify Address updated successfully");
			
		}
@Test(priority=5)
public void deleteAddress() {
List<Header> h = new ArrayList<>();
	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Authorization", "Bearer "+logtoken);
	Header h3 = new Header("Content-Type", "application/json");
	h.add(h1);
	h.add(h2);
	h.add(h3);
	addHeaders(h);
	DeleteAddress_Input_Pojo deleteAddress_input_pojo = new DeleteAddress_Input_Pojo(address_id);
	addBody(deleteAddress_input_pojo);
	Response response = requestType("DELETE", EndPoints.DELETEADDRESS);
	int statusCode = getStatusCode(response);
	System.out.println(statusCode);
	Assert.assertEquals(statusCode,200, "verify status code");
	UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
	String message = updateAddress_Output_Pojo.getMessage();
	Assert.assertEquals(message, "Address deleted successfully", "verify Address deleted successfully");
	
	
}
@Test(priority=6)
	public void getAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		addHeaders(h);
		Response response = requestType("GET", EndPoints.GETADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode,200, "verify status code");
		GetAddress_Output_Pojo getAddress_Output_Pojo = response.as(GetAddress_Output_Pojo.class);
		String message = getAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message,"OK","verify OK");
		
		}
@Test(priority=7)
public void changeProfile() throws FileNotFoundException, IOException {
	List<Header> h = new ArrayList<>();
	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Authorization", "Bearer "+logtoken);
	Header h3 = new Header("Content-Type", "multipart/form-data");
	h.add(h1);
	h.add(h2);
	h.add(h3);
	addHeaders(h);
	formData(getPropertyFileValue("path"));
	Response response = requestType("POST", EndPoints.CHANGEPROFILEPIC);
	int statusCode = getStatusCode(response);
	System.out.println(statusCode);
	Assert.assertEquals(statusCode, 200, "verify status code");
	ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
	
	String message = changeProfilePic_Output_Pojo.getMessage();
	Assert.assertEquals(message,"Profile updated Successfully", "verify Profile updated Successfully");

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	

}
