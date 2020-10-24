package br.com.programadorNube.todoList.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.programadorNube.todoList.dao.TodoDao;
import br.com.programadorNube.todoList.dto.TodoDto;
import br.com.programadorNube.todoList.model.Todo;
import br.com.programadorNube.todoList.model.parser.TodoParser;



@RequestScoped
@Traced
public class TodoService {

	@Inject
	TodoDao dao;
	
	private void validar(Todo todo) {
		if(dao.IsNomeRepetido(todo.getNome())) {
			throw new NotFoundException();
		}
	}
	@Transactional(rollbackOn = Exception.class)
	public void inserir(TodoDto todoDto) {
		//validação
		Todo todo = TodoParser.get().entidade(todoDto);
		validar(todo);
		
		todo.setDataCriacao(LocalDateTime.now());
		
		dao.inserir(todo);
	}
	
	public List<TodoDto> listar() {
		return dao
				.listar()
				.stream()
				.map(TodoParser.get()::dto)
				.collect(Collectors.toList());
	}
	
	public void excluir (Long id) {
		//validar se id é valido
		if (dao.buscarPorId(id)== null) {
			throw new NotFoundException();
		}
		dao.excluir(id);
			
	}
	
	public TodoDto buscar(Long id) {
		
		
		return TodoParser.get().dto(buscarPorId(id));
	}
	@Transactional(rollbackOn = Exception.class)
	public void atualizar(Long id , TodoDto dto) {
		Todo todo = TodoParser.get().entidade(dto);
		
		Todo todoBanco = buscarPorId(id);
		todoBanco.setNome(todo.getNome());
		dao.atualizar(todoBanco);
	}
	
	private Todo buscarPorId(Long id) {
		Todo todo = dao.buscarPorId(id);
		if(todo == null) {
			throw new NotFoundException();
		}
		return todo;
	}
}
