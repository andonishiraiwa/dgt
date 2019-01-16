package com.dgt.modelo.pojos;

import java.util.Date;

public class Multa {

	private long id;
	private float importe;
	private String concepto;
	private Date fecha_alta;
	private Date fecha_modificacion;
	private Date fecha_baja;

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

	public Multa(long id, float importe, String concepto, Date fecha_alta, Date fecha_modificacion, Date fecha_baja,Coche coche, Agente agente) {
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

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public Date getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	@Override
	public String toString() {
		return "Multa [id=" + id + ", importe=" + importe + ", concepto=" + concepto + ", fecha_alta=" + fecha_alta
				+ ", fecha_modificacion=" + fecha_modificacion + ", fecha_baja=" + fecha_baja + ", coche=" + coche
				+ ", agente=" + agente + "]";
	}

	

	
	
	
	

}
