package br.com.programadorNube.todoList.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.programadorNube.todoList.dao.UserDao;
import br.com.programadorNube.todoList.model.User;

@RequestScoped
public class UserService {
	@Inject
	UserDao dao;
	
	public User buscarUsuarioPorEmail(String email) {
		return dao.buscarUsuarioPorEmail(email);
	}
}
