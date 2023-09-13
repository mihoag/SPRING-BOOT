package com.springboot.AjaxCrudRestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.AjaxCrudRestApi.address.address;
import com.springboot.AjaxCrudRestApi.address.addressRepository;
import com.springboot.AjaxCrudRestApi.district.district;
import com.springboot.AjaxCrudRestApi.district.districtRepository;
import com.springboot.AjaxCrudRestApi.user.UserService;
import com.springboot.AjaxCrudRestApi.user.user;

@RestController
public class ApiController {
	@Autowired
	private UserService ser;
  
	@Autowired
	private districtRepository resdis;
	
	@Autowired
	private addressRepository resadd;
	
  @GetMapping(value = "/api/user/{id}")
  public user findOne(@PathVariable int id)
  {
	  return ser.findOne(id);
  }
  
  @PostMapping(value = "/api/user")
  public user insertUser(@RequestBody user u)
  {
	  return ser.save(u);
  }
  
  @PutMapping(value = "/api/user")
  public user updateUser(@RequestBody user u)
  {
	  return ser.save(u);
  }
  
 @GetMapping(value = "/api/{id}/district")
 public List<district> getDistrictById(@PathVariable int id)
  {
	  return resdis.getByID(id);
  }
 
 @DeleteMapping(value = "/api/{id}/user")
 public void deleteUser(@PathVariable int id)
 {
	 ser.delete(id);
 }

}
