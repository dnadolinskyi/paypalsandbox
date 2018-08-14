package com.paypal.examples.tests;

//Static imports
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import groovyjarjarantlr.debug.TraceAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;

import org.junit.Test;

import com.paypal.examples.base.BaseClass;
import com.paypal.examples.payment.pojo.Amount;
import com.paypal.examples.payment.pojo.Details;
import com.paypal.examples.payment.pojo.Item_List;
import com.paypal.examples.payment.pojo.Items;
import com.paypal.examples.payment.pojo.Payer;
import com.paypal.examples.payment.pojo.Payment_Options;
import com.paypal.examples.payment.pojo.PostObj;
import com.paypal.examples.payment.pojo.Redirect_urls;
import com.paypal.examples.payment.pojo.Shipping_Address;
import com.paypal.examples.payment.pojo.Transactions;

public class PostAsObject extends BaseClass{

	static String paymentId;
	
	@Test
	public void createAPayment(){
		System.out.println("----------------Test Started----------------");
		System.out.println("----------------POST A TRNX----------------");
		Redirect_urls red_url = new Redirect_urls();
		red_url.setCancel_url("https://example.com/cancel");
		red_url.setReturn_url("https://example.com/return");
		
		//Set the amount details
		Details details = new Details();
		details.setHandling_fee("1.00");
		details.setInsurance("0.01");
		details.setShipping("0.03");
		details.setShipping_discount("-1.00");
		details.setSubtotal("30.00");
		details.setTax("0.07");
		
		//Set the amount
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setDetails(details);
		amount.setTotal("30.11");
		
		//Set the shipping address
		Shipping_Address shipping_address = new Shipping_Address();
		shipping_address.setRecipient_name("Brian Robinson");
	    shipping_address.setLine1("4th Floor");
	    shipping_address.setLine2("Unit #34");
	    shipping_address.setCity("San Jose");
	    shipping_address.setCountry_code("US");
	    shipping_address.setPostal_code("95131");
	    shipping_address.setPhone("011862212345678");
	    shipping_address.setState("CA");
	    
	    //Set the items
	    Items items = new Items();
	    items.setName("hat");
	    items.setDescription("Brown hat.");
	    items.setQuantity("5");
	    items.setPrice("3");
	    items.setTax("0.01");
	    items.setSku("1");
	    items.setCurrency("USD");
	    
	    Items items2 = new Items();
	    items2.setName("handbag");
	    items2.setDescription("Black handbag.");
	    items2.setQuantity("1");
	    items2.setPrice("15");
	    items2.setTax("0.02");
	    items2.setSku("product34");
	    items2.setCurrency("USD");
	   
	    List<Items> itemslist = new ArrayList<>(); 
	    itemslist.add(items);
	    itemslist.add(items2);
	    
	    //Item list
	    Item_List item_list = new Item_List();
	    item_list.setShipping_address(shipping_address);
	    item_list.setItems(itemslist);
		
		//Set payment options
	    Payment_Options payment_options = new Payment_Options();
	    payment_options.setAllowed_payment_method("INSTANT_FUNDING_SOURCE");
	    
	    //Set Transactions
	    Transactions transaction = new Transactions();
	    transaction.setAmount(amount);
	    transaction.setCustom("EBAY_EMS_90048630024435");
	    transaction.setDescription("The payment transaction description.");
	    transaction.setInvoice_number("48787589673");
	    transaction.setItem_list(item_list);
	    transaction.setPayment_options(payment_options);
	    transaction.setSoft_descriptor("ECHI5786786");
	    
	    //Transactions lit
	    List<Transactions> trnx_list = new ArrayList<>();
	    trnx_list.add(transaction);
	    
	    //Set Payer
	    Payer payer = new Payer();
	    payer.setPayment_method("paypal");
	    
	    //Set the final post object
	    PostObj postObj = new PostObj();
	    postObj.setIntent("sale");
	    postObj.setNote_to_payer("Contact us for any questions on your order.");
	    postObj.setPayer(payer);
	    postObj.setRedirect_urls(red_url);
	    postObj.setTransactions(trnx_list);
	    
	    RestAssured.baseURI="https://api.sandbox.paypal.com";
	    RestAssured.basePath="/v1/payments/payment";
	    
		paymentId = given()
		.contentType(ContentType.JSON)
		.auth()
		.oauth2(accessToken)
		.when()
		.body(postObj)
		.post()
		.then()
		.log()
		.all()
		.extract()
		.path("id");
		System.out.println("The payment id is:"+paymentId);
		System.out.println("----------------Test End----------------");
	}
	
	@Test
	public void getPaymentDetails(){
		System.out.println("----------------Test Started----------------");
		System.out.println("----------------GET THE PAYMENT DETAILS----------------");
		RestAssured.baseURI="https://api.sandbox.paypal.com";
	    RestAssured.basePath="/v1/payments/payment/"+paymentId;
	    
		
	    given()
		.auth()
		.oauth2(accessToken)
		.when()
		.get()
		.then()
		.log()
		.all();
		
		System.out.println("----------------Test End----------------");
	}
}
