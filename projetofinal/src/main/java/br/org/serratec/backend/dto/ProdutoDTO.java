package br.org.serratec.backend.dto;

import java.io.Serializable;

import br.org.serratec.backend.model.Produto;

public class ProdutoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private Integer qtdEstoque;
	private Float vlUnitario;
	private String categoria;
	private String uri;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto produto) {

		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.vlUnitario = produto.getVlUnitario();
		this.categoria = produto.getCategoria().getNome();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Float getVlUnitario() {
		return vlUnitario;
	}

	public void setVlUnitario(Float vlUnitario) {
		this.vlUnitario = vlUnitario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}