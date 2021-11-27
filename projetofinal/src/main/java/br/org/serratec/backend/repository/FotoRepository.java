package br.org.serratec.backend.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Lob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Integer> {

//	@Query(value = "SELECT * FROM apiprova.foto WHERE id_cliente =:id", nativeQuery = true)
//	public Optional<List<Foto>> findByClientId(Integer id);
}
