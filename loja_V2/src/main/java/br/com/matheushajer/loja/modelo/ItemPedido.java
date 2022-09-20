package br.com.matheushajer.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal precoUnitatio;
	private Integer quantidade;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Pedido pedido;

	public ItemPedido() {
	}

	public ItemPedido(Integer quantidade, Produto produto, Pedido pedido) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrecoUnitatio() {
		return precoUnitatio;
	}

	public void setPrecoUnitatio(BigDecimal precoUnitatio) {
		this.precoUnitatio = precoUnitatio;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
