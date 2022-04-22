package datos;

import modelo.Ticket;

public class Remuneracion extends Aspecto {

	private static Remuneracion instance= null;
	private double [][] matriz = {{1,-0.5,-1},{1,1,-0.5}, {1,1,1}};

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



}
