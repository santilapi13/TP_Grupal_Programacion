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
	private double fondos;
	private static Agencia instance = null;
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<Empleador> empleadores = new ArrayList<Empleador>();
	private ArrayList<ElemRE> eleccionesEmpleadores = new ArrayList<ElemRE>();
	private Map<String,ElemRE> eleccionesEmpleados = new HashMap<String,ElemRE>();
	private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
	
	private Agencia() {
		this.fondos = 0;
	}
	
	public static Agencia getInstance() {
		if (instance == null)
			instance = new Agencia();
		return instance;
	}
	
	public ArrayList<Contrato> getContratos() {
		return contratos;
	}
	public void incrFondos(double incr) {
		this.fondos += incr;
	}
	public double getFondos() {
		return fondos;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public ArrayList<Empleador> getEmpleadores() {
		return empleadores;
	}
	
	
	
	public ArrayList<ElemRE> getEleccionesEmpleadores() {
		return eleccionesEmpleadores;
	}

	public Map<String, ElemRE> getEleccionesEmpleados() {
		return eleccionesEmpleados;
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
		Empleador empleadorElegido;
		int i;
		for (Empleado empleadoAct : this.empleados) {	// Carga hashmap con las elecciones de los empleados
			i = 0;
			while (i<this.empleadores.size() && !this.empleadores.get(i).getTickets().contains(empleadoAct.getTicketElegido()))
				i++;
			empleadorElegido = this.empleadores.get(i);
			ElemRE elemAct = new ElemRE(empleadoAct,empleadorElegido,empleadorElegido.getTickets().indexOf(empleadoAct.getTicketElegido()));
			this.eleccionesEmpleados.put(elemAct.getUsuarioActual().getUsername(), elemAct);
		}
		for (Empleador empleadorAct : this.empleadores) {	// Carga arraylist con las elecciones de los empleadores
			for (i=0;i<empleadorAct.getEmpleadosElegidos().size();i++) {
				ElemRE elemAct = new ElemRE(empleadorAct,empleadorAct.getEmpleadosElegidos().get(i),empleadorAct.getTickets().indexOf(empleadorAct.getTicketsAsignados().get(i)));	// arraylists paralelos
				this.eleccionesEmpleadores.add(elemAct);
			}
		}
		boolean fueElegido = false;
		for (Empleador empleadorAct : this.empleadores) {	// Se fija si algun empleador no fue elegido para penalizar su puntaje
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
			while (i < empleadorAct.getTickets().size() && empleadorAct.getTickets().get(i).getEstado().equalsIgnoreCase("finalizado") && ticketEmpleado.equals(empleadorAct.getTickets().get(i))) {
				i++;
			}
			ticketEmpleado = empleadorAct.getTickets().get(i);
			if (this.matcheoContratacion(ticketEmpleado,empleadoAct,empleadorAct,eleccionEmpleado)) {
				this.contratos.add(new Contrato(empleadoAct,empleadorAct));
				ticketEmpleado.setEstado("finalizado");
				empleadorAct.incrPuntajeApp(5);			// Redefinimos que en vez de 50, como 1 ticket equivale a 1 empleado contratado, se sume solo 5
				empleadoAct.getTicket().setEstado("finalizado");
				empleadoAct.getTicket().setResultado("exito");
				empleadoAct.incrPuntajeApp(10);
			}
		}
		this.iniciaCalculoComisiones();
	}
	
	private boolean matcheoContratacion(TicketEmpleado ticketEmpleado,Empleado empleadoAct,Empleador empleadorAct,ElemRE eleccionEmpleado) {
		return ticketEmpleado.getEstado().equalsIgnoreCase("activo") && empleadoAct.getTicket().getEstado().equalsIgnoreCase("activo") && empleadorAct == eleccionEmpleado.getUsuarioElegido() && ticketEmpleado.equals(empleadorAct.getTickets().get(eleccionEmpleado.getIndiceTicket()));
	}
	
	private void iniciaCalculoComisiones() {
		
	}
	
}
