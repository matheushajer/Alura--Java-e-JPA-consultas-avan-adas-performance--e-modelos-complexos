package br.com.matheushajer.loja_V2.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.matheushajer.loja_V2.modelo.Pedido;
import br.com.matheushajer.loja_V2.modelo.Produto;
import br.com.matheushajer.loja_V2.vo.RelatorioDeVendasVo;

public class PedidoDAO {

	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<RelatorioDeVendasVo> relatorioDeVendas(){
		String jpql = "SELECT new br.com.matheushajer.loja_V2.vo.RelatorioDeVendasVo ("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC ";
		return em.createQuery(jpql,RelatorioDeVendasVo.class).getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id =:id",Pedido.class)
				.setParameter("id", id).getSingleResult();
	}
	
	public List<Produto> BuscarPorParametrosCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if(nome != null && !nome.trim().isEmpty()) {
			builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if(preco != null) {
			builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if(dataCadastro !=null) {
			builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}

		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
	
}
