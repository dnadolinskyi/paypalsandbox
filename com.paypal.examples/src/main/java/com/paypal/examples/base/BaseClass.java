package com.paypal.examples.base;

//Static imports
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class BaseClass {
	protected static String accessToken;
	
	public static final String clientId="clientId1111";
	public static final String clientSecret="clientSecret1111";
	
	@BeforeClass
	public static void getToken(){		
		System.out.println("----------------Test Start----------------");
		System.out.println("----------------GET AccessToken----------------");
		RestAssured.baseURI="https://api.sandbox.paypal.com";
		RestAssured.basePath="/v1/oauth2/token";
		
	accessToken = given()
		.parameters("grant_type","client_credentials")
		.auth()
		.preemptive()
		.basic(clientId, clientSecret)
		.when()
		.post()
		.then()
		.log()
		.all()
		.extract()
		.path("access_token");
	
	System.out.println("The token is: "+accessToken);
	System.out.println("----------------Test End----------------");
	}
	
	@AfterClass
	public static void TearDown(){
		RestAssured.reset();
	}
}
