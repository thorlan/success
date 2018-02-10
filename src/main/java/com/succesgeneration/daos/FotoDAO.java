package com.succesgeneration.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.succesgeneration.infra.FileManagement;
import com.succesgeneration.models.Foto;

@Repository
@Transactional
public class FotoDAO {
	@Autowired
	private FileManagement fileManagement;
	
	@PersistenceContext
	private EntityManager manager;

	public void gravar(Foto foto) {
		manager.persist(foto);

	}

	public void excluir(Foto foto) {
		Foto removerFoto = manager.find(Foto.class, foto.getId());
		fileManagement.delete(removerFoto.getCaminho());
		System.out.println("------------- APAGANDO FOTO S3-----------------");
		manager.remove(removerFoto);
		
	}

	public Foto find(Long fotoId) {
		return manager.find(Foto.class, fotoId);
	}

}