package datos;

import modelo.Ticket;

public class Locacion extends Aspecto {

	private static Locacion instance= null;
	private double [][] matriz = {{1,-1,0.5},{-1,1,0.5}, {0.5,0.5,1}};
	
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

}
