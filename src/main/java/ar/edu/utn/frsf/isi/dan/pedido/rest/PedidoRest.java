package ar.edu.utn.frsf.isi.dan.pedido.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frsf.isi.dan.pedido.model.DetallePedido;
import ar.edu.utn.frsf.isi.dan.pedido.model.Pedido;

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
public class PedidoRest {
	private static final List<Pedido> PEDIDOS = new ArrayList<>();
//	private static Integer PEDIDO_SEQUENCE = 1;
//	private static Integer DETALLE_PEDIDO_SEQUENCE = 1;

	@PostMapping
	@Operation(summary = "Registra un nuevo pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido registrado correctamente"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"), })
	public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
//		pedido.setId(PEDIDO_SEQUENCE++);
		PEDIDOS.add(pedido);

		return ResponseEntity.ok(pedido);
	}

	@PostMapping(path = Api.PEDIDO_POST_ADD_DETALLE_PATH)
	@Operation(summary = "Agrega un detalle a un pedido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Detalle de pedido agregado correctamente"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedido inexistente"), })
	public ResponseEntity<Pedido> agregarDetalle(@Parameter(description = "Id del pedido a agregar el detalle") @PathVariable Integer idPedido,
			@RequestBody DetallePedido detallePedido) {
		Optional<Pedido> pedidoOpt = PEDIDOS.stream().filter(p -> p.getId().equals(idPedido)).findFirst();

		if (pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();

			if (pedido.getDetalle() == null)
				pedido.setDetalle(new ArrayList<DetallePedido>());

			pedido.getDetalle().add(detallePedido);

			return ResponseEntity.ok(pedido);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping(path = Api.PEDIDO_PUT_ID_PATH)
	@Operation(summary = "Actualiza un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedido inexistente") })
	public ResponseEntity<Pedido> actualizar(@Parameter(description = "Id del pedido a actualizar") @PathVariable Integer id, @RequestBody Pedido pedido) {
		int index = PEDIDOS.indexOf(pedido);

		if (index >= 0) {
			PEDIDOS.set(index, pedido);
			return ResponseEntity.ok(pedido);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping(path = Api.PEDIDO_DELETE_ID_PATH)
	@Operation(summary = "Elimina un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido eliminado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedido inexistente") })
	public ResponseEntity<Pedido> eliminar(@Parameter(description = "Id del pedido a eliminar") @PathVariable Integer id) {
		Optional<Pedido> pedido = PEDIDOS.stream().filter(c -> c.getId().equals(id)).findFirst();

		if (pedido.isPresent()) {
			PEDIDOS.remove(pedido.get());
			return ResponseEntity.of(pedido);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping(path = Api.PEDIDO_DELETE_DETALLE_ID_PATH)
	@Operation(summary = "Elimina un detalle de un pedido.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalle eliminado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedido o detalle inexistente") })
	public ResponseEntity<Pedido> eliminarDetalle(@Parameter(description = "Id del pedido") @PathVariable Integer idPedido, @Parameter(description = "Id del detalle a eliminar") @PathVariable Integer idDetalle) {
		Optional<Pedido> pedido = PEDIDOS.stream().filter(p -> p.getId().equals(idPedido)).findFirst();

		if (pedido.isPresent()) {
			Optional<DetallePedido> detalle = pedido.get().getDetalle().stream()
					.filter(d -> d.getId().equals(idDetalle)).findFirst();

			if (detalle.isPresent()) {
				pedido.get().getDetalle().remove(detalle.get());

				return ResponseEntity.of(pedido);
			}
			else
				return ResponseEntity.notFound().build();
		}
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping(path = Api.PEDIDO_GET_ID_PATH)
	@Operation(summary = "Retorna un pedido por id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido recuperado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedido inexistente") })
	public ResponseEntity<Pedido> obtenerPorId(@Parameter(description = "Id del pedido a retornar") @PathVariable Integer id) {
		Optional<Pedido> obra = PEDIDOS.stream().filter(c -> c.getId().equals(id)).findFirst();

		return ResponseEntity.of(obra);
	}

	@GetMapping(params = Api.PEDIDO_GET_ID_OBRA_REQUEST_PARAM)
	@Operation(summary = "Retorna los pedidos de una obra por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos recuperados"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedidos u obra inexistentes") })
	public ResponseEntity<List<Pedido>> obtenerPorIdObra(@Parameter(description = "Id de la obra") @RequestParam(name = "idObra") Integer idObra) {
		List<Pedido> pedidos = PEDIDOS.stream().filter(p -> p.getObra().getId().equals(idObra)).collect(Collectors.toList());

		if (pedidos != null && pedidos.size() > 0)
			return ResponseEntity.ok(pedidos);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping
	@Operation(summary = "Retorna los pedidos de un cliente por su id y/o cuit.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos recuperados"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedidos u obra inexistentes") })
	public ResponseEntity<List<Pedido>> obtenerPorCuitIdCliente(@Parameter(description = "Id del cliente") @RequestParam(name = "idCliente", required = false) Integer idCliente, @Parameter(description = "Cuit del cliente") @RequestParam(name = "cuit", required = false) String cuit) {
		List<Pedido> pedidos = PEDIDOS.stream().filter(p -> p.getObra().getCliente().getId().equals(idCliente) | p.getObra().getCliente().getCuit().equals(cuit)).collect(Collectors.toList());

		if (pedidos != null && pedidos.size() > 0)
			return ResponseEntity.ok(pedidos);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping(path = Api.PEDIDO_GET_DETALLE_ID_PATH)
	@Operation(summary = "Retorna un detalle dado de un pedido por su id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalle recuperado"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "Pedido o detalle inexistente") })
	public ResponseEntity<DetallePedido> obtenerDetallePorId(@Parameter(description = "Id del pedido") @PathVariable Integer idPedido, @Parameter(description = "Id del detalle a recuperar") @PathVariable Integer idDetalle) {
		Optional<Pedido> pedido = PEDIDOS.stream().filter(p -> p.getId().equals(idPedido)).findFirst();
		
		if (pedido.isPresent()) {
			Optional<DetallePedido> detalle = pedido.get().getDetalle().stream().filter(p -> p.getId().equals(idDetalle)).findFirst();
			
			return ResponseEntity.of(detalle);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping(path = Api.PEDIDO_GET_ALL_PATH)
	@Operation(summary = "Retorna todos los pedidos registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos registrados"),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "Prohibido"),
			@ApiResponse(responseCode = "404", description = "No existen pedidos registrados") })
	public ResponseEntity<List<Pedido>> listar() {
		if (!PEDIDOS.isEmpty())
			return ResponseEntity.ok(PEDIDOS);
		else
			return ResponseEntity.notFound().build();
	}

}
