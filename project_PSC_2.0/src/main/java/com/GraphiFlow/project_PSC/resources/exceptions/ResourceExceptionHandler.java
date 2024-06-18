package com.GraphiFlow.project_PSC.resources.exceptions;

import java.time.Instant;

import com.GraphiFlow.project_PSC.services.exceptions.DatabaseException;
import com.GraphiFlow.project_PSC.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Indica que a classe irá centralizar o tratamento de exceções para todos os controllers da aplicação
public class ResourceExceptionHandler {

    // Método para tratar exceções de resource não encontrado.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        // Mensagem de erro para resource não encontrado.
        String error = "Resource not found";

        // Status HTTP para resource não encontrado.
        HttpStatus status = HttpStatus.NOT_FOUND;

        // Criação do objeto StandardError com informações sobre o erro.
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        // Retorna uma resposta HTTP com o status e o corpo contendo o objeto StandardError.
        return ResponseEntity.status(status).body(err);
    }


    // Método para tratar exceções de erro de banco de dados.
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        // Mensagem de erro, para erro de banco de dados.
        String error = "Database error";

        // Status HTTP para erro de banco de dados.
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Criação do objeto StandardError com informações sobre o erro.
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        // Retorna uma resposta HTTP com o status e o corpo contendo o objeto StandardError.
        return ResponseEntity.status(status).body(err);
    }
}