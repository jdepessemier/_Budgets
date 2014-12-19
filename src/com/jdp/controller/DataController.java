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

    // GET -------------------------------------------------------------------------------------------------------------------------
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String forward="";
        String action = request.getParameter("action");      
        
        if (action.equalsIgnoreCase("listProjects")){ //--------------------------------------------- List Projects
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	forward =  "/ListProjects.jsp";
    	    request.setAttribute("projects", projectsList);       	
        
        } else if (action.equalsIgnoreCase("projectsStatus")) {
        	
        	List<SnapshotData> SnapshotDatasList = daoSnapshot.getAllSnapshotDatas();
        	forward = "/ProjectsStatus.jsp";
        	request.setAttribute("snapshots", SnapshotDatasList);
        	
        } else if (action.equalsIgnoreCase("listPurchaseOrders")){ //-------------------------------- List Purchase Orders      	
        	
        	List<PurchaseOrder> purchaseordersList = daoSnapshot.getPOs();
        	forward =  "/ListPurchaseOrders.jsp";
    	    request.setAttribute("purchaseorders", purchaseordersList);       	
        
        } else if (action.equalsIgnoreCase("listBills")){ //----------------------------------------- List Bills      	
        	
        	List<Bill> billsList = daoSnapshot.getBills();
        	forward =  "/ListBills.jsp";
    	    request.setAttribute("bills", billsList);       	
        
        } else if (action.equalsIgnoreCase("listUsers")){ //----------------------------------------- List Users
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("project", projectsList);
        	
        	List<User> usersList = daoUser.getAllUsers();
    	    forward =  "/ListUsers.jsp";
    	    request.setAttribute("users", usersList);   
        
        } else if (action.equalsIgnoreCase("getProject")){ //---------------------------------------- Get selected Project
        	
        	// Get the list of projects for the dropdown list
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("projectslist", projectsList);
        	
        	// Retrieve the selected project main data
        	String projectCode =  request.getParameter("data");
        	Project selectedProject = daoSnapshot.getProjectByAnalyticalCode(projectCode);
        	request.setAttribute("selectedproject", selectedProject);  
        	
        	// Retrieve the selected project budgets data
        	List<Budget> BudgetsB = daoSnapshot.getBudgetsBByAnalyticalCode(projectCode);
        	request.setAttribute("budgetsB", BudgetsB);
        	
        	double totalBudgetB = 0.00;
        	
        	for (int i = 0; i < BudgetsB.size(); i++) {
        		totalBudgetB = totalBudgetB + BudgetsB.get(i).getAmount();
        	}
        	request.setAttribute("totalBudgetB", totalBudgetB);
        	
        	selectedProject.setTotalBudgetB(totalBudgetB);
        	       	
        	// Retrieve the selected project budgets data
        	List<Budget> BudgetsC = daoSnapshot.getBudgetsCByAnalyticalCode(projectCode);
        	request.setAttribute("budgetsC", BudgetsC);
        	
        	double totalBudgetC = 0.00;
        	
        	for (int i = 0; i < BudgetsC.size(); i++) {
        		totalBudgetC = totalBudgetC + BudgetsC.get(i).getAmount();
        	}
        	request.setAttribute("totalBudgetC", totalBudgetC);
        	
        	selectedProject.setTotalBudgetC(totalBudgetC);
        	
        	daoSnapshot.addProject(selectedProject);
    	    
    	    forward =  "/ProjectsDetails.jsp";
        }
        else {	
            forward = ERROR;
        }
        
    	RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }
    
     // POST ------------------------------------------------------------------------------------------------------------------------     
     
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String forward="";
        String action = request.getParameter("action");        
            
        if (action.equalsIgnoreCase("login")){
        	           
            if (request.getParameter("userlogin").isEmpty()) { //------------------------------------ Error
            	
            	forward = ERROR;
            
            } else { //------------------------------------------------------------------------------ Retrieve User
            	
            	User myUser = new User();           	
                myUser.setUserLogin(request.getParameter("userlogin"));
                myUser.setUserPwd(request.getParameter("userpwd"));
                                        	
                User user =  daoUser.getUserByUserLogin(request.getParameter("userlogin"));
                                
                if (myUser.getUserPwd().equals(user.getUserPwd())) {
                	String userlogin = request.getParameter("userlogin");
                	request.setAttribute("user", userlogin);
                	forward = SUCCESS;	
                } else {
                	forward = ERROR;
                }          		
            }
        
        } else if (action.equalsIgnoreCase("register")){ //------------------------------------------ Register User        	
        	User user = new User();
            
            user.setLastName(request.getParameter("lastname"));
            user.setFirstName(request.getParameter("firstname"));
            user.setUserMail(request.getParameter("usermail"));
            user.setUserLogin(request.getParameter("userlogin"));
            user.setUserPwd(request.getParameter("userpwd"));
            
            daoUser.addUser(user);
            
            forward = WELCOME;
            
        } else if (action.equalsIgnoreCase("listUsers")){ //----------------------------------------- List Users
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("project", projectsList);
        	    	
        	List<User> usersList = daoUser.getAllUsers();        
    	    forward =  "/ListUsers.jsp";
    	    request.setAttribute("users", usersList);
    	    
        } else if (action.equalsIgnoreCase("getProjects")){ //--------------------------------------- Get Projects List      	
        	
        	List<Project> projectsList = daoSnapshot.getProjects();
        	String user =  request.getParameter("data");
        	forward =  "/Home.jsp";
        	request.setAttribute("username", user);
    	    request.setAttribute("projects", projectsList);   
    	    
        } else if (action.equalsIgnoreCase("getProject")){ //---------------------------------------- Get selected project
        	      	
        	// Get the list of projects for the dropdown list
        	List<Project> projectsList = daoSnapshot.getProjects();
        	request.setAttribute("projectslist", projectsList);
        	
        	// Retrieve the selected project main data
        	String projectCode =  request.getParameter("data");
        	Project selectedProject = daoSnapshot.getProjectByAnalyticalCode(projectCode);
        	request.setAttribute("selectedproject", selectedProject);  
        	
        	// Retrieve the selected project budgets data
        	List<Budget> BudgetsB = daoSnapshot.getBudgetsBByAnalyticalCode(projectCode);
        	request.setAttribute("budgetsB", BudgetsB);
        	
        	// Retrieve the selected project budgets data
        	List<Budget> BudgetsC = daoSnapshot.getBudgetsCByAnalyticalCode(projectCode);
        	request.setAttribute("budgetsC", BudgetsC);
        	
        	forward =  "/ProjectDetails.jsp";
    	          	
        } else {	
        
        	forward = ERROR;
        
        }
       
    	RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}