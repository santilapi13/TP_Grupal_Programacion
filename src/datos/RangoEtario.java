package datos;

import modelo.Ticket;

public class rangoEtario extends Aspecto {

	private static rangoEtario instance= null;
	private double [][] matriz = {{1,-0.5,-1},{-0.5,1,-0.5}, {-1,0.5,1}};

	private rangoEtario() {
	}

	public static rangoEtario getInstance() {
	    if(instance==null)
	        rangoEtario.instance=new rangoEtario();
	    return rangoEtario.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getRangoEtario();
	}

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso, String perspectiva) {
		int i, j;
		i = t1.getFormulario().getRangoEtario();
		j = t2.getFormulario().getRangoEtario();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}
}
