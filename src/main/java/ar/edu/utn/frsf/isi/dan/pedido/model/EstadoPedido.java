package ar.edu.utn.frsf.isi.dan.pedido.model;

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
	public boolean equals(Object obj) {
		if (obj instanceof EstadoPedido)
			return ((EstadoPedido) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "EstadoPedido [id=" + id + ", estado=" + estado + "]";
	}
}
