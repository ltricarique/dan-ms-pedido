package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.util.Objects;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class EstadoPedido {
	private Integer id;
	private String estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoPedido other = (EstadoPedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EstadoPedido [id=" + id + ", estado=" + estado + "]";
	}
}
