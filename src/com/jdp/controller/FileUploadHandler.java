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
        
        if (action.equalsIgnoreCase("uploadTeamMembersData")){ //------------------------------------------------

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
    	            
    	            int currentTM = 0;
    	            
    	            while ((line = bufferedReader.readLine()).charAt(0) != ';') {
    	            	
    	            	List<String> itemList = getItemsOfLine(line,3);
    	            	
    	                String DepartmentName = itemList.get(0);
    	                DepartmentName = StringUtil.trimLeft(DepartmentName);
    	                DepartmentName = StringUtil.trimRight(DepartmentName);
    	                
    	                String ServiceName = itemList.get(1);
    	                ServiceName = StringUtil.trimLeft(ServiceName);
    	                ServiceName = StringUtil.trimRight(ServiceName);
    	                ServiceName = ""; // Temporary until new organization and Services Nemes definition - JDP 28/12/2015

    	                String TeamMemberName = itemList.get(2);
    	                TeamMemberName = StringUtil.trimLeft(TeamMemberName);
    	                TeamMemberName = StringUtil.trimRight(TeamMemberName);
    	                TeamMemberName = TeamMemberName.toUpperCase();
    	                
    	                int TeamMemberId = TeamMemberName.hashCode();
    	                	
        	            if (!(TeamMemberId == currentTM)) { // a new Team Member Id has been detected
        	            	
        	            	Team savedTM = tsdao.getTeamMemberById(TeamMemberId);
        	                	
        	                if (savedTM == null) { // the Team Member does not exist in the database, then we create a new entry
        	                		
        	                	Team newTM = new Team(TeamMemberId,
        	                						  DepartmentName,
        	                						  ServiceName,
        	                						  TeamMemberName,
        	                						  "",
        	                						  true);

        	                		tsdao.addTeamMember(newTM);
        	                		
        	                } else { // the Team Member already exist in the database, then we do an update of the data
        	                		
        	                	savedTM.setDepartmentName(DepartmentName);
        	                	savedTM.setServiceName(ServiceName);
        	                	savedTM.setTeamMemberName(TeamMemberName);	                	
           		
        	                	tsdao.addTeamMember(savedTM);
        	                		
        	                }
        	            }     	 
    	                
    	                currentTM = TeamMemberId;
	            	
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
        
        if (action.equalsIgnoreCase("uploadSummaryData")){ //------------------------------------------------
        	
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
    	            
    	            int currentMissionId = 0;
    	               	            
    	            while ((line = bufferedReader.readLine()).charAt(0) != ';') {
    	            	
    	            	List<String> itemList = getItemsOfLine(line,6);
    	            	
    	                String personName = itemList.get(0);
    	                String year = itemList.get(1);
    	                String month = itemList.get(2);
    	                String missionType = itemList.get(3).toUpperCase();
    	                String missionName = itemList.get(4);
    	                String activityName = itemList.get(5);
    	                double prestation = Double.valueOf(itemList.get(6).replace(",", "."));
    	                double value = round(prestation,2);
    	                
 //   	                System.out.println(value);
    	                
    	                int index = Integer.parseInt(month);
    	                
    	                if (activityName.equalsIgnoreCase("Total")){
    	                	activityName = "Projets";
    	                }

    	                if (activityName.equalsIgnoreCase("Absence")){
    	                	activityName = "Congés & Absences";
    	                }
    	                
    	                if (activityName.equalsIgnoreCase("OTHERS")){
    	                	activityName = "Activités Hors Projets";
    	                }
    	                
    	            	personName = personName.toUpperCase();
    	            	personName = StringUtil.trimLeft(personName);
    	            	personName = StringUtil.trimRight(personName);
    	            	
    	            	missionName = missionName.toUpperCase();
    	            	missionName = StringUtil.trimLeft(missionName);
    	            	missionName = StringUtil.trimRight(missionName);
    	            	
    	            	int missionId = missionName.hashCode(); 
    	            	
    	            	if (!(missionId == currentMissionId)) { // a new Mission Id has been detected
        	            	
        	            	Mission savedMission = tsdao.getMissionById(missionId);
        	                	
        	                if (savedMission == null) { // the mission does not exist in the database, then we create a new entry
        	                		
        	                	Mission newMission = new Mission(missionId,
        	                						             "BIS",
        	                						             "SDM",
        	                						             missionName);

        	                		tsdao.addMission(newMission);
        	                		
        	                } else { // the mission already exist in the database, then we do an update of the data
        	                		
        	                	savedMission.setDepartmentName("BIS");
        	                	savedMission.setServiceName("SDM");
        	                	savedMission.setMissionName(missionName);	                	
           		
        	                	tsdao.addMission(savedMission);
        	                		
        	                }
        	            }
    	            	
    	            	currentMissionId = missionId;
    	            	
    	            	String code = personName + missionName + activityName + year;
    	            	int prId = code.hashCode();
    	            	
    	            	Prestation savedPrestation = tsdao.getPrestationById(prId);
       	            	       	                	
    	                if (savedPrestation == null) {   	                		
    	                	Prestation newPrestation = new Prestation(prId,
    	                											  personName,
    	                											  missionName,
    	                											  missionType,
    	                											  activityName,
    	                											  year);
    	                	newPrestation.setWorkDays(index, value);    	                	
    	                	tsdao.addPrestation(newPrestation);   	                		
    	                } else {	                	
    	                	savedPrestation.setWorkDays(index, value);	                	       		
    	                	tsdao.addPrestation(savedPrestation);
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
    	               	            
    	            while ((line = bufferedReader.readLine()).charAt(0) != ';') {
    	            	
    	            	List<String> itemList = getItemsOfLine(line,28);
    	            	
    	                String FullName = itemList.get(0);
    	                FullName = StringUtil.trimLeft(FullName);
    	                FullName = StringUtil.trimRight(FullName);
    	                FullName = FullName.toUpperCase();
    	                
    	                int TeamMemberId = FullName.hashCode();
    	                
    	                String AllocationId = itemList.get(1);
    	                AllocationId = StringUtil.trimLeft(AllocationId);
    	                AllocationId = StringUtil.trimRight(AllocationId);
    	                
    	                String Service = itemList.get(8);
    	                Service = StringUtil.trimLeft(Service);
    	                Service = StringUtil.trimRight(Service);   	                
    	                
    	                if (Service.equals("Svc. WAP")) {
    	                	Service = "WAP";
    	                } else if (Service.equals("Svc. IS")) {
    	                	Service = "IS";
    	                } else if (Service.equals("Svc. Architecture")) {
    	                	Service = "ARCH";
    	                } else {
    	                	Service = "Autre";
    	                }
    	                
    	                int Year = 0;
    	                String readValue = itemList.get(12);
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
    	                   	                
    	                String Mission = itemList.get(19);
    	                Mission = StringUtil.trimLeft(Mission);
    	                Mission = StringUtil.trimRight(Mission);
    	                
    	                int MissionId = Mission.hashCode();
    	                
    	                String MissionType = itemList.get(20);
    	                MissionType = StringUtil.trimLeft(MissionType);
    	                MissionType = StringUtil.trimRight(MissionType);    	                
    	                
    	                String Activity = itemList.get(22);
    	                Activity = StringUtil.trimLeft(Activity);
    	                Activity = StringUtil.trimRight(Activity); 
    	                
    	                if ((MissionType.equals("Projet")) || (MissionType.equals("Service"))) {
    	                	Activity = "--";
    	                }
    	                
                		if (MissionType.equals("Générique")){           			
                			if ((!(Activity.equals("Absence"))) && (!(Activity.equals("Formations")))) {
                				Activity = "Autre";
                			}
                		}
    	                
    	                double WorkInHours = 0.00;       
    	                readValue = itemList.get(26);
    	                if (!readValue.isEmpty()){
    	                	WorkInHours = round(Double.valueOf(readValue.replace(",", ".")),2);
    	                }
                		
    	                double WorkInDays = 0.00;       
    	                readValue = itemList.get(27);
    	                if (!readValue.isEmpty()){
    	                	WorkInDays = round(Double.valueOf(readValue.replace(",", ".")),2);
    	                }
    	                    	                    	            	
    	                System.out.println("Service : "+Service);
    	                System.out.println("AllocationId : "+AllocationId);    	                
    	                System.out.println("Working Days : "+WorkInDays);   	                
    	                  	                
    	                if (!(Service.equals("Autre"))) { // Only consider Allocations for WAP/IS/ARCH
    	                	
    	                	// Check if we already have registered this Team Member
        	            	Team savedTM = tsdao.getTeamMemberById(TeamMemberId);
    	                	
        	                if (savedTM == null) { // the Team Member does not exist in the database, then we create a new entry
        	                		
        	                	Team newTM = new Team(TeamMemberId,
        	                						  "Projects",
        	                						  Service,
        	                						  FullName,
        	                						  "",
        	                						  true);

        	                		tsdao.addTeamMember(newTM);
        	                		
        	                } 
        	                
        	                if ((MissionType.equals("Projet")) || (MissionType.equals("Service"))) {
        	                	
        	                	// Check if we already have registered this mission
            	                Mission savedMission = tsdao.getMissionById(MissionId);
            	                
            	                String Department = "";
            	                
            	                if (MissionType.equals("Projet")) {
            	                	Department = "Projects";
            	                }
            	                
            	                if (MissionType.equals("Service")) {
            	                	Department = "Services";
            	                }
            	                
            	                if (savedMission == null) { // the Mission does not exist in the database, then we create a new entry
        	                		
            	                	Mission newMission = new Mission(MissionId,
            	                						  		     Department,
            	                						  		     Service,
            	                						  		     Mission);

            	                	tsdao.addMission(newMission);
            	                		
            	                }
        	                	
        	                }
    	                	
    	                	// Check if we already have registered this allocation
            				Allocation savedAllocation = tsdao.getAllocationById(AllocationId);
    	                	
            				if (savedAllocation == null) {// The allocation does not exists, create it
            					
            					Allocation newAllocation = new Allocation(AllocationId,
										  								  FullName,
										  								  Service,
										  								  Year,
										  								  Month,
										  								  Week,
										  								  Day,
										  								  Mission,
										  								  MissionType,
										  								  Activity,
										  								  WorkInHours,
										  								  WorkInDays);
            					
            					tsdao.addAllocation(newAllocation);
            					
            				} else { // Update the existing allocation
            					
            					savedAllocation.setFullName(FullName);
            					savedAllocation.setService(Service);
            					savedAllocation.setYear(Year);
            					savedAllocation.setMonth(Month);
            					savedAllocation.setWeek(Week);
            					savedAllocation.setDay(Day);
            					savedAllocation.setMission(Mission);
            					savedAllocation.setMissionType(MissionType);
            					savedAllocation.setActivity(Activity);
            					savedAllocation.setWorkInHours(WorkInHours);
            					savedAllocation.setWorkInDays(WorkInDays);
            					
            					tsdao.addAllocation(savedAllocation);
            					
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
    	                
    	                if (activeProject.isActive()) {
    	                	
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
    	            	
    	            	// Get Project Analytical Code (CA Projet)            	
    	                String ProjectCode = itemList.get(0);
    	                ProjectCode = StringUtil.trimLeft(ProjectCode);
    	                ProjectCode = StringUtil.trimRight(ProjectCode);
    	                
    	                // Only consider the Projects linked to the activities of Service WAP (So far)
    	                ActiveProject activeProject = checkActiveProject(ProjectCode);
    	                
    	                if (activeProject.isActive()) {
    	                	
    	                	// Retrieve the description, the director name, and the project manager from the active project list
        	                String ProjectDesc = activeProject.getDescription();      	                
        	                String ProjectDirector = activeProject.getDirector();
        	                String ProjectManager = activeProject.getManager();
        	                
        	                // Retrieve the project year from the file (Année Prj)
        	                String ProjectYear = itemList.get(4);
        	                ProjectYear = StringUtil.trimLeft(ProjectYear);
        	                ProjectYear = StringUtil.trimRight(ProjectYear);
        	                   	                
        	                // Retrieve the document type from the file (Type_input)
        	                // The possible values are BUDGET B, BUDGET C, Commandes, Factures
        	                String DocType = itemList.get(11).toUpperCase();
        	                DocType = StringUtil.trimLeft(DocType);
        	                DocType = StringUtil.trimRight(DocType);
        	                
        	                // Retrieve the document type from the file (Tiers)
        	                String Supplier = itemList.get(12);
        	                Supplier = StringUtil.trimLeft(Supplier);
        	                Supplier = StringUtil.trimRight(Supplier);
        	                
          	                // Retrieve the document type from the file (Ref Tiers)
        	                String SupplierRef = itemList.get(13);
        	                SupplierRef = StringUtil.trimLeft(SupplierRef);
        	                SupplierRef = StringUtil.trimRight(SupplierRef);
        	                
        	                // Retrieve the document type from the file (N° Doc)
        	                String DocumentNb = itemList.get(14);
        	                DocumentNb = StringUtil.trimLeft(DocumentNb);
        	                DocumentNb = StringUtil.trimRight(DocumentNb);
        	                
        	                // Retrieve the document type from the file (Date Doc)
        	                String DocDate = itemList.get(15);
        	                DocDate = StringUtil.trimLeft(DocDate);
        	                DocDate = StringUtil.trimRight(DocDate);
        	                
        	                // Retrieve the document type from the file (Comment)
        	                String DocComment = itemList.get(16);
        	                DocComment = StringUtil.trimLeft(DocComment);
        	                DocComment = StringUtil.trimRight(DocComment);  
        	                
        	                // Retrieve the document type from the file (Année)
        	                String DocYear = itemList.get(17).toUpperCase();
        	                DocYear = StringUtil.trimLeft(DocYear);
        	                DocYear = StringUtil.trimRight(DocYear);
        	                    	                    	                
        	                // Retrieve the document type from the file (Montant TVAC)
        	                double DocAmount = round(Double.valueOf(itemList.get(18).replace(",", ".")),2);
        	                String AmountToString = new Double(DocAmount).toString();
        	                
        	                // Generate a unique Id for the data
        	                String DocId = ProjectCode.concat(DocType).concat(DocumentNb).concat(AmountToString);
        	                int DocUniqueId = DocId.hashCode();
        	                         
        	                if (!ProjectCode.equals(currentProject)) { // a new project analytical code has been detected
        	                	
        	                	// Check if a project has already been saved for this analytical code
        	                	Project savedProject = dao.getProjectByAnalyticalCode(ProjectCode);
        	                	
        	                	if (savedProject == null) { // the project does not exist in the database, so create an new entry with basic data
        	                		
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
        	                	
        	                	} else { // the project exists in the database, just update the project basic data
        	                		
        	                		savedProject.setDescription(ProjectDesc);
        	                		savedProject.setDirector(ProjectDirector);
        	                		savedProject.setManager(ProjectManager);
        	                		
        	                		dao.addProject(savedProject);
        	                		
        	                	}
        	                	
        	                	currentProject = ProjectCode;
        	                }
        	                
        	                if (DocType.equals("BUDGET B")) { // The document type is a budgetary decision of type "Liquidation"
        	                	
        	                	// Verify if this entry has already been registered for that Id
        	                	Budget savedBudget = dao.getBudgetByUniqueId(DocUniqueId);
        	                	
        	                	if (savedBudget == null) { // the budget line reference does not exist in the database, create a new budget line
        	                		
        	                		Budget newBudget = new Budget(DocUniqueId,
        	                									  ProjectCode,
        	                									  BUDGET_B,
        	                									  DocumentNb,
        	                									  DocDate,
        	                									  DocComment,
        	                									  DocYear,   	                									  
        	                									  DocAmount);
        	                		dao.addBudget(newBudget);
        	                		
        	                	} else { // the budget line exists in the database, just update the data
        	                		
        	                		savedBudget.setAnalyticalCode(ProjectCode);
        	                		savedBudget.setDocumentNb(DocumentNb);
        	                		savedBudget.setDocumentDate(DocDate);
        	                		savedBudget.setDocumentDescription(DocComment);
        	                		savedBudget.setDocumentYear(DocYear);
        	                		savedBudget.setAmount(DocAmount);
        	                		
        	                		dao.addBudget(savedBudget);
        	                		
        	                	}
        	                }
    	                	
        	                if (DocType.equals("BUDGET C")) { // The document type is a budgetary decision of type "Engagement"
        	                	
        	                	// Verify if this entry has already been registered for that Id
        	                	Budget savedBudget = dao.getBudgetByUniqueId(DocUniqueId);
        	                	
        	                	if (savedBudget == null) { // the budget line reference does not exist in the database, create a new budget line
        	                		
        	                		Budget newBudget = new Budget(DocUniqueId,
        	                									  ProjectCode,
        	                									  BUDGET_C,
        	                									  DocumentNb,
        	                									  DocDate,
        	                									  DocComment,
        	                									  DocYear,   	                									  
        	                									  DocAmount);
        	                		dao.addBudget(newBudget);
        	                	} else { // the budget line exists in the database, just update the data
        	                		
        	                		savedBudget.setAnalyticalCode(ProjectCode);
        	                		savedBudget.setDocumentNb(DocumentNb);
        	                		savedBudget.setDocumentDate(DocDate);
        	                		savedBudget.setDocumentDescription(DocComment);
        	                		savedBudget.setDocumentYear(DocYear);
        	                		savedBudget.setAmount(DocAmount);
        	                		
        	                		dao.addBudget(savedBudget);
        	                		
        	                	}
        	                }
        	                
        	                if (DocType.equals("COMMANDES")) { // The document type is a Purchase Order reference 
        	                	
        	                	// Verify if this entry has already been registered for that Id
        	                	PurchaseOrder savedPO = dao.getPOByUniqueId(DocUniqueId);
        	                	
    	                		//Extract the PO number from the document comment, there is no field with the PO number
    	                		String poNb = DocComment.substring(13, 21);
        	                	
        	                	if (savedPO == null) { // the purchase order line reference does not exist in the database, create a new Purchase Order entry
        	                		       	                		
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
        	                		
        	                	} else { // the Purchase Order exists in the database, just update the data
        	                		
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
        	                
        	                if (DocType.equals("FACTURES")) { // The document type is a Purchase Order reference
        	                	
        	                	// Verify if this entry has already been registered for that Id
        	                	Bill savedBill = dao.getBillByUniqueId(DocUniqueId);
        	                	
        	                	//Extract the PO number from the document comment, there is no field with the PO number
        	                	String poNb = DocComment.substring(2, 9);
        	                	
        	                	if (savedBill == null) { // the bill reference does not exist in the database, create a new bill entry
        	                			
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
        	                		
        	                	}  else { // the Bill exists in the database, just update the data
        	                		
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
	   
	//------------------------------------------------------------------------------------------------------------------
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
	    	   activeProject.setDescription("IAM");
			   activeProject.setManager("F. Monaco");
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
           case "41500800": 
        	   activeProject.setDescription("Software Architect");
			   activeProject.setManager("J. De Pessemier");
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
           default: activeProject.setDescription("");
					activeProject.setDirector("");
					activeProject.setManager("");
					activeProject.setActive(false);
                    break;
		   }		   
		   
		   return activeProject;
	   }

	   
	   //------------------------------------------------------------------------------------------------------------------
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
	   
	   //------------------------------------------------------------------------------------------------------------------	   
	   public double round(double what, int howmuch) {
	   	return (double)( (int)(what * Math.pow(10,howmuch) + .5) ) / Math.pow(10,howmuch);
	   }
	
}
