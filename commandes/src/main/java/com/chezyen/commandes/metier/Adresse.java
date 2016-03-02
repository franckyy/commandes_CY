package com.chezyen.commandes.metier;

import javax.persistence.Embeddable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Embeddable
public class Adresse {
	
	public static Logger log = LogManager.getLogger(ProduitConditionne.class);

	private String nomVoie;
	private String typeVoie;
	private String numeroVoie;
	private int codePostal;
	private String ville;
	
	public Adresse() {}
	public Adresse(String nomVoie, String typeVoie, String numeroVoie, int codePostal, String ville) {
		super();
		log.info("Adresse - new - nomVoie : " + nomVoie);
		log.info("numeroVoie : " + numeroVoie);
		this.nomVoie = nomVoie;
		this.typeVoie = typeVoie;
		this.numeroVoie = numeroVoie;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public String getNomVoie() {return nomVoie;}
	public void setNomVoie(String nomVoie) {this.nomVoie = nomVoie;}
	public String getTypeVoie() {return typeVoie;}
	public void setTypeVoie(String typeVoie) {this.typeVoie = typeVoie;}
	public String getNumeroVoie() {return numeroVoie;}
	public void setNumeroVoie(String numeroVoie) {this.numeroVoie = numeroVoie;}
	public int getCodePostal() {return codePostal;}
	public void setCodePostal(int codePostal) {this.codePostal = codePostal;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
}
