package com.jdp.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
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
    	Objectify obj = ObjectifyService.beginTransaction();
        obj.put(project);
        obj.getTxn().commit();
    }
    
    public List<Project> getProjects() {
    	
        List<Project> projects = new ArrayList<Project>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<Project> query = ofy.query(Project.class);
		
		for (Project projectFromQuery : query) {
			projects.add(projectFromQuery);
		}
        return projects;
    }
    
    public Project getProjectByAnalyticalCode(String analyticalcode) {
        
		Objectify ofy = ObjectifyService.begin();
		Query<Project> query = ofy.query(Project.class);
		
		Project myProject = query
				.filter("analyticalCode =", analyticalcode).get();
		
		return myProject;
		
    }
   
//  -------------------------------------------------------------------------------------- Budgets
    
    public void addBudget(Budget budget) {
    	Objectify obj = ObjectifyService.beginTransaction();
        obj.put(budget);
        obj.getTxn().commit();
    }
    
    public List<Budget> getBudgets() {
    	
        List<Budget> budgets = new ArrayList<Budget>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<Budget> query = ofy.query(Budget.class);
		
		for (Budget budgetFromQuery : query) {
			budgets.add(budgetFromQuery);
		}
        return budgets;
    }
    
    public List<Budget> getBudgetsBByAnalyticalCode(String analyticalCode) {
    	
        List<Budget> budgetsB = new ArrayList<Budget>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<Budget> query = ofy.query(Budget.class);
		
		Budget myBudgetB = query
				.filter("analyticalCode =", analyticalCode)
				.filter("documentType =", "Liquidation").get();
		
		for (Budget budgetFromQuery : query) {
			budgetsB.add(budgetFromQuery);
		}
        return budgetsB;
    }
    
    public List<Budget> getBudgetsCByAnalyticalCode(String analyticalCode) {
    	
        List<Budget> budgetsC = new ArrayList<Budget>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<Budget> query = ofy.query(Budget.class);
		
		Budget myBudgetC = query
				.filter("analyticalCode =", analyticalCode)
				.filter("documentType =", "Engagement").get();
		
		for (Budget budgetFromQuery : query) {
			budgetsC.add(budgetFromQuery);
		}
        return budgetsC;
    }

       
    public Budget getBudgetByDocumentNb(String documentnb) {
        
		Objectify ofy = ObjectifyService.begin();
		Query<Budget> query = ofy.query(Budget.class);
		
		Budget myBudget = query
				.filter("documentNb =", documentnb).get();
		
		return myBudget;
    }
      
//  -------------------------------------------------------------------------------------- Purchase Orders
    
    public void addPO(PurchaseOrder po) {
    	Objectify obj = ObjectifyService.beginTransaction();
        obj.put(po);
        obj.getTxn().commit();
    }
    
    public List<PurchaseOrder> getPOs() {
    	
        List<PurchaseOrder> POs = new ArrayList<PurchaseOrder>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<PurchaseOrder> query = ofy.query(PurchaseOrder.class);
		
		for (PurchaseOrder poFromQuery : query) {
			POs.add(poFromQuery);
		}
        return POs;
    }
    
    public PurchaseOrder getPOByDocumentNb(String documentnb) {
        
		Objectify ofy = ObjectifyService.begin();
		Query<PurchaseOrder> query = ofy.query(PurchaseOrder.class);
		
		PurchaseOrder myPO = query
				.filter("documentNb =", documentnb).get();
		
		return myPO;
		
    }
    
     
//  -------------------------------------------------------------------------------------- Bills
    
    public void addBill(Bill bill) {
    	Objectify obj = ObjectifyService.beginTransaction();
        obj.put(bill);
        obj.getTxn().commit();
    }
    
    public List<Bill> getBills() {
    	
        List<Bill> Bills = new ArrayList<Bill>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<Bill> query = ofy.query(Bill.class);
		
		for (Bill billFromQuery : query) {
			Bills.add(billFromQuery);
		}
        return Bills;
    }   
    
    public Bill getBillByDocumentNb(String documentnb) {
        
		Objectify ofy = ObjectifyService.begin();
		Query<Bill> query = ofy.query(Bill.class);
		
		Bill myBill = query
				.filter("documentNb =", documentnb).get();
		
		return myBill;
		
    }
    
    
//  -------------------------------------------------------------------------------------- Snapshots Data
    
    public void addSnapshotData(SnapshotData SnapshotData) {
    	Objectify obj = ObjectifyService.beginTransaction();
        obj.put(SnapshotData);
        obj.getTxn().commit();
    }
    
    public List<SnapshotData> getAllSnapshotDatas() {
    	
        List<SnapshotData> SnapshotDatas = new ArrayList<SnapshotData>();
        
		Objectify ofy = ObjectifyService.begin();
		Query<SnapshotData> query = ofy.query(SnapshotData.class);
		
		for (SnapshotData snapshotdataFromQuery : query) {
			SnapshotDatas.add(snapshotdataFromQuery);
		}
		
        return SnapshotDatas;
    }
    

}
