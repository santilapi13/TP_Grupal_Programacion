package modelo;

public class UsuarioFactory {
	
	public Usuario getUsuario(String tipoUsuario,String username, String password, String nombre, String tipoPersona, String rubro) {
		if (tipoUsuario == null)
			return null;
		if (tipoUsuario.equalsIgnoreCase("EMPLEADOR"))
			return new Empleador(username,password,nombre,tipoPersona,rubro);
		return null;
	}
	public Usuario getUsuario(String tipoUsuario,String username,String password,String nya, String telefono, int edad) {
		if (tipoUsuario == null)
			return null;
		if (tipoUsuario.equalsIgnoreCase("EMPLEADOR"))
			return new Empleado(username,password,nya,telefono,edad);
		return null;
	}
	
}
