package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name="product")

public class product {
	@Id
	private int pid;
	private int qty;
	private String description;
	private String name;
	private int price;
	@Lob
	@Column(columnDefinition="LONGBLOB")
	private byte[] pimg;
	public byte[] getPimg() {
		return pimg;
	}
	public void setPimg(byte[] pimg) {
		this.pimg = pimg;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
}
