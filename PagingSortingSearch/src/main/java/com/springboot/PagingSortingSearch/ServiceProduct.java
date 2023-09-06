package com.springboot.PagingSortingSearch;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ServiceProduct implements IServiceProduct{

	@Autowired
	private RepositoryProduct res;
	
	@Override
	public List<product> FindAll(String keyword) {
		// TODO Auto-generated method stub
		//return null;
		if(keyword != null)
		{
			return res.search(keyword);
		}
		return res.findAll();
	}
	@Override
	public void save(product p) {
		// TODO Auto-generated method stub
	res.save(p);		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		res.deleteById(id);
	}
	@Override
	public product FindOne(int id) {
		// TODO Auto-generated method stub
		//return null;
		return res.findById(id).get();
	}
	@Override
	public Page<product>  FindAll(int pagenumber, String sortfield, String sortdir) {
		// TODO Auto-generated method stub
	
		return res.findAll(PageRequest.of(pagenumber-1, 3, sortdir.equals("asc") ? Sort.by(sortfield).ascending() : Sort.by(sortfield).descending()));
	
	}

}
