package com.succesgeneration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@RequestMapping("/admin/") //recupera o modelAttribute do form
	public ModelAndView addFoto() {
		ModelAndView modelAndView = new ModelAndView("/admin/home");

		
		return modelAndView;
		
	}
}
