 package br.com.programadorNube.todoList.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.programadorNube.todoList.model.Todo;
import br.com.programadorNube.todoList.model.TodoStatus;
import io.quarkus.panache.common.Sort;

@RequestScoped
public class TodoStatusDao {
	
	@Transactional
	public void inserir(TodoStatus status) {
//		status.persistAndFlush();
		TodoStatus.persist(status);
	}
	
	public List<TodoStatus> buscarStatusPorTarefa(Long idTarefa) {
		return TodoStatus
				.list("todo", Sort.by("data").descending(), new Todo(idTarefa)); 
	}
}
