package modelo;

/** 
* @author Grupo 7
*/
public class Empleado extends NoAdmin {
	private String nya;
	private String telefono;
	private int edad;

    /**
     * @aggregation composite
     */
    private TicketEmpleo ticket;

    /**
     * @aggregation shared
     */
    private TicketEmpleado ticketElegido;
	
    
    /**
    * Constructor de la clase Empleado. <br>
    * <b>Pre</b>: username y password deben ser distintos de null, edad debe ser mayor a 18. <br>
    * <b>Post</b>: La instancia de Empleado tendrá sus atributos cargados correctamente<br>
    * @param username:  String que representa el nombre de usuario que utilizará el Empleado. <br>
    * @param password: String que representa la contraseña que utilizará el Empleado. <br>
    * @param nya:  String que representa el nombre y apellido del Empleado. <br>
    * @param telefono: String que representa el telefono del Empleado. <br>
    * @param edad: Edad del Empleado. 
    */
	public Empleado(String username,String password,String nya, String telefono, int edad) {
		super(username,password);
		this.nya = nya;
		this.telefono = telefono;
		this.edad = edad;
		this.ticketElegido = null;
	}
	
	
	public Ticket getTicketElegido() {
		return ticketElegido;
	}
	public void setTicketElegido(TicketEmpleado eleccion) {
		this.ticketElegido = eleccion;
	}
	public String getNya() {
		return nya;
	}
	public String getTelefono() {
		return telefono;
	}
	public int getEdad() {
		return edad;
	}
	public TicketEmpleo getTicket() {
		return ticket;
	}

	/**
	* Crea un formulario de búsqueda en base a las condiciones pasadas por parámetro. <br>
	* <b>Pre</b>: Todos los parámetros deben tener unicamente los valores 0, 1 o 2. <br>
	* @param username:  String que representa el nombre de usuario que utilizará el Empleado. <br>
	* @param password: String que representa la contraseña que utilizará el Empleado. <br>
	* @param nya:  String que representa el nombre y apellido del Empleado. <br>
	* @param telefono: String que representa el telefono del Empleado. <br>
	* @param edad: Edad del Empleado. <br>
	* @return Objeto de tipo Formulario con sus atributos cargados de acuerdo a las condiciones indicadas en los parámetros. 
	*/
	public Formulario creaFormulario(int locacion,int remuneracion,int cargaHr,int puestoLaboral,int expPrevia,int estudios) {
		int rangoEtario;
		if (this.edad < 40)
			rangoEtario = 0;
		else if (this.edad < 50)
			rangoEtario = 1;
		else
			rangoEtario = 2;
		return new Formulario(locacion,remuneracion,cargaHr,puestoLaboral,rangoEtario,expPrevia,estudios);
	}
	
	
	/**
	* Crea un ticket de empleo enviando el formulario y los pesos a la agencia mediante el patrón de diseño Double Dispatch. <br>
	* <b>Pre</b>: Todos los parámetros deben ser distintos de null. <br>
	* <b>Post</b>: El atributo ticket contendrá la referencia al objeto TicketEmpleo correspondiente a su solicitud de trabajo. <br>
	* @param agencia:  Representa un elemento que implemente la interfaz IAgencia. <br>
	* @param f: Representa un formulario cargado con las condiciones del Empleado. <br>
	* @param p: Representa la importancia que le da el empleado a cada aspecto del formulario. 
	*/
	@Override
	public void emiteFormulario(IAgencia agencia,Formulario f,Peso peso) {
		this.ticket = agencia.recibeFormEmpleado(f,peso);
	}
	
	/**
	* Modifica el estado del ticket del empleado y penaliza al mismo en su puntaje<br>
	* <b>Post</b>: El atributo ticket contendrá la referencia al objeto TicketEmpleo correspondiente a su solicitud de trabajo y el puntaje de la aplicación estará decrementado en 1. 
	*/
	public void cancelaTicket() {
		this.ticket.setEstado("cancelado");
		this.incrPuntajeApp(-1);
	}
	
}
