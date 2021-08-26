package ar.edu.utn.frsf.isi.dan.pedido.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frsf.isi.dan.pedido.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.pedido.exception.ErrorInternoException;
import ar.edu.utn.frsf.isi.dan.pedido.exception.OperacionNoPermitidaException;
import ar.edu.utn.frsf.isi.dan.pedido.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.pedido.model.DetallePedido;
import ar.edu.utn.frsf.isi.dan.pedido.model.EstadoPedido;
import ar.edu.utn.frsf.isi.dan.pedido.model.Pedido;
import ar.edu.utn.frsf.isi.dan.pedido.repository.PedidoRepository;
import ar.edu.utn.frsf.isi.dan.pedido.service.EstadoPedidoService;
import ar.edu.utn.frsf.isi.dan.pedido.service.PedidoService;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Service
public class PedidoServiceImpl implements PedidoService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PedidoServiceImpl.class);

	@Autowired
	private PedidoRepository pedidoRepository;
	//	@Autowired
	//	private DetallePedidoService detallePedidoService;
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public Pedido guardarPedido(Pedido pedido)
	{
		if (pedido == null)
			throw new ArgumentoIlegalException("Pedido no suministrado.");

		pedido.setId(null);

		if (pedido.getDetalle() == null || pedido.getDetalle().isEmpty())
			throw new ArgumentoIlegalException("Detalle de pedido no suministrado.");

		for (DetallePedido detalle : pedido.getDetalle())
		{
			if (detalle.getMaterial() == null || detalle.getCantidad() == null)
				throw new ArgumentoIlegalException("Detalle de pedido incompleto.");

			detalle.setPedido(pedido);
		}

		EstadoPedido estadoPedidoNuevo = estadoPedidoService.obtenerEstadoPedidoPorId(EstadoPedido.NUEVO);

		if (!estadoPedidoNuevo.equals(pedido.getEstado()))
			pedido.setEstado(estadoPedidoNuevo);

		pedido = pedidoRepository.save(pedido);
		LOGGER.info("Pedido guardado");

		//		jmsTemplate.convertAndSend("COLA_PEDIDOS", serializarDetallePedido(detallePedidoService.obtenerDetallePedidoPorId(pedido.getId())));
		jmsTemplate.convertAndSend("COLA_PEDIDOS", serializarDetallePedido(pedido.getDetalle()));
		LOGGER.info("Pedido enviado a COLA_PEDIDOS");

		return pedido;
	}

	private String serializarDetallePedido(List<DetallePedido> detalles)
	{
		String json = null;
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			json = mapper.writeValueAsString(detalles);
		}
		catch (JsonProcessingException e)
		{
			throw new ErrorInternoException(e);
		}

		return json;
	}

	@Override
	public Pedido actualizarPedido(Pedido pedido, Long id)
	{
		if (pedido == null)
			throw new ArgumentoIlegalException("Pedido no suministrado.");

		if (!pedidoRepository.existsById(id))
			throw new RecursoNoEncontradoException("No existe pedido.");

		pedido.setId(id);

		EstadoPedido estadoPedido = pedido.getEstado();

		if (estadoPedido == null)
			throw new ArgumentoIlegalException("Estado del pedido no suministrado.");

		switch (estadoPedido.getId().intValue())
		{
			//EstadoPedido.CONFIRMADO
			case 2:
			{
				EstadoPedido estado = determinarSiguienteEstadoConfirmado(pedido);
				pedido.setEstado(estado);
				pedidoRepository.save(pedido);
				LOGGER.info("Pedido actualizado");

				if (estado.getId().equals(EstadoPedido.RECHAZADO))
					throw new OperacionNoPermitidaException("Pedido RECHAZADO.");

				break;
			}
			default:
			{
				pedidoRepository.save(pedido);
				LOGGER.info("Pedido actualizado");

				break;
			}
		}

		return pedido;
	}

	/**
	 * Dado el estado del pedido CONFIRMADO, determina el siguiente estado.
	 * 
	 * @param pedido
	 * @return
	 */
	private EstadoPedido determinarSiguienteEstadoConfirmado(Pedido pedido)
	{
		if (existeStockDisponible(pedido))
		{
			if (!generaSaldoDeudor(pedido) || (esSaldoDeudorMenorDescubierto(pedido) && esSituacionCrediticiaBCRABajoRiesgo(pedido)))
				return estadoPedidoService.obtenerEstadoPedidoPorId(EstadoPedido.ACEPTADO);
			else
				return estadoPedidoService.obtenerEstadoPedidoPorId(EstadoPedido.RECHAZADO);
		}
		else
			return estadoPedidoService.obtenerEstadoPedidoPorId(EstadoPedido.PENDIENTE);
	}

	/**
	 * Determina si existe stock de todos los materiales detallados en cada <code>DetallePedido</code>. Se utiliza el
	 * <code>MaterialService</code> para determinar si existe stock.
	 * 
	 * @param pedido
	 * @return
	 */
	private boolean existeStockDisponible(Pedido pedido)
	{
		return true;
	}

	/**
	 * Determina si el pedido genera saldo deudor. El saldo se calcula como IMPORTE_TOTAL_PEDIDOS - IMPORTE_TOTAL_PAGOS = SALDO. Si SALDO > 0,
	 * entonces el pedido genera saldo deudor. Falso en caso contrario.
	 * 
	 * @param pedido
	 * @return
	 */
	private boolean generaSaldoDeudor(Pedido pedido)
	{
		return false;
	}

	/**
	 * Determina si el saldo deudor es menor al descubierto (Cliente.getMaximoCuentaCorriente())
	 * 
	 * @param pedido
	 * @return
	 */
	private boolean esSaldoDeudorMenorDescubierto(Pedido pedido)
	{
		return true;
	}

	/**
	 * Determina si la situación crediticia del cliente en BCRA es de bajo riesgo.
	 * 
	 * @param pedido
	 * @return
	 */
	private boolean esSituacionCrediticiaBCRABajoRiesgo(Pedido pedido)
	{
		return true;
	}

	@Override
	public Pedido obtenerPedidoPorId(Long id)
	{
		return pedidoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("No existe pedido."));
	}

	@Override
	public List<Pedido> obtenerPedidoPorEstadoId(Long id)
	{
		List<Pedido> pedidos = pedidoRepository.findByEstado_Id(id);

		if (pedidos == null || pedidos.isEmpty())
			throw new RecursoNoEncontradoException("No existen pedidos.");

		return pedidos;
	}

	@Override
	public List<Pedido> obtenerPedidoPorClienteId(Long id)
	{
		List<Pedido> pedidos = pedidoRepository.findByObra_Cliente_Id(id);

		if (pedidos == null || pedidos.isEmpty())
			throw new RecursoNoEncontradoException("No existen pedidos.");

		return pedidos;
	}

	@Override
	public List<Pedido> obtenerPedidoPorObraId(Long id)
	{
		List<Pedido> pedidos = pedidoRepository.findByObra_Id(id);

		if (pedidos == null || pedidos.isEmpty())
			throw new RecursoNoEncontradoException("No existen pedidos.");

		return pedidos;
	}

	@Override
	public List<Pedido> listarPedidos()
	{
		List<Pedido> pedidos = pedidoRepository.findAll();

		//		if (pedidos == null || pedidos.isEmpty())
		//			throw new RecursoNoEncontradoException("No existen pedidos.");

		return pedidos;
	}

	@Override
	public boolean existePedido(Long idPedido)
	{
		return pedidoRepository.existsById(idPedido);
	}
}
