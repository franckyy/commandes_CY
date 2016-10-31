package com.chezyen.commandes.actions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.dao.IConditionnementDAO;
import com.chezyen.commandes.dao.IProduitConditionneDAO;
import com.chezyen.commandes.dao.IProduitDAO;
import com.chezyen.commandes.metier.Conditionnement;
import com.chezyen.commandes.metier.Produit;
import com.chezyen.commandes.metier.ProduitConditionne;
import com.opensymphony.xwork2.ActionSupport;

public class ProduitsConditionnesAction extends ActionSupport{

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
	//peut-être pas besoin des deux suivants
	private Produit produitConditionneProduit;
	private Conditionnement produitConditionneConditionnement;
	private ProduitConditionne produitConditionneModif;
	private int produitConditionneModifID;
	private double produitConditionneModifPrix;
	private boolean produitConditionneModifEnCarte;
	private int produitConditionneSupprID;

	public int getProduitConditionneID() {return produitConditionneID;}
	public void setProduitConditionneID(int produitConditionneID) {this.produitConditionneID = produitConditionneID;}
	public int getProduitId() {return produitID;}
	public void setProduitID(int produitID) {this.produitID = produitID;}
	public int getConditionnementID() {return conditionnementID;}
	public void setConditionnementID(int conditionnementID) {this.conditionnementID = conditionnementID;}
	public double getProduitConditionnePrix() {return produitConditionnePrix;}
	public void setProduitConditionnePrix(double produitConditionnePrixProdCond) {this.produitConditionnePrix = produitConditionnePrixProdCond;}
	public boolean isProduitConditionneEnCarte() {return produitConditionneEnCarte;}
	public void setProduitConditionneEnCarte(boolean produitConditionneEnCarte) {this.produitConditionneEnCarte = produitConditionneEnCarte;}
	public Produit getProduitConditionneProduit() {return produitConditionneProduit;}
	public void setProduitConditionneProduit(Produit produitConditionneProduit) {this.produitConditionneProduit = produitConditionneProduit;}
	public Conditionnement getProduitConditionneConditionnement() {return produitConditionneConditionnement;}
	public void setProduitConditionneConditionnement(Conditionnement produitConditionneConditionnement) {this.produitConditionneConditionnement = produitConditionneConditionnement;}
	public ProduitConditionne getProduitConditionneModif () {return this.produitConditionneModif;}
	public void setProduitConditionneModif(ProduitConditionne produitConditionneModif) {this.produitConditionneModif = produitConditionneModif;}
	public int getProduitConditionneModifID() {return produitConditionneModifID;}
	public void setProduitConditionneModifID(int produitConditionneModifID) {this.produitConditionneModifID = produitConditionneModifID;}
	public double getProduitConditionneModifPrix() {return produitConditionneModifPrix;}
	public void setProduitConditionneModifPrix(double produitConditionneModifPrix) {this.produitConditionneModifPrix = produitConditionneModifPrix;}
	public boolean isProduitConditionneModifEnCarte() {return produitConditionneModifEnCarte;}
	public void setProduitConditionneModifEnCarte(boolean produitConditionneModifEnCarte) {this.produitConditionneModifEnCarte = produitConditionneModifEnCarte;}
	public int getProduitConditionneSupprID() {return produitConditionneSupprID;}
	public void setProduitConditionneSupprID(int produitConditionneSupprID) {this.produitConditionneSupprID = produitConditionneSupprID;}
	
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
	
	public String repertoire() {
		log.info("ProduitsConditionnesAction - repertoire");
		
		this.produitsConditionnes = produitConditionneDAO.findAll();
		this.listeProduits = produitDAO.findAll();
		this.listeConditionnements = conditionnementDAO.findAll();
		
		return SUCCESS;
	}
	
	public String modification() {
		log.info("ProduitsConditionnesAction - modifier ProduitConditionneID : " + getProduitConditionneID());
		this.produitConditionneModif = produitConditionneDAO.findByID(getProduitConditionneID());
		return SUCCESS;
	}
	
	public String validerModif() {
		log.info("ProduitsConsitionnesAction - validerModif - ProduitConditionneID : " + getProduitConditionneModifID() + ", prix modifié : " + getProduitConditionneModifPrix() + ", enCarte modifé : " + isProduitConditionneModifEnCarte());
		
		ProduitConditionne prodCondModif = produitConditionneDAO.findByID(getProduitConditionneModifID());
		prodCondModif.setPrixProdCond(getProduitConditionneModifPrix());
		prodCondModif.setEnCarte(isProduitConditionneModifEnCarte());
		prodCondModif = produitConditionneDAO.save(prodCondModif);
		
		this.produitsConditionnes = produitConditionneDAO.findAll();
		return SUCCESS;
	}
	
	public String suppression() {
		log.info("ProduitsConsitionnesAction - suppression - ProduitConditionneSupprID : " + getProduitConditionneSupprID());
		produitConditionne = produitConditionneDAO.findByID(getProduitConditionneSupprID());
		return SUCCESS;
	}
	
	public String nouveau(){
		log.info("ProduitsConditionnesAction - nouveau");
		log.info("prix : " + getProduitConditionnePrix() + ", produitID : " + getProduitId() + ", condiID : " + getConditionnementID() + ", enCarte ? : " + isProduitConditionneEnCarte());
		
		boolean existingPC = false;
		
		ProduitConditionne pc = new ProduitConditionne(produitDAO.findByID(getProduitId()), conditionnementDAO.findByID(getConditionnementID()), getProduitConditionnePrix(), isProduitConditionneEnCarte());
		
		for(ProduitConditionne prodCond : produitConditionneDAO.findAll()) {
			if(prodCond.getProduit().getIdProduit() == getProduitId() && prodCond.getConditionnement().getIdConditionnement() == getConditionnementID()) {
				existingPC = true;
			}
		}
		
		if(!existingPC) {
			produitConditionne = produitConditionneDAO.save(pc);
		} else {
			log.info("produitConditionné " + pc.getProduit().getDesignation() + " en conditionnement " + pc.getConditionnement().getDesignation() + " non sauvé car déjà en base");
		}
		
		return SUCCESS;
	}
}
