package modelo;

import java.util.ArrayList;

public class Agencia implements IAgencia {
	private static Agencia instance = null;
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<Empleador> empleadores = new ArrayList<Empleador>();
	
	private Agencia() {
	}
	
	public static Agencia getInstance() {
		if (instance == null)
			instance = new Agencia();
		return instance;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public ArrayList<Empleador> getEmpleadores() {
		return empleadores;
	}
	
	public void addEmpleado(Empleado e) {	// Excepcion para que no hayan mismos nros de usuario etc
		this.empleados.add(e);
	}
	
	public void addEmpleador(Empleador e) {	// Excepcion para que no hayan mismos nros de usuario etc
		this.empleadores.add(e);
	}
	
	public TicketEmpleo recibeFormEmpleado(Formulario f) {
		TicketEmpleo ticket = new TicketEmpleo();
		// hacer ticket de empleo
		
		return ticket;
	}
	
	public TicketEmpleado recibeFormEmpleador(Formulario f,int cantEmpSolicitados) {
		TicketEmpleado ticket = new TicketEmpleado();
		// hacer ticket de empleado
		return ticket;
	}
	
}
