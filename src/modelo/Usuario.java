package modelo;

public abstract class Usuario implements IUsuario {
	private String username;
	private String password;
	private double puntaje;
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public double getPuntaje() {
		return puntaje;
	}
	
	
}
