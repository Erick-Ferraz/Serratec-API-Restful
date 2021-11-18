package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import br.org.serratec.backend.model.Cliente;

public class ClienteInserirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;

	@Size(max = 20)
	@NotBlank
	private String username;

	@Size(max = 100)
	@NotBlank
	private String nome;

	@Size(max = 255)
	private String senha;

	@Size(max = 100)
	@Email(message = "O email informado não é um padrão de email válido!")
	private String email;

	@Size(max = 14)
	@CPF(message = "O CPF informado não é válido!")
	private String cpf;

	@Min(value = 1)
	private Integer numero;

	@Size(max = 100)
	private String complemento;

	@Size(max = 11)
	private String telefone;

	private LocalDate dtNasc;

	private EnderecoInserirDTO endereco;

	public ClienteInserirDTO() {

	}

	public ClienteInserirDTO(Cliente cliente) {
		this.username = cliente.getUsername();
		this.nome = cliente.getNome();
		this.senha = cliente.getSenha();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.complemento = cliente.getComplemento();
		this.numero = cliente.getNumero();
		this.telefone = cliente.getTelefone();
		this.dtNasc = cliente.getDtNasc();
		EnderecoInserirDTO endereco = new EnderecoInserirDTO(cliente.getEndereco());
		this.endereco = endereco;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public EnderecoInserirDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoInserirDTO endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(LocalDate dtNasc) {
		this.dtNasc = dtNasc;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
