package modelo;

public class Empleador extends Usuario {

	private String nombre;
	private String tipoPersona;
	private String rubro;
	
	public Empleador(String username, String password, String nombre, String tipoPersona, String rubro) {
		super(username, password);
		this.nombre = nombre;
		this.tipoPersona = tipoPersona;
		this.rubro = rubro;
	}
	
	
	
}
