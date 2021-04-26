package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.time.Instant;
import java.util.List;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class Pedido {
	private Integer id;
	private Instant fechaPedido;
	private Obra obra;
	private List<DetallePedido> detalle;
	private EstadoPedido estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Instant fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public List<DetallePedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallePedido> detalle) {
		this.detalle = detalle;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pedido)
			return ((Pedido) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fechaPedido=" + fechaPedido + ", obra=" + obra + ", detalle=" + detalle
				+ ", estado=" + estado + "]";
	}

}
