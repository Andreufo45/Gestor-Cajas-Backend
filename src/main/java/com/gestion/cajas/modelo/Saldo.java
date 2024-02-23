package com.gestion.cajas.modelo;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "saldo")
public class Saldo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_caja", nullable = false)
	private Long idCaja;

	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "cantidad", nullable = false)
	private Long cantidad;

	@Column(name = "descripcion")
	private String desc;

	public Saldo() {

	}

	public Saldo(Long id, Long idCaja, Date fecha, Long cantidad, String desc) {
		super();
		this.id = id;
		this.idCaja = idCaja;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
