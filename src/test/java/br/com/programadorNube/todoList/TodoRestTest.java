package br.com.programadorNube.todoList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoRestTest{
	@Test
	@Order(2)
	public void testListarTodoVazio() {
		given()
		.auth()
		.preemptive()
		.basic("teste@email.com", "teste")
		.when().get("/api/todo")
		.then()
		.statusCode(200)
		.body(is("[]"));
	}
	
	@Test
	@Order(1)
	public void testIncluirTodoComSucesso() {
		String body = "{\r\n" +
				"  \"nome\": \"string\"\r\n" +  
				"}";
		
		given()
		.auth()
		.preemptive()
		.basic("teste@email.com", "teste")
		.contentType(ContentType.JSON)
		.body(body)
		.when()
		.post("/api/todo")
		.then()
		.statusCode(201);
	}
}
