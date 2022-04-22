package datos;

import modelo.Ticket;

public abstract class Aspecto {
	
	public Aspecto() {
		// TODO Auto-generated constructor stub
	}

	public double calculaPuntAspecto(Ticket t1,Ticket t2) {
		double peso,puntaje = 0;
		peso = this.obtienePeso(t1);
		puntaje = calculaPuntaje(t1,t2,peso);
		return puntaje;
	}
	
	protected abstract double obtienePeso(Ticket t1);
	protected abstract double calculaPuntaje(Ticket t1,Ticket t2,double peso);
	
}
