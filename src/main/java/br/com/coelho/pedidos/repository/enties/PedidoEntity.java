package br.com.coelho.pedidos.repository.enties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "pedido")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@ManyToOne
	private ClienteEntity cliente;

	@ManyToOne
	private VendedorEntity vendedor;

	@ManyToOne
	private FormaPagamentoEntity formaPgto;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<PedidoItensEntity> pedidoItens;

	public PedidoEntity() {
		pedidoItens = new ArrayList<PedidoItensEntity>();
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public VendedorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorEntity vendedor) {
		this.vendedor = vendedor;
	}

	public FormaPagamentoEntity getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPagamentoEntity formaPgto) {
		this.formaPgto = formaPgto;
	}

	public List<PedidoItensEntity> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItensEntity> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
}
