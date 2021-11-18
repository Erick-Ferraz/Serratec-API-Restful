package br.org.serratec.backend.dto;

import java.io.Serializable;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;

public class PedidoInserirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	private Cliente cliente;
	
	public PedidoInserirDTO() {

	}
	
	public PedidoInserirDTO(Pedido pedido) { 
		this.cliente = pedido.getCliente();
		
	}
	
	public Cliente getCliente() {
		return cliente;
		
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
	}
	
}