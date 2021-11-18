package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

public class ProdutoInserirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;

	@NotBlank
	@Size(max = 100)
	private String nome;
	
	@Size(max = 150)
	private String descricao;
	
	@NotNull
	@Min(1)
	private Integer qtdEstoque;
	
	@NotNull
	@Min(1)
	private Float vlUnitario;
	
	private Categoria categoria;

	public ProdutoInserirDTO() {

	}

	public ProdutoInserirDTO(Produto produto) {

		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		produto.setDtCadastro(LocalDate.now());
		this.vlUnitario = produto.getVlUnitario();
		this.categoria = produto.getCategoria();
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

	public void setQtdEstoque(Integer qtd_estoque) {
		this.qtdEstoque = qtd_estoque;
	}

	public Float getVlUnitario() {
		return vlUnitario;
	}

	public void setVlUnitario(Float valor_unitario) {
		this.vlUnitario = valor_unitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
