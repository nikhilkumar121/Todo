package com.telus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
