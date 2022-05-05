package datos;

import modelo.Ticket;

public class Remuneracion extends Aspecto {

	private static Remuneracion instance= null;
	private double [][] matriz = {{1,-0.5,-1},{1,1,-0.5}, {1,1,1}};

	/**
	* Constructor privado debido a la implementación del patrón SINGLETON al calcular los puntajes.
	*/
	private Remuneracion() {
	}

	public static Remuneracion getInstance() {
	    if(instance==null)
	        Remuneracion.instance=new Remuneracion();
	    return Remuneracion.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getRemuneracion();
	}

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso, String perspectiva) {
		int i, j;
		i = t1.getFormulario().getRemuneracion();
		j = t2.getFormulario().getRemuneracion();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}

}
