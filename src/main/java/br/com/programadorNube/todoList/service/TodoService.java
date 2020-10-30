package br.com.programadorNube.todoList.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.opentracing.Traced;
import org.hibernate.annotations.CreationTimestamp;

import br.com.programadorNube.todoList.dao.TodoDao;
import br.com.programadorNube.todoList.dto.TodoDto;
import br.com.programadorNube.todoList.model.Todo;
import br.com.programadorNube.todoList.model.dominio.StatusEnum;
import br.com.programadorNube.todoList.model.parser.TodoParser;



@RequestScoped
@Traced
public class TodoService {

	@Inject
	TodoDao dao;
	
	@Inject
	TodoStatusService statusService;
	
	
	private void validar(Todo todo) {
		if(dao.IsNomeRepetido(todo.getNome())) {
			throw new NotFoundException();
		}
	}
	@Transactional(rollbackOn = Exception.class)
	/*
	 * regra de criacao
	 * toda tarefa criada vem por padrao na lista
	 * Todo e com a data corrente
	 */
	public void inserir(@Valid TodoDto todoDto, String emailLogado) {
		//validação
		Todo todo = TodoParser.get().entidade(todoDto);
		validar(todo);
		/*
		 * substituida por @CreationTimestamp
		 */
//		todo.setDataCriacao(LocalDateTime.now());
		
		Long id = dao.inserir(todo);
		
		statusService.inserir(id, StatusEnum.TODO, emailLogado);
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
	public void atualizar(Long id , TodoDto dto, String emailLogado) {
		Todo todo = TodoParser.get().entidade(dto);
		
		Todo todoBanco = buscarPorId(id);
		todoBanco.setNome(todo.getNome());
		dao.atualizar(todoBanco);
		statusService.atualizar(id, dto.getStatus(), emailLogado);
	}
	
	private Todo buscarPorId(Long id) {
		Todo todo = dao.buscarPorId(id);
		if(todo == null) {
			throw new NotFoundException();
		}
		return todo;
	}
}
