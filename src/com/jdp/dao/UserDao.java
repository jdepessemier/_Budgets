package com.jdp.dao;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.ObjectifyService;



import com.jdp.model.User;

public class UserDao {

    public UserDao() {
    	ObjectifyService.register(User.class);
    }

    public void addUser(User user) {

    	ObjectifyService.ofy().save().entity(user);
    	
//        	Objectify obj = ObjectifyService.beginTransaction();
//        	obj.put(user);
//        	obj.getTxn().commit();
    }

    public List<User> getAllUsers() {
    	
    	List<User> users =  ofy().load().type(User.class).list();
    	
//        List<User> users = new ArrayList<User>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<User> query = ofy.query(User.class);
//		
//		for (User userFromQuery : query) {
//			users.add(userFromQuery);
//		}
		
        return users;
    }
    
    public User getUserByUserLogin(String userlogin) {
    	
    	List<User> myUsers = ofy().load().type(User.class).filter("userLogin =", userlogin).list();
    	        
//		Objectify ofy = ObjectifyService.begin();
//		Query<User> query = ofy.query(User.class);
//		
//		User myUser = query
//				.filter("userLogin =", userlogin).get();
    	
    	User myUser = myUsers.get(0);
    	
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
