package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import datos.cargaHoraria;
import datos.estudiosCursados;
import datos.expPrevia;
import datos.locacion;
import datos.rangoEtario;
import datos.remuneracion;
import datos.tipoPuesto;

public class Agencia implements IAgencia {
	private static Agencia instance = null;
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<Empleador> empleadores = new ArrayList<Empleador>();
	private ArrayList<ElemRE> eleccionesEmpleadores = new ArrayList<ElemRE>();
	private Map<String,ElemRE> eleccionesEmpleados = new HashMap<String,ElemRE>();
	
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
		locacion loc = locacion.getInstance();
		remuneracion rem = remuneracion.getInstance();
		cargaHoraria ch = cargaHoraria.getInstance();
		tipoPuesto tp = tipoPuesto.getInstance();
		rangoEtario rgEt = rangoEtario.getInstance();
		expPrevia exp = expPrevia.getInstance();
		estudiosCursados estC = estudiosCursados.getInstance();
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
				if (puntajeAct > 0 && !lista.ticketRepetido(ticketEmpleado,empleadorAct))
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
				if (puntajeAct > 0 && !lista.ticketRepetido(empleadoAct.getTicket(),empleadoAct)) 
					lista.getUsuarios().add(new ElemLA(empleadoAct,puntajeAct,empleadoAct.getTicket()));
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
		this.actualizaPuntajes();
	}
	
	private void actualizaPuntajes() {
		int i;
		Empleado empAct;
		Empleador emprAct;
		for (i=0;i<this.empleados.size();i++) {
			empAct = empleados.get(i);
			try {
				empAct.getListaAsignacion().getUsuarios().first().getUsuario().incrPuntajeApp(10);
			} catch (NoSuchElementException e) {
				System.out.println("No se puede actualizar puntaje porque no hay empleadores en la lista de asignacion de " + empAct.getUsername());
			}
		}
		for (i=0;i<this.empleadores.size();i++) {
			emprAct = empleadores.get(i);
			try {
				emprAct.getListaAsignacion().getUsuarios().first().getUsuario().incrPuntajeApp(5);
				emprAct.getListaAsignacion().getUsuarios().last().getUsuario().incrPuntajeApp(-5);
			} catch(NoSuchElementException e) {
				System.out.println("No se puede actualizar puntaje porque no hay empleados en la lista de asignacion de " + emprAct.getUsername());
			}
		}
	}
	
	public void iniciaRondaElecciones() {
		
		boolean fueElegido = false;
		for (Empleador empleadorAct : this.empleadores) {
			for (Map.Entry<String,ElemRE> entry : this.eleccionesEmpleados.entrySet()) {
				fueElegido = empleadorAct == entry.getValue().getUsuarioElegido();
				if (fueElegido)
					break;
			}
			if (!fueElegido)
				empleadorAct.incrPuntajeApp(-20);
		}
	}
	
	public void iniciaRondaContrataciones() {
		ElemRE eleccionEmpleado;
		Empleador empleadorAct;
		Empleado empleadoAct;
		TicketEmpleado ticketEmpleado;
		int i;
		for (ElemRE eleccionEmpleador : this.eleccionesEmpleadores) {
			eleccionEmpleado = this.eleccionesEmpleados.get(eleccionEmpleador.getUsuarioElegido().getUsername());
			empleadorAct = (Empleador) eleccionEmpleador.getUsuarioActual();
			empleadoAct = (Empleado) eleccionEmpleado.getUsuarioActual();
			i = eleccionEmpleador.getIndiceTicket();
			ticketEmpleado = empleadorAct.getTickets().get(i);
			if (this.matcheoContratacion(ticketEmpleado,empleadoAct,empleadorAct,eleccionEmpleado)) {
				ticketEmpleado.setEstado("finalizado");
				empleadorAct.incrPuntajeApp(5);			// Redefinimos que en vez de 50, como 1 ticket equivale a 1 empleado contratado, se sume solo 5
				empleadoAct.getTicket().setEstado("finalizado");
				empleadoAct.getTicket().setResultado("exito");
				empleadoAct.incrPuntajeApp(10);
			}
		}
	}
	
	private boolean matcheoContratacion(TicketEmpleado ticketEmpleado,Empleado empleadoAct,Empleador empleadorAct,ElemRE eleccionEmpleado) {
		return ticketEmpleado.getEstado() == "activo" && empleadoAct.getTicket().getEstado() == "activo" && empleadorAct == eleccionEmpleado.getUsuarioElegido() && ticketEmpleado.equals(empleadorAct.getTickets().get(eleccionEmpleado.getIndiceTicket()));
	}
	
}
