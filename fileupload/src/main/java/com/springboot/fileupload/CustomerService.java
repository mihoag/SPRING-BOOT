package com.springboot.fileupload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository res;
	
	public List<customer> findAll()
	{
		return res.findAll();
	}
	
	public customer save(customer c) {
		return res.save(c);
	}
	
	
	
}
