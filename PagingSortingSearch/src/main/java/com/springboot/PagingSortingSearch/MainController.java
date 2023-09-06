package com.springboot.PagingSortingSearch;

import java.util.Arrays;
import java.util.List;

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
	
}
