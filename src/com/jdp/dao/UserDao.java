package com.jdp.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import com.jdp.model.User;

public class UserDao {

    public UserDao() {
    	ObjectifyService.register(User.class);
    }

    public void addUser(User user) {

        	Objectify obj = ObjectifyService.beginTransaction();
        	obj.put(user);
        	obj.getTxn().commit();
    }

    public List<User> getAllUsers() {
    	
        List<User> users = new ArrayList<User>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<User> query = ofy.query(User.class);
		
		for (User userFromQuery : query) {
			users.add(userFromQuery);
		}
		
        return users;
    }
    
    public User getUserByUserLogin(String userlogin) {
    	        
		Objectify ofy = ObjectifyService.begin();
		Query<User> query = ofy.query(User.class);
		
		User myUser = query
				.filter("userLogin =", userlogin).get();
		
		if (myUser == null) {
			
			User user = new User();
            user.setFirstName("---------");
            user.setLastName("---------");
            user.setUserMail("---------");
            user.setUserLogin("---------");
            user.setUserPwd("---------");
            
            return user;
		}  else {
			return myUser;
		}
    }
    
    
}
