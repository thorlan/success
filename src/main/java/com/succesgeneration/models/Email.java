package com.succesgeneration.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String telefone;
	
	@Transient
	private String mensagem;
	private boolean estaNaLista;
	
	public Email() {
		
	}

	public Email(Long id, String nome, String email, String telefone, String mensagem, boolean estaNaLista) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.mensagem = mensagem;
		this.estaNaLista = estaNaLista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isEstaNaLista() {
		return estaNaLista;
	}

	public void setEstaNaLista(boolean estaNaLista) {
		this.estaNaLista = estaNaLista;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", mensagem="
				+ mensagem + ", estaNaLista=" + estaNaLista + "]";
	}
	
}
