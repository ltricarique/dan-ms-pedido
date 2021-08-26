package ar.edu.utn.frsf.isi.dan.pedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.pedido.model.DetallePedido;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long>
{
	@Query("select count(d) > 0 from DetallePedido d where d.id = :idDetalle and d.pedido.id = :idPedido")
	boolean existsByIdDetalleAndIdPedido(Long idDetalle, Long idPedido);

	Optional<DetallePedido> findByIdAndPedidoId(Long idDetalle, Long idPedido);

	List<DetallePedido> findByPedidoId(Long idPedido);

}
