package com.succesgeneration.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.succesgeneration.config.AppWebConfiguration;
import com.succesgeneration.config.JPAConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class})
public class ServicoControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void deveRetornarParaOFormServico() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/servicos/form/"))
	        .andExpect(MockMvcResultMatchers.model().attributeExists("servico"))
	    	.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/servicos/form.jsp"));
	}
	
}
