package datos;

import modelo.Ticket;
/**
 * @author Grupo 7
 * <br>
 *Clase abstracta de la cual se extienden todos los aspectos del formulario. En ella se aplica el patr�n template, 
 *en el cual se emplea un algoritmo com�n de pasos con una implementaci�n que depender� de la clase extendida que se utilice.
 *
 */


public abstract class Aspecto {
	
	public Aspecto() {
	}

	/**
	*M�todo que aplica el patr�n TEMPLATE, en el cual se aplican pasos comunes para cada aspecto, y en el que cada 
	*uno de ellos que se extiende de la clase principal implementar� detalles espec�ficos.
	*<b>Pre: </b> La perspectiva debe ser �empleador� o �empleado� (ser� empleado por defecto)<br>
	*<b>Post: </b> El puntaje obtenido al comparar un ticket de empleador y uno de empleado, visto desde la perspectiva del usuario propietario de t1.
	*@param t1 : Par�metro de tipo Ticket (objeto), ser� uno de los dos tickets a comparar, para retornar el puntaje
	*@param t2 : Par�metro de tipo Ticket (objeto), ser� uno de los dos tickets a comparar para retornar el puntaje.
	*@param perspectiva : Par�metro de tipo String, indica si se calculara el aspecto de un empleado o empleador.
	*@return valor doble con el puntaje de coincidencias de los tickets de empleado y empleador en el aspecto espec�fico del llamado.
	*/

	public double calculaPuntAspecto(Ticket t1,Ticket t2,String perspectiva) {	// Template
		double peso,puntaje = 0;
		peso = this.obtienePeso(t1);
		puntaje = calculaPuntaje(t1,t2,peso,perspectiva);
		return puntaje;
	}
	
	protected abstract double obtienePeso(Ticket t1);
	/**M�todo abstracto que es llamado desde calculaPuntAspecto al aplicar el patr�n TEMPLATE, es implementado 
	*dentro de cada aspecto debido a que poseen distintas matrices de decisi�n. En �l se comparan las filas y columnas 
	*indicadas dentro del formulario que pertenece a ambos tickets, y en base a su posici�n retornara un puntaje que 
	*adem�s ser� modificado por el peso que el usuario le ha dado a ese aspecto y en base a la perspectiva del 
	*ticket (Empleado o Empleador). 
	*@param t1 : Par�metro de tipo Ticket (objeto), ser� uno de los dos tickets a comparar, para retornar el puntaje
	*@param t2 : Par�metro de tipo Ticket (objeto), ser� uno de los dos tickets a comparar para retornar el puntaje.
	*@param peso. Par�metro de tipo Peso (objeto) que contiene los pesos de cada aspecto del formulario.
	*@param perspectiva : Par�metro de tipo String, indica si se calculara el aspecto de un empleado o empleador.
	*@return valor doble con el puntaje de coincidencias de los tickets de empleado y empleador, en el aspecto espec�fico que realice el llamado.
	*/

	protected abstract double calculaPuntaje(Ticket t1,Ticket t2,double peso,String perspectiva);
	
}
