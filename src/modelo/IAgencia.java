package modelo;

import excepciones.UsuariosInsuficientesException;

public interface IAgencia {
	TicketEmpleado recibeFormEmpleador(Formulario f,Peso peso);
	TicketEmpleo recibeFormEmpleado(Formulario f,Peso peso);
	void iniciaRondaEncuentros() throws UsuariosInsuficientesException;
	void iniciaRondaElecciones();
	void iniciaRondaContrataciones();
	
}
