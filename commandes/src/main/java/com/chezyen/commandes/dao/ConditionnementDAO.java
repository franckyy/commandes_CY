package com.chezyen.commandes.dao;

import com.chezyen.commandes.metier.Conditionnement;

public class ConditionnementDAO extends GenericDAO<Conditionnement> implements IConditionnementDAO{
	
	public ConditionnementDAO() {
		super(Conditionnement.class);
	}
}
