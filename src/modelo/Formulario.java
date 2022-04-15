package modelo;

public class Formulario {
	private String locacion;
	private double v1;
	private double v2;
	private String cargaHr;
	private String puestoLaboral;
	private int rangoEtario;	// (1:<40 ; 2: >40 & <50 ; 3:>50)
	private String expPrevia;
	private String estudios;
	
	public Formulario(String locacion, double v1, double v2, String cargaHr, String puestoLaboral, int rangoEtario, String expPrevia, String estudios) {
		this.locacion = locacion;
		this.v1 = v1;
		this.v2 = v2;
		this.cargaHr = cargaHr;
		this.puestoLaboral = puestoLaboral;
		this.rangoEtario = rangoEtario;
		this.expPrevia = expPrevia;
		this.estudios = estudios;
	}

	public String getLocacion() {
		return locacion;
	}
	public double getV1() {
		return v1;
	}
	public double getV2() {
		return v2;
	}
	public String getCargaHr() {
		return cargaHr;
	}
	public String getPuestoLaboral() {
		return puestoLaboral;
	}
	public int getRangoEtario() {
		return rangoEtario;
	}
	public String getExpPrevia() {
		return expPrevia;
	}
	public String getEstudios() {
		return estudios;
	}
	
}
