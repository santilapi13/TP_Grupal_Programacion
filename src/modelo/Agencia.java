package modelo;

import java.util.ArrayList;

import datos.CargaHoraria;
import datos.EstudiosCursados;
import datos.ExpPrevia;
import datos.Locacion;
import datos.RangoEtario;
import datos.Remuneracion;
import datos.TipoPuesto;

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
	
	public TicketEmpleo recibeFormEmpleado(Formulario f,Peso peso) {
		TicketEmpleo ticket = new TicketEmpleo(f,peso);
		return ticket;
	}
	
	public TicketEmpleado recibeFormEmpleador(Formulario f,Peso peso) {
		TicketEmpleado ticket = new TicketEmpleado(f,peso);
		return ticket;
	}
	
	private double calculaPuntEntrevista(Ticket t1,Ticket t2,String perspectiva) {		// Singleton + Template
		double puntaje = 0;
		Locacion loc = Locacion.getInstance();
		Remuneracion rem = Remuneracion.getInstance();
		CargaHoraria ch = CargaHoraria.getInstance();
		TipoPuesto tp = TipoPuesto.getInstance();
		RangoEtario rgEt = RangoEtario.getInstance();
		ExpPrevia exp = ExpPrevia.getInstance();
		EstudiosCursados estC = EstudiosCursados.getInstance();
		puntaje += loc.calculaPuntAspecto(t1,t2,perspectiva);
		puntaje += rem.calculaPuntAspecto(t1,t2,perspectiva);
		puntaje += ch.calculaPuntAspecto(t1,t2,perspectiva);
		puntaje += tp.calculaPuntAspecto(t1,t2,perspectiva);
		puntaje += rgEt.calculaPuntAspecto(t1,t2,perspectiva);
		puntaje += exp.calculaPuntAspecto(t1,t2,perspectiva);
		puntaje += estC.calculaPuntAspecto(t1,t2,perspectiva);
		return puntaje;
	}
	
	private ListaAsignacion creaLAEmpleado(Empleado empAct) {	// Lista de asignacion de empleadores compatibles con el empleado
		ListaAsignacion lista = new ListaAsignacion();
		double puntajeAct;
		for (Empleador empleadorAct : this.empleadores) {
			for (TicketEmpleado ticketEmpleado : empleadorAct.getTickets()) {
				puntajeAct = this.calculaPuntEntrevista(empAct.getTicket(),ticketEmpleado,"Empleado");
				if (puntajeAct > 0 && !lista.ticketRepetido(ticketEmpleado))
					lista.getUsuarios().add(new ElemLA(empleadorAct,puntajeAct,ticketEmpleado));
			}
		}
		return lista;
	}
	
	private ListaAsignacion creaLAEmpleadores(Empleador emprAct) {	// Lista de asignacion de empleados compatibles con el empleador
		ListaAsignacion lista = new ListaAsignacion();
		double puntajeAct;
		for (Empleado empleadoAct : this.empleados) {
			for (TicketEmpleado ticketAct : emprAct.getTickets()) {
				puntajeAct = this.calculaPuntEntrevista(ticketAct,empleadoAct.getTicket(),"Empleador");
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
