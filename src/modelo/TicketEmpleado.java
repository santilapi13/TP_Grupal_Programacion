package modelo;

import java.util.Calendar;

public class TicketEmpleado extends Ticket {
	
	private int cantEmpSolicitados;
	private int cantEmpObtenidos;

	public TicketEmpleado(Formulario formulario, Calendar fecha, String estado) {
		super(formulario, fecha, estado);
	}

	public int getCantEmpSolicitados() {
		return cantEmpSolicitados;
	}

	public int getCantEmpObtenidos() {
		return cantEmpObtenidos;
	}

}
