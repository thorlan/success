package com.succesgeneration.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.succesgeneration.daos.ServicoDAO;
import com.succesgeneration.models.Endereco;
import com.succesgeneration.models.Foto;
import com.succesgeneration.models.Servico;

@Controller
public class ServicosController {

	private String mensagem = "";
	
	private Logger logger = Logger.getLogger(ServicosController.class);

	@Autowired
	private ServicoDAO servicoDao;

	@RequestMapping("/servicos/form")
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("/servicos/form");

		// Cria e seta o modelAttribute servico, para usar n form.
		Servico servico = new Servico();
		servico.setEndereco(new Endereco());
		modelAndView.addObject("servico", servico);

		return modelAndView;
	}

	@RequestMapping("/servicos/servicosAdd") // recupera o modelAttribute do form
	public ModelAndView servicosAdd(@ModelAttribute("servico") Servico servico) {

		ModelAndView modelAndView;

		Foto foto = new Foto();
		foto.setServico(servico);
		
		if (servico.getId() != null) {
			modelAndView = new ModelAndView("redirect:/servicos/mostrarComFoto");
		} else {
			modelAndView = new ModelAndView("/servicos/fotoservico");
		}
		
		logger.info("Inserindo novo serviço no banco de dados \n");
		servicoDao.gravar(servico);
		
		logger.info(
				"Serviço : " + servico.getNome() + " , de ID " + servico.getId() + " foi inserido no banco de dados");

		modelAndView.addObject("foto", foto);
		modelAndView.addObject("servico", servico);
		modelAndView.addObject("servicoId" , servico.getId());
	
		

		return modelAndView;

	}

	// Lista todos os serviços cadastrados
	@RequestMapping("/servicos")
	public ModelAndView listar() {

		logger.info(" Retrieving all services stored in database ");
		List<Servico> servicos = servicoDao.listar();
		ModelAndView modelAndView = new ModelAndView("/servicos/lista");
		modelAndView.addObject("servicos", servicos);
		
		modelAndView.addObject("mensagem", mensagem);

		return modelAndView;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluir(Long servicoId) {
		Servico servico = new Servico();
		servico.setId(servicoId);
		servicoDao.excluir(servico);
		// FAZ LIMPAR A BARRA DE ENDERECO, EVITANDO QUE UM F5 LANCE UMA EXCEPTION
		ModelAndView modelAndView = new ModelAndView("redirect:/servicos");
		return modelAndView;
	}

	// Update no serviço selecionado!
	@RequestMapping("/servicos/updateservico")
	public ModelAndView alterar(Long servicoId) {

		Servico servico = servicoDao.find(servicoId);

		ModelAndView modelAndView = new ModelAndView("/servicos/form");
		modelAndView.addObject("servico", servico);

		return modelAndView;
	}
	
	@RequestMapping("/cliente/mostrartrabalhos")
	public ModelAndView mostrarServicos() {
		ModelAndView modelAndView = new ModelAndView("/cliente/mostrartrabalhos");
		modelAndView.addObject("servicos", servicoDao.listar());
		return modelAndView;
	}
	
	@RequestMapping("/cliente/servico")
	public ModelAndView mostrarServico(Long servicoId) {
		ModelAndView modelAndView = new ModelAndView("/cliente/servico");
		Servico servico = servicoDao.find(servicoId);
		modelAndView.addObject("servico", servico);
		
		return modelAndView;
	}
}
