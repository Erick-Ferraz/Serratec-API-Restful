package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.dto.ProdutoFotoDTO;
import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.service.FotoService;
import br.org.serratec.backend.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/projetofinal/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService ps;
	
	@Autowired
	private FotoService fs;
	
	@GetMapping
	@ApiOperation(value = "Listar produtos", notes = "Listagem de produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<ProdutoDTO>> listar() {
		return ResponseEntity.ok(ps.listar());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar produto por ID", notes = "Busca de produtos pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Integer id) {
		if (ps.obterPorId(id) != null) {
			return ResponseEntity.ok(ps.obterPorId(id).get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/foto")
	@ApiOperation(value = "Obter foto do produto", notes = "Busca de foto correspondente a um produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma foto de um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<byte[]> obterFoto(@PathVariable Integer id) {
		Foto foto = fs.obterPorId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", foto.getTipo());
		headers.add("content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}

	@PostMapping("/adicionar")
	@ApiOperation(value = "Adicionar produto", notes = "Inserção de produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Insere um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserir(@Valid @RequestBody ProdutoInserirDTO produto) {
		return ResponseEntity.ok(ps.inserir(produto));
	}
	
	@PostMapping("/fotos/adicionar")
	@ApiOperation(value = "Adicionar uma foto a um produto", notes = "Inserção de uma foto para um produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o produto e a foto inserida"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserirFoto(@Valid @RequestPart ProdutoFotoDTO produto, @RequestParam("file") MultipartFile file) {
		try {
			return ResponseEntity.ok(fs.inserirProduto(produto, file));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um produto pelo nº de ID", notes = "Atualização de produto pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Atualiza um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizarPorId(@PathVariable Integer id, @RequestBody ProdutoInserirDTO produto) {
		if (ps.atualizarPorId(id, produto) != null) {
			return ResponseEntity.ok(produto);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar produto por ID", notes = "Exclusão de produto pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta um produto"),
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
	
	@DeleteMapping("/fotos/deletar/{id}")
	@ApiOperation(value = "Deletar foto de um produto por ID da foto", notes = "Exclusão de uma foto de um produto pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta foto de um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> deletarFotoPorId(@PathVariable Integer id) {
		if (fs.obterPorId(id) != null) {
			fs.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
