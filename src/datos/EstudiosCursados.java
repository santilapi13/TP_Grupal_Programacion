package datos;

import modelo.Ticket;

public class EstudiosCursados extends Aspecto {

	private static EstudiosCursados instance = null;
	private double[][] matriz = { { 1, 1.5, 2 }, { -0.5, 1, 1.5 }, { -2, -1.5, 1 } };

	private EstudiosCursados() {
	}

	public static EstudiosCursados getInstance() {
		if (instance == null)
			EstudiosCursados.instance = new EstudiosCursados();
		return EstudiosCursados.instance;
	}

	@Override
	protected double obtienePeso(Ticket t1) {
		return t1.getPeso().getEstudiosCursados();
	}

	@Override
	protected double calculaPuntaje(Ticket t1, Ticket t2, double peso, String perspectiva) {
		int i, j;
		i = t1.getFormulario().getEstudios();
		j = t2.getFormulario().getEstudios();
		return (perspectiva == "Empleador") ? (peso*this.matriz[i][j]) : (peso*this.matriz[j][i]);
	}

}
