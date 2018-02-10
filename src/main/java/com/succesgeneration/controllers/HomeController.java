package com.succesgeneration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public String teste() {
		return "/teste";
	}
	
}
