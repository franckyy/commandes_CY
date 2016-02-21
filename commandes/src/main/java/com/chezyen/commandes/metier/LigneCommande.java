package com.chezyen.commandes.metier;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
public class LigneCommande implements IGenericEntity {

	private static Logger log = LogManager.getLogger(LigneCommande.class);

	@Id@GeneratedValue
	private int idLigneCommande;
	@ManyToOne
	@JoinColumn(name="idCommande")
	private Commande commande;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idProduitConditionne")
	private ProduitConditionne produitConditionne;
	private int quantite;
	private double prixLigne;
	
	public LigneCommande()	{}
	public LigneCommande(Commande commande, ProduitConditionne produitConditionne, int quantite, double prixLigne) {
		super();
		this.commande = commande;
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
	public Commande getCommande() {return commande;}
	public void setCommande(Commande commande) {this.commande = commande;}
	
	@Override
	public int fetchPrimaryKey() {
		log.info("LigneCommande : fetchPrimaryKey");
		return getIdLigneCommande();
	}

}
