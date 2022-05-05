package modelo;
/** 
* @author Grupo 7
 * <br> 
* Clase que sirve para representar a los elementos que se almacenarán en la lista de asignación de cada usuario. <br>
*Implementa la interfaz Comparable 
*/

public class ElemLA implements Comparable<ElemLA> {

    /**
     * @aggregation shared
     */
    private NoAdmin usuario;
	private double puntEntrevista;

    /**
     * @aggregation shared
     */
    private Ticket ticket;

    /**
    * Constructor de la clase ElemLA. <br>
    * <b>Pre</b>: usuario, puntaje y ticket deben ser distinto de null.<br>
    * <b>Post</b>: La instancia de ElemLA tendrá sus atributos cargados correctamente<br>
    * @param usuario: Objeto NoAdmin que fue asignado a la lista de asignación. <br>
    * @param puntaje: Almacena el puntaje de la entrevista entre el Empleador y el Empleado. <br>
    * @param ticket: Almacena el ticket con el que se comparó en la entrevista entre Empleador y Empleado. 
    */
	public ElemLA(NoAdmin usuario, double puntaje, Ticket ticket) {
		this.usuario = usuario;
		this.puntEntrevista = puntaje;
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public NoAdmin getUsuario() {
		return usuario;
	}

	public double getPuntEntrevista() {
		return puntEntrevista;
	}
	
	/**
	* Sobreescritura del metodo compareTo( ) definido en la interfaz Comparable. <br>
	*Establece que el criterio de comparación entre los elementos de la lista de asignación sea en base al puntaje de cada entrevista Empleado - Empleador. <br>
	* <b>Pre</b>: Tanto el parámetro “o” como el objeto que llame al método deben tener un puntaje de entrevista distinto de null. <br>
	* <b>Post</b>: ElemLA será comparable<br>
	* @param o: Elemento de la lista de asignación con el que se desea comparar al elemento que hace la llamada al método. <br>
	* @return Resultado de la comparación, -1 si el puntaje del elemento pasado como parámetro es menor y 1 si es mayor o igual.
	*/
	@Override
	public int compareTo(ElemLA o) {
		int resultado=0;
		if (this.puntEntrevista>o.getPuntEntrevista()) {
			resultado = -1;      
		}
		else if (this.puntEntrevista<o.getPuntEntrevista()) {    
			resultado = 1;      
		}
		else if (this.puntEntrevista == o.getPuntEntrevista())
			resultado = 1;
		return resultado;
	}

	@Override
	public String toString() {
		return "username: " + usuario.getUsername() + ", puntaje: " + puntEntrevista;
	}

	
}
