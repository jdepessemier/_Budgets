package com.jdp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jdp.dao.*;
import com.jdp.model.*;
import com.jdp.util.StringUtil;

@SuppressWarnings("serial")
public class FileUploadHandler extends HttpServlet {
	
	private static String ERROR = "/Error.jsp";
	private static String SUCCESS = "/Success.jsp";
	private static String BUDGET_B = "Liquidation";
	private static String BUDGET_C = "Engagement";
	
    private SnapshotDao dao;

    public FileUploadHandler() {
        super();
        dao = new SnapshotDao();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("uploadSnaphot")){
        	
        	ServletFileUpload upload = new ServletFileUpload();
        	
    		try {
    			FileItemIterator it = upload.getItemIterator(request);
    			FileItemStream item = it.next();

    	        InputStream stream = item.openStream();
    	        
    	        try {
    	            InputStreamReader inputStreamReader = new InputStreamReader(stream, "ISO-8859-1");
    	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    	            bufferedReader.readLine();
    	            String line;
    	            
    	            String currentProject = "";

    	            while ((line = bufferedReader.readLine()).charAt(0) != ';') {
    	            	
    	            	List<String> itemList = getItemsOfLine(line,22);  	            	
    	            	
    	                String ProjectCode = itemList.get(0);
    	                ProjectCode = StringUtil.trimLeft(ProjectCode);
    	                ProjectCode = StringUtil.trimRight(ProjectCode);
    	                
    	                String ProjectDesc = itemList.get(1).toUpperCase();
    	                ProjectDesc = StringUtil.trimLeft(ProjectDesc);
    	                ProjectDesc = StringUtil.trimRight(ProjectDesc);
    	                
    	                String ProjectDirector = itemList.get(2).toUpperCase();
    	                ProjectDirector = StringUtil.trimLeft(ProjectDirector);
    	                ProjectDirector = StringUtil.trimRight(ProjectDirector);

    	                String ProjectManager = itemList.get(3).toUpperCase();
    	                ProjectManager = StringUtil.trimLeft(ProjectManager);
    	                ProjectManager = StringUtil.trimRight(ProjectManager);    	                
    	                
    	                String ProjectYear = itemList.get(4);
    	                ProjectYear = StringUtil.trimLeft(ProjectYear);
    	                ProjectYear = StringUtil.trimRight(ProjectYear);
    	                   	                
    	                String DocType = itemList.get(11).toUpperCase();
    	                DocType = StringUtil.trimLeft(DocType);
    	                DocType = StringUtil.trimRight(DocType);
    	                
    	                String Supplier = itemList.get(12);
    	                Supplier = StringUtil.trimLeft(Supplier);
    	                Supplier = StringUtil.trimRight(Supplier);
    	                
    	                String SupplierRef = itemList.get(13);
    	                SupplierRef = StringUtil.trimLeft(SupplierRef);
    	                SupplierRef = StringUtil.trimRight(SupplierRef);
    	                
    	                String DocumentNb = itemList.get(14);
    	                DocumentNb = StringUtil.trimLeft(DocumentNb);
    	                DocumentNb = StringUtil.trimRight(DocumentNb);
    	                
    	                String DocDate = itemList.get(15);
    	                DocDate = StringUtil.trimLeft(DocDate);
    	                DocDate = StringUtil.trimRight(DocDate);
    	                
    	                String DocComment = itemList.get(16);
    	                DocComment = StringUtil.trimLeft(DocComment);
    	                DocComment = StringUtil.trimRight(DocComment);  
    	                
    	                String DocYear = itemList.get(17).toUpperCase();
    	                DocYear = StringUtil.trimLeft(DocYear);
    	                DocYear = StringUtil.trimRight(DocYear);
    	                    	                    	                
    	                double DocAmount = round(Double.valueOf(itemList.get(18).replace(",", ".")),2);
    	                
    	                String AmountToString = new Double(DocAmount).toString();
    	                
    	                String DocUniqueId = ProjectCode.concat(DocType).concat(DocumentNb).concat(AmountToString);
    	                
    	                if (!ProjectCode.equals(currentProject)) { // a new project analytical code has been detected
    	                	
    	                	Project savedProject = dao.getProjectByAnalyticalCode(ProjectCode);
    	                	
    	                	if (savedProject == null) { // the project does not exist in the database
    	                		
    	                		Project newProject = new Project(ProjectCode,
   								     							 ProjectDesc,
   								     							 ProjectDirector,
   								     							 ProjectManager,
   								     							 ProjectYear);
    	                		
    	                		dao.addProject(newProject);	
    	                	}
    	                	currentProject = ProjectCode;
    	                }
    	                
    	                if (DocType.equals("BUDGET B")) {
    	                	
    	                	Budget savedBudget = dao.getBudgetByUniqueId(DocUniqueId);
    	                	
    	                	if (savedBudget == null) { // the budget line reference does not exist in the database
    	                		
    	                		Budget newBudget = new Budget(DocUniqueId,
    	                									  ProjectCode,
    	                									  BUDGET_B,
    	                									  DocumentNb,
    	                									  DocDate,
    	                									  DocComment,
    	                									  DocYear,   	                									  
    	                									  DocAmount);
    	                		dao.addBudget(newBudget);
    	                	}
    	                }
 
    	                if (DocType.equals("BUDGET C")) {
    	                	
    	                	Budget savedBudget = dao.getBudgetByUniqueId(DocUniqueId);
    	                	
    	                	if (savedBudget == null) { // the budget line reference does not exist in the database
    	                		
    	                		Budget newBudget = new Budget(DocUniqueId,
    	                									  ProjectCode,
    	                									  BUDGET_C,
    	                									  DocumentNb,
    	                									  DocDate,
    	                									  DocComment,
    	                									  DocYear,   	                									  
    	                									  DocAmount);
    	                		dao.addBudget(newBudget);
    	                	}
    	                }
    	                
    	                if (DocType.equals("COMMANDES")) {
    	                	
    	                	PurchaseOrder savedPO = dao.getPOByUniqueId(DocUniqueId);
    	                	
    	                	if (savedPO == null) { // the purchase order line reference does not exist in the database
    	                		
    	                		String poNb = DocComment.substring(13, 21);
    	                		
    	                		PurchaseOrder newPO = new PurchaseOrder(DocUniqueId,
    	                											    ProjectCode,
    	                										        poNb,
    	                										        Supplier,
    	                										        DocumentNb,
    	                										        DocDate,
    	                										        DocComment,
    	                										        DocYear,   	                									  
    	                										        DocAmount);
    	                		dao.addPO(newPO);
    	                	}
    	                }	                
    	                
    	                if (DocType.equals("FACTURES")) {
    	                	
    	                	Bill savedBill = dao.getBillByUniqueId(DocUniqueId);
    	                	
    	                	if (savedBill == null) { // the bill reference does not exist in the database
    	                		
    	                		String poNb = DocComment.substring(2, 9);
    	                		
    	                		Bill newBill = new Bill(DocUniqueId,
    	                								ProjectCode,
    	                								poNb,
    	                								SupplierRef,
    	                								Supplier,
    	                								DocumentNb,
    	                								DocDate,
    	                								DocComment,
    	                								DocYear,   	                									  
    	                								DocAmount);
    	                		dao.addBill(newBill);
    	                	}
    	                }  	                
   	                	  	                	             
//    		            SnapshotData newSnapshotData = new SnapshotData(ProjectCode,
//    		            										        ProjectDesc,
//    		            										        ProjectDirector,
//    		            										        ProjectManager,
//    		            										        ProjectYear,
//    		            										        DocType,
//    		            										        Supplier,
//    		            										        SupplierRef,
//    		            										        DocumentNb,
//    		            										        DocDate,
//    		            										        DocComment,
//    		            										        DocYear,
//    		            										        DocAmount);
//    		                  	
//    		            dao.addSnapshotData(newSnapshotData);
    		            
    	            }	               

    	        } finally {
    	            stream.close();
    	            forward = SUCCESS;   	            
    	        }
    	        
    		} catch (IOException e) {
    			forward = ERROR;
    			e.printStackTrace();
    		} catch (FileUploadException e) {
    			forward = ERROR;
    			e.printStackTrace();
    		}        	
        		
        }
		
    	RequestDispatcher view = request.getRequestDispatcher(forward);
        try {
			view.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	   private List<String> getItemsOfLine(String line, int nbOfItems ) {
		   	
	       List<String> itemList = new ArrayList<String>();
	       for (int i = 0; i < nbOfItems; i++) {
	           int pointIndex = line.indexOf(";");
	           String item = line.substring(0, pointIndex);
	           itemList.add(item);
	           line = line.substring(pointIndex + 1);
	       }
	       itemList.add(line);
	       return itemList;
	   }
	   
	   public double round(double what, int howmuch) {
	   	return (double)( (int)(what * Math.pow(10,howmuch) + .5) ) / Math.pow(10,howmuch);
	   }
	
}
