package com.example.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class createpdf {
	

public void getpdf() throws Exception  {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("inv.pdf"));

	document.open();
	final Font SUBFONT = new Font(Font.getFamily("TIMES_ROMAN"), 26,    Font.BOLD|Font.UNDERLINE);
Chunk a=new Chunk("invoice",SUBFONT);
Phrase b=new Phrase();
b.add(a);
Paragraph c=new Paragraph();
c.add(b);
c.setAlignment(Element.ALIGN_CENTER);
document.add(c);
document.add(Chunk.NEWLINE);
document.add(Chunk.NEWLINE);

	PdfPTable table = new PdfPTable(4);
	addTableHeader(table);
//	addRows(table);
//	addCustomRows(table);

	document.add(table);
	document.close();
}
@Autowired
private cartDAO p;
@Autowired
private productDAO pp;
private void addTableHeader(PdfPTable table)

{
    Stream.of("serial no.", "product name","qty", "price")
      .forEach(columnTitle -> {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(columnTitle));
        table.addCell(header);
    });
  
//    String[][] tble= {{"serial no","product name"},{"qty","price"}};



List<cart> crt=p.findAll();
int i=0;
int t=0;
for(cart c:crt) {
	Optional<product> ppp=pp.findById(c.getPid());
	product cc=ppp.get();
	i++;
	table.addCell(Integer.valueOf(i).toString());
	table.addCell(cc.getName());
	table.addCell(Integer.valueOf(c.getQty()).toString());
	table.addCell(Integer.valueOf(cc.getPrice()).toString());
	t=t+cc.getPrice()*c.getQty();
	
}
table.addCell("");
table.addCell("");
table.addCell("total price");
table.addCell(Integer.valueOf(t).toString());


//for(cart c:crt) {
//	Optional<product> ppp=pp.findById(c.getPid());
//	product cc=ppp.get();
//	table.addCell();
//
//}
}//end funcction









//private void addRows(PdfPTable table) {
//    table.addCell("row 1, col 1");
//    table.addCell("row 1, col 2");
//    table.addCell("row 1, col 3");
//    table.addCell("row 1, col 4");
//}
private void addCustomRows(PdfPTable table) 
		  throws URISyntaxException, BadElementException, IOException {
		    Path path = Paths.get(ClassLoader.getSystemResource("teddu.jpg").toURI());
		    Image img = Image.getInstance(path.toAbsolutePath().toString());
		    img.scalePercent(10);

		    PdfPCell imageCell = new PdfPCell(img);
		    table.addCell(imageCell);

		    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
		    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(horizontalAlignCell);

		    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
		    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		    table.addCell(verticalAlignCell);
		}
}
