package br.com.matheushajer.loja_V2.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.matheushajer.loja_V2.dao.CategoriaDAO;
import br.com.matheushajer.loja_V2.dao.ClienteDAO;
import br.com.matheushajer.loja_V2.dao.PedidoDAO;
import br.com.matheushajer.loja_V2.dao.ProdutoDAO;
import br.com.matheushajer.loja_V2.modelo.Categoria;
import br.com.matheushajer.loja_V2.modelo.Cliente;
import br.com.matheushajer.loja_V2.modelo.ItemPedido;
import br.com.matheushajer.loja_V2.modelo.Pedido;
import br.com.matheushajer.loja_V2.modelo.Produto;
import br.com.matheushajer.loja_V2.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		popularBanco();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		
		Produto produto = produtoDao.buscarPorId(1L);
		Cliente cliente = clienteDao.buscarPorId(1L);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(5, produto,  pedido));
		
		
		PedidoDAO pedidoDao = new PedidoDAO(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void popularBanco() {
		
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Modelo novo", new BigDecimal("800"), celulares );
		Cliente cliente = new Cliente("matheus", "123546");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
}
