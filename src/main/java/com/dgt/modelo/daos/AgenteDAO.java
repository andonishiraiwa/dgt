package com.dgt.modelo.daos;


public class AgenteDAO {
	
	private static AgenteDAO INSTANCE = null;

	public AgenteDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static AgenteDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new AgenteDAO();
		}
		return INSTANCE;
	}

}
