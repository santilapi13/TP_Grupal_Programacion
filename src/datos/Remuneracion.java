package datos;

import modelo.Ticket;

public class remuneracion extends Aspecto {

	private static remuneracion instance= null;
	private double [][] matriz = {{1,-0.5,-1},{1,1,-0.5}, {1,1,1}};

	private remuneracion() {
	}

	public static remuneracion getInstance() {
	    if(instance==null)
	        remuneracion.instance=new remuneracion();
	    return remuneracion.instance;
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
