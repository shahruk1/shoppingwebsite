package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.cart;
import com.example.demo.product;
import com.example.demo.cartDAO;
import com.example.demo.productDAO;
@Transactional
@Component
public class ExcelReporter {
	int noOfColumns=5;
	@Autowired
	cartDAO cd;
	@Autowired
	productDAO pd;
 public void readExcel(String fileLocation) throws Exception {
	 FileInputStream file = new FileInputStream(new File(fileLocation));
	 Workbook workbook = new XSSFWorkbook(file);
	 Sheet sheet = workbook.getSheetAt(0);
	 Map<Integer, List<String>> data = new HashMap<>();
	 int i = 0;
	 for (Row row : sheet) {
	     data.put(i, new ArrayList<String>());
	     for (Cell cell : row) {
	         switch (cell.getCellType()) {
	             case STRING: data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString()); break;
	             case NUMERIC: if (DateUtil.isCellDateFormatted(cell)) {
	            	    data.get(i).add(cell.getDateCellValue() + "");
	             } else {
	                 data.get(i).add(cell.getNumericCellValue() + "");
	             } break;
	             case BOOLEAN:data.get(i).add(cell.getBooleanCellValue() + "");  break;
	             case FORMULA: data.get(i).add(cell.getCellFormula() + ""); break;
	             default: data.get(Integer.valueOf(i)).add(" ");
	         }
	        
	     }
	     i++;
	 }
	 file.close();
	 workbook.close();
//	 for(i=0;i<=sheet.getLastRowNum();i++)
//	 {
//		 for(int j=0;j<noOfColumns;j++)
//			  System.out.println(data.get(i).get(j));
//	 }
 }
 public void createExcel() throws Exception {
	 int i;
	 String headerName[]= {"Product Id","Product Name","Cost/unit","Purchased(qty)","Total"};
	 Workbook workbook = new XSSFWorkbook();
	 Sheet sheet = workbook.createSheet("Persons");
	 sheet.setColumnWidth(0, 6000);
	 sheet.setColumnWidth(1, 4000);
	 Row header = sheet.createRow(0);
	 Row footer=sheet.createRow(1);
	 CellStyle headerStyle = workbook.createCellStyle();
	 headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	 headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	 XSSFFont font = ((XSSFWorkbook) workbook).createFont();
	 font.setFontName("Arial");
	 font.setFontHeightInPoints((short) 16);
	 font.setBold(true);
	 headerStyle.setFont(font);
	 Cell headerCell;
	 Cell footerCell;
	 String footerValues[]= {" "," "," ","Total"};
	 for(i=0;i<noOfColumns;i++)
	 {
	 headerCell = header.createCell(i);
	 headerCell.setCellValue(headerName[i]);
	 headerCell.setCellStyle(headerStyle);
	 }
	 for(i=0;i<noOfColumns-1;i++)
	 {
	 footerCell = footer.createCell(i);
	 footerCell.setCellValue(footerValues[i]);
	 footerCell.setCellStyle(headerStyle);
	 }
	 Cell cell = footer.createCell(4);
	 cell.setCellFormula("SUM(E2:E2)");
	 
	 CellStyle style = workbook.createCellStyle();
	 style.setWrapText(true);
	
	// File currDir = new File(".");
	 //String path = currDir.getAbsolutePath();
	  //path.substring(0, path.length() - 1) +
	 String fileLocation ="temp.xlsx";
	 FileOutputStream outputStream = new FileOutputStream(fileLocation);
	 workbook.write(outputStream);
	 outputStream.close();
	 workbook.close();
 }
 public void writeExcel(String fileLocation) throws Exception {
	 FileInputStream file = new FileInputStream(new File(fileLocation));
	 int i;
	 Cell headerCell;
	 List<String> newValues=new ArrayList<String>();
	 Workbook workbook = new XSSFWorkbook(file);
	 Sheet sheet = workbook.getSheetAt(0);
	 file.close();
	
	 CellStyle headerStyle = workbook.createCellStyle();
	 XSSFFont font = ((XSSFWorkbook) workbook).createFont();
	 font.setFontName("Arial");
	 font.setFontHeightInPoints((short) 16);
	 font.setBold(true);
	 headerStyle.setFont(font);
	 //System.out.println("yes1");
	 List<cart> lc=cd.findAll();
	 Row footerRow=sheet.getRow(sheet.getLastRowNum());
	
	
	 for(cart c:lc)
	 {
		 sheet.shiftRows(sheet.getLastRowNum(), sheet.getLastRowNum(), 1);
		 Row newRow = sheet.createRow(sheet.getLastRowNum()-1);
		 //System.out.println(c.getPid());
		product p=pd.getById(c.getPid());
		//System.out.println(p);
		newValues.add(String.valueOf(p.getPid()));
		newValues.add(p.getName());
		newValues.add(String.valueOf(p.getPrice()));
		newValues.add(String.valueOf(c.getQty()));
		newValues.add(String.valueOf(p.getPrice()*c.getQty()));
		//System.out.println("ys3");
		 for(i=0;i<noOfColumns;i++)
		 {
		 headerCell = newRow.createCell(i);
		 if(i>1)
		 {
			 headerCell.setCellValue(Double.valueOf(newValues.get(i)));
		 }
		 else
		 {
			 headerCell.setCellValue(newValues.get(i).toString());
		 }
		 headerCell.setCellStyle(headerStyle);
		 }
		 newValues.clear();
	 }	
//	 Row lastRow=sheet.createRow(sheet.getLastRowNum()+1);
//	 Cell mycell;
//	 for(i=0;i<noOfColumns;i++)
//	 {
//		 mycell=lastRow.createCell(i);
//		 mycell.setCellValue(footerRow.getCell(i+1).getStringCellValue());
//		 mycell.setCellStyle(footerRow.getRowStyle());
//	 }
//	// System.out.println("yes2");
	 footerRow.getCell(4).setCellFormula("SUM(E2:E"+sheet.getLastRowNum()+")");
	 CellStyle style = workbook.createCellStyle();
	 style.setWrapText(true);
	
	 FileOutputStream outputStream = new FileOutputStream(fileLocation);
	 workbook.write(outputStream);
	 workbook.close();
	 outputStream.close();
	 //System.out.println("complete");
 }
}