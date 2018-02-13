package com.succesgeneration.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.succesgeneration.daos.FotoDAO;
import com.succesgeneration.daos.ServicoDAO;
import com.succesgeneration.infra.FileManagement;
import com.succesgeneration.models.Foto;
import com.succesgeneration.models.Servico;

@Controller
public class FotoController {

	@Autowired
	private FotoDAO fotoDao;
	
	@Autowired
	private FileManagement fileManagement;
	
	@Autowired
	private ServicoDAO servicoDao;
	
	private List<Foto> fotos = new ArrayList<>();
	
	//REFATORAR ESSE METODO, UMA PARTE SO POPULA A ARRAYLIST E O SALVAR REALMENTE POE NO BANCO
	@RequestMapping("/servicos/servicosAddFoto")
	public ModelAndView addFoto(Foto foto, Long servicoId, String nomeServico , MultipartFile caminho) {

		ModelAndView modelAndView = new ModelAndView("redirect:/servicos/mostrarComFoto");
		
		Servico servico = new Servico();
		servico.setId(servicoId);
		servico.setNome(nomeServico);
			
		String caminhoDafoto = fileManagement.write(caminho);
		foto.setCaminho(caminhoDafoto);
		
		foto.setServico(servico);
		servico.setFoto(foto);

		fotoDao.gravar(foto); 
		servicoDao.gravarComFoto(servico); //so assim consegui atualizar o cache....

		modelAndView.addObject("servicoId", servico.getId());
		
		return modelAndView;

	}
	
	@RequestMapping("/servicos/mostrarComFoto")
	public ModelAndView mostrarComFotos(Long servicoId) {
		ModelAndView modelAndView = new ModelAndView("/servicos/fotoservico");
		
		Servico aEnviar = servicoDao.find(servicoId);
		fotos = aEnviar.getFotos();
		
		modelAndView.addObject("servico", aEnviar);
		modelAndView.addObject("fotos", fotos);
		String caminho = "";
		modelAndView.addObject("caminho", caminho);
		modelAndView.addObject("foto", new Foto());
		
		return modelAndView;
	}
	
	@RequestMapping("/excluirFoto")
	public ModelAndView excluirFoto(Long fotoId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/servicos/mostrarComFoto");
		Foto foto = fotoDao.find(fotoId);
		
		//Contornei o erro do hibernate ehcache assim, se nao der um "update" no servico, ele ainda busca
		//a lista de fotos do servico em cache e me da erro quando excluo uma foto!
		Servico servico = servicoDao.find(foto.getServico().getId());
		servico.getFotos().remove(foto);
		servicoDao.gravar(servico);
		
		fotoDao.excluir(foto);
		modelAndView.addObject("servicoId", servico.getId());
		
		return modelAndView;
	}
	
	@RequestMapping("/tornarprincipal")
	public ModelAndView tornarPrincipal(Long fotoId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/servicos/mostrarComFoto");
		Foto foto = fotoDao.find(fotoId);
		
		//Contornei o erro do hibernate ehcache assim, se nao der um "update" no servico, ele ainda busca
		//a lista de fotos do servico em cache e me da erro quando excluo uma foto!
		Servico servico = servicoDao.find(foto.getServico().getId());
		servico.setFotoPrincipal(foto);
		servicoDao.gravar(servico);

		modelAndView.addObject("servicoId", servico.getId());
		
		return modelAndView;
	}
}
