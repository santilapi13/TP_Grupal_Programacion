package modelo;

import java.util.Calendar;

public class TicketEmpleo extends Ticket {
	
	private String resultado;

	public TicketEmpleo(Formulario formulario, Calendar fecha, String estado) {
		super(formulario, fecha, estado);
	}

	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getResultado() {
		return resultado;
	}

	
}
