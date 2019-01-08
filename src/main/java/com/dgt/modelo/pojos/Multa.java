package com.dgt.modelo.pojos;

import java.sql.Date;

public class Multa {

	private long id;
	private float importe;
	private String concepto;
	private Date fecha;

	private Coche coche;

	private Agente agente;

	public Multa() {
		super();
		this.id = -1;
		this.importe = 0;
		this.concepto = "";
		//TODO mirar fecha actual
		this.fecha = null;
		
		this.coche = new Coche();
		this.agente = new Agente();	

	}

	public Multa(long id, float importe, String concepto, Date fecha, Coche coche, Agente agente) {
		this();
		this.id = id;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	
	
	
	

}
