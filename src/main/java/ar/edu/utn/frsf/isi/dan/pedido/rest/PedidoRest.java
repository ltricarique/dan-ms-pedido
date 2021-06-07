package ar.edu.utn.frsf.isi.dan.pedido.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frsf.isi.dan.pedido.exception.ArgumentoIlegalException;
import ar.edu.utn.frsf.isi.dan.pedido.exception.RecursoNoEncontradoException;
import ar.edu.utn.frsf.isi.dan.pedido.model.DetallePedido;
import ar.edu.utn.frsf.isi.dan.pedido.model.Pedido;
import ar.edu.utn.frsf.isi.dan.pedido.service.DetallePedidoService;
import ar.edu.utn.frsf.isi.dan.pedido.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@RestController
@RequestMapping(Api.PEDIDO_BASE_PATH)
@Tag(name = "PedidoRest", description = "Permite gestionar los pedidos realizados por los clientes de la empresa.")
public class PedidoRest
{
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private DetallePedidoService detallePedidoService;

	@PostMapping
	@Operation(summary = "Registra un nuevo pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido registrado correctamente"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Recurso no encontrado") })
	public ResponseEntity<?> registrar(@RequestBody Pedido pedido)
	{
		try
		{
			return ResponseEntity.ok(pedidoService.guardarPedido(pedido));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping(path = Api.PEDIDO_POST_ADD_DETALLE_PATH)
	@Operation(summary = "Agrega un detalle a un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalle de pedido agregado correctamente"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedido inexistente"), })
	public ResponseEntity<?> agregarDetalle(@RequestBody DetallePedido detallePedido,
		@Parameter(description = "Id del pedido a agregar el detalle") @PathVariable Long idPedido)
	{
		try
		{
			return ResponseEntity.ok(detallePedidoService.guardarDetallePedido(detallePedido, idPedido));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping(path = Api.PEDIDO_PUT_DETALLE_ID_PATH)
	@Operation(summary = "Actualiza un detalle de un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalle de pedido agregado correctamente"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedido inexistente"), })
	public ResponseEntity<?> actualizarDetalle(@RequestBody DetallePedido detallePedido,
		@Parameter(description = "Id del pedido a actualizar el detalle") @PathVariable Long idPedido,
		@Parameter(description = "Id detalle a actualizar") @PathVariable Long idDetalle)
	{
		try
		{
			return ResponseEntity.ok(detallePedidoService.actualizarDetallePedido(detallePedido, idPedido, idDetalle));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping(path = Api.PEDIDO_PUT_ID_PATH)
	@Operation(summary = "Actualiza un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedido inexistente") })
	public ResponseEntity<?> actualizar(@Parameter(description = "Pedido a actualizar") @RequestBody Pedido pedido,
		@Parameter(description = "Id del pedido a actualizar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(pedidoService.actualizarPedido(pedido, id));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping(path = Api.PEDIDO_DELETE_DETALLE_ID_PATH)
	@Operation(summary = "Elimina un detalle de un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalle eliminado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedido o detalle inexistente") })
	public ResponseEntity<?> eliminarDetalle(@Parameter(description = "Id del pedido") @PathVariable Long idPedido,
		@Parameter(description = "Id del detalle a eliminar") @PathVariable Long idDetalle)
	{
		try
		{
			detallePedidoService.eliminarDetallePedido(idPedido, idDetalle);
			return ResponseEntity.ok().build();
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_ID_PATH)
	@Operation(summary = "Retorna un pedido por id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido recuperado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedido inexistente") })
	public ResponseEntity<?> obtenerPorId(@Parameter(description = "Id del pedido a retornar") @PathVariable Long id)
	{
		try
		{
			return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_ID_OBRA_PATH)
	@Operation(summary = "Retorna los pedidos de una obra por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos recuperados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedidos u obra inexistentes") })
	public ResponseEntity<?> obtenerPorObraId(@Parameter(description = "Id de la obra") @PathVariable Long idObra)
	{
		try
		{
			return ResponseEntity.ok(pedidoService.obtenerPedidoPorObraId(idObra));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_ID_ESTADO_PATH)
	@Operation(summary = "Retorna los pedidos de una obra por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos recuperados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedidos u obra inexistentes") })
	public ResponseEntity<?> obtenerPorEstadoId(@Parameter(description = "Id estado") @PathVariable Long idEstado)
	{
		try
		{
			return ResponseEntity.ok(pedidoService.obtenerPedidoPorEstadoId(idEstado));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_ID_CLIENTE_PATH)
	@Operation(summary = "Retorna los pedidos de un cliente por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos recuperados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedidos inexistentes") })
	public ResponseEntity<?> obtenerPorClienteId(@Parameter(description = "Id del cliente") @PathVariable Long idCliente)
	{
		try
		{
			return ResponseEntity.ok(pedidoService.obtenerPedidoPorClienteId(idCliente));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_DETALLE_ID_PATH)
	@Operation(summary = "Retorna un detalle dado de un pedido por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalle recuperado"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "Pedido o detalle inexistente") })
	public ResponseEntity<?> obtenerDetallePorId(@Parameter(description = "Id del pedido") @PathVariable Long idPedido,
		@Parameter(description = "Id del detalle a recuperar") @PathVariable Long idDetalle)
	{
		try
		{
			return ResponseEntity.ok(detallePedidoService.obtenerDetallePedidoPorId(idPedido, idDetalle));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_ALL_PATH)
	@Operation(summary = "Retorna todos los pedidos registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos registrados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "No existen pedidos registrados") })
	public ResponseEntity<?> listar()
	{
		try
		{
			return ResponseEntity.ok(pedidoService.listarPedidos());
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping(path = Api.PEDIDO_GET_DETALLE_PATH)
	@Operation(summary = "Retorna todos los pedidos registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos registrados"),
		@ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		@ApiResponse(responseCode = "401", description = "No autorizado"), @ApiResponse(responseCode = "403", description = "Prohibido"),
		@ApiResponse(responseCode = "404", description = "No existen pedidos registrados") })
	public ResponseEntity<?> listarDetalles(@Parameter(description = "Id del pedido") @PathVariable Long idPedido)
	{
		try
		{
			return ResponseEntity.ok(detallePedidoService.obtenerDetallePedidoPorId(idPedido));
		}
		catch (ArgumentoIlegalException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch (RecursoNoEncontradoException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
