package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.backend.dto.ItemPedidoDTO;
import br.org.serratec.backend.dto.ItemPedidoInserirDTO;
import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ItemPedidoRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository ipr;
	
	@Autowired
	private ProdutoRepository pr;
	
	public List<ItemPedidoDTO> listar() {
		List<ItemPedidoDTO> itemPedidosDto = new ArrayList<ItemPedidoDTO>();
		List<ItemPedido> itemPedidos = ipr.findAll();

		for (ItemPedido item : itemPedidos) {
			ItemPedidoDTO itemPedidoDto = new ItemPedidoDTO(item);
			itemPedidosDto.add(itemPedidoDto);
		}
		return itemPedidosDto;
	}
	
	public Optional<ItemPedidoDTO> obterPorId(Integer id) {

		Optional<ItemPedido> itemPedido = ipr.findById(id);
		Optional<ItemPedidoDTO> itemPedidoDto = Optional.ofNullable(new ItemPedidoDTO(itemPedido.get()));

		if (itemPedido.isPresent()) {
			return itemPedidoDto;
		}
		return null;
	}

	public ItemPedidoDTO inserir(ItemPedidoInserirDTO itemPedido) {
		ItemPedido itemPed = new ItemPedido();
		itemPed.setPedido(itemPedido.getPedido());
		itemPed.setProduto(itemPedido.getProduto());
		itemPed.setQuantidade(itemPedido.getQuantidade());
		Optional<Produto> prod = pr.findById(itemPedido.getProduto().getId());
		itemPed.setPrecoVenda(prod.get().getVlUnitario());
		itemPed.setSubTotal(itemPed.getSubTotal());
		ipr.saveAndFlush(itemPed);

		return new ItemPedidoDTO(itemPed);
	}

	public ItemPedidoDTO atualizarPorId(Integer id, ItemPedidoInserirDTO itemPedido) {
		if (ipr.existsById(id)) {
			ItemPedido itemPed = new ItemPedido();
			itemPed.setId(id);
			itemPed.setPedido(itemPedido.getPedido());
			itemPed.setProduto(itemPedido.getProduto());
			itemPed.setQuantidade(itemPedido.getQuantidade());
			itemPed.setPrecoVenda(itemPedido.getProduto().getVlUnitario());
			itemPed.setSubTotal(itemPed.getSubTotal());
			ipr.saveAndFlush(itemPed);

			return new ItemPedidoDTO(itemPed);
		}
		return null;
	}

	public void deletarPorId(Integer id) {
		if (ipr.existsById(id)) {
			ipr.deleteById(id);
		}
	}
}
