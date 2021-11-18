package br.org.serratec.backend.dto;

import java.io.Serializable;

import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Produto;

public class ItemPedidoInserirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	
	private Pedido pedido;
	private Produto produto;
	private Integer quantidade;

	public ItemPedidoInserirDTO() {

	}

	public ItemPedidoInserirDTO(ItemPedido itemPed) {
		this.pedido = itemPed.getPedido();
		this.produto = itemPed.getProduto();
		this.quantidade = itemPed.getQuantidade();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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

}
