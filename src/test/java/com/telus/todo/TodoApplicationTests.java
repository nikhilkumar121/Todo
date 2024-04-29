package com.telus.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telus.entity.Todo;
import com.telus.model.TodoRequestModel;
import com.telus.repository.TodoRepository;

@SpringBootTest
class TodoApplicationTests {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	private static final int TEST_TODO_ID = 1;

//	@Test
//	void contextLoads() {
//	}

	@BeforeEach
    void setUp() {
        // Clean up the database before each test
        todoRepository.deleteById(TEST_TODO_ID);
    }

    @AfterEach
    void tearDown() {
        // Clean up the database after each test
        todoRepository.deleteById(TEST_TODO_ID);
    }

    
	// Create Todo Task
	@Test
	public void createTodo() {
		TodoRequestModel todoRequestModel = new TodoRequestModel();
		todoRequestModel.setId(TEST_TODO_ID);
		todoRequestModel.setDescription("Take Medicine at 4PM");
		todoRequestModel.setStatus("Pending");
		todoRepository.save(objectMapper.convertValue(todoRequestModel, Todo.class));
		assertNotNull(todoRepository.findById(TEST_TODO_ID).get());
	}

	// update Todo Task
	@Test
	public void updateTodo() {
		// Create a new Todo
		TodoRequestModel todoRequestModel = new TodoRequestModel();
		todoRequestModel.setId(TEST_TODO_ID);
		todoRequestModel.setDescription("Take Medicine at 4PM");
		todoRequestModel.setStatus("Pending");
		todoRepository.save(objectMapper.convertValue(todoRequestModel, Todo.class));

		// Retrieve the Todo from the database
		Todo todoToUpdate = todoRepository.findById(TEST_TODO_ID).get();

		// Update the Todo
		todoToUpdate.setDescription("Take Medicine at 5PM");
		todoRepository.save(todoToUpdate);

		// Retrieve the updated Todo
		Todo updatedTodo = todoRepository.findById(TEST_TODO_ID).get();

		// Assert that the description has been updated
		assertEquals("Take Medicine at 5PM", updatedTodo.getDescription());
	}

	// get All Todo List
	@Test
	public void getAllTodos() {
		List<Todo> todoList = todoRepository.findAll();
		assertThat(todoList).size().isGreaterThan(0);
	}

	// get Todo By Id
	@Test
	public void getTodoById() {
		Todo todo = todoRepository.findById(TEST_TODO_ID).get();
		assertEquals("Take Medicine at 4PM", todo.getDescription());
	}

	// Delete Tdod Task
	@Test
	public void deleteTodo() {
		todoRepository.deleteById(TEST_TODO_ID);
		assertThat(todoRepository.existsById(TEST_TODO_ID)).isFalse();
	}
}
