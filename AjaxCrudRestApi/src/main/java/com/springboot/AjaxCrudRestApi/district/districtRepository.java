package com.springboot.AjaxCrudRestApi.district;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.AjaxCrudRestApi.address.address;

public interface districtRepository extends CrudRepository<district, Integer>{

	
	@Query("select d from district d where province = ?1")
	public List<district> getByID(int id);
	
}
