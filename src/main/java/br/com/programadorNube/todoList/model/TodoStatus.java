package br.com.programadorNube.todoList.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import br.com.programadorNube.todoList.model.dominio.StatusEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "todostatus")
public class TodoStatus extends PanacheEntity{
	
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	
	public TodoStatus(StatusEnum statusEnum){
		this.status = statusEnum;
	}
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "todo_id", updatable = false)
	private Todo todo;
	
	TodoStatus(){
		super();
	} 
	
	@Column(name = "data")
	@UpdateTimestamp
	private LocalDateTime data;

		public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	@Override
	public String toString() {
		//TODO
		return status.name();
		
	}
	
	
	
}
