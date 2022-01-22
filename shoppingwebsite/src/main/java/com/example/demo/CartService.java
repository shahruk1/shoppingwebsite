package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service("cartService")
public class CartService {
@Autowired
private cartDAO cd;

public cartDAO getCd() {
	return cd;
}

public void setCd(cartDAO cd) {
	this.cd = cd;
}
public List<cart> getAllItems() {
	return(cd.findAll());
}
public cart findItem(int pid) {
	
	return(cd.getById(pid));
}
public void Store(int pid,int qty) {
	if(qty!=0)
	{
	cart c=new cart();
	c.setPid(pid);
	c.setQty(qty);
	cd.save(c);
	}
}
public void deleteItem(int pid) {
	cd.deleteById(pid);
}
public void deleteAll() {
	cd.deleteAll();
}
}
