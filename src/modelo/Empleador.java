package modelo;

import java.util.ArrayList;

public class Empleador extends Usuario {

	private String nombre;
	private String tipoPersona;
	private String rubro;
	private ArrayList<TicketEmpleado> tickets = new ArrayList<TicketEmpleado>();
	private ArrayList<Formulario> formularios = new ArrayList<Formulario>();
	private ArrayList<Peso> pesos = new ArrayList<Peso>();
	
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
	
	public void buscaEmpleados(IAgencia agencia, ArrayList<Formulario> formularios) {
		int i;
		for (i=0;i<formularios.size();i++)
			this.emiteFormulario(agencia,formularios.get(i),pesos.get(i));
		formularios.clear();
	}
	
}
