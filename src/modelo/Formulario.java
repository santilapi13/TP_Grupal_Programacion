package modelo;

public class Formulario {
	private int locacion;		// 0: home office ; 1: presencial ; 2: indistinta
	private int remuneracion;	// 0: <50k ; 1: >50k && <100k ; 2: >100k
	private int cargaHr;		// 0: media ; 1: completa ; 2: extendida
	private int puestoLaboral;	// 0: junior ; 1: senior ; 2: gerencial
	private int rangoEtario;	// (0:<40 ; 1: >40 && <50 ; 2:>50)
	private int expPrevia;		// 0: nada ; 1: media ; 2: mucha
	private int estudios;		// 0: primario ; 1: secundario ; 2: terciario
	
	/**
	 * Constructor del formulario de empleo/empleado. Se establecen datos relacionados con el empleo.<br>
	 * <b>Pre</b>: Los parámetros del formulario deben ser 0,1 o 2.<br>
	 * <b>Post</b>: El formulario se creó correctamente (se asignó a cada atributo el valor correcto) <br>
	 * @param locacion: locación que el empleador busca para el el empleo. 0=Home Office, 1=presencial, 2= indistinto.
	 * @param remuneracion: remuneración que el empleador ofrece pagar para el empleo. 0=hasta 50k, 1= entre 50k y 100k, 2=+100k
	 * @param cargaHr: carga horaria que el empleado busca para el empleo. 0=media, 1=completa, 2=extendida.
	 * @param puestoLaboral: tipo de puesto que el empleador ofrece para el trabajo. 0=junior, 1=senior, 2=managment
	 * @param rangoEtario: rango etario que el empleador busca para el empleo. 0=menos de 40, 1= entre 40 y 50, 2=más de 50.
	 * @param expPrevia: experiencia previa que el empleador busca para el empleo. 0= nada, 1=media, 2=mucha.
	 * @param estudios: estudios cursados que el empleador desea que el empleado tenga. 1=primario, 2=secundario, 3=terciario.
	 */
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
	/**
	 * Permite comparar 2 formularios y determinar si tienen todos los campos iguales.<br>
	 * <b>Pre</b>: obj !=null.
	 * <b>Post</b>: se realizó la comparación correctamente. Se retornó true o false<br>
	 * @param obj : formulario con el cual se desea comparar el valor de los campos.
	 * @return true: si los 2 formularios tienen todos los campos iguales. false: si los formularios difieren en el valor de 1 o más campos.
	 */
	public boolean equals(Object obj) {
		Formulario f = (Formulario) obj;
		return this.cargaHr == f.getCargaHr() && this.locacion == f.getLocacion() && this.remuneracion == f.getRemuneracion() && this.puestoLaboral == f.getPuestoLaboral() && this.rangoEtario == f.getRangoEtario() && this.expPrevia == f.getExpPrevia() && this.estudios == f.getEstudios();
	}

	
	@Override
	public String toString() {
		return "locacion: " + locacion + ", remuneracion: " + remuneracion + ", cargaHr: " + cargaHr
				+ ", puestoLaboral: " + puestoLaboral + ", rangoEtario: " + rangoEtario + ", expPrevia: " + expPrevia
				+ ", estudios: " + estudios;
	}
	
	
	
}
