package com.telus.service;

import java.util.List;

import com.telus.model.TodoRequestModel;
import com.telus.model.TodoResponseModel;

public interface TodoService {

	public List<TodoResponseModel> getAllTodos();

	public TodoResponseModel getTodoById(Integer id);

	public TodoResponseModel createTodo(TodoRequestModel todoRequestModel);

	public TodoResponseModel updateTodo(Integer id, String description);

	public Boolean deleteTodo(Integer id);

}
