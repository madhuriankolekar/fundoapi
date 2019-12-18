package com.bridgelabz.fundoonotes.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonotes.dto.Userdto;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.Userservice;

@RestController
public class UserController {
@Autowired
	private Userservice userservice;

	@PostMapping("users/register")
	public ResponseEntity<Response> register(@RequestBody Userdto userDto) {
		User user = userservice.registration(userDto);
		if (user != null) {
			return new ResponseEntity<Response>(new Response( HttpStatus.OK.value(),"register sucess"), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(new Response( HttpStatus.BAD_REQUEST.value(),"register is not sucessfull"), HttpStatus.BAD_REQUEST);

	}
	@PostMapping("users/delete/{id}")
	public ResponseEntity<Response> delete(@Valid @PathVariable Integer id) {
		User user = userservice.delete(id);
		if (user != null) {
			return new ResponseEntity<Response>(new Response( HttpStatus.OK.value(),"deleted sucessfully"), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(new Response( HttpStatus.BAD_REQUEST.value(),"not deleted"), HttpStatus.BAD_REQUEST);

	}
@PostMapping("users/update/{id}")	
	public ResponseEntity<Response> update(@Valid @PathVariable Integer id) {
		User user = userservice.update( id);
		if (user != null) {
			return new ResponseEntity<Response>(new Response( HttpStatus.OK.value(),"deleted sucessfully"), HttpStatus.OK);
		}
		return new ResponseEntity<Response>(new Response( HttpStatus.BAD_REQUEST.value(),"not deleted"), HttpStatus.BAD_REQUEST);

	}
@PostMapping("users/login")	
public ResponseEntity<Response> login(@Valid @RequestBody String password,	String email ) {
	if(userservice.login(password, email))
	{
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "login Successfully"),HttpStatus.OK);
	}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(),"login Unsuccesfull"),HttpStatus.BAD_REQUEST);
}

@PostMapping("users/forgetPassword")	
public ResponseEntity<Response> forgetPassword(@PathVariable("email")	String email ) {
	if(userservice.forgetPassword(email))
	{
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"password recovered"),HttpStatus.OK);
	}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(),"password unable to recover"),HttpStatus.BAD_REQUEST);

	
		}
@PostMapping("users/varified/{token}")	
public ResponseEntity<Response> isVarified(@PathVariable("token")	String token ) {
	if(userservice.isVarified( token))
	{
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"password recovered"),HttpStatus.OK);
	}
	return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST.value(),"password unable to recover"),HttpStatus.BAD_REQUEST);
}
}




