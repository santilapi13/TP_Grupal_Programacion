package modelo;

public class Formulario {
	private int locacion;
	private int remuneracion;	// 0: <50k ; 1: >50k && <100k ; 2: >100k
	private int cargaHr;
	private int puestoLaboral;
	private int rangoEtario;	// (0:<40 ; 1: >40 && <50 ; 2:>50)
	private int expPrevia;
	private int estudios;
	
	public Formulario(int locacion, int remuneracion, int cargaHr, int puestoLaboral, int rangoEtario, int expPrevia, int estudios) {
		super();
		this.locacion = locacion;
		this.remuneracion = remuneracion;
		this.cargaHr = cargaHr;
		this.puestoLaboral = puestoLaboral;
		this.rangoEtario = rangoEtario;
		this.expPrevia = expPrevia;
		this.estudios = estudios;
	}

	public int getLocacion() {
		return locacion;
	}

	public int getRemuneracion() {
		return remuneracion;
	}

	public int getCargaHr() {
		return cargaHr;
	}

	public int getPuestoLaboral() {
		return puestoLaboral;
	}

	public int getRangoEtario() {
		return rangoEtario;
	}

	public int getExpPrevia() {
		return expPrevia;
	}

	public int getEstudios() {
		return estudios;
	}

	@Override
	public boolean equals(Object obj) {
		Formulario f = (Formulario) obj;
		return this.cargaHr == f.getCargaHr() && this.locacion == f.getLocacion() && this.remuneracion == f.getRemuneracion() && this.puestoLaboral == f.getPuestoLaboral() && this.rangoEtario == f.getRangoEtario() && this.expPrevia == f.getExpPrevia() && this.estudios == f.getEstudios();
	}

	/*
	@Override
	public String toString() {
		return "locacion: " + locacion + ", remuneracion: " + remuneracion + ", cargaHr: " + cargaHr
				+ ", puestoLaboral: " + puestoLaboral + ", rangoEtario: " + rangoEtario + ", expPrevia: " + expPrevia
				+ ", estudios: " + estudios;
	}*/
	
	
	
}
