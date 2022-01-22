package com.example.demo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/homepage")
public class ProductController {
	@Autowired
	ProductService ps;
	@Autowired
	CartService cs;
	public ProductService getPs() {
		return ps;
	}
	public void setPs(ProductService ps) {
		this.ps = ps;
	}
	@RequestMapping("/display")
	public ModelAndView dispProduct(HttpServletRequest request) throws Exception{
		ModelAndView mav=new ModelAndView();
		product p=null;
		mav.setViewName("product");
		ps.createProduct();
		List<product> op=ps.getProductDAO().findAll();
//		File f;
		ByteArrayInputStream bis;
		BufferedImage bm;
		if(!op.isEmpty())
		{
			Iterator<product> l=op.iterator();
			int i=0;
			while(l.hasNext())
			{	
					i++;
					p=l.next();
				
				//mav.addObject("myProduct",p);
//				f=null;
				
				bis=new ByteArrayInputStream(p.getPimg());
				try {
					bm = ImageIO.read(bis);
					String fileName="pr"+String.valueOf(p.getPid())+".jpg";
					//System.out.println(fileName);
					String fileLocation = new File("src\\main\\resources\\static").getAbsolutePath() + "\\" + fileName;
					//f=new File("output.jpg");
					FileOutputStream fout=new FileOutputStream(fileLocation);
					//System.out.println(fileLocation);
					ImageIO.write(bm,"jpg",fout);
					//fout.write(bm);
					fout.close();
					mav.addObject("pid"+i,p.getPid());
					mav.addObject("pname"+i,p.getName());
					mav.addObject("imgName"+i,"/pr"+p.getPid()+".jpg");
					mav.addObject("pdesp"+i,p.getDescription());
					mav.addObject("price"+i,p.getPrice());
				}
				 catch (IOException e) {

					e.printStackTrace();
				}
				
			}
			mav.addObject("noOfProducts",i);
		}
		
		System.out.println("yes");
		return mav;	
		}
	@RequestMapping(path="/add" ,params={"pid","qty"},method=RequestMethod.POST)
	public void addtoCart(@RequestParam(name="pid") int id,@RequestParam(name="qty")int qty) {
		cs.Store(id, qty);
	}
}
