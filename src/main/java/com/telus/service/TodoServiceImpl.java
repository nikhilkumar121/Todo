package com.telus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Transactional
	@Override
	public List<TodoResponseModel> getAllTodos() {
		List<Todo> todos = Optional.of(todoRepository.findAll()).orElse(new ArrayList<>());
		if (todos.isEmpty()) {
			throw new CustomException(TodoServiceConstants.NO_RECORDS_FOUND);
		}
		return todos.stream().map(todo -> objectMapper.convertValue(todo, TodoResponseModel.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public TodoResponseModel getTodoById(Integer id) {
		Todo todo = todoRepository.findById(id).orElseThrow(
				() -> new CustomException(TodoServiceConstants.TODO_WITH_ID + id + TodoServiceConstants.NOT_FOUND));
		return objectMapper.convertValue(todo, TodoResponseModel.class);
	}

	@Transactional
	@Override
	public TodoResponseModel createTodo(TodoRequestModel todoRequestModel) {
		Todo todo = Todo.builder().description(todoRequestModel.getDescription()).status(todoRequestModel.getStatus())
				.build();
		Todo savedTodo = todoRepository.save(todo);
		return objectMapper.convertValue(savedTodo, TodoResponseModel.class);
	}

	@Transactional
	@Override
	public TodoResponseModel updateTodo(Integer id, String description) {
		Todo todo = todoRepository.findById(id).orElseThrow(
				() -> new CustomException(TodoServiceConstants.TODO_WITH_ID + id + TodoServiceConstants.NOT_FOUND));
		todo.setDescription(description);
		return objectMapper.convertValue(todo, TodoResponseModel.class);
	}

	@Transactional
	@Override
	public Boolean deleteTodo(Integer id) {
		Optional<Todo> todoOptional = todoRepository.findById(id);
		if (todoOptional.isPresent()) {
			todoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
