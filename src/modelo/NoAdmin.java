package modelo;

public abstract class NoAdmin extends Usuario implements IEmisor {
	private double puntajeApp;

    /**
     * @aggregation composite
     */
    private ListaAsignacion listaAsignacion;
	private double comisionAPagar;
	
	public NoAdmin(String username, String password) {
		super(username,password);
		this.listaAsignacion = new ListaAsignacion();
		this.comisionAPagar = 0;
	}

	
	public double getComisionAPagar() {
		return comisionAPagar;
	}

	public void agregaComision(double comision) {
		this.comisionAPagar += comision;
	}

	public double getPuntajeApp() {
		return puntajeApp;
	}
	
	public void incrPuntajeApp(double incr) {
		this.puntajeApp += incr;
	}

	public ListaAsignacion getListaAsignacion() {
		return listaAsignacion;
	}

	public void setListaAsignacion(ListaAsignacion listaAsignacion) {
		this.listaAsignacion = listaAsignacion;
	}


	@Override
	public String toString() {
		return this.getUsername() + ", PuntajeApp: " + puntajeApp + ", ComisionAPagar: " + comisionAPagar;
	}


	
}
