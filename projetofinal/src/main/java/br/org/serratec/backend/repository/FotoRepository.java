package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.backend.model.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Integer> {

}
