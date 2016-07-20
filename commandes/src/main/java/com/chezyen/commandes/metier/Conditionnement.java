package com.chezyen.commandes.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Conditionnement implements IGenericEntity {

	private static Logger log = LogManager.getLogger(Conditionnement.class);
	
	@Id@GeneratedValue
	private int idConditionnement;
	private String designation;
	private int quantite;
	@OneToMany(mappedBy="conditionnement")
	private Set<ProduitConditionne> produitsConditionnes;
	
	public Conditionnement() {}
	public Conditionnement(String designation, int quantite, Set<ProduitConditionne> produitsConditionnes) {
		super();
		this.designation = designation;
		this.quantite = quantite;
		this.produitsConditionnes = produitsConditionnes;
	}

	public int getIdConditionnement() {return idConditionnement;}
	public void setIdConditionnement(int idConditionnement) {this.idConditionnement = idConditionnement;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
	public Set<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
	public void setProduitsConditionnes(Set<ProduitConditionne> produitsConditionnes) {this.produitsConditionnes = produitsConditionnes;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Conditionnement - fetchPrimaryKey()");
		return getIdConditionnement();
	}

}
