package com.example.app.domain.entity;

import java.io.Serializable;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mt_delivery")
@Getter
@Setter
@SuppressWarnings("serial")
public class DeliveryEntity implements Serializable {
	
	@Column(name = "order_number")
	public String orderNumber;
	
	@Column(name = "delivery_date")
	public String deliveryDate;
	
	@Column(name = "delibery_quantity")
	public int deliberyQuantity;
	
	@Column(name = "recipient")
	public String recipient;
	
}
