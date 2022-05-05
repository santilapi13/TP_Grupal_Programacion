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
import modelo.Peso;

public class Prueba2 {

	public static void main(String[] args) {
		try {
			
			Agencia agencia = Agencia.getInstance();
			Empleado e1 = new Empleado("santisosa","saso123","Santiago Sosa","223585835",20);
			agencia.addEmpleado(e1);
			
			Admin a1 = new Admin("admin1","contraseñaAdmin1");
			
			// CREACION DE TICKETS ( 1 empleado y no hay empleadores)
			e1.emiteFormulario(agencia, e1.creaFormulario(1,2,1,1,1,2) , new Peso(1,0.2,0.4,1,0.2,1,0.8));
			
			a1.iniciaRondaEncuentros();
			
			// MUESTRA LISTAS DE ASIGNACION
			System.out.println("\n" + e1.getUsername());
			System.out.println("Lista de asignacion: ");
			for (ElemLA i : e1.getListaAsignacion().getUsuarios())
				System.out.println(i);
			
			a1.iniciaRondaElecciones();
			
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
			
		} catch (UsuariosInsuficientesException e) {
			System.out.println(e.getMessage());
		} catch (PesoInvalidoException e) {
			System.out.println(e.getMessage());
		} catch (UsuarioRepetidoException e) {
			System.out.println(e.getMessage());
		}

	}

}
