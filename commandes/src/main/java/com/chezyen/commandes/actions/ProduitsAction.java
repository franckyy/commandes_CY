package com.chezyen.commandes.actions;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.commons.Erreurs;
import com.chezyen.commandes.dao.IProduitDAO;
import com.chezyen.commandes.metier.Produit;
import com.chezyen.commandes.metier.ProduitConditionne;
import com.opensymphony.xwork2.ActionSupport;

public class ProduitsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static Logger log = LogManager.getLogger(IndexAction.class);
	
	private IProduitDAO produitDAO;
	public void setProduitDAO(IProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}
	
	private int produitID;
	private Set<ProduitConditionne> produitsConditionnes;
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
	public Set<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
	public void setProduitsConditionnes(Set<ProduitConditionne> produitsConditionnes) {this.produitsConditionnes = produitsConditionnes;}
	
	private Produit produit;
	public Produit getProduit() {return produit;}
	
	private List<Produit> produits;
	public List<Produit> getProduits() {return produits;}
	
	public String repertoire() {
		log.info("ProduitsAction - repertoire");
		this.produits = produitDAO.findAll();
		return SUCCESS;
	}
	
	public String nouveauProduit() {
		log.info("ProduitsAction - nouveauProduit");
		Produit produit = new Produit(getProduitDesignation(), getProduitPrix(), getProduitStock());
		if(verificationNouveauProduit(getProduitDesignation())){
			this.produit = produitDAO.save(produit);
			return SUCCESS;
		} else {
			Erreurs err = new Erreurs("NewProdErr", "erreur nouveau produit");
			return ERROR;
		}
	}
	
	private boolean verificationNouveauProduit(String designation) {
		if("".equals(designation)){
			return false;
		}
		
		return true;
	}
	
	public String modification() {
		log.info("ProduitsAction - modification - produitID : " + getProduitID());
		this.produit = produitDAO.findByID(getProduitID());
		log.info("ProduitsAction - modification - désignation  : " + this.produit.getDesignation());
		return SUCCESS;
	}
	
	public String valider_modification() {
		log.info("ProduitsAction - valider_modification - produitID : " + getProduitID());
		Produit produit = new Produit();
		log.info("ProduitsAction - valider_modification - produit id : " + produit.getIdProduit() + ", attr produitID : " + getProduitID());
		produit = produit.setProduit(getProduitID(), getProduitDesignation(), getProduitPrix(), getProduitStock());
		log.info("ProduitsAction - valider_modification - désignation : " + getProduitDesignation() + "id produit : " + produit.getIdProduit());
		this.produit = produitDAO.save(produit);
		log.info("ProduitsAction - save modified produit - désignation : " + this.produitDesignation + ", id : " + getProduitID());
		this.produits = produitDAO.findAll();
		return SUCCESS;
	}
	
	public String save() {
		log.info("IndexAction - save");
		this.produit = produitDAO.save(new Produit(
												getProduitsConditionnes(),
												getProduitDesignation(),
												getProduitPrix(),
												getProduitStock()
												)
										);
		return SUCCESS;
	}
}
