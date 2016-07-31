package com.chezyen.commandes.beans;

import java.util.Set;

import com.chezyen.commandes.metier.ProduitConditionne;

public class ProduitsConditionnesBean {

	
	private int idPC;
	private String designationPC;
	private double prixPC;
	private boolean enCarte;
	
	private int idProd;
	private Set<ProduitConditionne> produitsConditionnesProd;
	private String designationProd;
	private double prixProd;
	private int stockProd;	
	
	private int idCond;
	private String designationCond;
	private int quantiteCond;
	private Set<ProduitConditionne> produitsConditionnesCond;
	
	public int getIdPC() {return idPC;}
	public void setIdPC(int idPC) {this.idPC = idPC;}
	public String getDesignationPC() {return designationPC;}
	public void setDesignationPC(String designationPC) {this.designationPC = designationPC;}
	public double getPrixPC() {return prixPC;}
	public void setPrixPC(double prixPC) {this.prixPC = prixPC;}
	public boolean isEnCarte() {return enCarte;}
	public void setEnCarte(boolean enCarte) {this.enCarte = enCarte;}
	public int getIdProd() {return idProd;}
	public void setIdProd(int idProd) {this.idProd = idProd;}
	public Set<ProduitConditionne> getProduitsConditionnesProd() {return produitsConditionnesProd;}
	public void setProduitsConditionnesProd(Set<ProduitConditionne> produitsConditionnesProd) {this.produitsConditionnesProd = produitsConditionnesProd;}
	public String getDesignationProd() {return designationProd;}
	public void setDesignationProd(String designationProd) {this.designationProd = designationProd;}
	public double getPrixProd() {return prixProd;}
	public void setPrixProd(double prixProd) {this.prixProd = prixProd;}
	public int getStockProd() {return stockProd;}
	public void setStockProd(int stockProd) {this.stockProd = stockProd;}
	public int getIdCond() {return idCond;}
	public void setIdCond(int idCond) {this.idCond = idCond;}
	public String getDesignationCond() {return designationCond;}
	public void setDesignationCond(String designationCond) {this.designationCond = designationCond;}
	public int getQuantiteCond() {return quantiteCond;}
	public void setQuantiteCond(int quantiteCond) {this.quantiteCond = quantiteCond;}
	public Set<ProduitConditionne> getProduitsConditionnesCond() {return produitsConditionnesCond;}
	public void setProduitsConditionnesCond(Set<ProduitConditionne> produitsConditionnesCond) {	this.produitsConditionnesCond = produitsConditionnesCond;}
	
	public ProduitsConditionnesBean() {super();}
	
}
