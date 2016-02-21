package com.chezyen.commandes.metier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Commande implements IGenericEntity {

	private static Logger log = LogManager.getLogger(LigneCommande.class);
	
	@Id@GeneratedValue
	private int idCommande;
	private LigneCommande ligneCommande;
	private Date dateCommande;
	private boolean isValidated;
	private double prixTotal;
	private Client client;

	public Commande() {}
	public Commande(LigneCommande ligneCommande, Date dateCommande, boolean isValidated, double prixTotal,
			Client client) {
		super();
		this.ligneCommande = ligneCommande;
		this.dateCommande = dateCommande;
		this.isValidated = isValidated;
		this.prixTotal = prixTotal;
		this.client = client;
	}
	
	public int getIdCommande() {return idCommande;}
	public void setIdCommande(int id) {this.idCommande = id;}
	public LigneCommande getLigneCommande() {return ligneCommande;}
	public void setLigneCommande(LigneCommande ligneCommande) {this.ligneCommande = ligneCommande;}
	public Date getDateCommande() {return dateCommande;}
	public void setDateCommande(Date dateCommande) {this.dateCommande = dateCommande;}
	public boolean isValidated() {return isValidated;}
	public void setValidated(boolean isValidated) {this.isValidated = isValidated;}
	public double getPrixTotal() {return prixTotal;}
	public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}
	public Client getClient() {return client;}
	public void setClient(Client client) {this.client = client;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Commande - fetchPrimaryKey()");
		return getIdCommande();
	}

}
