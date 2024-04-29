package com.telus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telus.constants.TodoServiceConstants;
import com.telus.entity.Todo;
import com.telus.exception.CustomException;
import com.telus.model.TodoRequestModel;
import com.telus.model.TodoResponseModel;
import com.telus.repository.TodoRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

	// Logger for logging messages
	private static final Logger logger = LogManager.getLogger(TodoServiceImpl.class);

	// Autowired repository for interacting with the database
	@Autowired
	private TodoRepository todoRepository;

	// ObjectMapper for converting entities to DTOs and vice versa
	@Autowired
	private ObjectMapper objectMapper;

	// Method to fetch all todos
	@Transactional
	@Override
	public List<TodoResponseModel> getAllTodos() {
		logger.info("Fetching all todos...");
		// Fetch all todos from the repository
		List<Todo> todos = Optional.of(todoRepository.findAll()).orElse(new ArrayList<>());
		// Check if no todos found, throw exception
		if (todos.isEmpty()) {
			throw new CustomException(TodoServiceConstants.NO_RECORDS_FOUND);
		}
		// Convert todos to response models and return
		return todos.stream().map(todo -> objectMapper.convertValue(todo, TodoResponseModel.class))
				.collect(Collectors.toList());
	}

	// Method to fetch todo by id
	@Transactional
	@Override
	public TodoResponseModel getTodoById(Integer id) {
		logger.info("Fetching todo by id: {}", id);
		// Find todo by id from the repository
		Todo todo = todoRepository.findById(id).orElseThrow(
				() -> new CustomException(TodoServiceConstants.TODO_WITH_ID + id + TodoServiceConstants.NOT_FOUND));
		// Convert todo to response model and return
		return objectMapper.convertValue(todo, TodoResponseModel.class);
	}

	// Method to create a new todo
	@Transactional
	@Override
	public TodoResponseModel createTodo(TodoRequestModel todoRequestModel) {
		logger.info("Creating new Todo Task");
		// Create a new todo entity
		Todo todo = Todo.builder().description(todoRequestModel.getDescription()).status(todoRequestModel.getStatus())
				.build();
		// Save the todo to the repository
		Todo savedTodo = todoRepository.save(todo);
		// Convert saved todo to response model and return
		return objectMapper.convertValue(savedTodo, TodoResponseModel.class);
	}

	// Method to update an existing todo
	@Transactional
	@Override
	public TodoResponseModel updateTodo(Integer id, String description) {
		logger.info("Updating Todo Task");
		// Find todo by id from the repository
		Todo todo = todoRepository.findById(id).orElseThrow(
				() -> new CustomException(TodoServiceConstants.TODO_WITH_ID + id + TodoServiceConstants.NOT_FOUND));
		// Update todo description
		todo.setDescription(description);
		// Convert updated todo to response model and return
		return objectMapper.convertValue(todo, TodoResponseModel.class);
	}

	// Method to delete a todo by id
	@Transactional
	@Override
	public Boolean deleteTodo(Integer id) {
		logger.info("Delete todo by id: {}", id);
		// Check if todo with given id exists
		Optional<Todo> todoOptional = todoRepository.findById(id);
		// If todo exists, delete it from the repository
		if (todoOptional.isPresent()) {
			todoRepository.deleteById(id);
			return true;
		}
		// If todo does not exist, return false
		return false;
	}

}
