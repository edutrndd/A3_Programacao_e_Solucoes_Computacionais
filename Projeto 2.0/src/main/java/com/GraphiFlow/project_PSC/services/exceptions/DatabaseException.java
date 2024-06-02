package com.GraphiFlow.project_PSC.services.exceptions;

// Exceção personalizada para operações de banco de dados
public class DatabaseException extends RuntimeException {

    // Número de série para garantir compatibilidade de serialização
    private static final long serialVersionUID = 1L;

    // Construtor que recebe uma mensagem de erro
    public DatabaseException(String msg) {
        // Chama o construtor da superclasse (RuntimeException) com a mensagem fornecida
        super(msg);
    }
}