package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.org.serratec.backend.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


}
