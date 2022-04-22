package datos;

import modelo.Ticket;

public class RangoEtario extends Aspecto {

	private static RangoEtario instance= null;
	private double [][] matriz = {{1,-0.5,-1},{-0.5,1,-0.5}, {-1,0.5,1}};

	private RangoEtario() {
	}

	public static RangoEtario getInstance() {
	    if(instance==null)
	        RangoEtario.instance=new RangoEtario();
	    return RangoEtario.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getRangoEtario();
	}


}
