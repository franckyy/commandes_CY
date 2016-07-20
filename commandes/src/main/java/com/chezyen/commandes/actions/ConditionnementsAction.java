package com.chezyen.commandes.actions;

import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.dao.IConditionnementDAO;
import com.chezyen.commandes.metier.Conditionnement;
import com.chezyen.commandes.metier.ProduitConditionne;
import com.opensymphony.xwork2.ActionSupport;

public class ConditionnementsAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LogManager.getLogger(ConditionnementsAction.class);
	
	private IConditionnementDAO conditionnementDAO;
	public IConditionnementDAO getConditionnementDAO() {return conditionnementDAO;}
	public void setConditionnementDAO(IConditionnementDAO conditionnementDAO) {this.conditionnementDAO = conditionnementDAO;}

	private int conditionnementID;
	private String conditionnementDesignation;
	private int conditionnementQuantite;
	private Set<ProduitConditionne> produitsConditionnes;
	
	public int getConditionnementID() {return conditionnementID;}
	public void setConditionnementID(int conditionnementID) {this.conditionnementID = conditionnementID;}
	public String getConditionnementDesignation() {return conditionnementDesignation;}
	public void setConditionnementDesignation(String conditionnementDesignation) {this.conditionnementDesignation = conditionnementDesignation;}
	public int getConditionnementQuantite() {return conditionnementQuantite;}
	public void setConditionnementQuantite(int conditionnementQuantite) {this.conditionnementQuantite = conditionnementQuantite;}
	public Set<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
	public void setProduitsConditionnes(Set<ProduitConditionne> produitsConditionnes) {this.produitsConditionnes = produitsConditionnes;}


	private Conditionnement conditionnement;
	public Conditionnement getConditionnement() {return conditionnement;}
	
	private List<Conditionnement> conditionnements;
	public List<Conditionnement> getConditionnements() {return conditionnements;}
	
	public String repertoire() {
		log.info("ConditionnementsAction - repertoire");
		this.conditionnements = conditionnementDAO.findAll();
		return SUCCESS;
	}
	
	public String modification() {
		log.info("ConditionnementsAction - modification - id : " + getConditionnementID());
		this.conditionnement = conditionnementDAO.findByID(getConditionnementID());
		log.info("ConditionnementsAction - modification - désignation : " + this.conditionnement.getDesignation());
		return SUCCESS;
	}
	
	public String valider_modification() {
		log.info("ConditionnementsAction - valider_modification - id : " + getConditionnementID());
		Conditionnement conditionnement = new Conditionnement(getConditionnementID(), getConditionnementDesignation(), getConditionnementQuantite());
		log.info("ConditionnementsAction- valider_modification - nouveau conditonnement - id " + getConditionnementID() + ", désignation : " + getConditionnementDesignation() + ", quantité : " + getConditionnementQuantite());
		this.conditionnement = conditionnementDAO.save(conditionnement);
		log.info("ConditionnementsAction - valider_modification - designation du conditonnement modifié : " + this.conditionnement.getDesignation());
		this.conditionnements = conditionnementDAO.findAll();
		return SUCCESS;
	}
	
	public String nouveauConditionnement() {
		log.info("ConditionnementsAction - nouveauConditionnement - désignation : " + getConditionnementDesignation());
		Conditionnement conditionnement = new Conditionnement(getConditionnementDesignation(), getConditionnementQuantite());
		if (verification_nouveau_conditionnement(conditionnement)){
			this.conditionnement = conditionnementDAO.save(conditionnement);
		}
		return SUCCESS;
	}
	
	private boolean verification_nouveau_conditionnement(Conditionnement conditionnement){
		this.conditionnements = conditionnementDAO.findAll();
		//cas de conditionnements ayant même désignation
		for(Conditionnement cond : conditionnements){
			if(conditionnement.getDesignation().equals(cond.getDesignation())){
				log.info("la désignation de ce conditionnement est déjà en base de données");
				return false;
			} 
		}
		if ("".equals(conditionnement.getDesignation()) && "".equals(conditionnement.getQuantite())) {
			log.info("Ce coditionnement possède une désignation ou une quatité vide.");
			return false;
		}
		//si tous les tests sont OK
		return true;
	}
	
	public String suppression() {
		log.info("ConditionnementsAction - suppression - id : " + getConditionnementID());
		this.conditionnement = conditionnementDAO.findByID(getConditionnementID());
		
		return SUCCESS;
	}
	
	public String valider_suppression() {
		log.info("ConditionnementsAction - valider_suppression - id : " + getConditionnementID());
		Conditionnement conditionnement = conditionnementDAO.remove(getConditionnementID());
		this.conditionnements = conditionnementDAO.findAll();
		return SUCCESS;
	}
}
