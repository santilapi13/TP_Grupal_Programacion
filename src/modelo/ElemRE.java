package modelo;

public class ElemRE {

    /**
     * @aggregation shared
     */
    private NoAdmin usuarioActual;

    /**
     * @aggregation shared
     */
    private NoAdmin usuarioElegido;
	private int indiceTicket;	// Si esta en arrayList de empleadores, indica el ticket de esa eleccion. Si esta en hashMap de empleados, indica ticket del empleador elegido.
	
	public ElemRE(NoAdmin usuarioActual, NoAdmin usuarioElegido, int indiceTicket) {
		this.usuarioActual = usuarioActual;
		this.usuarioElegido = usuarioElegido;
		this.indiceTicket = indiceTicket;
	}

	public NoAdmin getUsuarioActual() {
		return usuarioActual;
	}
	public NoAdmin getUsuarioElegido() {
		return usuarioElegido;
	}
	public int getIndiceTicket() {
		return indiceTicket;
	}

	@Override
	public String toString() {
		return usuarioActual + "\teligio a\t" + usuarioElegido + ", indiceTicket: " + indiceTicket;
	}

	
}
