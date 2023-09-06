package com.demoweb.demoweb;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController
{
	
	@RequestMapping("/test")
	public String home()
	{
		System.out.println("Hle");
		return "test";
	}
	
	@RequestMapping(value = "/list_contact")
	public String contactControll(Model model)
	{
		System.out.println("ok");
		ArrayList<contact> arr = listContact.getList();
		for(contact c : arr)
		{
			System.out.println(c.getName());
		}
		model.addAttribute("contacts", arr);
		return "contact";
	}
	
}