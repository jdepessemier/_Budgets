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
    	
    	ObjectifyService.ofy().save().entity(project);
    	
//    	Objectify obj = ObjectifyService.beginTransaction();
//        obj.put(project);
//        obj.getTxn().commit();
    }
    
    public List<Project> getProjects() {
    	
//        List<Project> projects = new ArrayList<Project>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Project> query = ofy.query(Project.class);
//		
//		for (Project projectFromQuery : query) {
//			projects.add(projectFromQuery);
//		}
		
		List<Project> projects = (List<Project>) ofy().load().type(Project.class).list();
        return projects;
    }
    
    public Project getProjectByAnalyticalCode(String analyticalcode) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Project> query = ofy.query(Project.class);
//		
//		Project myProject = query
//				.filter("analyticalCode =", analyticalcode).get();
		
    	List<Project> myProjects = ofy().load().type(Project.class).filter("analyticalCode =", analyticalcode).list();
    	
    	Project myProject = myProjects.get(0);
    	
		return myProject;
		
    }
   
//  -------------------------------------------------------------------------------------- Budgets
    
    public void addBudget(Budget budget) {
    	
//    	Objectify obj = ObjectifyService.beginTransaction();
//        obj.put(budget);
//        obj.getTxn().commit();
        
        ObjectifyService.ofy().save().entity(budget);
        
    }
    
    public List<Budget> getBudgets() {
    	
//        List<Budget> budgets = new ArrayList<Budget>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Budget> query = ofy.query(Budget.class);
//		
//		for (Budget budgetFromQuery : query) {
//			budgets.add(budgetFromQuery);
//		}
		
		List<Budget> budgets = ofy().load().type(Budget.class).list();
		
        return budgets;
    }
    
    public List<Budget> getBudgetsBByAnalyticalCode(String analyticalCode) {
    	
//        List<Budget> budgetsB = new ArrayList<Budget>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Budget> query = ofy.query(Budget.class);
//		
//		Budget myBudgetB = query
//				.filter("analyticalCode =", analyticalCode)
//				.filter("documentType =", "Liquidation").get();
//		
//		for (Budget budgetFromQuery : query) {
//			budgetsB.add(budgetFromQuery);
//		}
		
		List<Budget> budgetsB = ofy().load().type(Budget.class)
				.filter("analyticalCode =", analyticalCode)
				.filter("documentType =", "Liquidation").list();
		
        return budgetsB;
    }
    
    public List<Budget> getBudgetsCByAnalyticalCode(String analyticalCode) {
    	
//        List<Budget> budgetsC = new ArrayList<Budget>();
//        
//		Objectify ofy = ObjectifyService.begin();
//				
//		Query<Budget> query = ofy.query(Budget.class);
//		
//		Budget myBudgetC = query
//				.filter("analyticalCode =", analyticalCode)
//				.filter("documentType =", "Engagement").get();
//		
//		for (Budget budgetFromQuery : query) {
//			budgetsC.add(budgetFromQuery);
//		}
		
		List<Budget> budgetsC = ofy().load().type(Budget.class)
				.filter("analyticalCode =", analyticalCode)
				.filter("documentType =", "Liquidation").list();
		
        return budgetsC;
    }

    public Budget getBudgetByUniqueId(String id) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Budget> query = ofy.query(Budget.class);
//		
//		Budget myBudget = query
//				.filter("uniqueId =", id).get();
		
		List<Budget> myBudgets = ofy().load().type(Budget.class)
				.filter("uniqueId =", id).list();
		Budget myBudget = myBudgets.get(0);
		
		return myBudget;
    }
       
    public Budget getBudgetByDocumentNb(String documentnb) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Budget> query = ofy.query(Budget.class);
//		
//		Budget myBudget = query
//				.filter("documentNb =", documentnb).get();

		List<Budget> myBudgets = ofy().load().type(Budget.class)
				.filter("documentNb =", documentnb).list();
		Budget myBudget = myBudgets.get(0);
		
		return myBudget;
    }
      
//  -------------------------------------------------------------------------------------- Purchase Orders
    
    public void addPO(PurchaseOrder po) {
//    	Objectify obj = ObjectifyService.beginTransaction();
//        obj.put(po);
//        obj.getTxn().commit();
        
        ObjectifyService.ofy().save().entity(po);
    }
    
    public List<PurchaseOrder> getPOs() {
    	
//        List<PurchaseOrder> POs = new ArrayList<PurchaseOrder>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<PurchaseOrder> query = ofy.query(PurchaseOrder.class);
//		
//		for (PurchaseOrder poFromQuery : query) {
//			POs.add(poFromQuery);
//		}
		
		List<PurchaseOrder> POs = ofy().load().type(PurchaseOrder.class).list();
		
        return POs;
    }
 
    public PurchaseOrder getPOByUniqueId(String id) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<PurchaseOrder> query = ofy.query(PurchaseOrder.class);
//		
//		PurchaseOrder myPO = query
//				.filter("uniqueId =", id).get();
		
		PurchaseOrder myPO = (PurchaseOrder) ofy().load().type(PurchaseOrder.class).filter("uniqueId =", id);
		
		return myPO;
		
    }
    
    public PurchaseOrder getPOByDocumentNb(String documentnb) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<PurchaseOrder> query = ofy.query(PurchaseOrder.class);
//		
//		PurchaseOrder myPO = query
//				.filter("documentNb =", documentnb).get();
		
		List<PurchaseOrder> myPOs = ofy().load().type(PurchaseOrder.class)
				.filter("documentNb =", documentnb).list();
		
		PurchaseOrder myPO = myPOs.get(0);
		
		return myPO;
		
    }
    
     
//  -------------------------------------------------------------------------------------- Bills
    
    public void addBill(Bill bill) {
    	
//    	Objectify obj = ObjectifyService.beginTransaction();
//        obj.put(bill);
//        obj.getTxn().commit();
        
        ObjectifyService.ofy().save().entity(bill);
    }
    
    public List<Bill> getBills() {
    	
//        List<Bill> Bills = new ArrayList<Bill>();
//        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Bill> query = ofy.query(Bill.class);
//		
//		for (Bill billFromQuery : query) {
//			Bills.add(billFromQuery);
//		}
		
		List<Bill> Bills = ofy().load().type(Bill.class).list();
		
        return Bills;
    }   
    
    public Bill getBillByUniqueId(String id) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Bill> query = ofy.query(Bill.class);
//		
//		Bill myBill = query
//				.filter("uniqueId =", id).get();
		
		List<Bill> myBills = ofy().load().type(Bill.class).filter("uniqueId =", id).list();
		
		Bill myBill = myBills.get(0);
		
		return myBill;
		
    }
    
    public Bill getBillByDocumentNb(String documentnb) {
        
//		Objectify ofy = ObjectifyService.begin();
//		Query<Bill> query = ofy.query(Bill.class);
//		
//		Bill myBill = query
//				.filter("documentNb =", documentnb).get();
		
		Bill myBill = (Bill) ofy().load().type(Bill.class).filter("documentNb =", documentnb);
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
