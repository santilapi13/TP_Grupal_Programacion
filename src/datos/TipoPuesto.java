package datos;

import modelo.Ticket;

public class TipoPuesto extends Aspecto {

	private static TipoPuesto instance= null;
	private double [][] matriz = {{1,-0.5,-1},{-0.5,1,-0.5}, {-1,0.5,1}};

	/**
	* Constructor privado debido a la implementación del patrón SINGLETON al calcular los puntajes.
	*/
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

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso, String perspectiva) {
		int i, j;
		i = t1.getFormulario().getPuestoLaboral();
		j = t2.getFormulario().getPuestoLaboral();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}

}
