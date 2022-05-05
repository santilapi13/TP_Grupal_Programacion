package datos;

import modelo.Ticket;

public class Locacion extends Aspecto {

	private static Locacion instance= null;
	private double [][] matriz = {{1,-1,0.5},{-1,1,0.5}, {0.5,0.5,1}};
	
	/**
	* Constructor privado debido a la implementación del patrón SINGLETON al calcular los puntajes.
	*/
	private Locacion() {
	}

	public static Locacion getInstance() {
	    if(instance==null)
	        Locacion.instance=new Locacion();
	    return Locacion.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getLocacion();
	}

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso, String perspectiva) {
		int i, j;
		i = t1.getFormulario().getLocacion();
		j = t2.getFormulario().getLocacion();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}
}
