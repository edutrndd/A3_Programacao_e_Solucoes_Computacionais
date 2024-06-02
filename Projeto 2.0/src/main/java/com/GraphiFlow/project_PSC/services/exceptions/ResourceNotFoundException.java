package com.GraphiFlow.project_PSC.services.exceptions;

// Exceção personalizada para indicar que um recurso não foi encontrado
public class ResourceNotFoundException extends RuntimeException {

    // Número de série para garantir compatibilidade de serialização
    private static final long serialVersionUID = 1L;

    // Construtor que recebe o ID do recurso não encontrado
    public ResourceNotFoundException(Object id) {
        // Chama o construtor da classe pai (RuntimeException) com uma mensagem personalizada
        super("Resource not found. Id " + id);
    }
}