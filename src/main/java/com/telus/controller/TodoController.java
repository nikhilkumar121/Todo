package com.telus.controller;

import java.util.List;

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

import com.telus.constants.UrlConstants;
import com.telus.model.SuccessResponseModel;
import com.telus.model.TodoRequestModel;
import com.telus.model.TodoResponseModel;
import com.telus.service.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(UrlConstants.TODOS)
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping
	public ResponseEntity<SuccessResponseModel> getAllTodos() {
		List<TodoResponseModel> todoList = todoService.getAllTodos();
		return new ResponseEntity<>(new SuccessResponseModel(todoList != null ? "success" : "something went wrong",
				HttpStatus.OK.value(), todoList), HttpStatus.OK);
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<SuccessResponseModel> getTodoById(@PathVariable("todoId") Integer todoId) {
		TodoResponseModel todoResponseModel = todoService.getTodoById(todoId);
		return new ResponseEntity<>(
				new SuccessResponseModel(todoResponseModel != null ? "Todo Found" : "something went wrong",
						HttpStatus.OK.value(), todoResponseModel),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SuccessResponseModel> createTodo(@Valid @RequestBody TodoRequestModel todoRequestModel) {
		TodoResponseModel savedTodoResponseModel = todoService.createTodo(todoRequestModel);
		return new ResponseEntity<>(
				new SuccessResponseModel(savedTodoResponseModel != null ? "Todo Saved" : "something went wrong",
						HttpStatus.OK.value(), savedTodoResponseModel),
				HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<SuccessResponseModel> updateTodo(@PathVariable("id") Integer id,
			@RequestParam String description) {
		TodoResponseModel updatedTodoResponseModel = todoService.updateTodo(id, description);
		return new ResponseEntity<>(
				new SuccessResponseModel(updatedTodoResponseModel != null ? "Todo Updated" : "something went wrong",
						HttpStatus.OK.value(), updatedTodoResponseModel),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponseModel> deleteTodo(@PathVariable("id") Integer id) {
		Boolean deleted = todoService.deleteTodo(id);
		return new ResponseEntity<>(new SuccessResponseModel(deleted ? "Todo Deleted" : "something went wrong",
				HttpStatus.OK.value(), null), HttpStatus.OK);
	}

}
