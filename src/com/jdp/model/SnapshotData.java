package com.jdp.model;

import javax.persistence.Id;

public class SnapshotData {
	
	@Id
	Long id;
	
    private String ProjectCA;
    private String ProjectDescription;
    private String ProjectDirector;
    private String ProjectManager;
    private String ProjectYear;
    private String DocumentType;
    private String SupplierName;
    private String SupplierReference;
    private String DocumentNb;
    private String DocumentDate;
    private String DocumentDescription;
    private String DocumentYear;
    private double DocumentAmount;
    
	public SnapshotData() {
		ProjectCA = "";
		ProjectDescription = "";
		ProjectDirector = "";
		ProjectManager = "";
		ProjectYear = "";
		DocumentType = "";
		SupplierName = "";
		SupplierReference = "";
		DocumentNb = "";
		DocumentDate = "";
		DocumentDescription = "";
		DocumentYear = "";
		DocumentAmount = 0.00;
	}

	public SnapshotData(String ProjectCA,
			String ProjectDescription,
			String ProjectDirector,
			String ProjectManager,
			String ProjectYear,
		    String DocumentType,
		    String SupplierName,
		    String SupplierReference,
		    String DocumentNb,
		    String DocumentDate,
		    String DocumentDescription,
		    String DocumentYear,
		    double DocumentAmount){
	
		this.ProjectCA = ProjectCA;
		this.ProjectDescription = ProjectDescription;
		this.ProjectDirector = ProjectDirector;
		this.ProjectManager = ProjectManager;
		this.ProjectYear = ProjectYear;
		this.DocumentType = DocumentType;
		this.SupplierName = SupplierName;
		this.SupplierReference = SupplierReference;
		this.DocumentNb = DocumentNb;
		this.DocumentDate = DocumentDate;
		this.DocumentDescription = DocumentDescription;
		this.DocumentYear = DocumentYear;
		this.DocumentAmount = DocumentAmount;
	}
	
    public String getProjectCA() {
        return ProjectCA;
    }
    public void setProjectCA(String ProjectCA) {
        this.ProjectCA = ProjectCA;
    }
    
    public String getProjectDescription() {
        return ProjectDescription;
    }
    public void setProjectDescription(String ProjectDescription) {
        this.ProjectDescription = ProjectDescription;
    }
	
    public String getProjectDirector() {
        return ProjectDirector;
    }
    public void setProjectDirector(String ProjectDirector) {
        this.ProjectDirector = ProjectDirector;
    }
    
    public String getProjectManager() {
        return ProjectManager;
    }
    public void setProjectManager(String ProjectManager) {
        this.ProjectManager = ProjectManager;
    }
    
    public String getProjectYear() {
        return ProjectYear;
    }
    public void setProjectYear(String ProjectYear) {
        this.ProjectYear = ProjectYear;
    }
    
    public String getDocumentType() {
        return DocumentType;
    }
    public void setDocumentType(String DocumentType) {
        this.DocumentType = DocumentType;
    } 
    
    public String getSupplierName() {
        return SupplierName;
    }
    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    } 
    
    public String getSupplierReference() {
        return SupplierReference;
    }
    public void setSupplierReference(String SupplierReference) {
        this.SupplierReference = SupplierReference;
    }
    
    public String getDocumentNb() {
        return DocumentNb;
    }
    public void setDocumentNb(String DocumentNb) {
        this.DocumentNb = DocumentNb;
    }
    
    public String getDocumentDate() {
        return DocumentDate;
    }
    public void setDocumentDate(String DocumentDate) {
        this.DocumentDate = DocumentDate;
    }
    
    public String getDocumentDescription() {
        return DocumentDescription;
    }
    public void setDocumentDescription(String DocumentDescription) {
        this.DocumentDescription = DocumentDescription;
    }
    
    public String getDocumentYear() {
        return DocumentYear;
    }
    public void setDocumentYear(String DocumentYear) {
        this.DocumentYear = DocumentYear;
    }
    
    public double getDocumentAmount() {
        return DocumentAmount;
    }
    public void setDocumentAmount(double DocumentAmount) {
        this.DocumentAmount = DocumentAmount;
    }

}
