package com.chezyen.commandes.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Produit implements IGenericEntity{
	
	private static Logger log = LogManager.getLogger(Produit.class);
	
	@Id @GeneratedValue
	private int id;
	private String designation;
	private double prix;
	private int stock;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	
	public Produit() {this(0, "nouveau produit", 0.0, 0);}
	public Produit(int id, String designation, double prix, int stock) {
		super();
		this.id = id;
		this.designation = designation;
		this.prix = prix;
		this.stock = stock;
		
		log.info("Creation produit : " + getDesignation());
	}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("produit : fetchPrimaryKey");
		return getId();
	}
}
