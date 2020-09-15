package com.example.app.domain.dto;

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
public class SelectByOrderDto implements Serializable {
	
	@Column(name = "isbn")
	public String isbn;
	
	@Column(name = "title")
	public String title;
	
	@Column(name = "author")
	public String author;
	
	@Column(name = "stock")
	public String stock;
	
	@Column(name = "price")
	public int price;
	
	@Column(name = "order_status")
	public String orderStatus;
	
	@Column(name = "order_date")
	public String orderDate;
	
	@Column(name = "order_select")
	public String orderSelect;
	
	@Column(name = "delivery_select")
	public String deliverySelect;
	
	@Column(name = "order_number")
	public String orderNumber;
}
