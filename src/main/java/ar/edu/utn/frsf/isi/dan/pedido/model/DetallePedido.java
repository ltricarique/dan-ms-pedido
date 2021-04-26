package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.math.BigDecimal;

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
	public boolean equals(Object obj) {
		if (obj instanceof DetallePedido)
			return ((DetallePedido) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", material=" + material + ", cantidad=" + cantidad + ", precio=" + precio
				+ "]";
	}

	
}
