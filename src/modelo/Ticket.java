package modelo;

import java.util.Calendar;

public abstract class Ticket {
	private Formulario formulario;
	private Calendar fecha;
	private String estado;
	
	public Ticket(Formulario formulario, Calendar fecha, String estado) {
		super();
		this.formulario = formulario;
		this.fecha = fecha;
		this.estado = estado;
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
