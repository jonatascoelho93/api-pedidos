package br.com.coelho.pedidos.repository.enties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity(name = "vendedores")
public class VendedoresEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVendedor;
	@Column(unique = true)
	private Long codigoVendedor;
	@NotNull
	private String nomeVendedor;
	@NotNull
	private String emailVendedor;
	private String telefoneVendedor;
	@NotNull
	private String celularVendedor;
	@NotNull
	private Long cpfVendedor;
	@NotNull
	private Boolean funcionario;
	@NotNull
	private String senhaVendedor;

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Long getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(Long codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getEmailVendedor() {
		return emailVendedor;
	}

	public void setEmailVendedor(String emailVendedor) {
		this.emailVendedor = emailVendedor;
	}

	public String getTelefoneVendedor() {
		return telefoneVendedor;
	}

	public void setTelefoneVendedor(String telefoneVendedor) {
		this.telefoneVendedor = telefoneVendedor;
	}

	public String getCelularVendedor() {
		return celularVendedor;
	}

	public void setCelularVendedor(String celularVendedor) {
		this.celularVendedor = celularVendedor;
	}

	public Long getCpfVendedor() {
		return cpfVendedor;
	}

	public void setCpfVendedor(Long cpfVendedor) {
		this.cpfVendedor = cpfVendedor;
	}

	public Boolean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Boolean funcionario) {
		this.funcionario = funcionario;
	}

	public String getSenhaVendedor() {
		return senhaVendedor;
	}

	public void setSenhaVendedor(String senhaVendedor) {
		this.senhaVendedor = senhaVendedor;
	}

}
