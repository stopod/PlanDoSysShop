package com.example.app.domain.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mt_library")
@Getter
@Setter
@SuppressWarnings("serial")
public class LibraryEntity implements Serializable {
	
	@Id
	public String isbn;
	
	public String title;
	
	public String genre;
	
	public String author;
	
	public String publisher;
	
	public String year;
	
	public int price;
	
	public int stock;
}
