package com.succesgeneration.models;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Embeddable
public class Endereco {
	private String pais;
	private String localidade;
	
	
	public Endereco(String pais, String localidade) {
		this.pais = pais;
		this.localidade = localidade;
	}
	public Endereco() {
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
	@Override
	public String toString() {
		return "Endereco [pais=" + pais + ", localidade=" + localidade + "]";
	}
	
}
