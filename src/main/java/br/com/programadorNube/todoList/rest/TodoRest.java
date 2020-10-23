package br.com.programadorNube.todoList.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.programadorNube.todoList.dto.TodoDto;
import br.com.programadorNube.todoList.model.Todo;
import br.com.programadorNube.todoList.service.TodoService;

@Path("todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoRest {
	
	@Inject
	TodoService service;
	
	@Inject
	Validator validator;
	
	@GET
	@Path("")
	@Operation(summary = "Lista tarefas",
	description = "Retorna uma lista de Todo.class")
	@APIResponse(responseCode = "200",
	description = "lista de tarefas",
	content = {
			@Content(mediaType = "application/json",
					schema = @Schema(implementation = Todo.class, type = SchemaType.ARRAY))
	})
	public Response listar() {
		return Response
				.status(Response.Status.OK)
				.entity(service.listar())
				.build();
	}
	
	@POST
	@Path("")
	@Operation(summary = "Incluir tarefa",
	description = "Incluir uma tarefa")
	@APIResponse(responseCode = "201",
	description = "tarefa",
	content = {
			@Content(mediaType = "application/json",
					schema = @Schema(implementation = Todo.class))
	})
	public Response incluir(@Valid TodoDto todo) {
		
		Set<ConstraintViolation<TodoDto>> erros
		= validator.validate(todo);
		
		if(erros.isEmpty()) {
			service.inserir(todo);
		}else {
			List<String> listaErros = erros.stream()
			.map(ConstraintViolation::getMessage)
			.collect(Collectors.toList());
//			listaErros.forEach(i -> {
//				System.out.println(i);
//			});
			throw new NotFoundException(listaErros.get(0));
			
		}
		
		
		return Response
				.status(Response.Status.CREATED)
				.build();
	}
	
	@DELETE
	@Path("/{id}")
	@Operation(summary = "Excluir tarefa",
	description = "Excluir uma tarefa")
	@APIResponse(responseCode = "202",
	description = "tarefa",
	content = {
			@Content(mediaType = "application/json",
					schema = @Schema(implementation = Todo.class))
	})
	public Response excluir(@PathParam("id")Long id) {
		service.excluir(id);
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
	
	
}