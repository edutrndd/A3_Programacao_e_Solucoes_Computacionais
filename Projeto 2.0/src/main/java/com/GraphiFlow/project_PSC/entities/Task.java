package com.GraphiFlow.project_PSC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity // Anota a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Table(name = "tb_tasks") // Especifica o nome da tabela no banco de dados que esta entidade representa
public class Task implements Serializable {
    private static final long serialVersionUID = 1L; // Define uma constante para versionamento da classe, usada na serialização

    @Id // Anota o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o valor da chave primária será gerado automaticamente pelo banco de dados
    private Long id;
    private String name;
    private String description;
    private String imgUrl;

    @ManyToMany // há uma relação muitos-para-muitos entre Task e Category.
    @JoinTable(name = "tb_task_category", // Define a tabela intermediária que conecta Task e Category. A tabela se chama tb_task_category.
    joinColumns = @JoinColumn(name = "task_id"), // Especifica que a coluna task_id na tabela intermediária se refere à Task.
    inverseJoinColumns = @JoinColumn(name = "category_id")) // Especifica que a coluna category_id na tabela intermediária se refere à Category.
    private Set<Category> categories = new HashSet<>(); // Cria um conjunto de Category para armazenar as categorias associadas a uma tarefa.

    public Task() {
    }

    public Task(Long id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override // Sobrescreve o método equals para comparar entidades por valor do campo id
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override // Sobrescreve o método hashCode para gerar um código hash baseado no campo id
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
