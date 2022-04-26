package modelo;

public class Contrato {

	private Empleado empleado;
	private Empleador empleador;
	
	public Contrato(Empleado empleado, Empleador empleador) {
		this.empleado = empleado;
		this.empleador = empleador;
	}

	public Empleado getEmpleado() {
		return empleado;
	}
	public Empleador getEmpleador() {
		return empleador;
	}

	@Override
	public String toString() {
		return empleador.getUsername() + " contrato a " + empleado.getUsername();
	}
	
	

}
