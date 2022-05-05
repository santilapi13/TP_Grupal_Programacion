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
 * @author grupo7
 * Clase con todas las funcionalidades del sistema
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
	
	/**
	 * Metodo propio del patron Singleton que sirve para asegurarse que siempre exista una unica instancia de la clase Agencia.<br>
	 * @return la unica Agencia que fue instanciada y en caso de nunca haber sido instanciada, crea una nueva Agencia.
	 */
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
	 * Crea una nueva Lista de Asignacion, ordenada segun puntaje de coincidencia, de los empleadores que mas coincidan con las preferencias del empleado pasado por parametro.<br>
	 * <b>Pre</b>: empAct debe ser distinto de null y debe haber emitido un ticket (lo tiene como atributo), cuyo estado debe ser activo.<br>
	 * <b>Post</b>: la Lista de Asignacion se carga con las caracteristicas de los tickets de los empleadores que pueden interesarle al empleado, ordenados por puntaje.<br>
	 * @param empAct : Empleado al cual se le generara su lista de asignacion a partir de todos los empleadores disponibles.<br>
	 * @return Lista de Asignacion correspondiente al empleado pasado por parametro.
	 */
	private ListaAsignacion creaLAEmpleado(Empleado empAct) {	
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
	
	/**
	 * Crea una nueva Lista de Asignacion, ordenada segun puntaje de coincidencia, de los empleados que mas coincidan con las preferencias del empleador pasado por parametro.<br>
	 * <b>Pre</b>: emprAct debe ser distinto de null y debe haber emitido al menos un ticket cuyo estado es activo.<br>
	 * <b>Post</b>: la Lista de Asignacion se carga con las caracteristicas de los tickets de los empleados que pueden interesarle al empleador, ordenados por puntaje.<br>
	 * @param emprAct : Empleador al cual se le generara su lista de asignacion a partir de todos los empleadores disponibles.<br>
	 * @return Lista de Asignacion correspondiente al empleador pasado por parametro.
	 */
	private ListaAsignacion creaLAEmpleadores(Empleador emprAct) {	
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
	
	/**
	 * Primero, llama a cargar una lista de empelados y otra de empleadores validadas. Luego, recorre estas nuevas listas para crear una nueva 
	 * Lista de Asignacion para cada Usuario con estas características.<br>
	 * <b>Pre</b>: Debe existir al menos un empleado y un empleador para iniciar la ronda.<br>
	 * <b>Post</b>: Cada usuario tendra su lista de asignacion correspondiente su/sus tickets.<br>
	 */
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
	}
	
	
	/**
	 * Carga a partir de las listas de empleados y empleadores, una nueva lista con los empleados que hayan emitido un ticket que siga activo
	 * y otra con los empleadores que hayan emitido al menos un ticket cuyo estado sea activo. De esta forma se evitan hacer validaciones en
	 * metodos posteriores.<br>
	 * <b>Post</b>: Se borraran los tickets inactivos de los empleadores y se cargaran las listas EmpleadosDisp y EmpleadoresDisp con aquellos que tengan al menos un ticket activo.<br>
	 */
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
	
	/**
	 * Actualiza los puntajes de la aplicacion de cada usuario partcipe de la ronda de encuentros. Se comprueban las siguientes caracteristicas:<br>
	 * - Por cada Lista de Asignacion de un empleado en la que un empleador se ubique primero, se le suma 10 puntos.<br>
	 * - Por cada Lista de Asignacion de un empleador en la que un empleado se ubique ultimo, se le suma 5 puntos a este empleado.<br>
	 * - Por cada Lista de Asignacion de un empleador en la que un empleado se ubique ultimo, se le resta 5 puntos a ese empleado. <br>
	 * - Si un empleador no fue elegido por ningun empleado, se le resta 20 puntos.<br>
	 * <b>Post</b>: Se modificara el puntaje de cada usuario en base a su posicion en las listas de asignacion de los demas.<br>
	 */
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
	
	/**
	 * La ronda de elecciones se hace luego de que, despues de un plazo de tiempo anunciado por la agencia, cada usuario hizo su eleccion.
	 * Los empleados que no realizaron su eleccion se retiran de la lista EmpleadosDisp.
	 * Despues, se cargan dos estructuras de datos. Un HashMap con las elecciones de los empleados, cuyos elementos tienen por clave el
	 * username del empleado y por valor un objeto ElemRE (Elemento de Ronda de Eleccion) que tiene al usuario que realizo la eleccion,
	 * al usuario elegido, y el indice del ticket del empleador (porque los empleadores tienen un ArrayList de tickets). Luego, se carga 
	 * un nuevo ArrayList con las elecciones de los empleadores, cuyos elementos son objetos ElemRE. Finalmente se actualizan los puntajes.<br>
	 * <b>Pre</b>: Los usuarios tienen en sus atributos las elecciones que hicieron (en caso de que un empleado no la haya realizado, tiene null)<br>
	 * <b>Post</b>: Se cargan correctamente las estructuras con las elecciones de cada usuario, preparando todo para la ronda de contratacion<br>
	 */
	public void iniciaRondaElecciones() {
		Empleador empleadorElegido;
		int i;
		this.depuraElecciones();
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
		this.actualizaPuntajes();
	}
	
	/**
	 * Borra de la lista empleadosDisp a todos los empleados que no hayan realizado una eleccion, ya que traeria problemas en la ronda de contratacion.
	 */
	public void depuraElecciones()  {
		for (Empleado empleadoAct : this.empleadosDisp) {
			if (empleadoAct.getTicketElegido() == null)
				this.empleadosDisp.remove(empleadoAct);
		}
	}
	
	/**
	 * Se controlan las coincidencias entre las elecciones de empleados y empleadores, de forma tal que si coinciden se realiza un contrato, sus
	 * correspondientes tickets se dan por finalizados y se actualizan los puntajes de la aplicacion de cada usuario en cada caso. Este control
	 * de coincidencias se realiza recorriendo la lista de elecciones de los empleadores y buscando el username del empleado elegido en el HashMap
	 * cuyas claves son los usernames de los empleados que realizaron la eleccion. Se verifica que el ticket del empleado no se haya finalizado y que
	 * si hay dos tickets para el mismo trabajo (igual formulario), tambien se tenga en cuenta.Luego se comprueba que se cumplan todas las condiciones 
	 * para que pueda instanciarse el contrato.<br>
	 *<b>Pre</b>: Cada empleado debe haber realizado su eleccion.<br>
	 * <b>Post</b>: Se realizaran los contratos empleado-empleador, actualizando estados de tickets y puntajes en cada caso. Las listas de empleadosDisp y empleadoresDisp seran vaciadas.<br>
	 */
	public void iniciaRondaContrataciones() {
		ElemRE eleccionEmpleado;
		Empleador empleadorAct;
		Empleado empleadoAct;
		TicketEmpleado ticketEmpleado;
		Contrato contrato;
		int i;
		for (ElemRE eleccionEmpleador : this.eleccionesEmpleadores) {
			eleccionEmpleado = this.eleccionesEmpleados.get(eleccionEmpleador.getUsuarioElegido().getUsername());
			if (eleccionEmpleado != null) {				// Si es null es porque ese empleado nunca hizo su eleccion
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
		}
		this.empleadosDisp.removeAll(empleadosDisp);
		this.empleadoresDisp.removeAll(empleadoresDisp);
	}
	
	/**
	 * Retornara true si los tickets emitidos por el empleador y el empleado estan activos (no finalizado), si el empleador elegido por el empleado
	 * es el empleador actual y si, ademas, ambos se eligieron entre si para el mismo trabajo (ticket).<br> 
	 * <b>Pre</b>: Todos los parametros deben ser distintos de null.<br>
	 * <b>Post</b>: La salida representara si hay o no coincidencia.<br>
	 * @param ticketEmpleado: Ticket de busqueda de un empleado emitido por un empleador.
	 * @param empleadoAct: Empleado elegido por el empleador y que se busca saber si este tambien lo eligio para el mismo trabajo.
	 * @param empleadorAct: Empleador que realizo la eleccion del empleadoAct.
	 * @param eleccionEmpleado: ElemRE con la informacion de la eleccion del empleado.
	 * @return booleano con la respuesta de si hay coincidencia y debe realizarse el contrato o no.
	 */
	private boolean matcheoContratacion(TicketEmpleado ticketEmpleado,Empleado empleadoAct,Empleador empleadorAct,ElemRE eleccionEmpleado) {
		return ticketEmpleado.getEstado().equalsIgnoreCase("activo") && empleadoAct.getTicket().getEstado().equalsIgnoreCase("activo") && empleadorAct == eleccionEmpleado.getUsuarioElegido() && ticketEmpleado.equals(empleadorAct.getTickets().get(eleccionEmpleado.getIndiceTicket()));
	}
	
	/**
	 * Delega el calculo de la tasa de comision correspondiente al Empleado y al Empleador del contrato a la clase Comisiones, para luego
	 * calcular la comision de cada uno como el producto entre la tasa y un sueldo fijo (que depende de los rangos de remuneracion acordados).
	 * @param contrato: Contrato entre un Empleado y un Empleador en base a los cuales se calculara la comision.
	 * @param i: Indice del ticket (en el arraylist) de empleado emitido por el empleador del contrato.
	 * @return La suma de las comisiones del empleado y del empleador.
	 */
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
