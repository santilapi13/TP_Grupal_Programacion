package datos;

import modelo.Ticket;

public class CargaHoraria extends Aspecto {

	private static CargaHoraria instance= null;
	private double [][] matriz = {{1,-0.5,-1},{-0.5,1,-0.5}, {-1,0.5,1}};

	/**
	* Constructor privado debido a la implementación del patrón SINGLETON al calcular los puntajes.
	*/

	private CargaHoraria() {
	}

	public static CargaHoraria getInstance() {
	    if(instance==null)
	        CargaHoraria.instance=new CargaHoraria();
	    return CargaHoraria.instance;
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
