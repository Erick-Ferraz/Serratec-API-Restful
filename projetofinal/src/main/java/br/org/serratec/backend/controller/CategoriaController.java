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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.CategoriaInserirDTO;
import br.org.serratec.backend.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projetofinal/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService cs;

	@GetMapping
	@ApiOperation(value = "Listar categorias", notes = "Listagem de categorias")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todas as categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<CategoriaDTO>> listar() {
		return ResponseEntity.ok(cs.listar());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter categoria por ID", notes = "Busca de categoria pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Integer id) {
		if (cs.obterPorId(id) != null) {
			return ResponseEntity.ok(cs.obterPorId(id).get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/adicionar")
	@ApiOperation(value = "Adicionar categoria", notes = "Inserção de categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Insere uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserir(@Valid @RequestBody CategoriaInserirDTO categoria) {
		return ResponseEntity.ok(cs.inserir(categoria));
	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar categoria por ID", notes = "Atualização de categoria pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Atualiza uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizarPorId(@PathVariable(name = "id") Integer id,
			@Valid @RequestBody CategoriaInserirDTO categoria) {
		if (cs.atualizarPorId(id, categoria) != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar")
	@ApiOperation(value = "Atualizar categoria por nome", notes = "Atualização de categoria pelo nome")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Atualiza uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizarPorNome(@RequestParam(value = "nome") String nome,
			@Valid @RequestBody CategoriaInserirDTO categoria) {
		if (cs.atualizarPorNome(nome, categoria) != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar categoria por ID", notes = "Exclusão de categoria pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		if (cs.obterPorId(id) != null) {
			cs.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
