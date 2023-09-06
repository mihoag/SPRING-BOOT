package com.demospring.springform;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {

	@RequestMapping(value = "/")
	public String home()
	{
		return "index";
	}
	
	@RequestMapping(value = "/register")
	public String register(Model model)
	{
		
		User u = new User();
		List<String> arr = Arrays.asList("Sales","Accountant","Human Resources");
		model.addAttribute("professList", arr);
		model.addAttribute("user", u);
		return "register";
	}
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("user") User u, Model model)
	{
	   	model.addAttribute("user", u);
	   	return "result";
	}
	
}
