/*
 * File: MathResponse.java Date: 05-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.payment.api.response;

/**
 * @author Dimit Chadha
 */
public class PaymentResponse extends AbstractResponse {

	private int amount;
	private String itemCode;
	private String itemName;
	private int quantity;
	private int price;

	public PaymentResponse() {
		super();
	}

	/**
	 * @param itemCode
	 * @param itemName
	 * @param quantity
	 * @param price
	 */
	public PaymentResponse(String itemCode, String itemName, int quantity, int price) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}

	public PaymentResponse(ResponseStatus status, ResponseReason reason, int amount) {
		this(status, reason, null, amount);
	}

	public PaymentResponse(ResponseStatus status, ResponseReason reason, String message, int amount) {
		super(status, reason, message);
		this.amount = amount;
	}

	/**
	 * @return the answer
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentResponse [itemCode=" + itemCode + ", itemName=" + itemName + ", quantity=" + quantity + ", price=" + price
				+ ", amount=" + amount + super.toString() + "]";
	}

}
