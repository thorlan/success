package com.succesgeneration.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.succesgeneration.models.Email;

@Repository
@Transactional
public class EmailDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Email email) {
		manager.persist(email);
	}
	
	public List<Email> listar() {
		return manager.createQuery("from Email", Email.class).getResultList();
	}
	
	//metodo para tirar da lista!
}
