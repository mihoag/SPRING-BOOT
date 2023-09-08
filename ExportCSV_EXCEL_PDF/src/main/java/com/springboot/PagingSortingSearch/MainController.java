package com.springboot.PagingSortingSearch;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;

@Controller
public class MainController {
	@Autowired
	private IServiceProduct is;
	
	
	@RequestMapping(value = "/page/{pagenum}", method = RequestMethod.GET)
	public String viewPage(Model model,@PathVariable(name = "pagenum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir, @Param("keyword") String keyword)
	{
		System.out.println(keyword);
		if(keyword != null)
		{
			
			return search(model, keyword);
		}
		else {
			
		
		///System.out.println(pageNum + " "  + sortField + " " + sortDir);
	    Page<product> page = is.FindAll(pageNum, sortField, sortDir);
	    model.addAttribute("listProducts", page.getContent());
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir",sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	  
		return "index";
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home(Model model)
	{
		//System.out.println("ok");
		return viewPage(model, 1, "name", "asc", null);
	}
	
	@RequestMapping(value = "/" , method = RequestMethod.POST)
	public String search(Model model, @RequestParam("keyword") String key)
	{
		System.out.println("ok");
		System.out.println(key);
		model.addAttribute("listProducts", is.FindAll(key));
		return "index1";
	}
	
	
	@RequestMapping(value = "/new")
	public String newProduct(Model model)
	{
		product p = new product();
		model.addAttribute("product", p);
		List<String> arr=  Arrays.asList("China", "USA", "Viet Nam");
		model.addAttribute("MadeList", arr);
		return "newform";
	}
	
	@RequestMapping(value = "/save")
	public String saveproduct(@ModelAttribute("product") product p)
	{
		System.out.println(p.getId());
		is.save(p);
		return "redirect:/";
		/// redirect:/.... (... là phần controller cần map tới)
	}
	
	@RequestMapping(value = "/edit")
	public String editProduct(Model model, @RequestParam("id") int id)
	{
		product p = is.FindOne(id);
		model.addAttribute("product", p);
		List<String> arr=  Arrays.asList("China", "USA", "Viet Nam");
		model.addAttribute("MadeList", arr);
		return "editform";
	}
    @RequestMapping(value = "/delete")
    public String deleteProduct(@RequestParam("id") int id)
    {
    	is.delete(id);
    	return "redirect:/";
    }
    
    @RequestMapping(value = "/exportCSV")
    public void exportToCSV(HttpServletResponse response) throws IOException
    {
       response.setContentType("text/csv");
       DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
       String currentdatetime = dateformatter.format(new Date());
       
       String headerkey = "Content-Disposition";
       String headervalue = "attachment; filename=product_" + currentdatetime + ".csv";
       response.setHeader(headerkey, headervalue);
       
       List<product> arr = is.FindAll(null);
       
       ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
       String[] csvHeader = {"ID", "Name" , "Brand", "Made in", "Price"};
       String[] nameMapping = {"id","name","brand","madein","price"};
       
       csvWriter.writeHeader(csvHeader);
       for(product p : arr)
       {
    	   System.out.println(p);
    	   csvWriter.write(p, nameMapping);
       }
       csvWriter.close();
    }
	
    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException
    {
    	response.setContentType("application/octet-stream");
    	DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	String currentDateTime = dateformatter.format(new Date());
    	
    	String headerkey = "Content-Disposition";
    	String headervalue = "attachment; filename=product_" + currentDateTime + ".xlsx";
    	response.setHeader(headerkey, headervalue);
    	
    	List<product> arr = is.FindAll(null);
    	ProductExcelExporter ex = new ProductExcelExporter(arr);
    	ex.export(response);
    }
    
    @RequestMapping(value = "/exportPDF")
    public void exportPDF(HttpServletResponse response) throws DocumentException, IOException
    {
    	response.setContentType("application/pdf");
    	DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	String currentDateTime = dateformatter.format(new Date());
    	
    	String headerkey = "Content-Disposition";
    	String headervalue = "attachment; filename=product_" + currentDateTime + ".pdf";
    	response.setHeader(headerkey, headervalue);
    	
    	List<product> arr = is.FindAll(null);
    	
    	ProductPDFExporter exporter = new ProductPDFExporter(arr);
    	exporter.export(response);
    }
    
}
