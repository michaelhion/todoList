package br.com.programadorNube.todoList.exceptions;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
@RequestScoped
public class ExceptionsTodo extends Exception{
	public String mensagem() {
		
		return "deu erro aqui";
	}
	
	public Response erroIncluir() {
		return Response.status(Response.Status.NOT_MODIFIED).entity("ocorreu um erro na inclusao").build();
	}
}
