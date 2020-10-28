package br.com.programadorNube.todoList.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.programadorNube.todoList.model.Todo;

@RequestScoped
public class TodoDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	/*
	 * inseri um todo e retorna o id criado
	 * @param todo
	 * @return
	 */
	public Long inserir(Todo todo) {
		String nomeSql = "INSERIR_TODO";
//		inserirOuAtualizar(nomeSql, todo);
		todo.persistAndFlush();
		return todo.getId();
	}
	
	@Transactional
	public void atualizar(Todo todo) {
		String nomeSql = "ATUALIZAR_TODO";
		inserirOuAtualizar(nomeSql, todo);
		
	}
	
	private void inserirOuAtualizar(String nomeSql, Todo todo) {
		Query query =em.createNamedQuery(nomeSql);
		query.setParameter("id", todo.getId());
		query.setParameter("nome", todo.getNome());
		query.setParameter("dataCriacao", todo.getDataCriacao());
		
		query.executeUpdate();
	}
	
	public List<Todo> listar(){
		String nomeSql ="CONSULTAR_TODO";
		List<Todo> listaRetorno;
		TypedQuery<Todo> query = em.createNamedQuery(nomeSql, Todo.class);
		try {
			listaRetorno = query.getResultList();
			
		} catch (NoResultException e) {
			listaRetorno = new ArrayList();
		}
		return listaRetorno;
	}
	@Transactional
	public void excluir(Long id) {
		String nomeSql = "EXCLUIR_TODO";
		Query query = em.createNamedQuery(nomeSql);
		
//		query.setParameter("id", id);
//		
//		query.executeUpdate();
		
		Todo.deleteById(id);
	}
	
	public Boolean IsNomeRepetido(String nome) {
		String nomeSql = "CONSULTAR_NOME_REPETIDO_TODO";
		Boolean nomeRepetido = Boolean.FALSE;
		TypedQuery<Todo>  query = em.createNamedQuery(nomeSql, Todo.class);
		
		query.setParameter("nome", nome);
		
		nomeRepetido = query.getResultList().size()>0;
		  
		return nomeRepetido;
	}
	
	public  Todo buscarPorId(Long id) {
		String nomeSql = "CONSULTAR_TODO_ID";
		Todo todo;
		TypedQuery<Todo> query = em.createNamedQuery(nomeSql, Todo.class);
		query.setParameter("id", id);
		try {
			todo = query.getSingleResult();
		} catch (NoResultException e) {
			todo = null;
		}
		return todo;
	}
}
