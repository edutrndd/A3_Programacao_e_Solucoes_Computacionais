package com.GraphiFlow.project_PSC.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity // Anota a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Table(name = "tb_category") // Especifica o nome da tabela no banco de dados que esta entidade representa
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;  // Define uma constante para versionamento da classe, usada na serialização

    @Id // Anota o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o valor da chave primária será gerado automaticamente pelo banco de dados
    private Long id;
    private String name;

    @JsonIgnore // Anota o campo para ser ignorado durante a serialização JSON
    @ManyToMany(mappedBy = "categories") // Define uma relação muitos-para-muitos com a entidade Task. O mapeamento é feito pelo atributo "categories" na entidade Task
    private Set<Task> tasks = new HashSet<>();

    public Category() {
    }

    public Category(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    @Override // Sobrescreve o método equals para comparar entidades por valor do campo id
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override // Sobrescreve o método hashCode para gerar um código hash baseado no campo id
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
