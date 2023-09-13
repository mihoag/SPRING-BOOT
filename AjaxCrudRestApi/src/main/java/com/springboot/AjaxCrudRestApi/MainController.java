package com.springboot.AjaxCrudRestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.AjaxCrudRestApi.address.address;
import com.springboot.AjaxCrudRestApi.address.addressRepository;
import com.springboot.AjaxCrudRestApi.district.district;
import com.springboot.AjaxCrudRestApi.district.districtRepository;
import com.springboot.AjaxCrudRestApi.user.UserService;
import com.springboot.AjaxCrudRestApi.user.user;

@Controller
public class MainController {

	@Autowired
	private UserService ser;
	
	@Autowired
	private addressRepository resAdd;
	
	@Autowired
	private districtRepository resdis;
	
	@RequestMapping(value = "/")
	public String index(Model model)
	{
		List<user>  arr = ser.listAll();
		model.addAttribute("listPersons", arr);
		return "index";
	}
	
	@RequestMapping(value = "/new")
	public String newPerson(Model model)
	{
		List<address> arr =  (List<address>) resAdd.findAll();
        List<district> arrdis = (List<district>) resdis.findAll();
		model.addAttribute("listProvince",arr);
		model.addAttribute("listDistrict", arrdis);
	    model.addAttribute("person", new user());
		return "newform";
	}
}
