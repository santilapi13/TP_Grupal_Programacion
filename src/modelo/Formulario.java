package modelo;

public class Formulario {
	private int locacion;		// 0: home office ; 1: presencial ; 2: indistinta
	private int remuneracion;	// 0: <50k ; 1: >50k && <100k ; 2: >100k
	private int cargaHr;		// 0: media ; 1: completa ; 2: extendida
	private int puestoLaboral;	// 0: junior ; 1: senior ; 2: gerencial
	private int rangoEtario;	// (0:<40 ; 1: >40 && <50 ; 2:>50)
	private int expPrevia;		// 0: nada ; 1: media ; 2: mucha
	private int estudios;		// 0: primario ; 1: secundario ; 2: terciario
	
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
