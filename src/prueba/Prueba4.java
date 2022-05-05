package prueba;


import excepciones.UsuarioRepetidoException;
import modelo.Agencia;
import modelo.Empleado;

public class Prueba4 {

	public static void main(String[] args) {
		Agencia agencia = Agencia.getInstance();
		try {
			Empleado e1 = new Empleado("santisosa","saso123","Santiago Sosa","223585835",20);
			agencia.addEmpleado(e1);
			Empleado e2 = new Empleado("santisosa","lau123","Lautaro Luna","223897563",21);
			agencia.addEmpleado(e2);

		} catch (UsuarioRepetidoException e) {
			System.out.println(e.getMessage());
		} finally {
			//MUESTRA EMPLEADOS PARA QUE SE VEA QUE NO SE AÑADIO
			for (Empleado empleadoAct : agencia.getEmpleados())
				System.out.println(empleadoAct);
		}
	}

}
