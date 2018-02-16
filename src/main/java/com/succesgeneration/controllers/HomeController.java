package com.succesgeneration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.succesgeneration.infra.EnviadorDeEmail;
import com.succesgeneration.models.Email;
import com.succesgeneration.models.Servico;

@Controller
public class HomeController {

	private String mensagem;

	@Autowired
	private EnviadorDeEmail enviadorDeEmail;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/index");
		String mensagem = "Mensagem do home controller";

		modelAndView.addObject("mensagem", mensagem);

		return modelAndView;
	}

	@RequestMapping("/teste")
	public ModelAndView teste() {
		ModelAndView modelAndView = new ModelAndView("/teste");
		modelAndView.addObject("servico", new Servico());
		return modelAndView;
	}

	@RequestMapping("/cliente/contato")
	public ModelAndView contato() {
		ModelAndView modelAndView = new ModelAndView("/cliente/contato");
		modelAndView.addObject("email", new Email());
		modelAndView.addObject("mensagem", mensagem);
		mensagem = "";
		return modelAndView;
	}

	@RequestMapping("/enviaEmail")
	public ModelAndView enviaEmail(Email email) {
		this.mensagem = "E-mail enviado com sucesso";
		ModelAndView modelAndView = new ModelAndView("redirect:/cliente/contato");
		enviadorDeEmail.enviarEmailDoCliente(email);

		return modelAndView;
	}

}
