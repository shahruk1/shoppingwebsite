package com.example.demo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





@Component("ps")
public class ProductService {
	@Autowired
	private productDAO productDAO;

	public productDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(productDAO productDAO) {
		this.productDAO = productDAO;
	}
	public void createProduct() throws IOException {
		product p=new product();
		BufferedImage bimg;
		ByteArrayOutputStream bos;
		byte[] data;
		
		p.setPid(1);
		p.setName("shampoo");
		p.setDescription("to cleanse ur pet furr");
		p.setQty(5);
		p.setPrice(250);
		bimg=ImageIO.read(new File("catshampoo.jpg"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", bos);
		data=bos.toByteArray();
		p.setPimg(data);
		productDAO.save(p);
		
		p=new product();
		p.setPid(2);
		p.setName("soap");
		p.setDescription("to cleanse body of ur pet");
		p.setQty(20);
		p.setPrice(100);
		bimg=ImageIO.read(new File("catsoap.jpg"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", bos);
		data=bos.toByteArray();
		p.setPimg(data);
		productDAO.save(p);
		
		p=new product();
		p.setPid(3);
		p.setName("brush");
		p.setDescription("to brush body of ur pet");
		p.setQty(30);
		p.setPrice(150);
		bimg=ImageIO.read(new File("catbrush.jpg"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", bos);
		data=bos.toByteArray();
		p.setPimg(data);
		productDAO.save(p);
	}
	public void findProduct(int id)
	{
		Optional<product> optional=productDAO.findById(Integer.valueOf(id));
		if(!optional.isEmpty())
		{
			product product=optional.get();
			System.out.println(product.getPid()+" "+product.getName()+" "+product.getDescription()+" "+product.getQty());
			ByteArrayInputStream bis=new ByteArrayInputStream(product.getPimg());
			try {
				BufferedImage bimg=ImageIO.read(bis);
				ImageIO.write(bimg,"jpg", new File("output.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void getAllProducts() {
		List<product> list=productDAO.findAll();
		for(product p:list) {
			System.out.println(p.getPid()+" "+p.getName()+" "+p.getDescription()+" "+p.getQty());
		}
	}
}
