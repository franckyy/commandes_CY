package com.chezyen.commandes.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class LigneCommande implements IGenericEntity {

	private static Logger log = LogManager.getLogger(LigneCommande.class);

	@Id@GeneratedValue
	private int idLigneCommande;
	private ProduitConditionne produitConditionne;
	private int quantite;
	private double prixLigne;
	
	public LigneCommande()	{}
	public LigneCommande(ProduitConditionne produitConditionne, int quantite, double prixLigne) {
		super();
		this.produitConditionne = produitConditionne;
		this.quantite = quantite;
		this.prixLigne = prixLigne;
	}
	
	public int getIdLigneCommande() {return idLigneCommande;}
	public void setIdLigneCommande(int idLigneCommande) {this.idLigneCommande = idLigneCommande;}
	public ProduitConditionne getProduitConditionne() {return produitConditionne;}
	public void setProduitConditionne(ProduitConditionne produitConditionne) {this.produitConditionne = produitConditionne;}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
	public double getPrixLigne() {return prixLigne;}
	public void setPrixLigne(double prixLigne) {this.prixLigne = prixLigne;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("LigneCommande : fetchPrimaryKey");
		return getIdLigneCommande();
	}

}
