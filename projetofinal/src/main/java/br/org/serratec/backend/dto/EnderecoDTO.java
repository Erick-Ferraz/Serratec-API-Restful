package br.org.serratec.backend.dto;

import java.io.Serializable;

import br.org.serratec.backend.model.Endereco;

public class EnderecoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String cep;
	
	public EnderecoDTO() {

	}

	public EnderecoDTO(Endereco endereco) {

		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
		this.cep = endereco.getCep();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
