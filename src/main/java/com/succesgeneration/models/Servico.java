package com.succesgeneration.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class Servico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	
	//Relação one to one
	@Embedded
	private Endereco endereco;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate inicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate termino;
	
	//Um servico, varias fotos!
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(mappedBy="servico")
	private List<Foto> fotos = new ArrayList<>();
	
	public Foto getPrincipal() {
		if (fotos == null | fotos.size() == 0){
			Foto foto = new Foto();
			foto.setCaminho("https://s3.amazonaws.com/successgeneration-teste/photonotavailable.jpeg");
			System.out.println("------ FOTO VAZIA CRIADA----------");
			fotos.add(foto);
		}
		return this.fotos.get(0);
	}
	
	public Servico() {

	}

	public Servico(String nome, String descricao, LocalDate inicio, Endereco endereco) {
		this.nome = nome;
		this.descricao = descricao;
		this.inicio = inicio;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getTermino() {
		return termino;
	}

	public void setTermino(LocalDate termino) {
		this.termino = termino;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", endereco=" + endereco
				+ ", inicio=" + inicio + ", termino=" + termino + ", fotos=" + fotos + "]";
	}
	
	public void setFoto(Foto foto) {
		this.fotos.add(foto);
	}
}
