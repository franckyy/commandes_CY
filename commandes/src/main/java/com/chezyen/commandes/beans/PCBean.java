package com.chezyen.commandes.beans;

public class PCBean {
	
	//ATTRIBUTS

	private int idProduitConditionne;
	private String produitDesignation;
	private double produitPrix;
	private String conditionnementDesignation;
	private double prixTotal;
	private boolean enCarte;
	
	//CONSTRUCTEUR
	public PCBean(String produitDesignation, double produitPrix,
			String conditionnementDesignation, double prixTotal, boolean enCarte) {
		super();
		this.produitDesignation = produitDesignation;
		this.produitPrix = produitPrix;
		this.conditionnementDesignation = conditionnementDesignation;
		this.prixTotal = prixTotal;
		this.enCarte = enCarte;
	}
	
	//ACCESSEURS
	public String getProduitDesignation() {return produitDesignation;}
	public void setProduitDesignation(String produitDesignation) {this.produitDesignation = produitDesignation;}
	
	public double getProduitPrix() {return produitPrix;}
	public void setProduitPrix(double produitPrix) {this.produitPrix = produitPrix;}
	
	public String getConditionnementDesignation() {return conditionnementDesignation;}
	public void setConditionnementDesignation(String conditionnementDesignation) {this.conditionnementDesignation = conditionnementDesignation;}
	
	public double getPrixTotal() {return prixTotal;}
	public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}
	
	public boolean isEnCarte() {return enCarte;}
	public void setEnCarte(boolean enCarte) {this.enCarte = enCarte;}

	public int getIdProduitConditionne() {return idProduitConditionne;}
	public void setIdProduitConditionne(int idProduitConditionne) {this.idProduitConditionne = idProduitConditionne;}
}
