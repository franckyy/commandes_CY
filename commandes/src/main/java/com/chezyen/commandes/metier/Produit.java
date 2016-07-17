package com.chezyen.commandes.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Produit implements IGenericEntity{
	
	private static Logger log = LogManager.getLogger(Produit.class);
	
	@Id @GeneratedValue
	private int id;
	@OneToMany(mappedBy="produit")
	private Set<ProduitConditionne> produitsConditionnes;
	private String designation;
	private double prix;
	private int stock;	
	
	public Produit() {}
	public Produit(String designation, double prix, int stock) {
		this(null, designation, prix, stock);
		log.info("Creation produit : " + getDesignation());
	}
	public Produit(Set<ProduitConditionne> produitsConditionnes, String designation, double prix, int stock) {
		super();
		this.produitsConditionnes = produitsConditionnes;
		this.designation = designation;
		this.prix = prix;
		this.stock = stock;
		
		log.info("Creation produit : " + getDesignation());
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	public Set<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
	public void setProduitsConditionnes(Set<ProduitConditionne> produitsConditionnes) {this.produitsConditionnes = produitsConditionnes;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Produit : fetchPrimaryKey");
		return getId();
	}
}
