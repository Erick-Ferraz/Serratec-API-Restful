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
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.dto.PedidoInserirDTO;
import br.org.serratec.backend.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projetofinal/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService ps;
	
	@GetMapping
	@ApiOperation(value = "Listar pedidos", notes = "Listagem de pedidos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<PedidoDTO>> listar() {
		return ResponseEntity.ok(ps.listar());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar pedido por ID", notes = "Busca um pedido pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) {
		if (ps.obterPorId(id) != null) {
			return ResponseEntity.ok(ps.obterPorId(id).get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ApiOperation(value = "Adicionar pedido", notes = "Inserção de pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Insere um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserir(@Valid @RequestBody PedidoInserirDTO pedido) {
		return ResponseEntity.ok(ps.inserir(pedido));
	}
	
	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar pedido por ID", notes = "Atualização de pedido pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Atualiza um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizarPorId(@PathVariable(name = "id") Integer id, @Valid @RequestBody PedidoInserirDTO pedido) {
		if (ps.atualizarPorId(id, pedido) != null) {
		return ResponseEntity.ok(pedido);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar pedido por ID", notes = "Exclusão de pedido pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		if (ps.obterPorId(id) != null) {
			ps.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
