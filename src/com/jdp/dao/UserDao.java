package com.jdp.dao;


import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.ObjectifyService;



import com.jdp.model.User;

public class UserDao {

    public UserDao() {
    	ObjectifyService.register(User.class);
    }

    public void addUser(User user) {

    	ObjectifyService.ofy().save().entity(user).now();
    }

    public List<User> getAllUsers() {
    	
    	List<User> users =  ofy().load().type(User.class).list();
    	
        return users;
    }
    
    public User getUserByUserLogin(String userlogin) {
    	
    	List<User> myUsers = ofy().load().type(User.class).filter("userLogin =", userlogin).list();
    	
    	User myUser = myUsers.get(0);
    	
		if (myUser == null) {
			
			User user = new User();
			
            user.setFirstName("---------");
            user.setLastName("---------");
            user.setUserMail("---------");
            user.setUserLogin("---------");
            user.setUserPwd("---------");
            user.setDepartment("---------");
            user.setService("---------");
            user.setRole("---------");
            
            return user;
            
		}  else {
			
			return myUser;
		
		}
    }
    
    
}
