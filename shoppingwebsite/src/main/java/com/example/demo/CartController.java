package com.example.demo;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/cart")
public class CartController {
	@Autowired
	ProductService ps;
	@Autowired
	CartService cs;
	@Autowired
	emailsender es;
	@Autowired
	createpdf cp;
	@Autowired
	ExcelReporter er;
	@RequestMapping(path="/show")
	public ModelAndView showItems() throws Exception {
		ModelAndView mav=new ModelAndView();
		product p=null;
		cart c=null;
		mav.setViewName("cart");
		//ps.createProduct();
		List<cart> op=cs.getAllItems();
		//File f;
		//ByteArrayInputStream bis;
		//BufferedImage bm;
		int i=0;
		if(!op.isEmpty())
		{
			Iterator<cart> l=op.iterator();
			
			while(l.hasNext())
			{	
					i++;
					c=l.next();
					p=ps.getProductDAO().getById(c.getPid());
				//mav.addObject("myProduct",p);
				//f=null;
				
				////bm = ImageIO.read(bis);
				////String fileName="pr"+String.valueOf(p.getPid())+".jpg";
					//System.out.println(fileName);
				////String fileLocation = new File("src\\main\\resources\\static").getAbsolutePath() + "\\" + fileName;
					//f=new File("output.jpg");
				////FileOutputStream fout=new FileOutputStream(fileLocation);
					//System.out.println(fileLocation);
				////ImageIO.write(bm,"jpg",fout);
					//fout.write(bm);
				////fout.close();
					mav.addObject("pid"+i,p.getPid());
					mav.addObject("pname"+i,p.getName());
					mav.addObject("imgName"+i,"/pr"+p.getPid()+".jpg");
					mav.addObject("pdesp"+i,p.getDescription());
					mav.addObject("price"+i,p.getPrice());
					mav.addObject("qty"+i,c.getQty());
			}
			
		}
		mav.addObject("noOfProducts",i);
		//System.out.println("yes");
		return mav;
	}
	@RequestMapping(path="/remove",params= {"pid"},method=RequestMethod.POST)
	public void removeItem(@RequestParam(name="pid")int id) {
		cs.deleteItem(id);
	}
	@RequestMapping(path="/pay",params= {"name","phoneno","emailid"},method=RequestMethod.POST)
	public void pay(HttpServletResponse response,@RequestParam(name="name")String name,@RequestParam(name="phoneno")String phoneno,@RequestParam(name="emailid")String emailid) throws Exception {
		//er.createExcel();
		er.writeExcel("temp.xlsx");
		cp.getpdf();
		es.sendmail(emailid);
		cs.deleteAll();
		response.sendRedirect("/homepage/display");
		//sms.sendSms(phoneno);
}
}
