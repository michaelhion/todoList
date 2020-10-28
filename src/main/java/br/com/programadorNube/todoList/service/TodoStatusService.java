package br.com.programadorNube.todoList.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;

import br.com.programadorNube.todoList.dao.TodoStatusDao;
import br.com.programadorNube.todoList.dto.TodoStatusDto;
import br.com.programadorNube.todoList.exceptions.ExceptionsTodo;
import br.com.programadorNube.todoList.model.Todo;
import br.com.programadorNube.todoList.model.TodoStatus;
import br.com.programadorNube.todoList.model.dominio.StatusEnum;
import br.com.programadorNube.todoList.model.parser.TodoStatusParser;

@RequestScoped
public class TodoStatusService {
	
	@Inject
	TodoStatusDao dao;
	
	@Inject
	UserService userService;
	
	private void validar(TodoStatus todoStatus) {
		if (StatusEnum.isInvalido(todoStatus.getStatus().toString())) {
			throw new NotFoundException();
		}
	}
	@Transactional(rollbackOn = ExceptionsTodo.class)
	public void inserir(Long id, StatusEnum enumTexto, String emailLogado) {
		TodoStatus status = new TodoStatus(enumTexto);
		status.setTodo(new Todo(id));
		status.setUser(userService.buscarUsuarioPorEmail(emailLogado));
		validar(status);
		dao.inserir(status);
	}
	
	private void validarAtualizacao(TodoStatus todoStatusBanco,
			 TodoStatus todoStatusTela) {
		validar(todoStatusTela);
		if(todoStatusBanco.getStatus().equals(StatusEnum.DONE)) {
			throw new NotAllowedException("Tarefa com status que não permite modificação");
		}
		
	}
	@Transactional(rollbackOn = ExceptionsTodo.class)
	public void atualizar(Long id, String enumTexo) {
		
		TodoStatus statusTela = new TodoStatus(StatusEnum.valueOf(enumTexo));
		statusTela.setTodo(new Todo(id));
		TodoStatus statusBanco = dao.buscarStatusPorTarefa(id).get(0);
		validarAtualizacao(statusBanco, statusTela);
		dao.inserir(statusTela);
	}
	
	public List<TodoStatusDto> buscarTodosStatusPorTarefa(Long idTarefa){
		List<TodoStatus> statusBanco = dao.buscarStatusPorTarefa(idTarefa);
		
		return statusBanco.stream().map(TodoStatusParser.get()::dto).collect(Collectors.toList());
	}
	
}
