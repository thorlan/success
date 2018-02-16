package com.succesgeneration.infra;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.succesgeneration.daos.EmailDAO;
import com.succesgeneration.models.Email;
import com.succesgeneration.models.Servico;

@Component
public class EnviadorDeEmail {

	@Autowired
	private MailSender sender;
	
	@Autowired
	private EmailDAO emailDao;
	
	private String emailSubject = "";
	private SimpleMailMessage emailAEnviar = new SimpleMailMessage();
	
	public void enviarEmailDoCliente(Email email) {
		
		String mensagem = compoeMensagem(email);
		String destinatario = "successgenerationPT@gmail.com";
		emailSubject = "Acabamos de receber uma nova mensagem de um cliente";
		
		setaEmail(email, emailSubject, mensagem, destinatario);
		sender.send(emailAEnviar);
		
		if ( email.isEstaNaLista()) {
			try {
				emailDao.gravar(email);
				email.setMensagem("Bem vindo a lista da SuccessGeneration, "
						+ "agora voce vai ficar por dentro das novidades do site \n"
						+ "Se quiser parar de receber esses emails é só clicar aqui(link)");
				enviaEmailConfirmacao(email);
			} catch (DataIntegrityViolationException e) {
				System.out.println(e);
			}
		}
		
	}

	private void setaEmail(Email email, String subject, String mensagem , String destinatario) {
		emailAEnviar.setSubject(subject);
		emailAEnviar.setTo(destinatario);
		emailAEnviar.setText(mensagem);
		emailAEnviar.setFrom(email.getEmail());
	}

	private String compoeMensagem(Email email) {
		
		String mensagem = "Nome: " + email.getNome() + "\n";
		//mensagem += "Dia e hora da mensagem : " +  LocalDateTime.now() + "\n"; 
		mensagem += "Telefone: " + email.getTelefone() + "\n";
		mensagem += "E-Mail: " + email.getEmail() + "\n";
		mensagem += email.getMensagem() + "\n";
		return mensagem;
	}

	public void enviaEmailConfirmacao(Email email) {
		
		String mensagem = compoeMensagem(email);
		String destinatario = email.getEmail();
		emailSubject = "Agora você vai ficar por dentro das novidades da SuccessGeneration";
		setaEmail(email, emailSubject, mensagem, destinatario);
		sender.send(emailAEnviar);
		
	}
	
	public void enviarServicoPorEmail (Servico servico) {
		
		String mensagem;
		String destinatario;
		List<Email> listaDeEmail = emailDao.listar();
		
		emailSubject = "Temos um novo serviço em nosso site";
		
		for (Email email : listaDeEmail) {
			email.setMensagem("Olá " + email.getNome() + " como você se registrou em nossa lista de novidades, gostaríamos que pudesse" + "\n"
				+ "ver o nosso trabalho que foi um(a) " + servico.getNome() + ". \n"
				+ "Para ver mais clique no link http://54.82.225.125:8080/succesgeneration/cliente/servico?servicoId=" + servico.getId() +"\n"
				+ "Se não quiser receber mais nossas mensagens clique aqui (colocar o link)" + "\n"
				+ "A SuccessGeneration bla bla bla mensagem bonita");
			mensagem = compoeMensagem(email);
			destinatario = email.getEmail();
			
			
			setaEmail(email, emailSubject, mensagem, destinatario);
			sender.send(emailAEnviar);
		}
		
		
		
		
		
		
	}
}
