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

	private String mensagem;

	private List<Foto> fotos = new ArrayList<>();

	// REFATORAR ESSE METODO, UMA PARTE SO POPULA A ARRAYLIST E O SALVAR REALMENTE
	// POE NO BANCO
	@RequestMapping("/servicos/servicosAddFoto")
	public ModelAndView addFoto(Foto foto, Long servicoId, String nomeServico, MultipartFile caminho) {

		ModelAndView modelAndView = new ModelAndView("redirect:/servicos/mostrarComFoto");

		Servico servico = new Servico();
		servico.setId(servicoId);
		servico.setNome(nomeServico);

		String caminhoDafoto = fileManagement.write(caminho);
		foto.setCaminho(caminhoDafoto);

		foto.setServico(servico);
		servico.setFoto(foto);

		fotoDao.gravar(foto);
		servicoDao.gravarComFoto(servico); // so assim consegui atualizar o cache....

		modelAndView.addObject("mensagem", "Foto Adicionada com Sucesso");
		modelAndView.addObject("servicoId", servico.getId());

		return modelAndView;

	}

	@RequestMapping("/servicos/mostrarComFoto")
	public ModelAndView mostrarComFotos(Long servicoId) {
		ModelAndView modelAndView = new ModelAndView("/servicos/fotoservico");

		Servico aEnviar = servicoDao.find(servicoId);

		if (aEnviar.getFotos() == null | aEnviar.getFotos().size() == 0) {
			fotos = new ArrayList<Foto>();
		} else {
			fotos = aEnviar.getFotos();
		}

		modelAndView.addObject("mensagem", mensagem);
		mensagem = "";
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

		// Contornei o erro do hibernate ehcache assim, se nao der um "update" no
		// servico, ele ainda busca
		// a lista de fotos do servico em cache e me da erro quando excluo uma foto!
		Servico servico = servicoDao.find(foto.getServico().getId());

		if (servico.getFotos().size() == 1) {
			servico.setFotoPrincipal(foto);
		}
		if (servico.getFotoPrincipal().getId() == foto.getId()) {
			System.out.println("-------ERRO, NÃO PODEMOS APAGAR A FOTO PRINCIPAL DE UM SERVIÇO-----------");
			mensagem = "Não podemos apagar a foto principal do Serviço. Escolha outra como principal e então <br> "
					+ "tente apagar essa novamente.";
		} else {
			mensagem = "Foto apagada com sucesso.";
			servico.getFotos().remove(foto);
			servico.setFotoPrincipal(servico.getFotos().get(0));
			fotoDao.excluir(foto);
			servicoDao.gravar(servico);
			System.out.println("---------FOTO APAGADA COM SUCESSO -------------------");
		}

		modelAndView.addObject("servicoId", servico.getId());

		return modelAndView;
	}

	@RequestMapping("/tornarprincipal")
	public ModelAndView tornarPrincipal(Long fotoId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/servicos/mostrarComFoto");
		Foto foto = fotoDao.find(fotoId);

		// Contornei o erro do hibernate ehcache assim, se nao der um "update" no
		// servico, ele ainda busca
		// a lista de fotos do servico em cache e me da erro quando excluo uma foto!
		Servico servico = servicoDao.find(foto.getServico().getId());
		servico.setFotoPrincipal(foto);
		servicoDao.gravar(servico);

		modelAndView.addObject("servicoId", servico.getId());

		return modelAndView;
	}
}
