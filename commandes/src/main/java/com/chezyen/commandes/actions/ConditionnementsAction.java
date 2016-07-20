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
	private String designation;
	private int quantite;
	private Set<ProduitConditionne> produitsConditionnes;
	
	public int getConditionnementID() {return conditionnementID;}
	public void setConditionnementID(int conditionnementID) {this.conditionnementID = conditionnementID;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
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
}
