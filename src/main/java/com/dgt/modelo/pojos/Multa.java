package com.dgt.modelo.pojos;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Multa {

	private long id;
	
	@NotNull
	@Min(value = 1, message = "Debe ser una cantidad mayor que 1")
	private float importe;
	
	@NotEmpty
	@Size(min = 10, max = 255)
	private String concepto;
	
	
	private Timestamp fecha_alta;
	private Timestamp fecha_modificacion;
	private Timestamp fecha_baja;

	private Coche coche;

	private Agente agente;

	public Multa() {
		super();
		this.id = -1;
		this.importe = 0;
		this.concepto = "";
		//TODO mirar fecha actual
		this.fecha_alta = null;
		this.fecha_modificacion = null;
		this.fecha_baja = null;
		this.coche = new Coche();
		this.agente = new Agente();	

	}

	public Multa(long id, float importe, String concepto, Timestamp fecha_alta, Timestamp fecha_modificacion, Timestamp fecha_baja,Coche coche, Agente agente) {
		this();
		this.id = id;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha_alta = fecha_alta;
		this.fecha_modificacion = fecha_modificacion;
		this.fecha_baja = fecha_baja;
		this.coche = coche;
		this.agente = agente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	
	

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public Timestamp getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Timestamp fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public Timestamp getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Timestamp fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	@Override
	public String toString() {
		return "Multa [id=" + id + ", importe=" + importe + ", concepto=" + concepto + ", fecha_alta=" + fecha_alta
				+ ", fecha_modificacion=" + fecha_modificacion + ", fecha_baja=" + fecha_baja + ", coche=" + coche
				+ ", agente=" + agente + "]";
	}

	

	
	
	
	

}
