package br.com.programadorNube.todoList.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.programadorNube.todoList.service.TodoService;
import br.com.programadorNube.todoList.service.TodoServicePrimeiroExemplo;

@Path("/todoPrimeiroExemplo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoRestPrimeiroExemplo {
	
	@Inject
	TodoServicePrimeiroExemplo service;
	
	@GET
	@Path("/{nome}")
	public Response adicionarLista(@PathParam("nome")String nome) {
		service.adicionar(nome);
		return Response
				.status(Status.ACCEPTED)
				.entity(service.listar())
				.build();
	}
	
	@GET
	@Path("/")
	public Response obter() {
		return Response.status(Status.OK).entity(null).build();
	}
}
