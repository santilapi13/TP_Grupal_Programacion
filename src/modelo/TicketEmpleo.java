package modelo;

/**
 * @author Grupo 7
 * <br>
 * Clase que representa el ticket de un empleador, se instancia cuando un empleador oferta al sistema puestos de trabajo. 
 * Almacenará un formulario con las instrucciones de qué tipo de puesto de trabajo busca, y para cada aspecto un peso asignado 
 * el cual indicará puntualmente qué rasgo anhela mas.
 *
 */



public class TicketEmpleo extends Ticket {
	
	private String resultado;

	/**
	*Constructor con dos parámetros para incluir en el ticket de empleo un formulario y los pesos asignados para cada 
	*aspecto del formulario, el resultado del ticket se setea inicialmente como “pendiente<br>
	*@param f : parámetro de tipo Formulario (objeto) que contiene las características del mismo 
	*@param peso : parámetro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
	*
	*/

	
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
