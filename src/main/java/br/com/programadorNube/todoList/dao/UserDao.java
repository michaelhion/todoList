package br.com.programadorNube.todoList.dao;

import javax.enterprise.context.RequestScoped;

import br.com.programadorNube.todoList.model.User;
@RequestScoped
public class UserDao {
	
	public User buscarUsuarioPorEmail(String email) {
		return User.find("email", email).firstResult();
		
	
	}
	
}
