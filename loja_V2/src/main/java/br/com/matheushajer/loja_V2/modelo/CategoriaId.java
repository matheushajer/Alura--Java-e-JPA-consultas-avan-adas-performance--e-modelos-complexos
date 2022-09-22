package br.com.matheushajer.loja_V2.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId {

	private String nome;
	private String tipo;

	public CategoriaId() {
	}

	public CategoriaId(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

}
