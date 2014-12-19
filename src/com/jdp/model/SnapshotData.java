package com.jdp.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;


@Entity
@Index
public class SnapshotData {
	
	@Id
	Long id;
	
    private String projectCA;
    private String projectDescription;
    private String projectDirector;
    private String projectManager;
    @Unindex private double reviewedBudgetC;
    @Unindex private double realizedBudgetC;
    @Unindex private double availableBudgetC;
    @Unindex private double reviewedBudgetB;
    @Unindex private double realizedBudgetB;
    @Unindex private double availableBudgetB;
    
	public SnapshotData() {
		projectCA = "";
		projectDescription = "";
		projectDirector = "";
		projectManager = "";
		reviewedBudgetC = 0.00;
		realizedBudgetC = 0.00;
		availableBudgetC = 0.00;
		reviewedBudgetB = 0.00;
		realizedBudgetB = 0.00;
		availableBudgetB = 0.00;
	}

	public SnapshotData(String ProjectCA,
			String ProjectDescription,
			String ProjectDirector,
			String ProjectManager,
		    double ReviewedBudgetC,
		    double RealizedBudgetC,
		    double AvailableBudgetC,
		    double ReviewedBudgetB,
		    double RealizedBudgetB,
		    double AvailableBudgetB){
	
		this.projectCA = ProjectCA;
		this.projectDescription = ProjectDescription;
		this.projectDirector = ProjectDirector;
		this.projectManager = ProjectManager;
		this.reviewedBudgetC = ReviewedBudgetC;
		this.realizedBudgetC = RealizedBudgetC;
		this.availableBudgetC = AvailableBudgetC;
		this.reviewedBudgetB = ReviewedBudgetB;
		this.realizedBudgetB = RealizedBudgetB;
		this.availableBudgetB = AvailableBudgetB;		
	}
	
    public String getProjectCA() {
        return projectCA;
    }
    public void setProjectCA(String ProjectCA) {
        this.projectCA = ProjectCA;
    }
    
    public String getProjectDescription() {
        return projectDescription;
    }
    public void setProjectDescription(String ProjectDescription) {
        this.projectDescription = ProjectDescription;
    }
	
    public String getProjectDirector() {
        return projectDirector;
    }
    public void setProjectDirector(String ProjectDirector) {
        this.projectDirector = ProjectDirector;
    }
    
    public String getProjectManager() {
        return projectManager;
    }
    public void setProjectManager(String ProjectManager) {
        this.projectManager = ProjectManager;
    }
       
    public double getReviewedBudgetC() {
        return reviewedBudgetC;
    }
    public void setReviewedBudgetC(double Amount) {
        this.reviewedBudgetC = Amount;
    }

    public double getRealizedBudgetC() {
        return realizedBudgetC;
    }
    public void setRealizedBudgetC(double Amount) {
        this.realizedBudgetC = Amount;
    }

    public double getAvailableBudgetC() {
        return availableBudgetC;
    }
    public void setAvailableBudgetC(double Amount) {
        this.availableBudgetC = Amount;
    }
    
    public double getReviewedBudgetB() {
        return reviewedBudgetB;
    }
    public void setReviewedBudgetB(double Amount) {
        this.reviewedBudgetB = Amount;
    }

    public double getRealizedBudgetB() {
        return realizedBudgetB;
    }
    public void setRealizedBudgetB(double Amount) {
        this.realizedBudgetB = Amount;
    }

    public double getAvailableBudgetB() {
        return availableBudgetB;
    }
    public void setAvailableBudgetB(double Amount) {
        this.availableBudgetB = Amount;
    }
    
}
