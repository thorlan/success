package com.succesgeneration.daos;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.succesgeneration.config.JPAConfiguration;
import com.succesgeneration.models.Servico;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ( classes = {JPAConfiguration.class, ServicoDAO.class})
public class ServicoDAOTest {

	@Autowired
	ServicoDAO dao = new ServicoDAO();
	
	
	@Test
	@Transactional
	public void verifica() {
		Servico servico = new Servico();
		servico.setId(new Long (74));
		servico.setNome("Teste");
		
		Servico servicoBuscado = dao.find(servico.getId());
		
		Assert.assertEquals(servicoBuscado.getNome(), servico.getNome());
	
	}
}
