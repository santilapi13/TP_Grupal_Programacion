package modelo;

public class ElemLA implements Comparable<ElemLA> {

    /**
     * @aggregation shared
     */
    private NoAdmin usuario;
	private double puntEntrevista;

    /**
     * @aggregation shared
     */
    private Ticket ticket;

	public ElemLA(NoAdmin usuario, double puntaje, Ticket ticket) {
		this.usuario = usuario;
		this.puntEntrevista = puntaje;
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public NoAdmin getUsuario() {
		return usuario;
	}

	public double getPuntEntrevista() {
		return puntEntrevista;
	}

	@Override
	public int compareTo(ElemLA o) {
		int resultado=0;
		if (this.puntEntrevista>o.getPuntEntrevista()) {
			resultado = -1;      
		}
		else if (this.puntEntrevista<o.getPuntEntrevista()) {    
			resultado = 1;      
		}
		else if (this.puntEntrevista == o.getPuntEntrevista())
			resultado = 1;
		return resultado;
	}

	@Override
	public String toString() {
		return "username: " + usuario.getUsername() + ", puntaje: " + puntEntrevista;
	}

	
}
