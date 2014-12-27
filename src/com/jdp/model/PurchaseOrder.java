package com.jdp.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.annotation.Id;


@Entity
@Index
public class PurchaseOrder {
	
	@Id
	Long id;
	
	private int uniqueId;
    private String analyticalCode;
    private String poNb;
    @Unindex private String supplier;
	private String documentNb;
	@Unindex private String documentDate;
	@Unindex private String documentDescription;
	@Unindex private String documentYear;
	@Unindex private double amount;
    
	public PurchaseOrder() {
		uniqueId = 0;
		analyticalCode = "";
		poNb = "";
		supplier = "";
		documentNb = "";
		documentDate = "";
		documentDescription = "";
		documentYear = "";
		amount = 0.00;
	}

	public PurchaseOrder(int uniqueId,
			String analyticalCode,
		    String poNb,
		    String supplier,
		    String documentNb,
		    String documentDate,
		    String documentDescription,
		    String documentYear,
		    double amount){
	
		this.uniqueId = uniqueId;
		this.analyticalCode = analyticalCode;
		this.poNb = poNb;
		this.supplier = supplier;
		this.documentNb = documentNb;
		this.documentDate = documentDate;
		this.documentDescription = documentDescription;
		this.documentYear = documentYear;
		this.amount = amount;
	}
	
    public int getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }
	
    public String getAnalyticalCode() {
        return analyticalCode;
    }
    public void setAnalyticalCode(String analyticalCode) {
        this.analyticalCode = analyticalCode;
    }
    
    public String getPoNb() {
        return poNb;
    }
    public void setPoNb(String poNb) {
        this.poNb = poNb;
    } 
    
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
