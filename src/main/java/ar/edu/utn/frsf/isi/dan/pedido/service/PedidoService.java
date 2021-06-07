package ar.edu.utn.frsf.isi.dan.pedido.service;

import java.util.List;

import ar.edu.utn.frsf.isi.dan.pedido.model.Pedido;

public interface PedidoService
{
	Pedido guardarPedido(Pedido pedido);

	Pedido actualizarPedido(Pedido pedido, Long id);

	Pedido obtenerPedidoPorId(Long id);

	List<Pedido> obtenerPedidoPorObraId(Long id);

	List<Pedido> obtenerPedidoPorEstadoId(Long id);

	List<Pedido> obtenerPedidoPorClienteId(Long id);

	List<Pedido> listarPedidos();

	boolean existePedido(Long idPedido);
}
