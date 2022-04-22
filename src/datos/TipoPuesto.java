package datos;

import modelo.Ticket;

public class TipoPuesto extends Aspecto {

	private static TipoPuesto instance= null;
	private double [][] matriz = {{1,-0.5,-1},{-0.5,1,-0.5}, {-1,0.5,1}};

	private TipoPuesto() {
	}

	public static TipoPuesto getInstance() {
	    if(instance==null)
	        TipoPuesto.instance=new TipoPuesto();
	    return TipoPuesto.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getTipoPuesto();
	}



}
