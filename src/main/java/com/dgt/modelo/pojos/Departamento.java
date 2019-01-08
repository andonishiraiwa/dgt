package com.dgt.modelo.pojos;

public class Departamento {
	
	private long id;
	private String nombre;
	public Departamento() {
		super();
		this.id = -1;
		this.nombre = "";
	}
	public Departamento(long id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	

}
