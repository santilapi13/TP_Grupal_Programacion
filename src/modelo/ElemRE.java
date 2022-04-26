package modelo;

public class ElemRE {

	private Usuario usuarioActual;
	private Usuario usuarioElegido;
	private int indiceTicket;	// Si esta en arrayList de empleadores, indica el ticket de esa eleccion. Si esta en hashMap de empleados, indica ticket del empleador elegido.
	
	public ElemRE(Usuario usuarioActual, Usuario usuarioElegido, int indiceTicket) {
		this.usuarioActual = usuarioActual;
		this.usuarioElegido = usuarioElegido;
		this.indiceTicket = indiceTicket;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	public Usuario getUsuarioElegido() {
		return usuarioElegido;
	}
	public int getIndiceTicket() {
		return indiceTicket;
	}

	
}
