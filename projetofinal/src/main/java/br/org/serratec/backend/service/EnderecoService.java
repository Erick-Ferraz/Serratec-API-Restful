package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.dto.EnderecoInserirDTO;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository er;

	public List<EnderecoDTO> listar() {

		List<EnderecoDTO> enderecosDto = new ArrayList<EnderecoDTO>();
		List<Endereco> enderecos = er.findAll();

		for (Endereco endereco : enderecos) {
			EnderecoDTO enderecoDto = new EnderecoDTO(endereco);
			enderecosDto.add(enderecoDto);
		}
		return enderecosDto;
	}

	public EnderecoDTO buscarPorCep(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(er.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			EnderecoInserirDTO enderec = new EnderecoInserirDTO();
			enderec.setCep(cep);
			return inserir(enderec);
		}
	}

	public Optional<EnderecoDTO> buscarPorId(Integer id) {
		Optional<Endereco> endereco = er.findById(id);
		Optional<EnderecoDTO> enderecoDto = Optional.ofNullable(new EnderecoDTO(endereco.get()));

		if (endereco.isPresent()) {
			return enderecoDto;
		}
		return null;
	}

	public EnderecoDTO inserir(EnderecoInserirDTO enderec) {

		Optional<Endereco> endereco = Optional.ofNullable(er.findByCep(enderec.getCep()));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String uriViaCep = "https://viacep.com.br/ws/" + enderec.getCep() + "/json/";
			Optional<Endereco> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);

				Endereco var = enderecoViaCep.get();
				EnderecoDTO enderecoDto = new EnderecoDTO(var);
				return enderecoDto;
			} else {
				return null;
			}
		}

	}
	
	public EnderecoDTO inserirDeFato(EnderecoInserirDTO enderec) {

		Optional<Endereco> endereco = Optional.ofNullable(er.findByCep(enderec.getCep()));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String uriViaCep = "https://viacep.com.br/ws/" + enderec.getCep() + "/json/";
			Optional<Endereco> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);

				Endereco var = enderecoViaCep.get();
				er.save(var);
				return new EnderecoDTO(var);
			} else {
				return null;
			}
		}

	}

	public void deletarPorId(Integer id) {
		if (er.existsById(id)) {
			er.deleteById(id);
		}
	}

}
