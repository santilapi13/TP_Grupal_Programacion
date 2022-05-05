package modelo;

import excepciones.PesoInvalidoException;

public class Peso {
	
	private double locacion;
	private double remuneracion;
	private double cargaHoraria;
	private double tipoPuesto;
	private double rangoEtario;
	private double expPrevia;
	private double estudiosCursados;
	
	/**
	 * Constructor de peso. Se determina que peso le da el usuario a cada campo del formulario. <br>
	 * <b>Pre</b>: ticket y usuario deben ser distinto de null.
	 * <b>Post</b>: se creó el objeto peso correctamente. Se asignaron los valores correctos a cada campo.<br>
	 * @param locacion: peso que el usuario le da a la locación.
	 * @param remuneracion: peso que el usuario le da a la remuneración.
	 * @param cargaHr: peso que el usuario le da a la carga horaria.
	 * @param puestoLaboral: peso que el usuario le da al puesto laboral.
	 * @param rangoEtario: peso que el usuario le da al rango etario.
	 * @param expPrevia: peso que el usuario le da a la experiencia previa.
	 * @param estudios: peso que el usuario le da a los estudios.
	 * @throws PesoInvalidoException : si alguno de los atributos del peso no esta en el rango establecido. 0<=valor campo<=1.
	 */
	public Peso(double locacion, double remuneracion, double cargaHoraria, double tipoPuesto, double rangoEtario, double expPrevia, double estudiosCursados) throws PesoInvalidoException{
		if (locacion < 0 || locacion > 1 || remuneracion < 0 || remuneracion > 1 || cargaHoraria < 0 || cargaHoraria > 1 || tipoPuesto < 0 || tipoPuesto > 1 || rangoEtario < 0 || rangoEtario > 1 || expPrevia < 0 || expPrevia > 1 || estudiosCursados < 0 || estudiosCursados > 1 )
			throw new PesoInvalidoException("Cada peso ingresado debe tener un valor entre 0 y 1");
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

	/**
	*Sobreescribe el método equals, para comparar cada atributo específico de los objetos Peso
	*<br>
	*@return boolean que indica si los objetos de tipo Peso poseen mismos atributos(true) o no(false).
	*/

	
	
	@Override
	public boolean equals(Object obj) {
		Peso p = (Peso) obj;
		return this.cargaHoraria == p.getCargaHoraria() && this.locacion == p.getLocacion() && this.remuneracion == p.getRemuneracion() && this.tipoPuesto == p.getTipoPuesto() && this.rangoEtario == p.getRangoEtario() && this.expPrevia == p.getExpPrevia() && this.estudiosCursados == p.getEstudiosCursados();
	}
	
	
	
}
