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
    
    public void updateUser(User user) {
    	
    	List<User> myUsers = ofy().load().type(User.class).filter("userLogin =", user.getUserLogin()).list();
    	
    	User myUser = myUsers.get(0);
    	
    	myUser.setFirstName(user.getFirstName());
    	myUser.setLastName(user.getLastName());
    	myUser.setUserMail(user.getUserMail());
    	myUser.setUserLogin(user.getUserLogin());
    	myUser.setUserPwd(user.getUserPwd());
    	myUser.setDepartment(user.getDepartment());
    	myUser.setService(user.getService());
    	myUser.setRole(user.getRole());
    	
    	ObjectifyService.ofy().save().entity(myUser).now();
    }
    
    public List<User> getAllUsers() {
    	
    	List<User> users =  ofy().load().type(User.class).order("userLogin").list();
    	
        return users;
    }
    
    public void deleteUserByUserLogin(String userlogin) {
    	
    	List<User> myUsers = ofy().load().type(User.class).filter("userLogin =", userlogin).list();
    	
    	User myUser = myUsers.get(0);
    	
    	ofy().delete().entity(myUser).now();
    	
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
