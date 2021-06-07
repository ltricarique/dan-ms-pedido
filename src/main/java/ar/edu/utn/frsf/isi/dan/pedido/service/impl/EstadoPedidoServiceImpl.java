package ar.edu.utn.frsf.isi.dan.pedido.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frsf.isi.dan.pedido.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.pedido.model.EstadoPedido;
import ar.edu.utn.frsf.isi.dan.pedido.repository.EstadoPedidoRepository;
import ar.edu.utn.frsf.isi.dan.pedido.service.EstadoPedidoService;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService
{
	@Autowired
	private EstadoPedidoRepository estadoPedidoRepository;

	@Override
	public EstadoPedido obtenerEstadoPedidoPorId(Long id)
	{
		return estadoPedidoRepository.findById(id)
			.orElseThrow(() -> new RecursoNoEncontradoException("No existe estado pedido (id=" + id + ")"));
	}

}
