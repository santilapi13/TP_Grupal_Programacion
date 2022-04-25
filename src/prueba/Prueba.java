package prueba;

import modelo.Agencia;
import modelo.ElemLA;
import modelo.Empleado;
import modelo.Empleador;
import modelo.Peso;
import modelo.UsuarioFactory;

public class Prueba {

	public Prueba() {
	}

	public static void main(String[] args) {
		Agencia agencia = Agencia.getInstance();
		UsuarioFactory factory = new UsuarioFactory();
		Empleado e1 = new Empleado("santisosa","saso123","Santiago Sosa","223585835",20);
		agencia.addEmpleado(e1);
		Empleado e2 = new Empleado("lauluna","lau123","Lautaro Luna","223897563",21);
		agencia.addEmpleado(e2);
		Empleador er1 = new Empleador("santilapi SA", "contraseña", "Santiago Lapiana", "Juridica", "Salud");
		agencia.addEmpleador(er1);
		Empleador er2 = new Empleador("wencho SRL", "contraseña2", "Wenceslao Avalos", "Fisica", "Comercio Internacional");
		agencia.addEmpleador(er2);

		e1.emiteFormulario(agencia, e1.creaFormulario(0,2,0,1,1,2) , new Peso(-30,1,1,1,1,1,1));
		e2.emiteFormulario(agencia, e2.creaFormulario(0,0,2,2,1,2) , new Peso(-30,1,1,1,1,1,1));

		er1.creaFormulario(0, 1, 0, 1, 0, 1, 2, new Peso(1,1,1,1,1,1,1));
		er1.creaFormulario(0,2,0,1,0,1,2,new Peso(1,1,1,1,1,1,1));
		er1.creaFormulario(0,2,0,1,0,1,2,new Peso(1,1,1,1,1,1,1));
		er1.buscaEmpleados(agencia);
		er2.creaFormulario(0, 1, 0, 1, 0, 1, 2, new Peso(1,1,1,1,1,1,1));
		er2.buscaEmpleados(agencia);
		
		try {
			agencia.iniciaRondaEncuentros();
		} catch (NullPointerException e) {
			System.out.println("Debe existir al menos un usuario de cada tipo para iniciar la ronda de encuentros.");
		}
		
		System.out.println("EMPLEADO 1");
		System.out.println(e1);
		System.out.println("Lista de asignacion: ");
		for (ElemLA i : e1.getListaAsignacion().getUsuarios())
			System.out.println(i);
		
		System.out.println("\nEMPLEADO 2");
		System.out.println(e2);
		System.out.println("Lista de asignacion: ");
		for (ElemLA k : e2.getListaAsignacion().getUsuarios())
			System.out.println(k);

		System.out.println("\nEMPLEADOR 1");
		System.out.println(er1);
		System.out.println("Lista de asignacion: ");
		for (ElemLA j : er1.getListaAsignacion().getUsuarios())
			System.out.println(j);
		
		System.out.println("\nEMPLEADOR 2");
		System.out.println(er2);
		System.out.println("Lista de asignacion: ");
		for (ElemLA l : er2.getListaAsignacion().getUsuarios())
			System.out.println(l);

	}

}
