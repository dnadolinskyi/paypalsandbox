package com.paypal.examples.payment.pojo;

public class Transactions {

	private Amount amount;
	private String description;
	private String custom;
	private String invoice_number;
	private Payment_Options payment_options;
	private String soft_descriptor;
	private Item_List item_list;
	
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	public Payment_Options getPayment_options() {
		return payment_options;
	}
	public void setPayment_options(Payment_Options payment_options) {
		this.payment_options = payment_options;
	}
	public String getSoft_descriptor() {
		return soft_descriptor;
	}
	public void setSoft_descriptor(String soft_descriptor) {
		this.soft_descriptor = soft_descriptor;
	}
	public Item_List getItem_list() {
		return item_list;
	}
	public void setItem_list(Item_List item_list) {
		this.item_list = item_list;
	}
	
	
}
