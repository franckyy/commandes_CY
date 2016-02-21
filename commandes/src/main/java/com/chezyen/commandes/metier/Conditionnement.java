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
	private int id;
	private String designation;
	private int quantite;
	@OneToMany(mappedBy="conditionnement")
	private Set<ProduitConditionne> produitsConditionnes;
	
	public Conditionnement() {this("construct", 0);}
	public Conditionnement(String designation, int quantite) {
		super();
		this.designation = designation;
		this.quantite = quantite;
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
	public Set<ProduitConditionne> getProduitsConditionnes() {return produitsConditionnes;}
	public void setProduitsConditionnes(Set<ProduitConditionne> produitsConditionnes) {this.produitsConditionnes = produitsConditionnes;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Conditionnement - fetchPrimaryKey()");
		return getId();
	}

}
