package br.com.matheushajer.loja_V2.dao;

import javax.persistence.EntityManager;

import br.com.matheushajer.loja_V2.modelo.Pedido;

public class PedidoDAO {

	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

}
