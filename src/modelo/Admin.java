package modelo;

import java.util.ArrayList;

public class Admin extends Usuario {

    /**
     * @aggregation shared
     */
    private Agencia agencia = Agencia.getInstance();

	public Admin(String username, String password) {
		super(username, password);
	}

	public void iniciaRondaEncuentros() {
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
