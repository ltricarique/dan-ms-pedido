package ar.edu.utn.frsf.isi.dan.pedido.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.pedido.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.pedido.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.pedido.model.DetallePedido;
import ar.edu.utn.frsf.isi.dan.pedido.model.Pedido;
import ar.edu.utn.frsf.isi.dan.pedido.repository.DetallePedidoRepository;
import ar.edu.utn.frsf.isi.dan.pedido.service.DetallePedidoService;
import ar.edu.utn.frsf.isi.dan.pedido.service.PedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DetallePedidoServiceImpl.class);

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private PedidoService pedidoService;

	@Override
	public DetallePedido guardarDetallePedido(DetallePedido detalle, Long idPedido)
	{
		if (detalle == null)
			throw new ArgumentoIlegalException("Detalle pedido ausente.");

		Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);

		detalle.setId(null);
		detalle.setPedido(pedido);

		detalle = detallePedidoRepository.save(detalle);
		LOGGER.info("Detalle pedido guardado");

		return detalle;
	}

	@Override
	public DetallePedido actualizarDetallePedido(DetallePedido detalle, Long idPedido, Long idDetalle)
	{
		if (detalle == null)
			throw new ArgumentoIlegalException("Detalle pedido ausente.");

		if (!pedidoService.existePedido(idPedido))
			throw new RecursoNoEncontradoException("No existe pedido.");

		if (!detallePedidoRepository.existsById(idDetalle))
			throw new RecursoNoEncontradoException("No existe detalle pedido.");

		if (!detallePedidoRepository.existsByIdDetalleAndIdPedido(idDetalle, idPedido))
			throw new ArgumentoIlegalException("El detalle no corresponde al pedido.");

		Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);

		detalle.setId(idDetalle);
		detalle.setPedido(pedido);

		detalle = detallePedidoRepository.save(detalle);
		LOGGER.info("Detalle pedido guardado");

		return detalle;
	}

	@Override
	public DetallePedido obtenerDetallePedidoPorId(Long idPedido, Long idDetalle)
	{
		return detallePedidoRepository.findByIdAndPedidoId(idDetalle, idPedido)
			.orElseThrow(() -> new RecursoNoEncontradoException("No existe detalle pedido."));
	}

	@Override
	public void eliminarDetallePedido(Long idPedido, Long idDetalle)
	{
		if (!pedidoService.existePedido(idPedido))
			throw new RecursoNoEncontradoException("No existe pedido.");

		if (!detallePedidoRepository.existsById(idDetalle))
			throw new RecursoNoEncontradoException("No existe detalle pedido.");

		if (!detallePedidoRepository.existsByIdDetalleAndIdPedido(idDetalle, idPedido))
			throw new ArgumentoIlegalException("El detalle no corresponde al pedido.");

		detallePedidoRepository.deleteById(idDetalle);
		LOGGER.info("Detalle pedido eliminado");
	}

	@Override
	public List<DetallePedido> obtenerDetallePedidoPorId(Long idPedido)
	{
		return detallePedidoRepository.findByPedidoId(idPedido);
	}

}
