package ar.edu.utn.frsf.isi.dan.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frsf.isi.dan.pedido.model.Pedido;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>
{
	List<Pedido> findByEstado_Id(Long idEstado);

	List<Pedido> findByObra_Cliente_Id(Long idCliente);

	List<Pedido> findByObra_Id(Long idObra);

}
