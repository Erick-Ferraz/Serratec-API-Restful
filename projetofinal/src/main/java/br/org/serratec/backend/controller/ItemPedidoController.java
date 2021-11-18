package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.org.serratec.backend.dto.ItemPedidoDTO;
import br.org.serratec.backend.dto.ItemPedidoInserirDTO;
import br.org.serratec.backend.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projetofinal/pedidoitens")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService ips;
	
	@GetMapping
	@ApiOperation(value = "Listar itens de pedidos", notes = "Listagem de itens")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os itens de todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<ItemPedidoDTO>> listar() {
		return ResponseEntity.ok(ips.listar());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um item de um pedido por ID", notes = "Busca de um item pedido por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um item pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<ItemPedidoDTO> buscarPorId(@PathVariable Integer id) {
		if (ips.obterPorId(id) != null) {
			return ResponseEntity.ok(ips.obterPorId(id).get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ApiOperation(value = "Adicionar um item de pedido", notes = "Cadastro de um item de pedido")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserir(@Valid @RequestBody ItemPedidoInserirDTO itemPed) {
		return ResponseEntity.ok(ips.inserir(itemPed));
	}
	
	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um item de pedido", notes = "Atualização de um item de pedido")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Atualiza um item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizarPorId(@PathVariable(name = "id") Integer id, @Valid @RequestBody ItemPedidoInserirDTO itemPed) {
		if (ips.atualizarPorId(id, itemPed) != null) {
		return ResponseEntity.ok(itemPed);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar um item de pedido", notes = "Exclusão de um item de pedido")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Deleta um item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		if (ips.obterPorId(id) != null) {
			ips.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
