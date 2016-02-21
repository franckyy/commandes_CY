package com.chezyen.commandes.metier;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Client implements IGenericEntity {

	private static Logger log = LogManager.getLogger(LigneCommande.class);

	@Id@GeneratedValue
	private int idClient;
	private String nom;
	private String prenom;
//	@Embedded
//	private Adresse adresse;
	
	public int getIdClient() {return idClient;}
	public void setIdClient(int id) {this.idClient = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
//	public Adresse getAdresse() {return adresse;}
//	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	
	public Client() {super();}
	public Client(int idClient, String nom, String prenom, Adresse adresse) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
//		this.adresse = adresse;
	}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Client - fetchPrimaryKey()");
		return getIdClient();
	}

}
