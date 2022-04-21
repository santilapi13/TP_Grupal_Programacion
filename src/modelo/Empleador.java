package modelo;

import java.util.ArrayList;

public class Empleador extends Usuario {

	private String nombre;
	private String tipoPersona;
	private String rubro;
	private ArrayList<TicketEmpleado> tickets = new ArrayList<TicketEmpleado>();
	private ArrayList<Formulario> formularios = new ArrayList<Formulario>();
	
	public Empleador(String username, String password, String nombre, String tipoPersona, String rubro) {
		super(username, password);
		this.nombre = nombre;
		this.tipoPersona = tipoPersona;
		this.rubro = rubro;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public String getRubro() {
		return rubro;
	}
	
	public void creaFormulario(String locacion, double v1, double v2, String cargaHr, String puestoLaboral, int rangoEtario, String expPrevia, String estudios) {
		Formulario f = new Formulario(locacion,v1,v2,cargaHr,puestoLaboral,rangoEtario,expPrevia,estudios);
		this.formularios.add(f);
	}
	
	public ArrayList<TicketEmpleado> getTickets() {
		return tickets;
	}

	@Override
	public void emiteFormulario(IAgencia agencia,Formulario f) {
		this.tickets.add(agencia.recibeFormEmpleador(f));
	}
	
	public void buscaEmpleados(IAgencia agencia, ArrayList<Formulario> formularios) {
		int i;
		for (i=0;i<formularios.size();i++)
			this.emiteFormulario(agencia,formularios.get(i));
		formularios.clear();
	}
	
}
