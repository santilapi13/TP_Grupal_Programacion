package modelo;

public class TicketEmpleo extends Ticket {
	
	private String resultado;

	public TicketEmpleo(Formulario formulario, Peso peso) {
		super(formulario,peso);
		this.resultado = "pendiente";
	}

	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getResultado() {
		return resultado;
	}

	
}
