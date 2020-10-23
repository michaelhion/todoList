package br.com.programadorNube.todoList.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TodoDto  implements Serializable{

	private Long id;
	
	private String nome;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull(message = "Nome é obrigatório")
	@NotBlank(message = "Não é permito nome vazio")
	@Length(min = 3, max = 250, message = "Não é permido nomes "
			+ "menores que 3 caracteres ou maiores que 250")
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
