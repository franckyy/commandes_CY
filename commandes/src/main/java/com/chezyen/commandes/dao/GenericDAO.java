package com.chezyen.commandes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.chezyen.commandes.metier.IGenericEntity;

public class GenericDAO<T extends IGenericEntity> implements IGenericDAO<T> {

	private static Logger log = LogManager.getLogger(GenericDAO.class);
	
	@PersistenceContext
	private EntityManager em;
	private Class<T> entityType;
	
	public GenericDAO(Class entityType) {
		log.info("Création GenericDAO");
		this.entityType = entityType;
	}
	
	@Override
	@Transactional
	public List<T> findAll() {
		log.info("GenericDAO : findAll - from " + entityType.getSimpleName());
		return em.createQuery("from " + entityType.getSimpleName(), entityType).getResultList();
	}

	@Override
	@Transactional
	public T findByID(int id) {
		log.info("GenericDAO : findByID");
		return em.find(entityType, id);
	}

	@Override
	@Transactional
	public T save(T entity) {
		
		if(entity.fetchPrimaryKey() == 0){
			log.info("GenericDAO : save persist");
			em.persist(entity);
		} else {
			log.info("GenericDAO : save merge");
			entity = em.merge(entity);
		}
		return entity;
	}

	@Override
	@Transactional
	public T remove(int id) {
		log.info("GenericDAO : remove");
		T entity = em.find(entityType, id);
		if(entity != null){
			em.remove(entity);
		}
		return entity;
	}
}
