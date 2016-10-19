package com.chezyen.commandes.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.beans.PCBean;
import com.chezyen.commandes.dao.IConditionnementDAO;
import com.chezyen.commandes.dao.IProduitConditionneDAO;
import com.chezyen.commandes.dao.IProduitDAO;
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
	private int produitID;
	private int conditionnementID;
	private double produitConditionnePrix;
	private boolean produitConditionneEnCarte;

	public int getProduitConditionneID() {return produitConditionneID;}
	public void setProduitConditionneID(int produitConditionneID) {this.produitConditionneID = produitConditionneID;}
	public int getProduitID() {return produitID;}
	public void setProduitID(int produitID) {this.produitID = produitID;}
	public int getConditionnementID() {return conditionnementID;}
	public void setConditionnementID(int conditionnementID) {this.conditionnementID = conditionnementID;}
	public double getProduitConditionnePrix() {return produitConditionnePrix;}
	public void setProduitConditionnePrix(double produitConditionnePrix) {this.produitConditionnePrix = produitConditionnePrix;}
	public boolean isProduitConditionneEnCarte() {return produitConditionneEnCarte;}
	public void setProduitConditionneEnCarte(boolean produitConditionneEnCarte) {this.produitConditionneEnCarte = produitConditionneEnCarte;}
	
	private ProduitConditionne produitConditionne;
	public ProduitConditionne getProduitConditionne() {return produitConditionne;}
	
	private List<ProduitConditionne> produitsConditionnes;
	public List<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
	
	
	private IProduitDAO produitDAO;	
	public IProduitDAO getProduitDAO() {return produitDAO;}
	public void setProduitDAO(IProduitDAO produitDAO) {this.produitDAO = produitDAO;}
	
	private IConditionnementDAO conditionnementDAO;
	public IConditionnementDAO getConditionnementDAO() {return conditionnementDAO;}
	public void setConditionnementDAO(IConditionnementDAO conditionnementDAO) {this.conditionnementDAO = conditionnementDAO;}

	private List<Produit> listeProduits;
	public List<Produit> getListeProduits() {return listeProduits;}
	
	private List<Conditionnement> listeConditionnements;
	public List<Conditionnement> getListeConditionnements() {return listeConditionnements;}
	
	private List<PCBean> listeTable;
	public List<PCBean> getListeTable() {return listeTable;}
	
	public String repertoire() {
		log.info("ProduitsConditionnesAction - repertoire");
		
		this.produitsConditionnes = produitConditionneDAO.findAll();
		this.listeProduits = produitDAO.findAll();
		this.listeConditionnements = conditionnementDAO.findAll();
		
		this.listeTable = new ArrayList<PCBean>();

		
		//Creation of the table view
		for(ProduitConditionne pc : this.produitsConditionnes){
			PCBean pcBean = new PCBean(pc.getProduit().getDesignation(),
										pc.getProduit().getPrix(),
										pc.getConditionnement().getDesignation(),
										pc.getPrixProdCond(),
										pc.isEnCarte());
			listeTable.add(pcBean);
		}

		return SUCCESS;
	}
	
	public String nouveau(){
		log.info("ProduitsConditionnesAction - nouveau");
		log.info("prix : " + getProduitConditionnePrix());
		log.info("produitId : " + getProduitID());
		log.info("conditionnementId : " + getConditionnementID());
		
		ProduitConditionne pc = new ProduitConditionne(produitDAO.findByID(getProduitID()), 
														conditionnementDAO.findByID(getConditionnementID()), 
														getProduitConditionnePrix(), 
														isProduitConditionneEnCarte());
		pc = produitConditionneDAO.save(pc);

		
		return SUCCESS;
	}
}
