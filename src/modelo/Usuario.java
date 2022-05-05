package modelo;
/**
 * @author Grupo 7
 * <br>
 *Clase abstracta que alberga las características comunes de un usuario del sistema, de ella se extienden 
 *las clases Admin y Admin.
 *
 */



public abstract class Usuario {

	private String username;
	private String password;

	/**
	*Constructor con dos parámetros para crear un usuario, con su respectivo nombre de usuario y contraseña.<br>
	*@param username : Parámetro de tipo String, será el nombre para logueo del usuario.
	*@param password : Parámetro de tipo String, será la contraseña de logueo del usuario.
	*
	*/

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return username;
	}
	
	
}
