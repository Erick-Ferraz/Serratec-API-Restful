package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.dto.PedidoInserirDTO;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pr;
	

	public List<PedidoDTO> listar() {
		List<PedidoDTO> pedidosDto = new ArrayList<PedidoDTO>();
		List<Pedido> pedidos = pr.findAll();

		for (Pedido pedido : pedidos) {
			PedidoDTO pedidoDto = new PedidoDTO(pedido);
			pedidosDto.add(pedidoDto);
		}
		return pedidosDto;
	}
	
	public Optional<PedidoDTO> obterPorId(Integer id) {

		Optional<Pedido> pedido = pr.findById(id);
		Optional<PedidoDTO> pedidoDto = Optional.ofNullable(new PedidoDTO(pedido.get()));

		if (pedido.isPresent()) {
			return pedidoDto;
		}
		return null;
	}

	public PedidoDTO inserir(PedidoInserirDTO pedido) {		
		Pedido ped = new Pedido(pedido);
		ped.setDtPedido(LocalDate.now());
		ped.setDtEnvio(LocalDate.now().plusDays(3));
		ped.setDtEntrega(LocalDate.now().plusDays(10));
		ped.setStatus("Status: em andamento");
		ped.setCliente(pedido.getCliente());

		pr.saveAndFlush(ped);
		return new PedidoDTO(ped);
	}

	public PedidoDTO atualizarPorId(Integer id, PedidoInserirDTO pedido) {
		if (pr.existsById(id)) {
			Pedido ped = new Pedido();
			ped.setId(id);
			ped.setDtPedido(LocalDate.now());
			ped.setDtEnvio(LocalDate.now().plusDays(3));
			ped.setDtEntrega(LocalDate.now().plusDays(10));
			if (pr.getById(id).getDtEntrega().isBefore(LocalDate.now())) {
				ped.setStatus("Status: finalizado");
			}else {
				ped.setStatus("Status: em andamento");
			}
			ped.setCliente(pedido.getCliente());
			pr.saveAndFlush(ped);

			PedidoDTO pedDto = new PedidoDTO(ped);

			return pedDto;
		}
		return null;
	}

	public void deletarPorId(Integer id) {
		if (pr.existsById(id)) {
			pr.deleteById(id);
		}
	}
}
