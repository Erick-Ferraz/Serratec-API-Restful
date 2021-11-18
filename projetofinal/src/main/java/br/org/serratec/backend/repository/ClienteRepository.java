package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.org.serratec.backend.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	public Cliente findByUsername(String username);
	public Cliente findByNome (String nome);
	public Cliente findByEmail(String email);
	public Cliente findByCpf(String cpf);
	
	public Cliente existsByNome(String nome);
	public Cliente existsByCpf(String cpf);
	
	public Cliente deleteByNome(String nome);
	public Cliente deleteByCpf(String cpf);
	
	public Cliente getByCpf(String cpf);
}
