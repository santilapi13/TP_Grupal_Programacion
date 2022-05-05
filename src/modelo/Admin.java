package modelo;

import java.util.ArrayList;

import excepciones.UsuariosInsuficientesException;

/** 
* @author Grupo 7
 * <br> 
* Clase que sirve para representar a los usuarios que tienen más acceso a los métodos del sistema. <br>
 * Todos sus métodos delegan la funcionalidad a la agencia.
*/
public class Admin extends Usuario {

    /**
     * @aggregation shared
     */
    private Agencia agencia = Agencia.getInstance();

	public Admin(String username, String password) {
		super(username, password);
	}

	public void iniciaRondaEncuentros() throws UsuariosInsuficientesException {
		agencia.iniciaRondaEncuentros();
	}
	
	public void iniciaRondaElecciones() {
		agencia.iniciaRondaElecciones();
	}
	
	public void iniciaRondaContrataciones() {
		agencia.iniciaRondaContrataciones();
	}
	
	public ArrayList<Contrato> getContratos() {
		return agencia.getContratos();
	}
	
	public ArrayList<Empleado> getEmpleados() {
		return agencia.getEmpleados();
	}
	
	public ArrayList<Empleador> getEmpleadores() {
		return agencia.getEmpleadores();
	}
}
