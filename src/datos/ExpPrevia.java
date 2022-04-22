package datos;

import modelo.Ticket;

public class ExpPrevia extends Aspecto {

	private static ExpPrevia instance= null;
	private double [][] matriz = {{1,1.5,2},{-0.5,1,1.5}, {-2,-1.5,1}};

	private ExpPrevia() {
	}

	public static ExpPrevia getInstance() {
	    if(instance==null)
	        ExpPrevia.instance=new ExpPrevia();
	    return ExpPrevia.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getExpPrevia();
	}



}
