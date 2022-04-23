package prueba;

import modelo.Agencia;
import modelo.Empleado;
import modelo.Empleador;
import modelo.Formulario;
import modelo.Peso;
import modelo.UsuarioFactory;

public class Prueba {

	public Prueba() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Agencia agencia = Agencia.getInstance();
		UsuarioFactory factory = new UsuarioFactory();
		Empleado e1 = new Empleado("santisosa","saso123","Santiago Sosa","223585835",20);
		Empleador er1 = new Empleador("santilapi SA", "contraseña", "Santiago Lapiana", "Juridica", "Salud");
		e1.emiteFormulario(agencia, new Formulario(0,2,0,1,0,1,2) , new Peso(1,1,1,1,1,1,1));
		er1.creaFormulario(0, 2, 0, 1, 0, 1, 2, new Peso(1,1,1,1,1,1,1));
		er1.buscaEmpleados(agencia);
		agencia.iniciaRondaEncuentros();
		System.out.println(e1.getListaAsignacion().getUsuarios().first());
		System.out.println(er1.getListaAsignacion().getUsuarios().first());
	}

}
