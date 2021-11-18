package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import br.org.serratec.backend.model.Pedido;

public class PedidoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	
	private Integer id;
	private List<ItemPedidoDTO> itensPedido;
	private LocalDate dtPedido;
	private LocalDate dtEnvio;
	private LocalDate dtEntrega;
	private String status;
	private Double vlTotal;

	public PedidoDTO() {

	}

	public PedidoDTO(Pedido pedido) {

		this.id = pedido.getId();
		this.dtPedido = pedido.getDtPedido();
		this.dtEnvio = pedido.getDtEnvio();
		this.dtEntrega = pedido.getDtEntrega();
		this.status = pedido.getStatus();
		if (pedido.getItensPedido() != null) {
			this.itensPedido = pedido.getItensPedido().stream().map(itemPedido -> new ItemPedidoDTO(itemPedido))
				.collect(Collectors.toList());
			this.vlTotal = pedido.getVlTotal();
		}	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(LocalDate dtPedido) {
		this.dtPedido = dtPedido;
	}

	public LocalDate getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(LocalDate dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public LocalDate getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(LocalDate dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getVlTotal() {
		return vlTotal;
	}

	public List<ItemPedidoDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}

}
