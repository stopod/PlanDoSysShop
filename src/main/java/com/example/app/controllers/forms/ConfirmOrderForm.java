package com.example.app.controllers.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class ConfirmOrderForm implements Serializable {
	
	public String isbn;
	
	public int orderQuantity;

}
