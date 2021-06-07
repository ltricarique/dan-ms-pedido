package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@Entity
@Table(name = "DETALLE_PEDIDO", schema = "MS_PEDIDO")
public class DetallePedido implements Serializable
{
	private static final long serialVersionUID = 331171547910769120L;

	@Id
	@SequenceGenerator(name = "detallePedidoGenerator", sequenceName = "SECUENCIA_DETALLE_PEDIDO", schema = "MS_PEDIDO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallePedidoGenerator")
	@Column(name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MATERIAL", foreignKey = @ForeignKey(name = "FK_DETALLE_PEDIDO_ID_MATERIAL_MATERIAL_ID"))
	private Material material;
	@Column(name = "CANTIDAD")
	private BigDecimal cantidad;
	@Column(name = "PRECIO")
	private BigDecimal precio;
	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO", foreignKey = @ForeignKey(name = "FK_DETALLE_PEDIDO_ID_PEDIDO_TO_PEDIDO_ID"))
	@JsonIgnore
	private Pedido pedido;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Material getMaterial()
	{
		return material;
	}

	public void setMaterial(Material material)
	{
		this.material = material;
	}

	public BigDecimal getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad)
	{
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio()
	{
		return precio;
	}

	public void setPrecio(BigDecimal precio)
	{
		this.precio = precio;
	}

	public Pedido getPedido()
	{
		return pedido;
	}

	public void setPedido(Pedido pedido)
	{
		this.pedido = pedido;
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
		DetallePedido other = (DetallePedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "DetallePedido [id=" + id + ", material=" + material + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}

}
