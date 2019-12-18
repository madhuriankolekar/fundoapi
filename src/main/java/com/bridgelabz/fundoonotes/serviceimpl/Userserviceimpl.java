package com.bridgelabz.fundoonotes.serviceimpl;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoonotes.dto.Userdto;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.Repo;
import com.bridgelabz.fundoonotes.service.Userservice;
import com.bridgelabz.fundoonotes.utility.JMSprovider;
import com.bridgelabz.fundoonotes.utility.JWTprovider;

@Service
public class Userserviceimpl implements Userservice {
	@Autowired
	private Repo userRepo;
	@Autowired
	private JMSprovider provider;
	@Autowired
	private JWTprovider jwtprovider;
@Transactional
	@Override
	public User registration(Userdto userDto) {
		User userObj = userDTOToUSer(userDto);
		String email=userDto.getEmail();
		String token=jwtprovider.generateToken(email);
		String url="http://localhost:8080/users/varified/";
		JMSprovider.sendEmail(email,"for authorization", url+token);
		User user = userRepo.saveUser(userObj);
		return user;
	}

	@Transactional
	@Override
	public User update(Integer id) {
		User use = userRepo.update(id);
		return use;
	}

	public User userDTOToUSer(Userdto userDto) {

		User user = new User();
		user.setFname(userDto.getFname());
		user.setLname(userDto.getLname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}

	@Transactional
	@Override
	public User delete(Integer id) {
		User user = userRepo.delete(id);
		return user;

	}

	public boolean matches(String Password, String encodedPassword) {
		boolean status = false;
		if (encodedPassword == null || encodedPassword.length() == 0) {
			if (encodedPassword.equals(Password)) {
				status = true;
			}
		}
		return status;
	}

	public boolean login(String email, String password) {
		List<User> userList = userRepo.getAllUser();
		for (User user : userList)
			if (user.getEmail().equalsIgnoreCase(email) && matches(password, user.getPassword())) {
				if (userRepo.isVarified(user)) {
					return true;
				}
			}
		return false;
	}
@Override
	public boolean forgetPassword(String emailid) {
		List<User> userList = userRepo.getAllUser();
		for (User user : userList) {
			if (user.getEmail().equals(emailid)) {
				String email = user.getEmail();
			 String token=jwtprovider.generateToken(email);
				String url = "http://localhost:8080/api/resetpassword/";
				 JMSprovider.sendEmail(email, "for reset password", url+token);
				return true;
			}
		}
		return false;

	}

public void parseToken(String token) {
	String email=jwtprovider.parseToken(token);
	List<User> userList=userRepo.getAllUser();
	for(User user:userList) {
		String checkEmail=user.getEmail();
		if(email.equals(checkEmail)) {
			user.setStatus(true);
			userRepo.saveUser(user);
		}
	}
}

private List<User> getAllUser( String token) {
	
	 return userRepo.getAllUser();
}

@Override
public boolean isVarified(@Valid String email) {
	// TODO Auto-generated method stub
	return false;
}


}

	
	


