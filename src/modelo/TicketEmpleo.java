package modelo;

public class TicketEmpleo extends Ticket {
	
	private String resultado;

	public TicketEmpleo(Formulario formulario) {
		super(formulario);
		this.resultado = "pendiente";
	}

	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getResultado() {
		return resultado;
	}

	
}
