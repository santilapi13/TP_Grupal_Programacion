package modelo;

public interface IAgencia {
	TicketEmpleado recibeFormEmpleador(Formulario f,Peso peso);
	TicketEmpleo recibeFormEmpleado(Formulario f,Peso peso);
}