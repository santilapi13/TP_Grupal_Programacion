package modelo;

public class Formulario {
	private int locacion;
	private int remuneracion;
	private int cargaHr;
	private int puestoLaboral;
	private int rangoEtario;	// (1:<40 ; 2: >40 & <50 ; 3:>50)
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
	
}
