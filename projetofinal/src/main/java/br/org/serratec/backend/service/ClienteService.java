package br.org.serratec.backend.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.org.serratec.backend.config.EmailConfig;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.UsernameException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cr;

	@Autowired
	private EnderecoService es;
	
	@Autowired
	private FotoService fs;

	@Autowired
	BCryptPasswordEncoder cripto;

	@Autowired
	EmailConfig emailConfig;

	private ClienteDTO adicionarUriFoto(Cliente cliente) {

		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setUsername(cliente.getUsername());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setTelefone(cliente.getTelefone());
		clienteDto.setEndereco(cliente.getEndereco());
	
//		Foto foto = new Foto();
//		foto = fs.obterPorId(cliente.getFoto().getId());
//		if (foto != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/projetofinal/clientes/{id}/foto")
					.buildAndExpand(cliente.getId()).toUri();
			clienteDto.setUri(uri.toString());
//		}

		return clienteDto;
	}
	
	public List<ClienteDTO> listar() {

		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		List<Cliente> clientes = cr.findAll();
		
		for (Cliente cliente : clientes) {
			clientesDTO.add(adicionarUriFoto(cliente));
		}

		return clientesDTO;
	}

	public ClienteDTO obterPorId(Integer id) {

		Optional<Cliente> cliente = cr.findById(id);
		if (cliente.isPresent()) {
			return adicionarUriFoto(cliente.get());
		}
		return null;
	}

	public ClienteDTO obterPorCpf(String cpf) {
		Optional<Cliente> cliente = Optional.ofNullable(cr.findByCpf(cpf));
		if (cliente.isPresent()) {
			return adicionarUriFoto(cliente.get());
		}
		return null;
	}

	public ClienteDTO inserir(ClienteInserirDTO clienteInserirDto)
			throws EmailException, CpfException, UsernameException, IOException {
		if (cr.findByEmail(clienteInserirDto.getEmail()) != null) {
			throw new EmailException("Email já cadastrado! Escolha outro.");
		} else if (cr.findByCpf(clienteInserirDto.getCpf()) != null) {
			throw new CpfException("Este CPF já se encontra cadastrado!");
		} else if (cr.findByUsername(clienteInserirDto.getUsername()) != null) {
			throw new UsernameException("O username informado já está em uso! Escolha outro.");
		}

		Cliente cliente = new Cliente();
		cliente.setCpf(clienteInserirDto.getCpf());
		cliente.setEmail(clienteInserirDto.getEmail());
		cliente.setUsername(clienteInserirDto.getUsername());
		cliente.setNome(clienteInserirDto.getNome());
		cliente.setDtNasc(clienteInserirDto.getDtNasc());
		cliente.setTelefone(clienteInserirDto.getTelefone());
		EnderecoDTO var = es.inserir(clienteInserirDto.getEndereco());
		Endereco endereco = new Endereco(var.getCep(), var.getLogradouro(), var.getBairro(), var.getLocalidade(),
				var.getUf());
		cliente.setEndereco(endereco);
		cliente.setSenha(cripto.encode(clienteInserirDto.getSenha()));
		cliente.setComplemento(clienteInserirDto.getComplemento());
		cliente.setNumero(clienteInserirDto.getNumero());

//		fs.inserir(cr.save(cliente), file);
		cr.save(cliente);;
		emailConfig.enviarEmail(cliente.getEmail(), "API Rest: Cadastro confirmado!", cliente.toString());
		adicionarUriFoto(cliente);
		return new ClienteDTO(cliente);
	}

	public ClienteDTO atualizarPorId(Integer id, ClienteInserirDTO clienteInserirDto) throws IOException {
		if (cr.existsById(id)) {

			Cliente cliente = new Cliente(clienteInserirDto);
			cliente.setId(id);
			cliente.setCpf(clienteInserirDto.getCpf());
			cliente.setEmail(clienteInserirDto.getEmail());
			cliente.setUsername(clienteInserirDto.getUsername());
			cliente.setNome(clienteInserirDto.getNome());
			cliente.setDtNasc(clienteInserirDto.getDtNasc());
			cliente.setTelefone(clienteInserirDto.getTelefone());
			EnderecoDTO var = es.buscarPorCep(clienteInserirDto.getEndereco().getCep());
			Endereco endereco = new Endereco(var.getCep(), var.getLogradouro(), var.getBairro(), var.getLocalidade(),
					var.getUf());
			cliente.setEndereco(endereco);
			cliente.setSenha(cripto.encode(clienteInserirDto.getSenha()));
			cliente.setComplemento(clienteInserirDto.getComplemento());
			cliente.setNumero(clienteInserirDto.getNumero());

			cr.save(cliente);
			emailConfig.enviarEmail(cliente.getEmail(), "API Rest: Alterações na conta!", cliente.toString());
			adicionarUriFoto(cliente);
			return new ClienteDTO(cliente);
		}
		return null;
	}

	public void deletarPorCpf(String cpf) {
		if (cr.existsByCpf(cpf) != null) {
			cr.deleteByCpf(cpf);
		}
	}

	public void deletarPorId(Integer id) {
		if (cr.existsById(id)) {
			cr.deleteById(id);
		}
	}

}
