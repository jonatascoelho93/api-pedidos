package br.com.coelho.pedidos.repository.enties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "estoque")
public class EstoqueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstoque;

	@Column(unique = true)
	private Long qtdEstoque;
	private Long qtdVendida;
	private Long qtdSaldo;

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Long getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Long qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Long getQtdVendida() {
		return qtdVendida;
	}

	public void setQtdVendida(Long qtdVendida) {
		this.qtdVendida = qtdVendida;
	}

	public Long getQtdSaldo() {
		return qtdSaldo;
	}

	public void setQtdSaldo(Long qtdSaldo) {
		this.qtdSaldo = qtdSaldo;
	}

}
