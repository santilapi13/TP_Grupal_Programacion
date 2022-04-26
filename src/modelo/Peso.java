package modelo;

public class Peso {
	
	private double locacion;
	private double remuneracion;
	private double cargaHoraria;
	private double tipoPuesto;
	private double rangoEtario;
	private double expPrevia;
	private double estudiosCursados;
	
	public Peso(double locacion, double remuneracion, double cargaHoraria, double tipoPuesto, double rangoEtario, double expPrevia, double estudiosCursados) {
		this.locacion = locacion;
		this.remuneracion = remuneracion;
		this.cargaHoraria = cargaHoraria;
		this.tipoPuesto = tipoPuesto;
		this.rangoEtario = rangoEtario;
		this.expPrevia = expPrevia;
		this.estudiosCursados = estudiosCursados;
	}

	public double getLocacion() {
		return locacion;
	}

	public double getRemuneracion() {
		return remuneracion;
	}

	public double getCargaHoraria() {
		return cargaHoraria;
	}

	public double getTipoPuesto() {
		return tipoPuesto;
	}

	public double getRangoEtario() {
		return rangoEtario;
	}

	public double getExpPrevia() {
		return expPrevia;
	}

	public double getEstudiosCursados() {
		return estudiosCursados;
	}

	public void setLocacion(double locacion) {
		this.locacion = locacion;
	}

	public void setRemuneracion(double remuneracion) {
		this.remuneracion = remuneracion;
	}

	public void setCargaHoraria(double cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public void setTipoPuesto(double tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}

	public void setRangoEtario(double rangoEtario) {
		this.rangoEtario = rangoEtario;
	}

	public void setExpPrevia(double expPrevia) {
		this.expPrevia = expPrevia;
	}

	public void setEstudiosCursados(double estudiosCursados) {
		this.estudiosCursados = estudiosCursados;
	}

	@Override
	public String toString() {
		return "Locacion: " + locacion + ", Remuneracion: " + remuneracion + ", CargaHoraria: " + cargaHoraria
				+ ", TipoPuesto: " + tipoPuesto + ", RangoEtario: " + rangoEtario + ", ExpPrevia: " + expPrevia
				+ ", EstudiosCursados: " + estudiosCursados;
	}

	@Override
	public boolean equals(Object obj) {
		Peso p = (Peso) obj;
		return this.cargaHoraria == p.getCargaHoraria() && this.locacion == p.getLocacion() && this.remuneracion == p.getRemuneracion() && this.tipoPuesto == p.getTipoPuesto() && this.rangoEtario == p.getRangoEtario() && this.expPrevia == p.getExpPrevia() && this.estudiosCursados == p.getEstudiosCursados();
	}
	
	
	
}
