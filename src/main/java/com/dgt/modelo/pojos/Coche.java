package com.dgt.modelo.pojos;

public class Coche {
	
	private long id;
	private String matricula;
	private String modelo;
	private String km;
	
	
	public Coche() {
		super();
		this.id = -1;
		this.matricula = "";
		this.modelo = "";
		this.km = "";
	}


	public Coche(long id, String matricula, String modelo, String km) {
		this();
		this.id = id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.km = km;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getKm() {
		return km;
	}


	public void setKm(String km) {
		this.km = km;
	}


	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", km=" + km + "]";
	}


	
	
	
	
}
