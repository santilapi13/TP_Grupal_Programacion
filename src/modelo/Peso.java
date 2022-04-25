package modelo;

public class Peso {
	
	private double Locacion;
	private double Remuneracion;
	private double CargaHoraria;
	private double TipoPuesto;
	private double RangoEtario;
	private double ExpPrevia;
	private double EstudiosCursados;
	
	public Peso(double locacion, double remuneracion, double cargaHoraria, double tipoPuesto, double rangoEtario, double expPrevia, double estudiosCursados) {
		Locacion = locacion;
		Remuneracion = remuneracion;
		CargaHoraria = cargaHoraria;
		TipoPuesto = tipoPuesto;
		RangoEtario = rangoEtario;
		ExpPrevia = expPrevia;
		EstudiosCursados = estudiosCursados;
	}

	public double getLocacion() {
		return Locacion;
	}

	public double getRemuneracion() {
		return Remuneracion;
	}

	public double getCargaHoraria() {
		return CargaHoraria;
	}

	public double getTipoPuesto() {
		return TipoPuesto;
	}

	public double getRangoEtario() {
		return RangoEtario;
	}

	public double getExpPrevia() {
		return ExpPrevia;
	}

	public double getEstudiosCursados() {
		return EstudiosCursados;
	}

	public void setLocacion(double locacion) {
		Locacion = locacion;
	}

	public void setRemuneracion(double remuneracion) {
		Remuneracion = remuneracion;
	}

	public void setCargaHoraria(double cargaHoraria) {
		CargaHoraria = cargaHoraria;
	}

	public void setTipoPuesto(double tipoPuesto) {
		TipoPuesto = tipoPuesto;
	}

	public void setRangoEtario(double rangoEtario) {
		RangoEtario = rangoEtario;
	}

	public void setExpPrevia(double expPrevia) {
		ExpPrevia = expPrevia;
	}

	public void setEstudiosCursados(double estudiosCursados) {
		EstudiosCursados = estudiosCursados;
	}

	@Override
	public String toString() {
		return "Locacion: " + Locacion + ", Remuneracion: " + Remuneracion + ", CargaHoraria: " + CargaHoraria
				+ ", TipoPuesto: " + TipoPuesto + ", RangoEtario: " + RangoEtario + ", ExpPrevia: " + ExpPrevia
				+ ", EstudiosCursados: " + EstudiosCursados;
	}
	
}
