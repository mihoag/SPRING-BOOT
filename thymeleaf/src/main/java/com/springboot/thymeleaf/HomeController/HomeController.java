package com.springboot.thymeleaf.HomeController;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.thymeleaf.User;

@Controller
public class HomeController {

	@GetMapping("/register")
	public String formRegister(Model model)
	{
		List<String> listprofession =  Arrays.asList("Doctor","Sale" , "Accountant");
		model.addAttribute("listprofession", listprofession);
		User user = new User();
		model.addAttribute("user", user);
		return "form_register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User u, Model model)
	{
		model.addAttribute("user", u);
		return "success_register";
	}
	
	@GetMapping("/showlist")
	public String showList(Model model)
	{
		ArrayList<User> arr = new ArrayList<>();
		arr.add(new User("John", "leminhhoang123456le@gmail.com", "1212312", "Male", "none", false, new Date(2000, 1, 1), "student"));
		arr.add(new User("Peter", "leminhh123le@gmail.com", "121231das2", "Male", "none", false, new Date(2020, 1, 1), "student"));
		arr.add(new User("Marry", "leminhhoang123456le@gmail.com", "1212312", "Male", "none", false, new Date(2000, 1, 1), "student"));
		arr.add(new User("Bean", "leminhhoang123456le@gmail.com", "1212312", "Male", "none", false, new Date(2000, 1, 1), "student"));
	    model.addAttribute("listUser", arr);
		return "list_user";
	}

}
