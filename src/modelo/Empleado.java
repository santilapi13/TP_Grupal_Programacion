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

	@Override
	public void emiteFormulario(IAgencia agencia,Formulario f) {
		this.ticket = agencia.recibeFormEmpleado(f);
	}
	
}
