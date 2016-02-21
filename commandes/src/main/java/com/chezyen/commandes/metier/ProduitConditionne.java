package com.chezyen.commandes.metier;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class ProduitConditionne implements IGenericEntity {

	public static Logger log = LogManager.getLogger(ProduitConditionne.class);
	
	@Id@GeneratedValue
	private int idProduitConditionne;
	private String designation;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idProduit")
	private Produit produit;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idConditionnement")
	private Conditionnement conditionnement;
	private double prixProdCond;
	private boolean enCarte;

	public ProduitConditionne() {}
	public ProduitConditionne(String designation, Produit produit, Conditionnement conditionnement,
			double prixProdCond, boolean enCarte) {
		super();
		this.designation = designation;
		this.produit = produit;
		this.conditionnement = conditionnement;
		this.prixProdCond = prixProdCond;
		this.enCarte = enCarte;
	}
	
	public int getIdProduitConditionne() {return idProduitConditionne;}
	public void setIdProduitConditionne(int idProduitConditionne) {this.idProduitConditionne = idProduitConditionne;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public Produit getProduit() {return produit;}
	public void setProduit(Produit produit) {this.produit = produit;}
	public Conditionnement getConditionnement() {return conditionnement;}
	public void setConditionnement(Conditionnement conditionnement) {this.conditionnement = conditionnement;}
	public double getPrixProdCond() {return prixProdCond;}
	public void setPrixProdCond(double prixProdCond) {this.prixProdCond = prixProdCond;}
	public boolean isEnCarte() {return enCarte;}
	public void setEnCarte(boolean enCarte) {this.enCarte = enCarte;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("ProduitConditionne : fetchPrimaryKey()");
		return getIdProduitConditionne();
	}
}
