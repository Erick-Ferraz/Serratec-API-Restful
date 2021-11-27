package br.org.serratec.backend.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.UsernameException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.service.ClienteService;
import br.org.serratec.backend.service.FotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projetofinal/clientes")
public class ClienteController {

	@Autowired
	private ClienteService cs;
	
	@Autowired
	private FotoService fs;

	@GetMapping
	@ApiOperation(value = "Listar todos os clientes", notes = "Listagem de clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(cs.listar());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Obter cliente por ID", notes = "Busca de um cliente pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<ClienteDTO> obterPorId(@PathVariable Integer id) {
		if (cs.obterPorId(id) != null) {
			return ResponseEntity.ok(cs.obterPorId(id));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cpf")
	@ApiOperation(value = "Obter cliente por CPF", notes = "Busca de um cliente pelo nº do CPF")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<ClienteDTO> obterPorCpf(@RequestParam(value = "cpf") String cpf) {
		if (cs.obterPorCpf(cpf) != null) {
			return ResponseEntity.ok(cs.obterPorCpf(cpf));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/foto")
	@ApiOperation(value = "Obter foto do cliente", notes = "Busca de foto correspondente a um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma foto de um cliente"),
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
	
	@PostMapping("/fotos/adicionar")
	@ApiOperation(value = "Adicionar uma foto a um cliente", notes = "Inserção de uma foto para um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o cliente e a foto inserida"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserirFoto(@Valid @RequestPart Cliente cliente, @RequestParam("file") MultipartFile file) {
		try {
			return ResponseEntity.ok(fs.inserir(cliente, file));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping(value = "/adicionar", consumes = "multipart/form-data")
	@ApiOperation(value = "Adicionar um cliente", notes = "Inserção de um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Insere um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> inserir(@Valid @RequestBody ClienteInserirDTO clienteInserirDto) {
		try {
			ClienteDTO clienteDto = cs.inserir(clienteInserirDto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(clienteDto.getId())
					.toUri();
			return ResponseEntity.created(uri).body(clienteDto); 
		} catch (EmailException | UsernameException | CpfException | IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um cliente por ID", notes = "Atualização de um cliente pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Atualiza um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizarPorId(@PathVariable Integer id,
			@Valid @RequestBody ClienteInserirDTO clienteInserirDto) {
		try {
			if (cs.atualizarPorId(id, clienteInserirDto) != null) {
				return ResponseEntity.ok(clienteInserirDto);
			}
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar um cliente por ID", notes = "Deleção de um cliente pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta um cliente"),
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

	@DeleteMapping("/deletar")
	@ApiOperation(value = "Deletar um cliente por CPF", notes = "Exclusão de um cliente pelo nº do CPF")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Void> deletarPorCpf(@RequestParam(value = "cpf") String cpf) {
		if (cs.obterPorCpf(cpf) != null) {
			cs.deletarPorCpf(cpf);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/fotos/deletar/{id}")
	@ApiOperation(value = "Deletar foto de um cliente por ID da foto", notes = "Exclusão de uma foto de um cliente pelo nº do ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleta foto de um cliente"),
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
