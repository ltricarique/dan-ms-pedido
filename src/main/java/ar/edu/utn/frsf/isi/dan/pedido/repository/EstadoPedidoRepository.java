package ar.edu.utn.frsf.isi.dan.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.pedido.model.EstadoPedido;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long>
{

}
