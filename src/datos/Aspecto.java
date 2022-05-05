package datos;

import modelo.Ticket;
/**
 * @author Grupo 7
 * <br>
 *Clase abstracta de la cual se extienden todos los aspectos del formulario. En ella se aplica el patrón template, 
 *en el cual se emplea un algoritmo común de pasos con una implementación que dependerá de la clase extendida que se utilice.
 *
 */


public abstract class Aspecto {
	
	public Aspecto() {
	}

	/**
	*Método que aplica el patrón TEMPLATE, en el cual se aplican pasos comunes para cada aspecto, y en el que cada 
	*uno de ellos que se extiende de la clase principal implementará detalles específicos.
	*<b>Pre: </b> La perspectiva debe ser “empleador” o “empleado” (será empleado por defecto)<br>
	*<b>Post: </b> El puntaje obtenido al comparar un ticket de empleador y uno de empleado, visto desde la perspectiva del usuario propietario de t1.
	*@param t1 : Parámetro de tipo Ticket (objeto), será uno de los dos tickets a comparar, para retornar el puntaje
	*@param t2 : Parámetro de tipo Ticket (objeto), será uno de los dos tickets a comparar para retornar el puntaje.
	*@param perspectiva : Parámetro de tipo String, indica si se calculara el aspecto de un empleado o empleador.
	*@return valor doble con el puntaje de coincidencias de los tickets de empleado y empleador en el aspecto específico del llamado.
	*/

	public double calculaPuntAspecto(Ticket t1,Ticket t2,String perspectiva) {	// Template
		double peso,puntaje = 0;
		peso = this.obtienePeso(t1);
		puntaje = calculaPuntaje(t1,t2,peso,perspectiva);
		return puntaje;
	}
	
	protected abstract double obtienePeso(Ticket t1);
	/**Método abstracto que es llamado desde calculaPuntAspecto al aplicar el patrón TEMPLATE, es implementado 
	*dentro de cada aspecto debido a que poseen distintas matrices de decisión. En él se comparan las filas y columnas 
	*indicadas dentro del formulario que pertenece a ambos tickets, y en base a su posición retornara un puntaje que 
	*además será modificado por el peso que el usuario le ha dado a ese aspecto y en base a la perspectiva del 
	*ticket (Empleado o Empleador). 
	*@param t1 : Parámetro de tipo Ticket (objeto), será uno de los dos tickets a comparar, para retornar el puntaje
	*@param t2 : Parámetro de tipo Ticket (objeto), será uno de los dos tickets a comparar para retornar el puntaje.
	*@param peso. Parámetro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
	*@param perspectiva : Parámetro de tipo String, indica si se calculara el aspecto de un empleado o empleador.
	*@return valor doble con el puntaje de coincidencias de los tickets de empleado y empleador, en el aspecto específico que realice el llamado.
	*/

	protected abstract double calculaPuntaje(Ticket t1,Ticket t2,double peso,String perspectiva);
	
}
