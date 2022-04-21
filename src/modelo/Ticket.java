package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Ticket {
	private Formulario formulario;
	private Calendar fecha;
	private String estado;
	
	public Ticket(Formulario formulario) {
		super();
		this.formulario = formulario;
		this.estado = "activo";
		this.fecha = GregorianCalendar.getInstance();
	}
	
	public Formulario getFormulario() {
		return formulario;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public String getEstado() {
		return estado;
	}
	
}
