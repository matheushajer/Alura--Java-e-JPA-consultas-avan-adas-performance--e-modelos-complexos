package br.com.matheushajer.loja_V2.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId id;

	public Categoria() {
	}

	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "XPTO");
	}

}
