package com.demospringboot.restful;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restfulAPI {

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name" , defaultValue = "World") String name)
	{
		return "Hello " + name;
	}
	
	@GetMapping(value = "/product", produces   = {MediaType.APPLICATION_JSON_VALUE})
	public product getProduct()
	{
		System.out.println("ok");
		return new product(1, "T-shirt", 100000);
	}
	
	@PostMapping(value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public product addProduct(@RequestBody product p)
	{
		//System.out.println(p);
		return p;
	}
	@GetMapping(value = "/product/{id}")
	public ResponseEntity<product> getProductbyID(@PathVariable int id)
	{
		if(id == 3)
		{
			return new ResponseEntity<product>(new product(3, "Hoang", 100), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<product>(HttpStatus.NOT_FOUND);
		}
	}
	//{"id" : 1,"name" : "Hoang", "price" : 100}
	@GetMapping(value = "/listProduct", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ArrayList<product> getListArr()
	{
		ArrayList<product> arr = new ArrayList<product>();
		arr.add(new product(1,"Short",100));
		arr.add(new product(2,"T-shirt",100));
		arr.add(new product(3,"Hat",100));
		return arr;
	}
}
