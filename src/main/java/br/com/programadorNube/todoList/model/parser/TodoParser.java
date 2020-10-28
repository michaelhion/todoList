package br.com.programadorNube.todoList.model.parser;

import br.com.programadorNube.todoList.dto.TodoDto;
import br.com.programadorNube.todoList.model.Todo;

public class TodoParser {

	public static TodoParser get() {
		return new TodoParser();
	}
	public Todo entidade(TodoDto dto) {
		Todo entidade = new Todo();
		
		entidade.setId(dto.getId());
		entidade.setNome(dto.getNome());
		entidade.setDataCriacao(dto.getDataCriacao());
		
		return entidade;
	}
	
	public TodoDto dto(Todo entidade) {
		
		TodoDto dto = new TodoDto();
		dto.setId(entidade.getId());
		dto.setNome(entidade.getNome());
		dto.setDataCriacao(entidade.getDataCriacao());
		
		dto.setStatus(entidade.getStatus().get(0).toString());
		
		return dto;
	}
}
