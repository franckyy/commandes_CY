package com.chezyen.commandes.metier;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class Client implements IGenericEntity {

	private static Logger log = LogManager.getLogger(Client.class);

	@Id@GeneratedValue
	private int idClient;
	private String nom;
	private String prenom;
	@Embedded
	private Adresse adresse;
	@OneToMany(mappedBy="client")
	private Set<Commande> commandes;
	
	public Client() {super();}
	public Client(String nom, String prenom, Adresse adresse) {
		this(nom, prenom, adresse, null);
	}
	public Client(int idClient, String nom, String prenom, Adresse adresse) {
		this(nom, prenom, adresse, null);
		this.idClient = idClient;
	}
	public Client(String nom, String prenom, Adresse adresse, Set<Commande> commandes) {
		super();
		log.info("new Client() - nom : " + nom);
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.commandes = commandes;
	}


	public int getIdClient() {return idClient;}
	public void setIdClient(int id) {this.idClient = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	public Set<Commande> getCommandes() {return commandes;}
	public void setCommandes(Set<Commande> commandes) {this.commandes = commandes;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Client - fetchPrimaryKey() - id : " + this.getIdClient());
		return this.getIdClient();
	}
	
	public Client setClient(int id, String nom, String prenom, String nomVoie, String typeVoie, String numeroVoie, int codePostal, String ville) {
		Adresse adresse = new Adresse(nomVoie, typeVoie, numeroVoie, codePostal, ville);
		Client client = new Client(id, nom, prenom, adresse);
		return client;
	}

}
