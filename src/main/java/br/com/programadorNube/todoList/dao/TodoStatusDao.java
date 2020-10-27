package br.com.programadorNube.todoList.dao;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.programadorNube.todoList.model.TodoStatus;

@RequestScoped
public class TodoStatusDao {
	
	@Transactional
	public void inserir(TodoStatus status) {
//		status.persistAndFlush();
		TodoStatus.persist(status);
	}
}
