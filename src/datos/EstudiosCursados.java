package datos;

import modelo.Ticket;

public class EstudiosCursados extends Aspecto {

	private static EstudiosCursados instance= null;
	private double [][] matriz = {{1,1.5,2},{-0.5,1,1.5}, {-2,-1.5,1}};

	private EstudiosCursados() {
	}

	public static EstudiosCursados getInstance() {
	    if(instance==null)
	        EstudiosCursados.instance=new EstudiosCursados();
	    return EstudiosCursados.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getEstudiosCursados();
	}



}
