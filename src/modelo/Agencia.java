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
		TicketEmpleo ticket = new TicketEmpleo(f);
		return ticket;
	}
	
	public TicketEmpleado recibeFormEmpleador(Formulario f) {
		TicketEmpleado ticket = new TicketEmpleado(f);
		return ticket;
	}
	
	private ListaAsignacion creaLAEmpleado(Empleado empAct) {
		ListaAsignacion lista = new ListaAsignacion();
		int puntajeAct;
		for (Empleador empleadorAct : this.empleadores) {
			for (TicketEmpleado ticketEmpleado : empleadorAct.getTickets()) {
				puntajeAct = this.calculaPuntEntrevista(ticketEmpleado,empAct);
				if (puntajeAct > 0 && !lista.ticketRepetido(ticketEmpleado))
					lista.getUsuarios().add(new ElemLA(empleadorAct,puntajeAct,ticketEmpleado));
			}
		}
		return lista;
	}
	
	private ListaAsignacion creaLAEmpleadores(Empleador emprAct) {
		ListaAsignacion lista = new ListaAsignacion();
		int puntajeAct;
		for (Empleado empleadoAct : this.empleados) {
			for (Ticket ticketAct : emprAct.getTickets()) {
				puntajeAct = this.calculaPuntEntrevista();
				if (puntajeAct > 0 && !lista.ticketRepetido(ticketAct))
					lista.getUsuarios().add(new ElemLA(empleadoAct,puntajeAct,ticketAct));
			}
		}
		return lista;
	}
	
	public void iniciaRondaEncuentros() {
		int i;
		Empleado empAct;
		Empleador emprAct;
		for (i=0;i<this.empleados.size();i++) {
			empAct = empleados.get(i);
			empAct.setListaAsignacion(this.creaLAEmpleado(empAct));
		}
		for (i=0;i<this.empleadores.size();i++) {
			emprAct = empleadores.get(i);
			emprAct.setListaAsignacion(this.creaLAEmpleadores(emprAct));
		}
	}
	
}
