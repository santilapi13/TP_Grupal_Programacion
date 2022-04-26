package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Ticket {
	private Formulario formulario;
	private Calendar fecha;
	private String estado;
	private Peso peso;
	
	public Ticket(Formulario formulario, Peso peso) {
		super();
		this.formulario = formulario;
		this.estado = "activo";
		this.fecha = GregorianCalendar.getInstance();
		this.peso = peso;
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

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Peso getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "fecha: " + fecha.getTime() + ", estado: " + estado + "\npeso: " + peso;
	}

	@Override
	public boolean equals(Object obj) {
		Ticket t = (Ticket) obj;
		return t.getFormulario().equals(this.formulario) && t.getPeso().equals(this.peso);
	}
	
	
	
}
