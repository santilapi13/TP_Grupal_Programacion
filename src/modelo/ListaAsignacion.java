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
	
	/**
	 * Determina si un ticket idéntico (el mismo valor en todos los campos) ya se encuentra en la lista de asignación de cierto usuario. Esto se realiza para luego no agregar tickets repetidos.<br>
	 * <b>Pre</b>: ticket y usuario deben ser distinto de null.
	 * <b>Post</b>: se verificó si el ticket está en la lista de asignación correctamente. Se retornó true o false<br>
	 * @param ticket : ticket del cual se busca determinar si ya se encuentra en la lista de asignación.
	 * @param usuario : usuario del cual se aalizará su lista de asignación.
	 * @return true: si el ticket se encuentra en el la lista de asignación. false: si el ticket no está en la lista de asignación.
	 */
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
