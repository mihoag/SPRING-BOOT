package com.springboot.PagingSortingSearch;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ProductExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<product> listproducts;
	public ProductExcelExporter(List<product> listproducts) {
		//super();
		//this.listproducts = listproducts;
		this.listproducts = listproducts;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("products");
		Row row = sheet.createRow(0);
		
		 CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        
	        createCell(row, 0, "ID", style);
	        createCell(row, 1, "Name", style);
	        createCell(row, 2, "Brand", style);
	        createCell(row, 3, "Made in", style);
	        createCell(row, 4, "Price", style);
	        
	}
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		   sheet.autoSizeColumn(columnCount);
		   Cell cell = row.createCell(columnCount);
		   
		   if(value instanceof Integer)
		   {
			   cell.setCellValue( (Integer)value);
		   }
		   else if(value instanceof String)
		   {
			   cell.setCellValue((String) value);
		   }
		   else if(value instanceof Boolean)
		   {
			   cell.setCellValue((Boolean)value);
		   }
		   else if(value instanceof Float)
		   {
			   cell.setCellValue((Float )value);
		   }
		   
		   cell.setCellStyle(style); 
	}
	private void writeDateLines() {
	
		int rowcount = 1;
		
		 CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        
	       for(product p : listproducts)
	       {
	    	   Row r = sheet.createRow(rowcount++);
	    	   int columnCount = 0;
	    	   
	    	   createCell(r, columnCount++, p.getId(), style);
	    	   createCell(r, columnCount++, p.getName(), style);
	    	   createCell(r, columnCount++, p.getBrand(), style);
	    	   createCell(r, columnCount++, p.getMadein(), style);
	    	   createCell(r, columnCount++, p.getPrice(), style);
	       }
	}
	
	public void export(HttpServletResponse response) throws IOException
	{
		writeHeaderLine();
		writeDateLines();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
	
	
	
}
