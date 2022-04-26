package datos;

import modelo.Ticket;

public class estudiosCursados extends Aspecto {

	private static estudiosCursados instance = null;
	private double[][] matriz = { { 1, 1.5, 2 }, { -0.5, 1, 1.5 }, { -2, -1.5, 1 } };

	private estudiosCursados() {
	}

	public static estudiosCursados getInstance() {
		if (instance == null)
			estudiosCursados.instance = new estudiosCursados();
		return estudiosCursados.instance;
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
