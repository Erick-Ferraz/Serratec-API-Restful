package br.org.serratec.backend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Categoria;

public class CategoriaInserirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	@NotBlank
	@Size(max = 80)
	private String nome;
	
	@Size(max = 150)
	private String descricao;
	
	public CategoriaInserirDTO() {

	}

	public CategoriaInserirDTO(Categoria categoria) {
		super();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
