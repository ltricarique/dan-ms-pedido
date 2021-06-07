package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@Entity
@Table(name = "PEDIDO", schema = "MS_PEDIDO")
public class Pedido implements Serializable
{
	private static final long serialVersionUID = 7108750446742268667L;

	@Id
	@SequenceGenerator(name = "pedidoGenerator", sequenceName = "SECUENCIA_PEDIDO", schema = "MS_PEDIDO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidoGenerator")
	@Column(name = "ID")
	private Long id;
	@Column(name = "FECHA_PEDIDO")
	private Instant fechaPedido;
	@Column(name = "FECHA_ENVIO")
	private Instant fechaEnvio;
	@ManyToOne
	@JoinColumn(name = "ID_OBRA", foreignKey = @ForeignKey(name = "FK_PEDIDO_ID_OBRA_OBRA_ID"))
	private Obra obra;
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<DetallePedido> detalle;
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO", foreignKey = @ForeignKey(name = "FK_PEDIDO_ID_ESTADO_ESTADO_PEDIDO_ID"))
	private EstadoPedido estado;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Instant getFechaPedido()
	{
		return fechaPedido;
	}

	public void setFechaPedido(Instant fechaPedido)
	{
		this.fechaPedido = fechaPedido;
	}

	public Instant getFechaEnvio()
	{
		return fechaEnvio;
	}

	public void setFechaEnvio(Instant fechaEnvio)
	{
		this.fechaEnvio = fechaEnvio;
	}

	public Obra getObra()
	{
		return obra;
	}

	public void setObra(Obra obra)
	{
		this.obra = obra;
	}

	public List<DetallePedido> getDetalle()
	{
		return detalle;
	}

	public void setDetalle(List<DetallePedido> detalle)
	{
		this.detalle = detalle;
	}

	public EstadoPedido getEstado()
	{
		return estado;
	}

	public void setEstado(EstadoPedido estado)
	{
		this.estado = estado;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Pedido [id=" + id + ", fechaPedido=" + fechaPedido + ", obra=" + obra + ", detalle=" + detalle + ", estado=" + estado + "]";
	}

}
