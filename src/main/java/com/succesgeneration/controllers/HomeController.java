package com.succesgeneration.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.succesgeneration.models.Email;
import com.succesgeneration.models.Servico;

@Controller
public class HomeController {

	private String mensagem;

	@Autowired
	private MailSender sender;
	
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
		
		enviarEmailDoCliente(email);
		
		return modelAndView;
	}

	private void enviarEmailDoCliente(Email email) {
		SimpleMailMessage emailAEnviar = new SimpleMailMessage();
		
		String mensagemDeEmail = "Dia e hora da mensagem : " +  LocalDateTime.now() + "\n"; 
		mensagemDeEmail += "Nome : " + email.getNome() + "\n";
		mensagemDeEmail += "Telefone : " + email.getTelefone() + "\n";
		mensagemDeEmail += "E-Mail : " + email.getEmail() + "\n";
		mensagemDeEmail += email.getMensagem();
		
		emailAEnviar.setSubject("Acabamos de receber uma nova mensagem de um cliente");
		emailAEnviar.setTo(email.getEmail());
		emailAEnviar.setText(mensagemDeEmail);
		emailAEnviar.setFrom("successgenerationPT@gmail.com");
		
		sender.send(emailAEnviar);
		
	}
		
}
