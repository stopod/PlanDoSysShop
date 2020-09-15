package com.example.app.controllers.forms;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class ConfirmDeliveryForm implements Serializable {
	
	public String isbn;
	
	public int price;
	
	public String title;
	
	public String orderNumber;
	
	public int orderQuantity;
	
	public int deliveryQuantity;
	
	public int confirmDeliveryQuantity;
	
	public String confirmDeliveryDate;
	
	public String confirmRecipient;

}
