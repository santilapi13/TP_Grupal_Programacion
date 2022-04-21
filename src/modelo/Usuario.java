package modelo;

public abstract class Usuario implements IUsuario {
	private String username;
	private String password;
	private double puntajeApp;
	private ListaAsignacion listaAsignacion;
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public double getPuntajeApp() {
		return puntajeApp;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public ListaAsignacion getListaAsignacion() {
		return listaAsignacion;
	}

	public void setListaAsignacion(ListaAsignacion listaAsignacion) {
		this.listaAsignacion = listaAsignacion;
	}

	@Override
	public String toString() {
		return "username: " + username + ", password: " + password + ", puntajeApp: " + puntajeApp + ", listaAsignacion: " + listaAsignacion;
	}
	
	
	
}
