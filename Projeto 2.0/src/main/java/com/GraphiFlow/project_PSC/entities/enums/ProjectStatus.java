package com.GraphiFlow.project_PSC.entities.enums;

public enum ProjectStatus {

    // Definição dos valores da enumeração ProjectStatus e seus códigos associados
    WAITING_DELIVERY(1),  // Estado: Esperando entrega, código: 1
    DELIVERED(2),         // Estado: Entregue, código: 2
    CANCELED(3);          // Estado: Cancelado, código: 3

    // Declaração do campo privado que armazena o código associado a cada constante
    private int code;

    // Construtor da enumeração ProjectStatus, recebe um código e atribui ao campo 'code'
    private ProjectStatus(int code) {
        this.code = code;
    }

    // Método público para obter o código associado a cada constante
    public int getCode() {
        return code;
    }

    // Método estático para obter a constante da enumeração associada a um código
    public static ProjectStatus valueOf(int code) {
        for (ProjectStatus value : ProjectStatus.values()) { // Percorre todas as constantes da enumeração
            if (value.getCode() == code) { // Compara o código de cada constante com o código passado como argumento
                return value; // Retorna a constante correspondente se encontrada
            }
        }
        // Lança uma exceção se nenhum código correspondente for encontrado
        throw new IllegalArgumentException("Código inválido" + code);
    }
}
