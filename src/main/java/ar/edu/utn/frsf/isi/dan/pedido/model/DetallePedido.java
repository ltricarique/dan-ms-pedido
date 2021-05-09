package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class DetallePedido {
	private Integer id;
	private Material material;
	private Integer cantidad;
	private BigDecimal precio;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
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
		DetallePedido other = (DetallePedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", material=" + material + ", cantidad=" + cantidad + ", precio=" + precio
				+ "]";
	}

	
}
