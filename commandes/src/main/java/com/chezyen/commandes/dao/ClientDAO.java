package com.chezyen.commandes.dao;

import com.chezyen.commandes.metier.Client;

public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

	public ClientDAO() {
		super(Client.class);
	}
}
