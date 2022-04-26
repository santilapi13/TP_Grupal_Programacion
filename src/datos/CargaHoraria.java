package datos;

import modelo.Ticket;

public class cargaHoraria extends Aspecto {

	private static cargaHoraria instance= null;
	private double [][] matriz = {{1,-0.5,-1},{-0.5,1,-0.5}, {-1,0.5,1}};

	private cargaHoraria() {
	}

	public static cargaHoraria getInstance() {
	    if(instance==null)
	        cargaHoraria.instance=new cargaHoraria();
	    return cargaHoraria.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getCargaHoraria();
	}

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso,String perspectiva) {	
		int i,j;
		i = t1.getFormulario().getCargaHr();	
		j = t2.getFormulario().getCargaHr();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}
}
