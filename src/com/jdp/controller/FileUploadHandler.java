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
    private TimeSheetDao tsdao;

    public FileUploadHandler() {
        super();
        dao = new SnapshotDao();
        tsdao = new TimeSheetDao();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("uploadTimeSheetsData")){ //------------------------------------------------

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
    	            
    	            int currentTS = 0;
    	            
    	            while ((line = bufferedReader.readLine()).charAt(0) != ';') {
    	            	
    	            	List<String> itemList = getItemsOfLine(line,27);
    	            	
    	                String FullName = itemList.get(0);
    	                FullName = StringUtil.trimLeft(FullName);
    	                FullName = StringUtil.trimRight(FullName);
    	                
    	                int tsId = 0;
    	                String readValue = itemList.get(1);
    	                if (!readValue.isEmpty()){
    	                	tsId = Integer.valueOf(itemList.get(1));
    	                }
    	                
    	                String Company = itemList.get(2);
    	                Company = StringUtil.trimLeft(Company);
    	                Company = StringUtil.trimRight(Company);
    	                
    	                String DepartmentHead = itemList.get(7);
    	                DepartmentHead = StringUtil.trimLeft(DepartmentHead);
    	                DepartmentHead = StringUtil.trimRight(DepartmentHead);
    	                
    	                String Service = itemList.get(8);
    	                Service = StringUtil.trimLeft(Service);
    	                Service = StringUtil.trimRight(Service);
    	                
    	                String ServiceHead = itemList.get(9);
    	                ServiceHead = StringUtil.trimLeft(ServiceHead);
    	                ServiceHead = StringUtil.trimRight(ServiceHead);
    	                
    	                int Year = 0;
    	                readValue = itemList.get(12);
    	                if (!readValue.isEmpty()){
    	                	Year = Integer.valueOf(itemList.get(12));
    	                }
    	                
    	                int Month = 0;
    	                readValue = itemList.get(13);
    	                if (!readValue.isEmpty()){
    	                	Month = Integer.valueOf(itemList.get(13));
    	                }
    	                
    	                int Week = 0;
    	                readValue = itemList.get(14);
    	                if (!readValue.isEmpty()){
    	                	Week = Integer.valueOf(itemList.get(14));
    	                }
    	                
    	                int Day = 0;
    	                readValue = itemList.get(15);
    	                if (!readValue.isEmpty()){
    	                	Day = Integer.valueOf(itemList.get(15));
    	                }
    	                
    	                int DayInWeek = 0;
    	                readValue = itemList.get(16);
    	                if (!readValue.isEmpty()){
    	                	DayInWeek = Integer.valueOf(itemList.get(16));
    	                }
    	                
    	                String Subject = itemList.get(18);
    	                Subject = StringUtil.trimLeft(Subject);
    	                Subject = StringUtil.trimRight(Subject);
    	                
    	                String Mission = itemList.get(19);
    	                Mission = StringUtil.trimLeft(Mission);
    	                Mission = StringUtil.trimRight(Mission);
    	                
    	                String MissionType = itemList.get(20);
    	                MissionType = StringUtil.trimLeft(MissionType);
    	                MissionType = StringUtil.trimRight(MissionType);
    	                
    	                String Activity = itemList.get(22);
    	                Activity = StringUtil.trimLeft(Activity);
    	                Activity = StringUtil.trimRight(Activity);  
    	                
    	                String Task = itemList.get(23);
    	                Task = StringUtil.trimLeft(Task);
    	                Task = StringUtil.trimRight(Task);
  
    	                int WorkInHours = 0;
    	                readValue = itemList.get(26);
    	                if (!readValue.isEmpty()){
    	                	WorkInHours = Integer.valueOf(itemList.get(25));
    	                }  
    	                
    	                double WorkInDays = 0.00;       
    	                readValue = itemList.get(26);
    	                if (!readValue.isEmpty()){
    	                	WorkInDays = round(Double.valueOf(itemList.get(26).replace(",", ".")),2);
    	                }
    	            	
    	                if (!(Service.equals("Svc. SAP"))) {
    	                	
        	                if (!(tsId == currentTS)) { // a new TimeSheet Id has been detected
        	                	
        	                	TimeSheet savedTS = tsdao.getTimeSheetById(tsId);
        	                	
        	                	if (savedTS == null) { // the TimeSheet does not exist in the database, then we create a new entry
        	                		
        	                		TimeSheet newTS = new TimeSheet(tsId,
    		 														FullName,
    		 														Company,
    		 														DepartmentHead,
    		 														Service,
    		 														ServiceHead,
    		 														Year,
    		 														Month,
    		 														Week,
    		 														Day,
    		 														DayInWeek,
    		 														Mission,
    		 														MissionType,
    		 														Activity,
    		 														Task,
    		 														Subject,
    		 														WorkInHours,
    		 														WorkInDays);

        	                		tsdao.addTimeSheet(newTS);
        	                		
        	                	} else { // the TimeSheet already exist in the database, then we do an update of the data
        	                		
        	                		savedTS.setMission(Mission);
        	                		savedTS.setMissionType(MissionType);
        	                		savedTS.setActivity(Activity);
        	                		savedTS.setTask(Task);
        	                		savedTS.setSubject(Subject);
        	                		savedTS.setWorkInHours(WorkInHours);
        	                		savedTS.setWorkInDays(WorkInDays);
           		
        	                		tsdao.addTimeSheet(savedTS);
        	                		
        	                	}
        	                }     	
    	                } 
    	                
    	                currentTS = tsId;
	            	
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
        
        if (action.equalsIgnoreCase("uploadDirectorsReportData")){ //------------------------------------------------
        	
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
    	            	
    	            	List<String> itemList = getItemsOfLine(line,9);
    	            	
    	                String ProjectCode = itemList.get(0);
    	                ProjectCode = StringUtil.trimLeft(ProjectCode);
    	                ProjectCode = StringUtil.trimRight(ProjectCode);
    	                
    	                ActiveProject activeProject = checkActiveProject(ProjectCode);
    	                
    	                if (activeProject.getActive()) {
    	                	
        	                String ProjectDesc = activeProject.getDescription();      	                
        	                String ProjectDirector = activeProject.getDirector();
        	                String ProjectManager = activeProject.getManager();
        	                
        	                double reviewedBudgetCAmount = 0.00;
        	                String readAmount = itemList.get(4);
        	                if (!readAmount.isEmpty()){
        	                	reviewedBudgetCAmount = round(Double.valueOf(itemList.get(4).replace(",", ".")),2);
        	                } 
        	                
        	                double realizedBudgetCAmount = 0.00;       
        	                readAmount = itemList.get(5);
        	                if (!readAmount.isEmpty()){
        	                	realizedBudgetCAmount = round(Double.valueOf(itemList.get(5).replace(",", ".")),2);
        	                }
     
        	                double availableBudgetCAmount = 0.00;	                	
        	                readAmount = itemList.get(6);
        	                if (!readAmount.isEmpty()){
        	                	availableBudgetCAmount = round(Double.valueOf(itemList.get(6).replace(",", ".")),2);    	                } else {
        	                }
     
        	                double reviewedBudgetBAmount = 0.00;
        	                readAmount = itemList.get(7);
        	                if (!readAmount.isEmpty()){
        	                	reviewedBudgetBAmount = round(Double.valueOf(itemList.get(7).replace(",", ".")),2);
        	                }
        	                
        	                double realizedBudgetBAmount = 0.00;
        	                readAmount = itemList.get(8);
        	                if (!readAmount.isEmpty()){
        	                	realizedBudgetBAmount = round(Double.valueOf(itemList.get(8).replace(",", ".")),2);
        	                }
     
        	                double availableBudgetBAmount = 0.00;
        	                readAmount = itemList.get(9);
        	                if (!readAmount.isEmpty()){
        	                	availableBudgetBAmount = round(Double.valueOf(itemList.get(9).replace(",", ".")),2);
        	                }     	                
        	                
        	                if (!ProjectCode.equals(currentProject)) { // a new project analytical code has been detected
        	                	
        	                	SnapshotData savedSnapshotData = dao.getSnapshotDataByAnalyticalCode(ProjectCode);
        	                	
        	                	if (savedSnapshotData == null) { // the project does not exist in the database, then we create a new entry
        	                		
        	                		SnapshotData newSnapshotData = new SnapshotData(ProjectCode,
    		 									ProjectDesc,
    		 									ProjectDirector,
    		 									ProjectManager,
    		 									reviewedBudgetCAmount,
    		 									realizedBudgetCAmount,
    		 									availableBudgetCAmount,
    		 									reviewedBudgetBAmount,
    		 									realizedBudgetBAmount,
    		 									availableBudgetBAmount);

        	                		dao.addSnapshotData(newSnapshotData);
        	                		
        	                	} else { // the project already exist in the database, then we do an update of the data
        	                		
        	                		savedSnapshotData.setProjectDescription(ProjectDesc);
        	                		savedSnapshotData.setProjectDirector(ProjectDirector);
        	                		savedSnapshotData.setProjectManager(ProjectManager);
        	                		savedSnapshotData.setReviewedBudgetC(reviewedBudgetCAmount);
        	                		savedSnapshotData.setRealizedBudgetC(realizedBudgetCAmount);
        	                		savedSnapshotData.setAvailableBudgetC(availableBudgetCAmount);
        	                		savedSnapshotData.setReviewedBudgetB(reviewedBudgetBAmount);
        	                		savedSnapshotData.setRealizedBudgetB(realizedBudgetBAmount);
        	                		savedSnapshotData.setAvailableBudgetB(availableBudgetBAmount);
           		
        	                		dao.addSnapshotData(savedSnapshotData);
        	                		
        	                	}
        	                }
        	                
        	                currentProject = ProjectCode;                	
    	                }
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
        
        if (action.equalsIgnoreCase("uploadMissionsSituationtData")){ //-------------------------------------------------
        	
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
    	                
    	                ActiveProject activeProject = checkActiveProject(ProjectCode);
    	                
    	                if (activeProject.getActive()) {
    	                	
        	                String ProjectDesc = activeProject.getDescription();      	                
        	                String ProjectDirector = activeProject.getDirector();
        	                String ProjectManager = activeProject.getManager();
        	                
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
        	                
        	                String DocId = ProjectCode.concat(DocType).concat(DocumentNb).concat(AmountToString);
        	                int DocUniqueId = DocId.hashCode();
        	                         
        	                if (!ProjectCode.equals(currentProject)) { // a new project analytical code has been detected
        	                	
        	                	Project savedProject = dao.getProjectByAnalyticalCode(ProjectCode);
        	                	
        	                	if (savedProject == null) { // the project does not exist in the database
        	                		
        	                		Project newProject = new Project(ProjectCode,
       								     							 ProjectDesc,
       								     							 ProjectDirector,
       								     							 ProjectManager,
       								     							 ProjectYear,
       								     							 0.00,
       								     							 0.00,
       								     							 0.00,
       								     							 0.00);        	                		
        	                	
        	                		dao.addProject(newProject);       	                		
        	                	
        	                	} else { // the project exists in the database
        	                		
        	                		savedProject.setDescription(ProjectDesc);
        	                		savedProject.setDirector(ProjectDirector);
        	                		savedProject.setManager(ProjectManager);
        	                		
        	                		dao.addProject(savedProject);
        	                		
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
        	                		
        	                	} else {
        	                		
        	                		savedBudget.setAnalyticalCode(ProjectCode);
        	                		savedBudget.setDocumentNb(DocumentNb);
        	                		savedBudget.setDocumentDate(DocDate);
        	                		savedBudget.setDocumentDescription(DocComment);
        	                		savedBudget.setDocumentYear(DocYear);
        	                		savedBudget.setAmount(DocAmount);
        	                		
        	                		dao.addBudget(savedBudget);
        	                		
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
        	                	} else {
        	                		
        	                		savedBudget.setAnalyticalCode(ProjectCode);
        	                		savedBudget.setDocumentNb(DocumentNb);
        	                		savedBudget.setDocumentDate(DocDate);
        	                		savedBudget.setDocumentDescription(DocComment);
        	                		savedBudget.setDocumentYear(DocYear);
        	                		savedBudget.setAmount(DocAmount);
        	                		
        	                		dao.addBudget(savedBudget);
        	                		
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
        	                		
        	                	} else {
        	                		
        	                		String poNb = DocComment.substring(13, 21);
        	                		
        	                		savedPO.setAnalyticalCode(ProjectCode);
        	                		savedPO.setPoNb(poNb);
        	                		savedPO.setSupplier(Supplier);
        	                		savedPO.setDocumentNb(DocumentNb);
        	                		savedPO.setDocumentDate(DocDate);
        	                		savedPO.setDocumentDescription(DocComment);
        	                		savedPO.setDocumentYear(DocYear);
        	                		savedPO.setAmount(DocAmount);
        	                		
        	                		dao.addPO(savedPO);
        	                		
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
        	                		
        	                	}  else {
        	                		
        	                		String poNb = DocComment.substring(2, 9);
        	                		
        	                		savedBill.setAnalyticalCode(ProjectCode);
        	                		savedBill.setPoNb(poNb);
        	                		savedBill.setSupplier(Supplier);
        	                		savedBill.setDocumentNb(DocumentNb);
        	                		savedBill.setDocumentDate(DocDate);
        	                		savedBill.setDocumentDescription(DocComment);
        	                		savedBill.setDocumentYear(DocYear);
        	                		savedBill.setAmount(DocAmount);
        	                		
        	                		dao.addBill(savedBill);
        	                		
        	                	}
        	                }
        	                
    	                }
    	                
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
	
	   private ActiveProject checkActiveProject(String analyticalcode) {
		   
		   ActiveProject activeProject = new ActiveProject();
		   activeProject.setDirector("N. Jelinski");
		   
		   switch (analyticalcode) {
		   
           case "41100260": 
        	   activeProject.setDescription("BOS ASP");
           	   activeProject.setManager("T. Nguyen");
           	   activeProject.setActive(true);
           	   break;
           case "41220490": 
        	   activeProject.setDescription("BOS Core");
           	   activeProject.setManager("T. Nguyen");
           	   activeProject.setActive(true);
           	   break;
           case "41220010": 
        	   activeProject.setDescription("BOS Rollouts");
           	   activeProject.setManager("T. Nguyen");
           	   activeProject.setActive(true);
           	   break;
           case "41500400": 
        	   activeProject.setDescription("BOS Developments & Rollouts");
			   activeProject.setManager("T. Nguyen");
			   activeProject.setActive(true);
			   break;
           case "41501200": 
        	   activeProject.setDescription("CAS-IDM");
			   activeProject.setManager("F. Monaco");
			   activeProject.setActive(true);
			   break;
           case "41400380": 
        	   activeProject.setDescription("Fix-My-Street RBC 4.0");
			   activeProject.setManager("L. Afif");
			   activeProject.setActive(true);
			   break;
           case "41500300": 
        	   activeProject.setDescription("Fix-My-Street");
			   activeProject.setManager("L. Afif");
			   activeProject.setActive(true);
			   break;				
           case "39020000": 
        	   activeProject.setDescription("NOVA ASP");
			   activeProject.setManager("H. Dewyspelaere");
			   activeProject.setActive(true);
			   break;	
           case "41220480": 
        	   activeProject.setDescription("NOVA Core");
			   activeProject.setManager("H. Dewyspelaere");
			   activeProject.setActive(true);
			   break;
           case "41220030": 
        	   activeProject.setDescription("NOVA 5.0");
			   activeProject.setManager("H. Dewyspelaere");
			   activeProject.setActive(true);
			   break;
           case "41500700": 
        	   activeProject.setDescription("Plateforme M-GOVERNMENT");
			   activeProject.setManager("TBD");
			   activeProject.setActive(true);
			   break;
           case "41400750": 
        	   activeProject.setDescription("Migration be.brussels");
			   activeProject.setManager("D. Butaye");
			   activeProject.setActive(true);
			   break;
           case "41400740": 
        	   activeProject.setDescription("GeoPortail 2.0");
			   activeProject.setManager("G. Charlot");
			   activeProject.setActive(true);
			   break;
           case "41400250": 
        	   activeProject.setDescription("IRISbox Developments");
			   activeProject.setManager("F. Monaco");
			   activeProject.setActive(true);
			   break;			   
           case "41400520": 
        	   activeProject.setDescription("ISR");
			   activeProject.setManager("D. Le Grelle");
			   activeProject.setActive(true);
			   break;	
           case "41501100": 
        	   activeProject.setDescription("ISR Licences");
			   activeProject.setManager("D. Le Grelle");
			   activeProject.setActive(true);
			   break;			   
           case "41300440": 
        	   activeProject.setDescription("SEO");
			   activeProject.setManager("L. Afif");
			   activeProject.setActive(true);
			   break;	
           case "41400600": 
        	   activeProject.setDescription("SHARE CPAS 5.0");
			   activeProject.setManager("R. Himpe");
			   activeProject.setActive(true);
			   break;	
           case "41401800": 
        	   activeProject.setDescription("SHARE CPAS 6.0");
			   activeProject.setManager("R. Himpe");
			   activeProject.setActive(true);
			   break;	
           case "41500500": 
        	   activeProject.setDescription("SINCRHO Developments");
			   activeProject.setManager("TBD");
			   activeProject.setActive(true);
			   break;
           case "41500800": 
        	   activeProject.setDescription("Software Architect");
			   activeProject.setManager("J. De Pessemier");
			   activeProject.setActive(true);
			   break;			   			   			   
           case "41500900": 
        	   activeProject.setDescription("URBIS AAS - Migration");
			   activeProject.setManager("V. Streignard");
			   activeProject.setActive(true);
			   break;			   
           case "41400180": 
        	   activeProject.setDescription("Support Developments Tools");
			   activeProject.setManager("J. De Pessemier");
			   activeProject.setActive(true);
			   break;			   
           case "41220170": 
        	   activeProject.setDescription("Support Testing");
			   activeProject.setManager("J. De Pessemier");
			   activeProject.setActive(true);
			   break;			   		   
           case "41501000": 
        	   activeProject.setDescription("URBIS Online 3D");
			   activeProject.setManager("V. Streignard");
			   activeProject.setActive(true);
			   break;
           default: activeProject.setDescription("");
					activeProject.setDirector("");
					activeProject.setManager("");
					activeProject.setActive(false);
                    break;
		   }		   
		   
		   return activeProject;
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
