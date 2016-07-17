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
