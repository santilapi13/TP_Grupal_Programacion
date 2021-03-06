package modelo;

/**
 * @author Grupo 7
 * <br>
 * Clase que representa el ticket de un empleador, se instancia cuando un empleador oferta al sistema puestos de trabajo. 
 * Almacenar? un formulario con las instrucciones de qu? tipo de puesto de trabajo busca, y para cada aspecto un peso asignado 
 * el cual indicar? puntualmente qu? rasgo anhela mas.
 *
 */



public class TicketEmpleo extends Ticket {
	
	private String resultado;

	/**
	*Constructor con dos par?metros para incluir en el ticket de empleo un formulario y los pesos asignados para cada 
	*aspecto del formulario, el resultado del ticket se setea inicialmente como ?pendiente<br>
	*@param f : par?metro de tipo Formulario (objeto) que contiene las caracter?sticas del mismo 
	*@param peso : par?metro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
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
