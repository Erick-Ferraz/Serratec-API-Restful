package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Endereco;

public class EnderecoInserirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	@NotBlank
	@Size(max = 9)
	private String cep;
	
	public EnderecoInserirDTO() {

	}

	public EnderecoInserirDTO(Endereco endereco) {
		this.cep = endereco.getCep();

	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
