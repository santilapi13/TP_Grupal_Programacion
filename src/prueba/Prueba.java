package prueba;

import java.util.Map;

import excepciones.PesoInvalidoException;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuariosInsuficientesException;
import modelo.Admin;
import modelo.Agencia;
import modelo.Contrato;
import modelo.ElemLA;
import modelo.ElemRE;
import modelo.Empleado;
import modelo.Empleador;
import modelo.Peso;
import modelo.Ticket;
import modelo.TicketEmpleado;

public class Prueba {

	public static void main(String[] args) {
		
		try {
			Agencia agencia = Agencia.getInstance();
			Empleado e1 = new Empleado("santisosa","saso123","Santiago Sosa","223585835",20);
			agencia.addEmpleado(e1);
			Empleado e2 = new Empleado("lauluna","lau123","Lautaro Luna","223897563",21);
			agencia.addEmpleado(e2);
			Empleador er1 = new Empleador("santilapi SA", "contraseña", "Santiago Lapiana", 1, 0);
			agencia.addEmpleador(er1);
			Empleador er2 = new Empleador("wencho SRL", "contraseña2", "Wenceslao Avalos", 0, 2);
			agencia.addEmpleador(er2);
			Admin a1 = new Admin("admin1","contraseñaAdmin1");
			
			// CREACION DE TICKETS
			e1.emiteFormulario(agencia, e1.creaFormulario(1,2,1,1,1,2) , new Peso(1,0.2,0.4,1,0.2,1,0.8));
			e2.emiteFormulario(agencia, e2.creaFormulario(0,0,2,2,1,2) , new Peso(1,0.5,0.4,1,0.2,1,0.8));

			er1.creaFormulario(0,1,0,1,0,1,2, new Peso(1,1,1,1,1,1,1));		// indice 0
			er1.creaFormulario(0,1,0,1,0,1,2,new Peso(1,1,1,1,1,1,1));		// indice 1
			er1.buscaEmpleados(agencia);	// 2 tickets iguales
			
			er2.creaFormulario(1, 2, 0, 2, 1, 1, 0, new Peso(0.3,0.5,1,1,0.1,1,1));
			er2.buscaEmpleados(agencia);
			
			a1.iniciaRondaEncuentros();
			
			// MUESTRA LISTAS DE ASIGNACION
			System.out.println("\n" + e1.getUsername());
			System.out.println(e1.getTicket());
			System.out.println("Lista de asignacion: ");
			for (ElemLA i : e1.getListaAsignacion().getUsuarios())
				System.out.println(i);
			
			System.out.println("\n" + e2.getUsername());
			System.out.println(e2.getTicket());
			System.out.println("Lista de asignacion: ");
			for (ElemLA k : e2.getListaAsignacion().getUsuarios())
				System.out.println(k);

			System.out.println("\n" + er1.getUsername());
			for (Ticket ticket : er1.getTickets())
				System.out.println(ticket);
			System.out.println("Lista de asignacion: ");
			for (ElemLA j : er1.getListaAsignacion().getUsuarios())
				System.out.println(j);
			
			System.out.println("\n" + er2.getUsername());
			for (Ticket ticket : er2.getTickets())
				System.out.println(ticket);
			System.out.println("Lista de asignacion: ");
			for (ElemLA l : er2.getListaAsignacion().getUsuarios())
				System.out.println(l);
			
			// ELECCIONES DE CADA USUARIO
			e1.setTicketElegido((TicketEmpleado) e1.getListaAsignacion().getUsuarios().first().getTicket());
			e2.setTicketElegido((TicketEmpleado) e2.getListaAsignacion().getUsuarios().first().getTicket());
			er1.eligeEmpleado(e1,er1.getTickets().get(0));
			er1.eligeEmpleado(e2,er1.getTickets().get(1));
			er2.eligeEmpleado(e1, er2.getTickets().get(0));
			
			a1.iniciaRondaElecciones();
			
			System.out.println("\nELECCIONES EMPLEADORES:");
			for (ElemRE eleccionEmpleador : agencia.getEleccionesEmpleadores())		// Muestra elecciones de empleadores
				System.out.println(eleccionEmpleador);
			
			System.out.println("\nELECCIONES EMPLEADOS");
			for (Map.Entry<String,ElemRE> entry : agencia.getEleccionesEmpleados().entrySet()) {	// Muestra elecciones de empleados
				System.out.println(entry.getValue());
			}
			
			a1.iniciaRondaContrataciones();
			
			System.out.println("\nCONTRATOS RESULTANTES:");
			for (Contrato contratoAct : agencia.getContratos())
				System.out.println(contratoAct);
			
			System.out.println("\nComision a cobrar por la agencia: $" + agencia.getFondos()) ;
			
			System.out.println("\nESTADOS FINALES");
			System.out.println(e1);
			System.out.println(e2);
			System.out.println(er1);
			System.out.println(er2);
			
		} catch (UsuariosInsuficientesException e) {
			System.out.println(e.getMessage());
		} catch (PesoInvalidoException e) {
			System.out.println(e.getMessage());
		} catch (UsuarioRepetidoException e) {
			System.out.println(e.getMessage());
		}
		
		

	}

}
