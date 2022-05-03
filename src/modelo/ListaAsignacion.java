package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ListaAsignacion {

    /**
     * @aggregation composite
     */
    private SortedSet<ElemLA> usuarios = new TreeSet<ElemLA>();
	private Calendar fecha;

	public ListaAsignacion() {
		this.fecha = GregorianCalendar.getInstance();
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	public SortedSet<ElemLA> getUsuarios() {
		return usuarios;
	}
	
	public boolean ticketRepetido(Ticket ticket, NoAdmin usuario) {
		boolean esta = false;
		ElemLA elemAct;
		Iterator<ElemLA> iterator = this.usuarios.iterator();
		while (iterator.hasNext() && !esta) {
			elemAct= iterator.next();
			esta = ticket.getFormulario().equals(elemAct.getTicket().getFormulario()) && elemAct.getUsuario() == usuario;
		}
		return esta;
	}

	@Override
	public String toString() {
		return "\n\tfecha: " + fecha.getTime() +"\n";
	}
	
}
