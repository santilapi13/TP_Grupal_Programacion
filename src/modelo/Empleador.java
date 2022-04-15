package modelo;

import java.util.ArrayList;

public class Empleador extends Usuario {

	private String nombre;
	private String tipoPersona;
	private String rubro;
	private int cantEmpSolicitados;
	private ArrayList<TicketEmpleado> tickets = new ArrayList<TicketEmpleado>();
	
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
	
	// PREGUNTAR POR ESTA CHANCHADA
	public Formulario creaFormulario(String locacion, double v1, double v2, String cargaHr, String puestoLaboral, int rangoEtario, String expPrevia, String estudios,int cantEmpSolicitados) {
		this.cantEmpSolicitados = cantEmpSolicitados;
		Formulario f = new Formulario(locacion,v1,v2,cargaHr,puestoLaboral,rangoEtario,expPrevia,estudios);
		return f;
	}
	
	@Override
	public void emiteFormulario(IAgencia agencia,Formulario f) {
		this.tickets.add(agencia.recibeFormEmpleador(f,this.cantEmpSolicitados));
	}
	
}
