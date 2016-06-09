package com.chezyen.commandes.commons;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chezyen.commandes.metier.IGenericEntity;

@Entity
public class Erreurs implements IGenericEntity{
	private static Logger log = LogManager.getLogger(Erreurs.class);
	
	@Id@GeneratedValue
	private int idErreur;
	private String clé;
	private String message;
	
	public Erreurs() {super();}
	public Erreurs(String clé, String message) {
		super();
		this.clé = clé;
		this.message = message;
	}
	
	public String getClé() {return clé;}
	public void setClé(String clé) {this.clé = clé;}
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	public int getIdErreur() {return idErreur;}
	public void setIdErreur(int idErreur) {this.idErreur = idErreur;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("fetchPrimaryKey(");
		return getIdErreur();
	}
}
