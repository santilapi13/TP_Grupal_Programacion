package modelo;

import java.util.ArrayList;

public class Empleador extends NoAdmin {

	private String nombre;
	private int tipoPersona;		// 0: Fisica ; 1: Juridica
	private int rubro;			// 0: Salud ; 1: Comercio Local ; 2: Comercio Internacional
	private ArrayList<TicketEmpleado> tickets = new ArrayList<TicketEmpleado>();
	private ArrayList<Formulario> formularios = new ArrayList<Formulario>();
	private ArrayList<Peso> pesos = new ArrayList<Peso>();
	private ArrayList<Empleado> empleadosElegidos = new ArrayList<Empleado>();
	private ArrayList<TicketEmpleado> ticketsAsignados = new ArrayList<TicketEmpleado>();
	
	public Empleador(String username, String password, String nombre, int tipoPersona, int rubro) {
		super(username, password);
		this.nombre = nombre;
		this.tipoPersona = tipoPersona;
		this.rubro = rubro;
	}
	
	public void eligeEmpleado(Empleado empleado,TicketEmpleado ticket) {
		this.empleadosElegidos.add(empleado);
		this.ticketsAsignados.add(ticket);
	}
	
	public ArrayList<TicketEmpleado> getTicketsAsignados() {
		return ticketsAsignados;
	}
	public ArrayList<Empleado> getEmpleadosElegidos() {
		return empleadosElegidos;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTipoPersona() {
		return tipoPersona;
	}

	public int getRubro() {
		return rubro;
	}
	
	public void creaFormulario(int locacion, int remuneracion, int cargaHr, int puestoLaboral, int rangoEtario, int expPrevia, int estudios,Peso peso) {
		Formulario f = new Formulario(locacion,remuneracion,cargaHr,puestoLaboral,rangoEtario,expPrevia,estudios);
		this.formularios.add(f);
		this.pesos.add(peso);
	}
	
	public ArrayList<TicketEmpleado> getTickets() {
		return tickets;
	}

	@Override
	public void emiteFormulario(IAgencia agencia,Formulario f,Peso peso) {
		this.tickets.add(agencia.recibeFormEmpleador(f,peso));
	}
	
	public void buscaEmpleados(IAgencia agencia) {
		int i;
		for (i=0;i<this.formularios.size();i++)
			this.emiteFormulario(agencia,this.formularios.get(i),pesos.get(i));
		this.formularios.clear();
	}
	
	public void cancelaTicket(int i) {
		this.tickets.get(i).setEstado("cancelado");
	}
	
}
