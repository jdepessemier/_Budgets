package com.jdp.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.annotation.Id;

@Entity
@Index
public class Budget {
	
	@Id
	Long id;
	
	private String uniqueId;
    private String analyticalCode;
    private String documentType;
	private String documentNb;
	private String documentDate;
	@Unindex private String documentDescription;
	@Unindex private String documentYear;
	@Unindex private double amount;
    
	public Budget() {
		uniqueId = "";
		analyticalCode = "";
		documentType = "";
		documentNb = "";
		documentDate = "";
		documentDescription = "";
		documentYear = "";
		amount = 0.00;
	}

	public Budget(String uniqueId,
			String analyticalCode,
		    String documentType,
		    String documentNb,
		    String documentDate,
		    String documentDescription,
		    String documentYear,
		    double amount){
	
		this.uniqueId = uniqueId;
		this.analyticalCode = analyticalCode;
		this.documentType = documentType;
		this.documentNb = documentNb;
		this.documentDate = documentDate;
		this.documentDescription = documentDescription;
		this.documentYear = documentYear;
		this.amount = amount;
	}
	
    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    	
    public String getAnalyticalCode() {
        return analyticalCode;
    }
    public void setAnalyticalCode(String analyticalCode) {
        this.analyticalCode = analyticalCode;
    }
    
    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    } 
    
    public String getDocumentNb() {
        return documentNb;
    }
    public void setDocumentNb(String documentNb) {
        this.documentNb = documentNb;
    }
    
    public String getDocumentDate() {
        return documentDate;
    }
    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }
    
    public String getdocumentDescription() {
        return documentDescription;
    }
    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }
    
    public String getdocumentYear() {
        return documentYear;
    }
    public void setDocumentYear(String documentYear) {
        this.documentYear = documentYear;
    }
    
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
