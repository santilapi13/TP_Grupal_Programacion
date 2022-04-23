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

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso, String perspectiva) {
		int i, j;
		i = t1.getFormulario().getExpPrevia();
		j = t2.getFormulario().getExpPrevia();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}

}
