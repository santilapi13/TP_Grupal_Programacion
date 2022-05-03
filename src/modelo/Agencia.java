package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import datos.Comisiones;
import datos.CargaHoraria;
import datos.EstudiosCursados;
import datos.ExpPrevia;
import datos.Locacion;
import datos.RangoEtario;
import datos.Remuneracion;
import datos.TipoPuesto;
import excepciones.UsuarioRepetidoException;

/**
 * @author USUARIO
 *
 */
/**
 * @author USUARIO
 *
 */
public class Agencia implements IAgencia {
	private double fondos;					// fondos representa la suma de las comisiones que deben cada usuario
	private static Agencia instance = null;

    /**
     * @aggregation shared
     */
    private ArrayList<Empleado> empleadosDisp = new ArrayList<Empleado>();

    /**
     * @aggregation shared
     */
    private ArrayList<Empleador> empleadoresDisp = new ArrayList<Empleador>();

    /**
     * @aggregation composite
     */
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    /**
     * @aggregation composite
     */
    private ArrayList<Empleador> empleadores = new ArrayList<Empleador>();

    /**
     * @aggregation composite
     */
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

	public double getFondos() {
		return fondos;
	}
	public ArrayList<Empleado> getEmpleadosDisp() {
		return empleadosDisp;
	}
	public ArrayList<Empleador> getEmpleadoresDisp() {
		return empleadoresDisp;
	}
	public ArrayList<ElemRE> getEleccionesEmpleadores() {
		return eleccionesEmpleadores;
	}
	public Map<String, ElemRE> getEleccionesEmpleados() {
		return eleccionesEmpleados;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public ArrayList<Empleador> getEmpleadores() {
		return empleadores;
	}

	/**
	 * Agrega el empleado ya instanciado a la lista de empleados de la agencia. <br>
	 * <b>Pre</b>: El parametro e debe ser distinto de null. La lista de empleados debe estar inicializada. <br>
	 * <b>Post</b>: El empleado estara en la lista empleados. <br>
	 * @param e : Empleado que se desea agregar a la lista.
	 * @throws UsuarioRepetidoException : Evita que el empleado se agregue a la lista si su username ya esta siendo usado por otro empleado.
	 */
	public void addEmpleado(Empleado e) throws UsuarioRepetidoException {
		for (Empleado empAct : this.empleados) {					// Estamos admitiendo que haya un empleado y empleador con igual username
			if (empAct.getUsername().equals(e.getUsername()))
				throw new UsuarioRepetidoException("El nombre del nuevo empleado ya existe");
		}
		this.empleados.add(e);
	}
	
	
	/**
	 * Agrega el empleador ya instanciado a la lista de empleadores de la agencia. <br>
	 * <b>Pre</b>: El parametro e debe ser distinto de null. La lista de empleadores debe estar inicializada. <br>
	 * <b>Post</b>: El empleador estara en la lista empleadores. <br>
	 * @param e : Empleador que se desea agregar a la lista.
	 * @throws UsuarioRepetidoException : Evita que el empleador se agregue a la lista si su username ya esta siendo usado por otro empleador.
	 */
	public void addEmpleador(Empleador e) throws UsuarioRepetidoException {	
		for (Empleador empAct : this.empleadores)					// Estamos admitiendo que haya un empleado y empleador con igual username 
			if (empAct.getUsername().equals(e.getUsername()))
				throw new UsuarioRepetidoException("El nombre del nuevo empleador ya existe");
		this.empleadores.add(e);
	}
	
	/**
	 * Genera el ticket de empleo a partir del formulario pasado por un empleado y sus pesos mediante el patron de diseño Double Dispatch.<br>
	 * <b>Pre</b>: Formulario y peso deben ser distintos de null.<br>
	 * <b>Post</b>: El empleado que lo emitio debe recibir correctamente el ticket retornado por este metodo.<br>
	 * @param f : Formulario con la preferencia del empleado sobre varios aspectos que afectara al calculo de las coincidencias.<br>
	 * @param peso : Tiene el peso correspondiente a cada uno de los aspectos, segun la preferencia del empleado.<br> 
	 * @return Ticket de empleo con la informacion correspondiente al formulario f y al peso pasados por parametro.
	 */
	public TicketEmpleo recibeFormEmpleado(Formulario f,Peso peso) {	// double dispatch
		TicketEmpleo ticket = new TicketEmpleo(f,peso);
		return ticket;
	}
	
	/**
	 * Genera el ticket de empleado a partir del formulario pasado por un empleador y sus pesos mediante el patron de diseño Double Dispatch.<br>
	 * <b>Pre</b>: Formulario y peso deben ser distintos de null.<br>
	 * <b>Post</b>: El empleador que lo emitio debe recibir correctamente el ticket retornado por este metodo.<br>
	 * @param f : Formulario con la preferencia del empleador sobre varios aspectos que afectara al calculo de las coincidencias.<br>
	 * @param peso : Tiene el peso correspondiente a cada uno de los aspectos, segun la preferencia del empleador.<br> 
	 * @return Ticket de empleado con la informacion correspondiente al formulario f y al peso pasados por parametro.
	 */
	public TicketEmpleado recibeFormEmpleador(Formulario f,Peso peso) {		
		TicketEmpleado ticket = new TicketEmpleado(f,peso);
		return ticket;
	}
	
	/**
	 * Calcula el puntaje de las coincidencias entre los tickets de un empleador y un empleado como la suma del puntaje de coincidencia de cada aspecto. Para ello se utilizaron instancias de clases a las que se le aplico el Patron Singleton.<br>
	 * <b>Pre</b>: t1 y t2 deben ser distintos de null. t1 debe ser el ticket desde el cual se "toma perspectiva". Perspectiva contendra las palabras "Empleado" o "Empleador".<br>
	 * <b>Post</b>: puntaje representara la suma de los puntajes de coincidencia de cada aspecto considerado desde la perspectiva del propietario de t1.<br>
	 * @param t1 : Ticket del usuario cuyo puntaje de coincidencia va a ser calculado en base a sus pesos.<br>
	 * @param t2 : Ticket del usuario con el cual se busca coincidir t1.<br>
	 * @param perspectiva : String con la palabra Empleado o Empleador segun corresponda, que sirve para que los metodos que lo invocan puedan distinguir el tipo de usuario del propietario de t1.<br>
	 * @return valor double con el puntaje de coincidencia de la entrevista entre el empleado y el empleador.
	 */
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
	
	/**
	 * Crea una nueva Lista de Asignacion, ordenada segun puntaje de coincidencia, de los empleadores que mas coincidan con las preferencias del empleador pasado por parametro.<br>
	 * <b>Pre</b>: empAct debe ser distinto de null y debe haber emitido un ticket (lo tiene como atributo).
	 * <b>
	 * @param empAct : Empleado al cual se le generara su lista de asignacion a partir de todos los empleadores disponibles.<br>
	 * @return Lista de Asignacion correspondiente al empleado pasado por parametro.
	 */
	private ListaAsignacion creaLAEmpleado(Empleado empAct) {	// Lista de asignacion de empleadores compatibles con el empleado
		ListaAsignacion lista = new ListaAsignacion();
		double puntajeAct;
		for (Empleador empleadorAct : this.empleadoresDisp) {
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
		for (Empleado empleadoAct : this.empleadosDisp) {
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
		this.cargaDisponibles();
		for (i=0;i<this.empleadosDisp.size();i++) {
			empAct = empleadosDisp.get(i);
			empAct.setListaAsignacion(this.creaLAEmpleado(empAct));
		}
		for (i=0;i<this.empleadoresDisp.size();i++) {
			emprAct = empleadoresDisp.get(i);
			emprAct.setListaAsignacion(this.creaLAEmpleadores(emprAct));
		}
		this.actualizaPuntajes();
	}
	
	private void cargaDisponibles() {
		for (Empleado empleadoAct : this.empleados) {
			if (empleadoAct.getTicket() != null && empleadoAct.getTicket().getEstado().equalsIgnoreCase("Activo"))
				this.empleadosDisp.add(empleadoAct);
		}
		for (Empleador empleadorAct : this.empleadores) {
			if (!empleadorAct.getTickets().isEmpty()) {			// borra todos los tickets no activos 
				for (TicketEmpleado ticketAct : empleadorAct.getTickets()) {
					if (!ticketAct.getEstado().equalsIgnoreCase("Activo"))
						empleadorAct.getTickets().remove(ticketAct);
				}
				if (!empleadorAct.getTickets().isEmpty())
					this.empleadoresDisp.add(empleadorAct);
			}
		}
	}

	private void actualizaPuntajes() {
		int i;
		Empleado empAct;
		Empleador emprAct;
		for (i=0;i<this.empleadosDisp.size();i++) {
			empAct = empleadosDisp.get(i);
			try {
				empAct.getListaAsignacion().getUsuarios().first().getUsuario().incrPuntajeApp(10);
			} catch (NoSuchElementException e) {
				System.out.println("No se puede actualizar puntaje porque no hay empleadores en la lista de asignacion de " + empAct.getUsername());
			}
		}
		for (i=0;i<this.empleadoresDisp.size();i++) {
			emprAct = empleadoresDisp.get(i);
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
		for (Empleado empleadoAct : this.empleadosDisp) {	// Carga hashmap con las elecciones de los empleados
			i = 0;
			while (i<this.empleadoresDisp.size() && !this.empleadoresDisp.get(i).getTickets().contains(empleadoAct.getTicketElegido()))
				i++;
			empleadorElegido = this.empleadoresDisp.get(i);
			ElemRE elemAct = new ElemRE(empleadoAct,empleadorElegido,empleadorElegido.getTickets().indexOf(empleadoAct.getTicketElegido()));
			this.eleccionesEmpleados.put(elemAct.getUsuarioActual().getUsername(), elemAct);
		}
		for (Empleador empleadorAct : this.empleadoresDisp) {	// Carga arraylist con las elecciones de los empleadores
			for (i=0;i<empleadorAct.getEmpleadosElegidos().size();i++) {
				ElemRE elemAct = new ElemRE(empleadorAct,empleadorAct.getEmpleadosElegidos().get(i),empleadorAct.getTickets().indexOf(empleadorAct.getTicketsAsignados().get(i)));	// arraylists paralelos
				this.eleccionesEmpleadores.add(elemAct);
			}
		}
		boolean fueElegido = false;
		for (Empleador empleadorAct : this.empleadoresDisp) {	// Se fija si algun empleador no fue elegido para penalizar su puntaje
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
		Contrato contrato;
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
				contrato = new Contrato(empleadoAct,empleadorAct);
				this.contratos.add(contrato);
				ticketEmpleado.setEstado("finalizado");
				empleadorAct.incrPuntajeApp(5);			// Redefinimos que en vez de 50, como 1 ticket equivale a 1 empleado contratado, se sume solo 5
				empleadoAct.getTicket().setEstado("finalizado");
				empleadoAct.getTicket().setResultado("exito");
				empleadoAct.incrPuntajeApp(10);
				this.fondos += this.calculaComision(contrato,eleccionEmpleado.getIndiceTicket());
			}
		}
		this.empleadosDisp.removeAll(empleadosDisp);
		this.empleadoresDisp.removeAll(empleadoresDisp);
	}
	
	private boolean matcheoContratacion(TicketEmpleado ticketEmpleado,Empleado empleadoAct,Empleador empleadorAct,ElemRE eleccionEmpleado) {
		return ticketEmpleado.getEstado().equalsIgnoreCase("activo") && empleadoAct.getTicket().getEstado().equalsIgnoreCase("activo") && empleadorAct == eleccionEmpleado.getUsuarioElegido() && ticketEmpleado.equals(empleadorAct.getTickets().get(eleccionEmpleado.getIndiceTicket()));
	}
	
	private double calculaComision(Contrato contrato,int i) {		// El porcentaje se calcula sobre valores fijos en base a los rangos de remuneracion establecidos en su formulario
		Empleador empleador = contrato.getEmpleador();
		Empleado empleado = contrato.getEmpleado();
		double tasaEmpleador = Comisiones.calculaTasa(empleador.getTipoPersona(),empleador.getRubro(),empleador.getPuntajeApp());
		double tasaEmpleado = Comisiones.calculaTasa(empleado.getTicket().getFormulario().getPuestoLaboral(),empleado.getPuntajeApp());
		double sueldo = 0;
		switch (empleador.getTickets().get(i).getFormulario().getRemuneracion()) {
			case 0: sueldo = 25000;		// promedio entre 0 y 50.000	
		break;
			case 1: sueldo = 75000;		// promedio entre 50.000 y 100.000
		break;
			case 2: sueldo = 125000; 	// valor fijo correspondiente a "mas de 100.000"
		break;
		}
		double comisionEmpleado = sueldo*tasaEmpleado;
		double comisionEmpleador = sueldo*tasaEmpleador;
		empleado.agregaComision(comisionEmpleado);
		empleador.agregaComision(comisionEmpleador);
		return comisionEmpleado + comisionEmpleador;
	}
	
}
