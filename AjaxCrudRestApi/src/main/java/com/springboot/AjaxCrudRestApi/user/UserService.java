package com.springboot.AjaxCrudRestApi.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   @Autowired
   private UserRepository res;
   
   public List<user> listAll()
   {
	   return (List<user>) res.findAll();
   }
   
   public user save(user u)
   {
	   return res.save(u);
   }
   
   public void delete(int id)
   {
	   res.deleteById(id);
   }
   
   public user findOne(int id)
   {
	  return res.findById(id).get();
   }
}
