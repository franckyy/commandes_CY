package com.chezyen.commandes.dao;

import com.chezyen.commandes.metier.Produit;

public class ProduitDAO extends GenericDAO<Produit> implements IProduitDAO {

	public ProduitDAO() {
		super(Produit.class);
	}
}
