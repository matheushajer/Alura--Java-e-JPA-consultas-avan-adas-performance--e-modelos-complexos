package br.com.matheushajer.loja_V2.dao;

import javax.persistence.EntityManager;

import br.com.matheushajer.loja_V2.modelo.Categoria;

public class CategoriaDAO {

	private EntityManager em;

	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void deletar(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}


}
