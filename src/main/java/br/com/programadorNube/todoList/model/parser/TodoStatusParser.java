package br.com.programadorNube.todoList.model.parser;

import br.com.programadorNube.todoList.dto.TodoStatusDto;
import br.com.programadorNube.todoList.model.TodoStatus;

public class TodoStatusParser {

	public static TodoStatusParser get() {
		return new TodoStatusParser();
	}
	
	
	public TodoStatusDto dto(TodoStatus entidade) {
		TodoStatusDto dto = new TodoStatusDto();
		dto.setId(entidade.getId());
		dto.setData(entidade.getData());
		dto.setStatus(entidade.getStatus().name());
		dto.setNomeUsuario(entidade.getUser().getNome());
		return dto;
	}
	
	//metodo que retorna uma entidade com base no dto
	
}
