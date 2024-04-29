package com.telus.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telus.constants.TodoServiceConstants;
import com.telus.constants.UrlConstants;
import com.telus.model.SuccessResponseModel;
import com.telus.model.TodoRequestModel;
import com.telus.model.TodoResponseModel;
import com.telus.service.TodoService;
import com.telus.service.TodoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(UrlConstants.TODOS)
public class TodoController {

	// Logger for logging messages
	private static final Logger logger = LogManager.getLogger(TodoServiceImpl.class);

	// Autowired service for interacting with the business logic
	@Autowired
	private TodoService todoService;

	/**
	 * Endpoint to fetch all todos.
	 * 
	 * @return ResponseEntity containing the list of todos and success message
	 */
	@GetMapping
	public ResponseEntity<SuccessResponseModel> getAllTodos() {
		logger.debug("Request received to fetch all todos.");
		List<TodoResponseModel> todoList = todoService.getAllTodos();
		return new ResponseEntity<>(new SuccessResponseModel(todoList != null ? TodoServiceConstants.SUCCESS : TodoServiceConstants.SOMETHING_WENT_WRONG,
				HttpStatus.OK.value(), todoList), HttpStatus.OK);
	}

	/**
	 * Endpoint to fetch todo by id.
	 * 
	 * @param todoId The id of the todo to fetch
	 * @return ResponseEntity containing the fetched todo and success message
	 */
	@GetMapping("/{todoId}")
	public ResponseEntity<SuccessResponseModel> getTodoById(@PathVariable("todoId") Integer todoId) {
		logger.debug("Request received to fetch todo by id: {}", todoId);
		TodoResponseModel todoResponseModel = todoService.getTodoById(todoId);
		return new ResponseEntity<>(
				new SuccessResponseModel(todoResponseModel != null ? TodoServiceConstants.TODO_FOUND : TodoServiceConstants.SOMETHING_WENT_WRONG,
						HttpStatus.OK.value(), todoResponseModel),
				HttpStatus.OK);
	}

	/**
	 * Endpoint to create a new todo.
	 * 
	 * @param todoRequestModel The request model containing todo details
	 * @return ResponseEntity containing the created todo and success message
	 */
	@PostMapping
	public ResponseEntity<SuccessResponseModel> createTodo(@Valid @RequestBody TodoRequestModel todoRequestModel) {
		logger.debug("Request received to create a new todo.");
		TodoResponseModel savedTodoResponseModel = todoService.createTodo(todoRequestModel);
		return new ResponseEntity<>(
				new SuccessResponseModel(savedTodoResponseModel != null ? TodoServiceConstants.TODO_SAVED : TodoServiceConstants.SOMETHING_WENT_WRONG,
						HttpStatus.OK.value(), savedTodoResponseModel),
				HttpStatus.OK);
	}

	/**
	 * Endpoint to update an existing todo.
	 * 
	 * @param id          The id of the todo to update
	 * @param description The new description for the todo
	 * @return ResponseEntity containing the updated todo and success message
	 */
	@PatchMapping("/{id}")
	public ResponseEntity<SuccessResponseModel> updateTodo(@PathVariable("id") Integer id,
			@RequestParam String description) {
		logger.debug("Request received to update todo with id: {} and description: {}", id, description);
		TodoResponseModel updatedTodoResponseModel = todoService.updateTodo(id, description);
		return new ResponseEntity<>(
				new SuccessResponseModel(updatedTodoResponseModel != null ? TodoServiceConstants.TODO_UPDATED :TodoServiceConstants.SOMETHING_WENT_WRONG,
						HttpStatus.OK.value(), updatedTodoResponseModel),
				HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a todo by id.
	 * 
	 * @param id The id of the todo to delete
	 * @return ResponseEntity containing the success message
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponseModel> deleteTodo(@PathVariable("id") Integer id) {
		logger.debug("Request received to delete todo with id: {}", id);
		Boolean deleted = todoService.deleteTodo(id);
		return new ResponseEntity<>(new SuccessResponseModel(deleted ?TodoServiceConstants.TODO_DELETED : TodoServiceConstants.SOMETHING_WENT_WRONG,
				HttpStatus.OK.value(), null), HttpStatus.OK);
	}

}
