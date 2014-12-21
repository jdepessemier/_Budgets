package com.jdp.model;

import com.googlecode.objectify.annotation.*;

@Entity
@Index
public class User {
	
	@Id 
	private Long id;
    @Unindex private String firstName;
    @Unindex private String lastName;
    @Unindex private String userMail;
    private String userLogin;
    private String userPwd;
    @Unindex private String department;
    @Unindex private String service;
    @Unindex private String role;
    
	public User() {
		firstName = "";
		lastName = "";
		userMail = "";
	    userLogin = "";
		userPwd = "";
		department = "";
		service = "";
		role = "";
	}

	public User(String firstname,
				String lastname,
				String usermail,
				String userlogin,
				String userpwd,
				String department,
				String service,
				String role) {
		
		this.firstName = firstname;
		this.lastName = lastname;
		this.userMail = usermail;
		this.userLogin = userlogin;
		this.userPwd = userpwd;
		this.department = department;
		this.service = service;
		this.role = role;
	}
       
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUserMail() {
        return userMail;
    }
    public void setUserMail(String usermail) {
        this.userMail = usermail;
    }
    
    public String getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(String userlogin) {
        this.userLogin = userlogin;
    }
    
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userpwd) {
        this.userPwd = userpwd;
    }
      
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
