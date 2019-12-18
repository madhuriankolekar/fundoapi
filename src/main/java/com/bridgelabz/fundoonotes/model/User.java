package com.bridgelabz.fundoonotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.fundoonotes.dto.Userdto;

@Entity
@Table(name = "user")
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;

		@Column()
		private String fname;
		@Column()
		private String lname;
		@Column()
		private String password;
		@Column()
		private String email;
	    public User() {
	    	
	    }
		public User(Userdto userdto){
			this.fname=userdto.getFname();
			this.lname=userdto.getLname();
			this.email=userdto.getEmail();
			this.password=userdto.getPassword();
		}
		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public void setfname() {
			// TODO Auto-generated method stub
			
		}
		public void setlname() {
			// TODO Auto-generated method stub
			
		}
		public void setemail() {
			// TODO Auto-generated method stub
			
		}
		public void setpassword() {
			// TODO Auto-generated method stub
			
		}
		public boolean getStatus() {
			// TODO Auto-generated method stub
			return false;
		}
		public void setStatus(boolean b) {
			// TODO Auto-generated method stub
			
		}


	}



