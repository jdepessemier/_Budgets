package com.jdp.model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
@Index
public class Project {
	
	@Id
	Long id;
	
    private String analyticalCode;
    @Unindex private String description;
    @Unindex  private String director;
    @Unindex private String manager;
    @Unindex private String year;
    @Unindex private double totalBudgetC;
    @Unindex private double remainingBudgetC;
    @Unindex private double totalBudgetB;
    @Unindex private double remainingBudgetB;    
    
	public Project() {
		analyticalCode = "";
		description = "";
		director = "";
		manager = "";
		year = "";
		totalBudgetC = 0.00;
		remainingBudgetC = 0.00;
		totalBudgetB = 0.00;
		remainingBudgetB = 0.00;
	}

	public Project(String analyticalCode,
			String description,
			String director,
			String manager,
			String year,
			double totalBudgetC,
			double remainingBudgetC,
			double totalBudgetB,
			double remainingBudgetB
			){
	
		this.analyticalCode = analyticalCode;
		this.description = description;
		this.director = director;
		this.manager = manager;
		this.year = year;
		this.totalBudgetC = totalBudgetC;
		this.remainingBudgetC = remainingBudgetC;
		this.totalBudgetB = totalBudgetB;
		this.remainingBudgetB = remainingBudgetB;
	}
	
    public String getAnalyticalCode() {
        return analyticalCode;
    }
    public void setAnalyticalCode(String analyticalCode) {
        this.analyticalCode = analyticalCode;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    public double getTotalBudgetC() {
        return totalBudgetC;
    }
    public void setTotalBudgetC(double totalbudgetC) {
        this.totalBudgetC = totalbudgetC;
    }
 
    public double getRemainingBudgetC() {
        return remainingBudgetC;
    }
    public void setRemainingBudgetC(double remainingbudgetC) {
        this.remainingBudgetC = remainingbudgetC;
    }
    
    public double getTotalBudgetB() {
        return totalBudgetB;
    }
    public void setTotalBudgetB(double totalbudgetB) {
        this.totalBudgetB = totalbudgetB;
    }
   
    public double getRemainingBudgetB() {
        return remainingBudgetB;
    }
    public void setRemainingBudgetB(double remainingbudgetB) {
        this.remainingBudgetB = remainingbudgetB;
    }
    
}
