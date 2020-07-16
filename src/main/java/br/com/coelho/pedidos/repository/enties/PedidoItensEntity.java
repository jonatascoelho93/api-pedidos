package br.com.coelho.pedidos.repository.enties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "pedidoitens")
public class PedidoItensEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedidoItens;
	@NotNull(message = "{Campo não pode ser nulo}")
	private Long quantidade;
	@NotNull(message = "{Campo não pode ser nulo}")
	private Float totalItens;
	
	@ManyToOne
	private ProdutoEntity produto;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private PedidoEntity pedido;

	public Long getIdPedidoItens() {
		return idPedidoItens;
	}

	public void setIdPedidoItens(Long idPedidoItens) {
		this.idPedidoItens = idPedidoItens;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Float getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(Float totalItens) {
		this.totalItens = totalItens;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

}
