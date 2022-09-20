package br.com.matheushajer.loja_V2.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.matheushajer.loja_V2.dao.CategoriaDAO;
import br.com.matheushajer.loja_V2.dao.ProdutoDAO;
import br.com.matheushajer.loja_V2.modelo.Categoria;
import br.com.matheushajer.loja_V2.modelo.Produto;
import br.com.matheushajer.loja_V2.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		
		Produto produto = dao.buscarPorId(1L);
		System.out.println(produto.getPreco());
		
		List<Produto> todos = dao.buscarPorNomeCategoria("CELULARES");
		
		todos.forEach(p -> System.out.println(p.getNome()));
		
		BigDecimal precoProduto = dao.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
		
		System.out.println(precoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Modelo novo", new BigDecimal("800"), celulares );
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
