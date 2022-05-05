package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * @author Grupo 7
 * <br>
 * Clase que representa el ticket de un usuario, es una clase abstracta debido  que la instanciación de un
 * ticket dependerá de su tipo,  ya sea de empleado o de empleador.
 *
 */

public abstract class Ticket {
    /**
     * @aggregation composite
     */
    private Formulario formulario;
	private Calendar fecha;
	private String estado;

    /**
     * @aggregation composite
     */
    private Peso peso;
	
    /**
    *Constructor con dos parámetros para incluir en el ticket un formulario y los pesos asignados para cada aspecto del formulario<br>
    *@param f : parámetro de tipo Formulario (objeto) que contiene las características del mismo 
    *@param peso : parámetro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
    *
    */    
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
		return "fecha: " + fecha.getTime() + ", estado: " + estado;
	}

	/**
	*Sobreescribe el método equals, para comparar cada atributo específico de los objetos Ticket<br>
	*@return boolean que indica si los objetos de tipo Ticket poseen mismo atributos(true) o no(false).
	*/

	@Override
	public boolean equals(Object obj) {
		Ticket t = (Ticket) obj;
		return t.getFormulario().equals(this.formulario) && t.getPeso().equals(this.peso);
	}
	
	
	
}
