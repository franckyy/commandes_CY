package com.chezyen.commandes.dao;

import com.chezyen.commandes.metier.ProduitConditionne;

public class ProduitConditionneDAO extends GenericDAO<ProduitConditionne> implements IProduitConditionneDAO{

	public ProduitConditionneDAO() {
		super(ProduitConditionne.class);
	}

}
