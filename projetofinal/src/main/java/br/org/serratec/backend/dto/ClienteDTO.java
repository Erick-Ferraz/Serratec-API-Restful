package br.org.serratec.backend.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

public class ClienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	private Integer id;
	private String nome;
	private String username;
	private String email;
	private String telefone;
	private Endereco endereco;
	private String uri;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.username = cliente.getUsername();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.endereco = cliente.getEndereco();
	}

	public ClienteDTO(Optional<Cliente> cliente) {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(id, other.id);
	}

}
