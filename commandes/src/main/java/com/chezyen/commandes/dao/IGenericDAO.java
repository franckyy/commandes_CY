package com.chezyen.commandes.dao;

import java.util.List;

import com.chezyen.commandes.metier.IGenericEntity;

public interface IGenericDAO <T extends IGenericEntity>{
	// renvoie la liste des entite de type T
	List<T> findAll();

	// recupere une entite de type T
	T findByID(int id);

	T save(T entity);

	T remove(int id);
}
