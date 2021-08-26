package ar.edu.utn.frsf.isi.dan.pedido.service;

import java.util.List;

import ar.edu.utn.frsf.isi.dan.pedido.model.DetallePedido;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
public interface DetallePedidoService
{
	DetallePedido guardarDetallePedido(DetallePedido detalle, Long idPedido);

	DetallePedido actualizarDetallePedido(DetallePedido detalle, Long idPedido, Long idDetalle);

	void eliminarDetallePedido(Long idPedido, Long idDetalle);

	DetallePedido obtenerDetallePedidoPorId(Long idPedido, Long idDetalle);

	List<DetallePedido> obtenerDetallePedidoPorId(Long idPedido);
}
