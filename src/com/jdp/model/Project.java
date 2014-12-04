package com.jdp.model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Project {
	
	@Id
	Long id;
	
    private String analyticalCode;
    private String description;
    private String director;
    private String manager;
    private String year;
    
	public Project() {
		analyticalCode = "";
		description = "";
		director = "";
		manager = "";
		year = "";
	}

	public Project(String analyticalCode,
			String description,
			String director,
			String manager,
			String year){
	
		this.analyticalCode = analyticalCode;
		this.description = description;
		this.director = director;
		this.manager = manager;
		this.year = year;
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
   
}
