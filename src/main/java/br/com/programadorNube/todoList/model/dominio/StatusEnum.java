package br.com.programadorNube.todoList.model.dominio;

public enum StatusEnum {
	TODO("Para Executar"),
	DOING("Executando"),
	DONE("Feito"),
	BLOCK("Bloqueado");
		
	private String descricao;
	
	StatusEnum(String descricao){
		this.descricao = descricao;
	}
}
