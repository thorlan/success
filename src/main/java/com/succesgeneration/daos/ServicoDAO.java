package com.succesgeneration.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.succesgeneration.infra.FileManagement;
import com.succesgeneration.models.Foto;
import com.succesgeneration.models.Servico;

@Repository
@Transactional
public class ServicoDAO {

	
	private List<Foto> fotos;
	
	@Autowired
	private FileManagement fileManagement;
	
	@PersistenceContext
	private EntityManager manager;

	public String gravar(Servico servico) {
		
		String mensagem = "Serviço ";
		if (servico.getId() == null) {
			manager.persist(servico);
			mensagem += "adicionado com sucesso";
		}	
		else {
			manager.merge(servico);
			mensagem += "alterado com sucesso";
		}
			return mensagem;
	}

	public List<Servico> listar() {
		return manager.createQuery("from Servico", Servico.class).getResultList();
	}

	public void excluir(Servico servico) {

		Servico removerServico = manager.find(Servico.class, servico.getId());
		fotos = removerServico.getFotos();
		fotos.forEach((Foto f) -> {
			fileManagement.delete(f.getCaminho());
			System.out.println("------------- APAGANDO FOTO S3-----------------");
			manager.remove(f);
		});
		manager.remove(removerServico);

	}
	
	public Servico find(Long servicoId) {
		return manager.find(Servico.class, servicoId);
	}

	public void gravarComFoto(Servico servico) {
		Servico gravarServico = manager.find(Servico.class, servico.getId());
		gravarServico.setFotos(servico.getFotos()); //ACHO Q POSSO EXCLUIR ESSA LINHA!
		gravar(gravarServico);

	}
}
