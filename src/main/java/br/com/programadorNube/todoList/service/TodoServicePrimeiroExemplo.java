package br.com.programadorNube.todoList.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

@RequestScoped //MUITO IMPORTANTE SEM ELE O REST NÃO FUNCIONA
public class TodoServicePrimeiroExemplo {
	private List<String> listaNome = new ArrayList<String>();
	
	public void adicionar(String nome) {
		listaNome.add(nome);
	}
	
	public List<String> listar(){
		return listaNome;
	}
	
}
