package com.telus.exception;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.telus.model.ErrorObjectModel;
import com.telus.model.SuccessResponseModel;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
	private final static int CUSTOM_ERROR_CODE = 400;
	private final static String CUSTOM_ERROR_OCCURED = "Something went wrong";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(Throwable ex) {
		log.info("********     EXCEPTION OCCURED       *************************  ::  {}", ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(DataAccessException ex) {
		log.info("********    DATA ACCESS EXCEPTION OCCURED       *************************  ::  {}", ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(DuplicateKeyException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataRetrievalFailureException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(DataRetrievalFailureException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(EmptyResultDataAccessException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(DataIntegrityViolationException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	/*** Validation Exception Handler ***/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(MethodArgumentNotValidException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(MethodArgumentTypeMismatchException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	/*** Invalid IP Address or Hostname Exception Handler ***/
	@ExceptionHandler(UnknownHostException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(UnknownHostException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(SQLException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

	/* CustomException Exception Hnadler ***/
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<SuccessResponseModel> myCustomExceptionMessage(CustomException ex) {
		log.info("********    DUPLICATE KEY EXCEPTION OCCURED       *************************  ::  {}",
				ex.getMessage());
		ErrorObjectModel errorObject = new ErrorObjectModel(CUSTOM_ERROR_OCCURED, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(new SuccessResponseModel(CUSTOM_ERROR_OCCURED, CUSTOM_ERROR_CODE, errorObject),
				HttpStatus.BAD_REQUEST);
	}

}
