package prueba;

import excepciones.PesoInvalidoException;
import excepciones.UsuarioRepetidoException;
import modelo.Agencia;
import modelo.Empleado;
import modelo.Peso;

public class Prueba5 {

	public static void main(String[] args) {
		try {
			
			Agencia agencia = Agencia.getInstance();
			Empleado e1 = new Empleado("santisosa","saso123","Santiago Sosa","223585835",20);
			agencia.addEmpleado(e1);
			
			// CREACION DE TICKETS (Con un peso negativo)
			e1.emiteFormulario(agencia, e1.creaFormulario(1,2,1,1,1,2) , new Peso(-1,0.2,0.4,1,0.2,1,0.8));
			
		} catch (PesoInvalidoException e) {
			System.out.println(e.getMessage());
		} catch (UsuarioRepetidoException e) {
			System.out.println(e.getMessage());
		}
	}

}
