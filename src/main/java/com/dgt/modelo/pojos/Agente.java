package com.dgt.modelo.pojos;

public class Agente {
	
	private long id;
	private String nombre;
	private String placa;
	private String password;
	
	
	private Departamento departamento;

	public Agente() {
		super();
		this.id=-1;
		this.nombre="";
		this.placa="";
		this.password="";
		
		this.departamento = new Departamento();
	}

	public Agente(long id, String nombre, String placa, Departamento departamento, String password) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.placa = placa;
	
		this.departamento = departamento;
		this.password=password;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Agente [id=" + id + ", nombre=" + nombre + ", placa=" + placa + ", departamento=" + departamento + "]";
	}


	
	
	
	
	

}
