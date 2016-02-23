package com.chezyen.commandes.actions;

import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.dao.IClientDAO;
import com.chezyen.commandes.metier.Adresse;
import com.chezyen.commandes.metier.Client;
import com.chezyen.commandes.metier.Commande;
import com.opensymphony.xwork2.ActionSupport;

public class ClientsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static Logger log = LogManager.getLogger(ClientsAction.class);
	
	private IClientDAO clientDAO;
	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	private int clientID;
	private String clientNom;
	private String clientPrenom;
	private Adresse clientCodePostal;
	private Adresse clientNomVoie;
	private Adresse clientTypeVoie;
	private Adresse clientNumeroVoie;
	private Adresse clientVille;
	private Set<Commande> commandes;
	
	public int getClientID() {return clientID;}
	public void setClientID(int clientID) {this.clientID = clientID;}
	public String getClientNom() {return clientNom;}
	public void setClientNom(String clientNom) {this.clientNom = clientNom;}
	public String getClientPrenom() {return clientPrenom;}
	public void setClientPrenom(String clientPrenom) {this.clientPrenom = clientPrenom;}
	public Adresse getClientCodePostal() {return clientCodePostal;}
	public void setClientCodePostal(Adresse clientCodePostal) {this.clientCodePostal = clientCodePostal;}
	public Adresse getClientNomVoie() {return clientNomVoie;}
	public void setClientNomVoie(Adresse clientNomVoie) {this.clientNomVoie = clientNomVoie;}
	public Adresse getClientTypeVoie() {return clientTypeVoie;}
	public void setClientTypeVoie(Adresse clientTypeVoie) {this.clientTypeVoie = clientTypeVoie;}
	public Adresse getClientNumeroVoie() {return clientNumeroVoie;}
	public void setClientNumeroVoie(Adresse clientNumeroVoie) {this.clientNumeroVoie = clientNumeroVoie;}
	public Adresse getClientVille() {return clientVille;}
	public void setClientVille(Adresse clientVille) {this.clientVille = clientVille;}
	public Set<Commande> getCommandes() {return commandes;}
	public void setCommandes(Set<Commande> commandes) {this.commandes = commandes;}

	private Client client;
	public Client getClient() {return client;}
	
	private List<Client> clients;
	public List<Client> getClients() {return clients;}
	
	public String repertoire() {
		log.info("ClientsAction - repertoire");
		this.clients = clientDAO.findAll();
		return SUCCESS;
	}
}
