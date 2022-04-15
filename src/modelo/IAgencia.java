package modelo;

public interface IAgencia {
	TicketEmpleado recibeFormEmpleador(Formulario f,int cantEmpSolicitados);
	TicketEmpleo recibeFormEmpleado(Formulario f);
}
