package com.dgt.modelo.pojos;

public class Agente {
	
	private long id;
	private String nombre;
	private String placa;
	
	private Departamento departamento;

	public Agente() {
		super();
		this.id=-1;
		this.nombre="";
		this.placa="";
		
		this.departamento = new Departamento();
	}

	public Agente(long id, String nombre, String placa, Departamento departamento) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.placa = placa;
		this.departamento = departamento;
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	
	
	
	
	

}
