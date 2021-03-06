package modelo;

/**
 * @author Grupo 7
 * <br>
 * Clase que representa el ticket de un empleado, se instancia cuando un empleado se postula para un puesto de trabajo.
 * Almacenar? un formulario con las instrucciones de qu? tipo de puesto de trabajo busca, y para cada aspecto 
 * un peso asignado el cual indicar? puntualmente en qu? caracter?stica de trabajo se destaca o prefiere.
 *
 */


public class TicketEmpleado extends Ticket {

	
	/**
	*Constructor con dos par?metros para incluir en el ticket de empleado un formulario y los pesos asignados para 
	*cada aspecto del formulario.<br>
	*@param f : par?metro de tipo Formulario (objeto) que contiene las caracter?sticas del mismo 
	*@param peso : par?metro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
	*
	*/

	public TicketEmpleado(Formulario formulario, Peso peso) {
		super(formulario,peso);
	}

}
