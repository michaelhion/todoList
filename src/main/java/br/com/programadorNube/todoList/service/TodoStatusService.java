package br.com.programadorNube.todoList.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.programadorNube.todoList.dao.TodoStatusDao;
import br.com.programadorNube.todoList.model.Todo;
import br.com.programadorNube.todoList.model.TodoStatus;
import br.com.programadorNube.todoList.model.dominio.StatusEnum;

@RequestScoped
public class TodoStatusService {
	
	@Inject
	TodoStatusDao dao;
	
	private void validar(TodoStatus status) {
		if (StatusEnum.
				isInvalido(
						status.getStatus().toString())) {
			throw new NotFoundException();
		}
	}
	@Transactional(rollbackOn = Exception.class)
	public void inserir(Long id, StatusEnum enumTexto) {
		TodoStatus status = new TodoStatus(enumTexto);
		status.setTodo(new Todo(id));
		validar(status);
		dao.inserir(status);
	}
	
}
