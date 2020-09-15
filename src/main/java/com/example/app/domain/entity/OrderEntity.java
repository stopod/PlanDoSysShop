package com.example.app.domain.entity;

import java.io.Serializable;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mt_order")
@Getter
@Setter
@SuppressWarnings("serial")
public class OrderEntity implements Serializable {
	
	
	@Column(name = "isbn")
	public String isbn;
	
	@Id
	@Column(name = "order_number")
	public String orderNumber;
	
	@Column(name = "order_date")
	public String orderDate;
	
	@Column(name = "order_quantity")
	public int orderQuantity;
	
	@Column(name = "receipt_all_flg")
	public int receiptAllFlg;

}
