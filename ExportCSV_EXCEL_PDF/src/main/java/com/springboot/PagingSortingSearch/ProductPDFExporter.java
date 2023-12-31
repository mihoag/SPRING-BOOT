package com.springboot.PagingSortingSearch;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

public class ProductPDFExporter {
  private  List<product> listProduct;

public ProductPDFExporter(List<product> listProduct) {
	//super();
	this.listProduct = listProduct;
}

private void writeTableHeader(PdfPTable table) {
	PdfPCell cell = new PdfPCell();
	cell.setBackgroundColor(Color.BLUE);
    cell.setPadding(5);
       
    Font font = FontFactory.getFont(FontFactory.HELVETICA);
    font.setColor(Color.WHITE);
      
    cell.setPhrase(new Phrase("ID", font));
    
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Name", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Brand", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Made in", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Price", font));
    table.addCell(cell);    
	
}
private void writeTableData(PdfPTable table) {
    for (product p  : listProduct) {
        table.addCell(String.valueOf(p.getId()));
        table.addCell(p.getName());
        table.addCell(p.getBrand());
        table.addCell(p.getMadein());
        table.addCell(String.valueOf(p.getPrice()));
    }
}
public void export(HttpServletResponse response) throws DocumentException, IOException
{
	Document document = new Document(PageSize.A4);
	PdfWriter.getInstance(document, response.getOutputStream());
	
	document.open();
	Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    font.setSize(18);
    font.setColor(Color.BLUE);
     
    Paragraph p = new Paragraph("List of PRODUCTS", font);
    p.setAlignment(Paragraph.ALIGN_CENTER);
    
    document.add(p);
    
    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100f);
    table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
    table.setSpacingBefore(10);
    writeTableHeader(table);
    writeTableData(table);
     
    document.add(table);
     
    document.close();
}
  
}
