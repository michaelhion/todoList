package br.com.programadorNube.todoList.dto;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

public class TodoStatusDto {
	
	private Long id;
	private String status;
	
	private String nomeUsuario;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
	
}
