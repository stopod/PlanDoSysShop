package com.example.app.controllers.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class IndexSearchForm implements Serializable {
	
	public String genre;
	
	public String isbn;
	
	public String author;
	
	public String title;
	
	public String receiptAllFlg;

}
