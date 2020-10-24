package br.com.programadorNube.todoList.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaDescriptor;

@Entity
@Table(name="todo")
@NamedNativeQueries({
	@NamedNativeQuery(name = "CONSULTAR_TODO", query ="" 
			+ "SELECT id, nome, dataCriacao FROM todo", resultClass = Todo.class),
	@NamedNativeQuery(name = "INSERIR_TODO", query ="" 
			+ "INSERT INTO todo (id, nome, dataCriacao) values "
			+ "(:id, :nome, :dataCriacao)"),
	@NamedNativeQuery(name = "EXCLUIR_TODO", query ="DELETE todo WHERE id=:id"), 
	@NamedNativeQuery(name = "CONSULTAR_NOME_REPETIDO_TODO", query ="" 
			+ "SELECT id, nome, dataCriacao FROM todo where nome = :nome", resultClass = Todo.class),
	@NamedNativeQuery(name = "CONSULTAR_TODO_ID", query ="" 
			+ "SELECT id, nome, dataCriacao FROM todo where id = :id", resultClass = Todo.class),
})
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 250, nullable = false)
	private String nome;
	
	@Column(name = "dataCriacao", nullable = false, updatable = false)
	private LocalDateTime dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
