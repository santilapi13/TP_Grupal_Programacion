package datos;

public abstract class Comisiones {
	
	private static double [][] matriz = {{60,70,80},{80,90,100}}; 

	public Comisiones() {
	}
	
	public static double calculaTasa(int fila, int columna, double puntaje) {
		return (matriz[fila][columna] - puntaje) < 0 ? 0.05: (matriz[fila][columna] - puntaje)/100;		// como minimo cobra una comision del 5%
	}

	public static double calculaTasa(int puesto, double puntaje) {
		double tasa = 0;
		switch (puesto) {
			case 0: tasa = 80;
		break;
			case 1: tasa = 90;
		break;
			case 2: tasa = 100;
		break;
		}
		return (tasa - puntaje) < 0 ? 0.05 : (tasa - puntaje)/100;		// como minimo cobra una comision del 5%
	}
}
