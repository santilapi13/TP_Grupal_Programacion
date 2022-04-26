package datos;

import modelo.Ticket;

public class locacion extends Aspecto {

	private static locacion instance= null;
	private double [][] matriz = {{1,-1,0.5},{-1,1,0.5}, {0.5,0.5,1}};
	
	private locacion() {
	}

	public static locacion getInstance() {
	    if(instance==null)
	        locacion.instance=new locacion();
	    return locacion.instance;
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
