package com.chezyen.commandes.actions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.beans.ProduitsConditionnesBean;
import com.chezyen.commandes.dao.IProduitConditionneDAO;
import com.chezyen.commandes.metier.Conditionnement;
import com.chezyen.commandes.metier.Produit;
import com.chezyen.commandes.metier.ProduitConditionne;
import com.opensymphony.xwork2.ActionSupport;

public class ProduitsConditionnesAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LogManager.getLogger(ProduitsConditionnesAction.class);
	
	private IProduitConditionneDAO produitConditionneDAO;
	public IProduitConditionneDAO getProduitConditionneDAO() {return produitConditionneDAO;}
	public void setProduitConditionneDAO(IProduitConditionneDAO produitConditionneDAO) {this.produitConditionneDAO = produitConditionneDAO;}
	
	private int produitConditionneID;
	private String produitConditionneDesignation;
	private Produit produitConditionneProduit;
	private Conditionnement produitConditionneConditionnement;
	private double produitConditionnePrixProdCond;
	private boolean produitConditionneEnCarte;

	public int getProduitConditionneID() {return produitConditionneID;}
	public void setProduitConditionneID(int produitConditionneID) {this.produitConditionneID = produitConditionneID;}
	public String getProduitConditionneDesignation() {return produitConditionneDesignation;}
	public void setProduitConditionneDesignation(String produitConditionneDesignation) {this.produitConditionneDesignation = produitConditionneDesignation;}
	public Produit getProduitConditionneProduit() {return produitConditionneProduit;}
	public void setProduitConditionneProduit(Produit produitConditionneProduit) {this.produitConditionneProduit = produitConditionneProduit;}
	public Conditionnement getProduitConditionneConditionnement() {return produitConditionneConditionnement;}
	public void setProduitConditionneConditionnement(Conditionnement produitConditionneConditionnement) {this.produitConditionneConditionnement = produitConditionneConditionnement;}
	public double getProduitConditionnePrixProdCond() {return produitConditionnePrixProdCond;}
	public void setProduitConditionnePrixProdCond(double produitConditionnePrixProdCond) {this.produitConditionnePrixProdCond = produitConditionnePrixProdCond;}
	public boolean isProduitConditionneEnCarte() {return produitConditionneEnCarte;}
	public void setProduitConditionneEnCarte(boolean produitConditionneEnCarte) {this.produitConditionneEnCarte = produitConditionneEnCarte;}
	
	private ProduitConditionne produitConditionne;
	public ProduitConditionne getProduitConditionne() {return produitConditionne;}
	
	private List<ProduitConditionne> produitsConditionnes;
	public List<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
		
	public String repertoire() {
		log.info("ProduitsConditionnesAction - repertoire");
		this.produitsConditionnes = produitConditionneDAO.findAll();

		for(ProduitConditionne pc : this.produitsConditionnes){
			pc.setProduit(pc.getProduit());
			produitsConditionnes.set(produitsConditionnes.indexOf(pc), pc);
		}
		return SUCCESS;
	}
}
