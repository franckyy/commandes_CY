package com.chezyen.commandes.metier;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Commande implements IGenericEntity {

	private static Logger log = LogManager.getLogger(LigneCommande.class);
	
	@Id@GeneratedValue
	private int idCommande;
	@OneToMany(mappedBy="commande")
	private Set<LigneCommande> lignesCommande;
	private Date dateCommande;
	private boolean isValidated;
	private double prixTotal;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idClient")
	private Client client;

	public Commande() {}
	public Commande(Set<LigneCommande> lignesCommande, Date dateCommande, boolean isValidated, double prixTotal,
			Client client) {
		super();
		this.lignesCommande = lignesCommande;
		this.dateCommande = dateCommande;
		this.isValidated = isValidated;
		this.prixTotal = prixTotal;
		this.client = client;
	}

	public int getIdCommande() {return idCommande;}
	public void setIdCommande(int id) {this.idCommande = id;}
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
