package com.example.app.controllers.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class IndexDeliveryForm implements Serializable {
	
	public String isbn;
	
	public int price;
	
	public String title;
	
	public String orderNumber;

}
