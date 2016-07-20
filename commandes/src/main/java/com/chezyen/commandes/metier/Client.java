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

	@Id @GeneratedValue
	private int idClient;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	@Embedded
	private Adresse adresse;
	@OneToMany(mappedBy="client")
	private Set<Commande> commandes;
	
	public Client() {super();}
	//Constructeur sans la commande et sans id
	public Client(String nom, String prenom, String email, String telephone, Adresse adresse) {
		this(nom, prenom, email, telephone, adresse, null);
	}
	//Constructeur sans la commande mais avec un identifiant
	public Client(int idClient, String nom, String prenom, String email, String telephone, Adresse adresse) {
		this(nom, prenom, email, telephone, adresse, null);
		this.idClient = idClient;
	}
	//Constructeur complet mais sans identifiant
	public Client(String nom, String prenom, String email, String telephone, Adresse adresse, Set<Commande> commandes) {
		super();
		log.info("new Client() - nom : " + nom);
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.commandes = commandes;
	}


	public int getIdClient() {return idClient;}
	public void setIdClient(int id) {this.idClient = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getTelephone() {return telephone;}
	public void setTelephone(String telephone) {this.telephone = telephone;}
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	public Set<Commande> getCommandes() {return commandes;}
	public void setCommandes(Set<Commande> commandes) {this.commandes = commandes;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("Client - fetchPrimaryKey() - id : " + this.getIdClient());
		return this.getIdClient();
	}
	
	public Client setClient(int id, String nom, String prenom, String email, String telephone, String nomVoie, String typeVoie, String numeroVoie, int codePostal, String ville) {
		Adresse adresse = new Adresse(nomVoie, typeVoie, numeroVoie, codePostal, ville);
		Client client = new Client(id, nom, prenom, email, telephone, adresse);
		return client;
	}
}
