package modelo;

public class Empleado extends Usuario {
	private String nya;
	private String telefono;
	private int edad;
	
	public Empleado(String username,String password,String nya, String telefono, int edad) {
		super(username,password);
		this.nya = nya;
		this.telefono = telefono;
		this.edad = edad;
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
	
	
	
}
