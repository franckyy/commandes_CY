package com.chezyen.commandes.actions;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.commons.Erreurs;
import com.chezyen.commandes.dao.IClientDAO;
import com.chezyen.commandes.metier.Adresse;
import com.chezyen.commandes.metier.Client;
import com.chezyen.commandes.metier.Commande;
import com.opensymphony.xwork2.ActionSupport;

public class ClientsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static Logger log = LogManager.getLogger(ClientsAction.class);
	
	private IClientDAO clientDAO;
	public void setClientDAO(IClientDAO clientDAO) {this.clientDAO = clientDAO;}
	public IClientDAO getClientDAO() {return clientDAO;}
	
	private int clientID;
	private String clientNom;
	private String clientPrenom;
	private int clientCodePostal;
	private String clientNomVoie;
	private String clientTypeVoie;
	private String clientNumeroVoie;
	private String clientVille;
	private Set<Commande> commandes;
	
	public int getClientID() {return clientID;}
	public void setClientID(int clientID) {this.clientID = clientID;}
	public String getClientNom() {return clientNom;}
	public void setClientNom(String clientNom) {this.clientNom = clientNom;}
	public String getClientPrenom() {return clientPrenom;}
	public void setClientPrenom(String clientPrenom) {this.clientPrenom = clientPrenom;}
	public int getClientCodePostal() {return clientCodePostal;}
	public void setClientCodePostal(int clientCodePostal) {this.clientCodePostal = clientCodePostal;}
	public String getClientNomVoie() {return clientNomVoie;}
	public void setClientNomVoie(String clientNomVoie) {this.clientNomVoie = clientNomVoie;}
	public String getClientTypeVoie() {return clientTypeVoie;}
	public void setClientTypeVoie(String clientTypeVoie) {this.clientTypeVoie = clientTypeVoie;}
	public String getClientNumeroVoie() {return clientNumeroVoie;}
	public void setClientNumeroVoie(String clientNumeroVoie) {this.clientNumeroVoie = clientNumeroVoie;}
	public String getClientVille() {return clientVille;}
	public void setClientVille(String clientVille) {this.clientVille = clientVille;}
	public Set<Commande> getCommandes() {return commandes;}
	public void setCommandes(Set<Commande> commandes) {this.commandes = commandes;}

	private Client client;
	public Client getClient() {return client;}
	
	private List<Client> clients;
	public List<Client> getClients() {return clients;}
	
	public String repertoire() {		
		log.info("répertoire client");
		this.clients = clientDAO.findAll();
		return SUCCESS;
	}
	
	public String nouveauClient() {
		log.info("nouveauClient - nom : " + getClientNom());
		Adresse adresse = new Adresse(getClientNomVoie(), getClientTypeVoie(), getClientNumeroVoie(), getClientCodePostal(), getClientVille());
		log.info("numero adresse : " + getClientNumeroVoie() + ", " + getClientTypeVoie() + " " + getClientNomVoie() + " " + getClientCodePostal() + " " + getClientVille());
		Client client = new Client(getClientNom(), getClientPrenom(), adresse, null);
		if(verificationNouveauClient(adresse, client)){
			log.info("vérification OK");
			this.client = getClientDAO().save(client);
			return SUCCESS;
		} else {
			Erreurs err = new Erreurs("NewCliErr", "erreur nouveau client");
			return ERROR;
		}
	}
	
	private boolean verificationNouveauClient(Adresse adresse, Client client) {
		this.clients = clientDAO.findAll();
		for(Client cl : clients) {
			if(adresse.getNomVoie().equals(cl.getAdresse().getNomVoie())){
				log.info("Nom de voie identique trouvé. Nom : " + adresse.getNomVoie());
				return false;
			}
		}
		return true;
	}
	public String suppression(){
		log.info("ClientsAction - suppression - clientID : " + getClientID() + ", nom : " + getClientNom());
		Client client = new Client();
		client = getClientDAO().remove(getClientID());
		this.clients = clientDAO.findAll();
		return SUCCESS;
	}
	
	
}
