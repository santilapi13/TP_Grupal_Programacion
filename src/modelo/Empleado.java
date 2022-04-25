package modelo;

public class Empleado extends Usuario {
	private String nya;
	private String telefono;
	private int edad;
	private TicketEmpleo ticket;
	
	public Empleado(String username,String password,String nya, String telefono, int edad) {
		super(username,password);
		this.nya = nya;
		this.telefono = telefono;
		this.edad = edad;
	}

	public String getNya() {
		return nya;
	}
	public String getTelefono() {
		return telefono;
	}
	public int getEdad() {
		return edad;
	}
	public TicketEmpleo getTicket() {
		return ticket;
	}

	public Formulario creaFormulario(int locacion,int remuneracion,int cargaHr,int puestoLaboral,int expPrevia,int estudios) {
		int rangoEtario;
		if (this.edad < 40)
			rangoEtario = 0;
		else if (this.edad < 50)
			rangoEtario = 1;
		else
			rangoEtario = 2;
		return new Formulario(locacion,remuneracion,cargaHr,puestoLaboral,rangoEtario,expPrevia,estudios);
	}
	
	@Override
	public void emiteFormulario(IAgencia agencia,Formulario f,Peso peso) {
		this.ticket = agencia.recibeFormEmpleado(f,peso);
	}
	
}
