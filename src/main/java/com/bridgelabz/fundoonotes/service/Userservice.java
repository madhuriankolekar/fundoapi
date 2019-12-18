package com.bridgelabz.fundoonotes.service;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.dto.Userdto;
import com.bridgelabz.fundoonotes.model.User;

public interface Userservice {
	User registration(Userdto user);
	User delete(Integer id);
	User update( Integer id);
	boolean login(String password,String email);
	boolean forgetPassword(String emailId);
   boolean isVarified(@Valid String email);
	
	
}
