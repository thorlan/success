package com.succesgeneration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.succesgeneration.models.Servico;

@Controller
public class HomeController {

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
	
}
