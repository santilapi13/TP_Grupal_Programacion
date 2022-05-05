package modelo;

/**
 * @author Grupo 7
 * <br>
 * Clase que representa el ticket de un empleado, se instancia cuando un empleado se postula para un puesto de trabajo.
 * Almacenará un formulario con las instrucciones de qué tipo de puesto de trabajo busca, y para cada aspecto 
 * un peso asignado el cual indicará puntualmente en qué característica de trabajo se destaca o prefiere.
 *
 */


public class TicketEmpleado extends Ticket {

	
	/**
	*Constructor con dos parámetros para incluir en el ticket de empleado un formulario y los pesos asignados para 
	*cada aspecto del formulario.<br>
	*@param f : parámetro de tipo Formulario (objeto) que contiene las características del mismo 
	*@param peso : parámetro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
	*
	*/

	public TicketEmpleado(Formulario formulario, Peso peso) {
		super(formulario,peso);
	}

}
