package com.chezyen.commandes.actions;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.dao.IProduitDAO;
import com.chezyen.commandes.metier.Produit;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static Logger log = LogManager.getLogger(IndexAction.class);
	
	private IProduitDAO produitDAO;
	public void setProduitDAO(IProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}
	
	private int produitID;
	private String produitDesignation;
	private double produitPrix;
	private int produitStock;
	
	public int getProduitID() {return produitID;}
	public void setProduitID(int produitID) {this.produitID = produitID;}
	public String getProduitDesignation() {return produitDesignation;}
	public void setProduitDesignation(String produitDesignation) {this.produitDesignation = produitDesignation;}
	public double getProduitPrix() {return produitPrix;}
	public void setProduitPrix(double produitPrix) {this.produitPrix = produitPrix;}
	public int getProduitStock() {return produitStock;}
	public void setProduitStock(int produitStock) {this.produitStock = produitStock;}

	private String message;	
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	
	private Produit produit;
	public Produit getProduit() {return produit;}
	
	private List<Produit> produits;
	public List<Produit> getProduits() {return produits;}
	
	public String index() {
		log.info("Appel de index");
		
		message = "bonjour depuis index le " + new Date();
		
		return SUCCESS;
	}
	
	public String gestionCommandes() {
		log.info("IndexAction - gestionCommandes");
		this.produits = produitDAO.findAll();
		return SUCCESS;
	}
	
	public String save() {
		log.info("IndexAction - save");
		this.produit = produitDAO.save(new Produit(
												getProduitID(),
												getProduitDesignation(),
												getProduitPrix(),
												getProduitStock()
												)
										);
		return SUCCESS;
	}
}
