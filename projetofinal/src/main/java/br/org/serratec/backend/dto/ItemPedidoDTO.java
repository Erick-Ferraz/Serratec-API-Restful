package br.org.serratec.backend.dto;

import java.io.Serializable;

import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Produto;

public class ItemPedidoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	
	private Produto produto;
	private Integer quantidade;
	private Float precoVenda;
	private Double subTotal;

	public ItemPedidoDTO() {
		
	}
	
	public ItemPedidoDTO(ItemPedido itemPedido) {
		
		this.produto = itemPedido.getProduto();
		this.quantidade = itemPedido.getQuantidade();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.subTotal = itemPedido.getSubTotal();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

}
