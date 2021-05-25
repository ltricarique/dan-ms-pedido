package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@Entity
@Table(name = "MATERIAL", schema = "MS_MATERIAL")
public class Material
{
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	@Column(name = "DESCUENTO_CANTIDAD")
	private BigDecimal descuentoCantidad;
	@Column(name = "CANTIDAD_MINIMA")
	private BigDecimal cantidadMinima;
	@Column(name = "STOCK_MAXIMO")
	private BigDecimal stockActual;
	@Column(name = "STOCK_MINIMO")
	private BigDecimal stockMinimo;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioUnitario()
	{
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario)
	{
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getDescuentoCantidad()
	{
		return descuentoCantidad;
	}

	public void setDescuentoCantidad(BigDecimal descuentoCantidad)
	{
		this.descuentoCantidad = descuentoCantidad;
	}

	public BigDecimal getCantidadMinima()
	{
		return cantidadMinima;
	}

	public void setCantidadMinima(BigDecimal cantidadMinima)
	{
		this.cantidadMinima = cantidadMinima;
	}

	public BigDecimal getStockActual()
	{
		return stockActual;
	}

	public void setStockActual(BigDecimal stockActual)
	{
		this.stockActual = stockActual;
	}

	public BigDecimal getStockMinimo()
	{
		return stockMinimo;
	}

	public void setStockMinimo(BigDecimal stockMinimo)
	{
		this.stockMinimo = stockMinimo;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Material [id=" + id + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario + "]";
	}

}
