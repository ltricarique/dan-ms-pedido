package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.Immutable;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@Entity
@Immutable
@Table(name = "ESTADO_PEDIDO", schema = "MS_PEDIDO")
public class EstadoPedido implements Serializable
{
	private static final long serialVersionUID = 3484395586745154150L;

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "ESTADO")
	private String estado;

	@Transient
	public static final Long NUEVO = 1L;
	@Transient
	public static final Long CONFIRMADO = 2L;
	@Transient
	public static final Long PENDIENTE = 3L;
	@Transient
	public static final Long CANCELADO = 4L;
	@Transient
	public static final Long ACEPTADO = 5L;
	@Transient
	public static final Long RECHAZADO = 6L;
	@Transient
	public static final Long EN_PREPARACION = 7L;
	@Transient
	public static final Long EN_VIAJE = 8L;
	@Transient
	public static final Long ENTREGADO = 9L;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
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
		EstadoPedido other = (EstadoPedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "EstadoPedido [id=" + id + ", estado=" + estado + "]";
	}
}
