package com.jdp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdp.dao.*;
import com.jdp.model.*;

public class DataController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    private static String WELCOME = "/Welcome.jsp";
    private static String ERROR = "/Error.jsp";
    private static String SUCCESS = "/Success.jsp";
    
    private UserDao daoUser;
    private SnapshotDao daoSnapshot;

    public DataController() {
        super();
        daoUser = new UserDao();
        daoSnapshot = new SnapshotDao();
    }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String forward="";
        String action = request.getParameter("action");        
        
        if (action.equalsIgnoreCase("listProjects")){       	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	forward =  "/ListProjects.jsp";
    	    request.setAttribute("projects", projectsList);       	
        } else if (action.equalsIgnoreCase("listPurchaseOrders")){       	
        	List<PurchaseOrder> purchaseordersList = daoSnapshot.getPOs();
        	forward =  "/ListPurchaseOrders.jsp";
    	    request.setAttribute("purchaseorders", purchaseordersList);       	
        } else if (action.equalsIgnoreCase("listBills")){       	
        	List<Bill> billsList = daoSnapshot.getBills();
        	forward =  "/ListBills.jsp";
    	    request.setAttribute("bills", billsList);       	
        } else if (action.equalsIgnoreCase("listUsers")){
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("project", projectsList);
        	
        	List<User> usersList = daoUser.getAllUsers();
    	    forward =  "/ListUsers.jsp";
    	    request.setAttribute("users", usersList);   
        } else if (action.equalsIgnoreCase("getProject")){ 
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("project", projectsList);
        	
        	String projectCode =  request.getParameter("data");
        	Project selectedProject = daoSnapshot.getProjectByAnalyticalCode(projectCode);
        	forward =  "/ProjectDetails.jsp";
    	    request.setAttribute("project", selectedProject);       	
        }
        else {	
            forward = ERROR;
        }
        
    	RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String forward="";
        String action = request.getParameter("action");        
            
        if (action.equalsIgnoreCase("login")){
        	           
            if (request.getParameter("userlogin").isEmpty()) {
            	forward = ERROR;
            } else {
            	
            	User myUser = new User();           	
                myUser.setUserLogin(request.getParameter("userlogin"));
                myUser.setUserPwd(request.getParameter("userpwd"));
                                        	
                User user =  daoUser.getUserByUserLogin(request.getParameter("userlogin"));
                                
                if (myUser.getUserPwd().equals(user.getUserPwd())) { 	
                	forward = SUCCESS;	
                } else {
                	forward = ERROR;
                }          		
            }
        } else if (action.equalsIgnoreCase("register")){
        	
        	User user = new User();
            
            user.setLastName(request.getParameter("lastname"));
            user.setFirstName(request.getParameter("firstname"));
            user.setUserMail(request.getParameter("usermail"));
            user.setUserLogin(request.getParameter("userlogin"));
            user.setUserPwd(request.getParameter("userpwd"));
            
            daoUser.addUser(user);
            
            forward = WELCOME;
            
        } else if (action.equalsIgnoreCase("listUsers")){
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("project", projectsList);
        	    	
        	List<User> usersList = daoUser.getAllUsers();        
    	    forward =  "/ListUsers.jsp";
    	    request.setAttribute("users", usersList);
    	    
        } else if (action.equalsIgnoreCase("getProjects")){       	
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	forward =  "/Home.jsp";
    	    request.setAttribute("projects", projectsList);   
    	    
        } else if (action.equalsIgnoreCase("getProject")){ 
        	      	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("project", projectsList);
        	
        	String projectCode =  request.getParameter("data");
        	Project selectedProject = daoSnapshot.getProjectByAnalyticalCode(projectCode);
        	request.setAttribute("project", selectedProject);
        	
        	forward =  "/ProjectDetails.jsp";
    	          	
        } else {	
            forward = ERROR;
        }
       
    	RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}