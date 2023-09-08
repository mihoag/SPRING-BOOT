package com.springboot.PagingSortingSearch;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IServiceProduct {
    public List<product> FindAll(String keyword);
    public Page<product>  FindAll(int pagenumber, String sortfield, String sortdir);
    public product FindOne(int id);
    public void save(product p);
    public void delete(int id);
}

