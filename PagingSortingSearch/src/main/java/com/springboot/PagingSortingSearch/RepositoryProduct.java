package com.springboot.PagingSortingSearch;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryProduct extends JpaRepository<product,Integer>{
    @Query("select u from product u where u.name like %?1%"  + " OR u.brand LIKE %?1%"
            + " OR u.madein LIKE %?1%"
            + " OR CONCAT(u.price, '') LIKE %?1%")
	public ArrayList<product> search(String keyword);
}
