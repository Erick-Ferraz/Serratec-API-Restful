package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.dto.EnderecoInserirDTO;
import br.org.serratec.backend.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projetofinal/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService es;
	
	@GetMapping
	@ApiOperation(value = "Listar endereços", notes = "Listagem de endereços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os endereços"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<EnderecoDTO>> listar() {
		return ResponseEntity.ok(es.listar());
	}
	
	@PostMapping("/adicionar")
	@ApiOperation(value = "Adicionar endereço", notes = "Inserção de endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Insere um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserir(@Valid @RequestBody EnderecoInserirDTO endereco) {
		return ResponseEntity.ok(es.inserirDeFato(endereco));		
	}
	
	@GetMapping("/{cep}")
	@ApiOperation(value = "Buscar endereço por CEP", notes = "Busca um endereço pelo nº do CEP")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<EnderecoDTO> buscarPorCep(@RequestParam(value = "cep") String cep) {
		return ResponseEntity.ok(es.buscarPorCep(cep));
	}
	
	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar endereço por ID", notes = "Exclusão de um endereço pelo nº do CEP")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta um endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		if (es.buscarPorId(id) != null) {
			es.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
