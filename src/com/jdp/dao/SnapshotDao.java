package com.jdp.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.jdp.model.*;

public class SnapshotDao {
	
    public SnapshotDao() {
    	ObjectifyService.register(SnapshotData.class);
    	ObjectifyService.register(Project.class);
    	ObjectifyService.register(Budget.class);
    	ObjectifyService.register(PurchaseOrder.class);
    	ObjectifyService.register(Bill.class);
    }
    
//  -------------------------------------------------------------------------------------- Projects    
    
    public void addProject(Project project) {
    	
    	ObjectifyService.ofy().save().entity(project).now();	
    }
    
    public List<Project> getProjects() {
		
		List<Project> projects = (List<Project>) ofy().load().type(Project.class).list();
        return projects;
    }
    
    public Project getProjectByAnalyticalCode(String analyticalcode) {
    	
//    	Project myProject = new Project("41220030",
//    			"Test",
//    			"NJE",
//    			"JDP",
//    			"2014");
    	
//    	Key<Project> keyMyProject = Key.create(Project.class, analyticalcode);
//    	Project myProject = ofy().load().key(keyMyProject).now();
    	
//    	Project myProject = ofy().load().type(Project.class).filter("analyticalCode =", analyticalcode).first().now();
    
		
    	List<Project> myProjects = ofy().load().type(Project.class).filter("analyticalCode =", analyticalcode).list();
    	   	
    	Project myProject = new Project();
    	
    	if (myProjects.size() == 0) {
    		myProject = null;
    	} else {
    		myProject = myProjects.get(0);
    	}
    	
		return myProject;	
    }
   
//  -------------------------------------------------------------------------------------- Budgets
    
    public void addBudget(Budget budget) {
        
        ObjectifyService.ofy().save().entity(budget).now();    
    }
    
    public List<Budget> getBudgets() {
		
		List<Budget> budgets = ofy().load().type(Budget.class).list();
		
        return budgets;
    }
    
    public List<Budget> getBudgetsBByAnalyticalCode(String analyticalCode) {
		
		List<Budget> budgetsB = ofy().load().type(Budget.class)
				.filter("analyticalCode =", analyticalCode)
				.filter("documentType =", "Liquidation")
				.order("documentNb").list();
		
        return budgetsB;
        
    }
    
    public List<Budget> getBudgetsCByAnalyticalCode(String analyticalCode) {
		
		List<Budget> budgetsC = ofy().load().type(Budget.class)
				.filter("analyticalCode =", analyticalCode)
				.filter("documentType =", "Engagement")
				.order("documentNb").list();
		
        return budgetsC;
    }

    public Budget getBudgetByUniqueId(String id) {
		
		List<Budget> myBudgets = ofy().load().type(Budget.class)
				.filter("uniqueId =", id).list();
			
    	Budget myBudget = new Budget();
    	
    	if (myBudgets.size() == 0) {
    		myBudget = null;
    	} else {
    		myBudget = myBudgets.get(0);
    	}
		
		return myBudget;
    }
       
    public Budget getBudgetByDocumentNb(String documentnb) {

		List<Budget> myBudgets = ofy().load().type(Budget.class)
				.filter("documentNb =", documentnb).list();
		
    	Budget myBudget = new Budget();
    	
    	if (myBudgets.size() == 0) {
    		myBudget = null;
    	} else {
    		myBudget = myBudgets.get(0);
    	}
		
		return myBudget;
    }
      
//  -------------------------------------------------------------------------------------- Purchase Orders
    
    public void addPO(PurchaseOrder po) {
        
        ObjectifyService.ofy().save().entity(po).now();
    }
    
    public List<PurchaseOrder> getPOs() {
		
		List<PurchaseOrder> POs = ofy().load().type(PurchaseOrder.class).list();
		
        return POs;
    }
 
    public PurchaseOrder getPOByUniqueId(String id) {
    	
    	List<PurchaseOrder> myPOs = ofy().load().type(PurchaseOrder.class)
    			.filter("uniqueId =", id).list();
		
		PurchaseOrder myPO = new PurchaseOrder();
    	
    	if (myPOs.size() == 0) {
    		myPO = null;
    	} else {
    		myPO = myPOs.get(0);
    	}	
		
		return myPO;	
    }
    
    public PurchaseOrder getPOByDocumentNb(String documentnb) {
		
		List<PurchaseOrder> myPOs = ofy().load().type(PurchaseOrder.class)
				.filter("documentNb =", documentnb).list();
		
		
		PurchaseOrder myPO = new PurchaseOrder();
    	
    	if (myPOs.size() == 0) {
    		myPO = null;
    	} else {
    		myPO = myPOs.get(0);
    	}	
		
		return myPO;
    }
    
     
//  -------------------------------------------------------------------------------------- Bills
    
    public void addBill(Bill bill) {
        
        ObjectifyService.ofy().save().entity(bill).now();
    }
    
    public List<Bill> getBills() {
		
		List<Bill> Bills = ofy().load().type(Bill.class).list();
		
        return Bills;
    }   
    
    public Bill getBillByUniqueId(String id) {
		
		List<Bill> myBills = ofy().load().type(Bill.class).filter("uniqueId =", id).list();
		
		
		Bill myBill = new Bill();
    	
    	if (myBills.size() == 0) {
    		myBill = null;
    	} else {
    		myBill = myBills.get(0);
    	}
		
		return myBill;
    }
    
    public Bill getBillByDocumentNb(String documentnb) {
		
    	List<Bill> myBills = ofy().load().type(Bill.class).filter("documentNb =", documentnb).list();
		
		Bill myBill = new Bill();
    	
    	if (myBills.size() == 0) {
    		myBill = null;
    	} else {
    		myBill = myBills.get(0);
    	}
		
		return myBill;
    }
    
//  -------------------------------------------------------------------------------------- Snapshots Data
    
//    public void addSnapshotData(SnapshotData SnapshotData) {
////    	Objectify obj = ObjectifyService.beginTransaction();
////        obj.put(SnapshotData);
////        obj.getTxn().commit();
//        
//        ObjectifyService.ofy().save().entity(SnapshotData);
//    }
//    
//    public List<SnapshotData> getAllSnapshotDatas() {
//    	
//        List<SnapshotData> SnapshotDatas = new ArrayList<SnapshotData>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<SnapshotData> query = ofy.query(SnapshotData.class);
//		
//		for (SnapshotData snapshotdataFromQuery : query) {
//			SnapshotDatas.add(snapshotdataFromQuery);
//		}
//		
//        return SnapshotDatas;
//    }
//    

}
