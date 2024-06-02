package com.GraphiFlow.project_PSC.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity // Anota a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Table(name = "tb_user_adm") // Especifica o nome da tabela no banco de dados que esta entidade representa
public class UserAdm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // Anota o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o valor da chave primária será gerado automaticamente pelo banco de dados
    private Long id;
    private String name;
    private String email;
    private String senha;

    @JsonIgnore // Indica que este campo deve ser ignorado na serialização JSON
    @OneToMany(mappedBy = "client") // Mapeia a relação um-para-muitos com a entidade Project, com mapeamento pelo campo "client"
    private List<Project> projects = new ArrayList<>(); // Declara uma lista de projetos associados a este cliente, inicializada como uma lista vazia.

    public UserAdm() {
    }

    public UserAdm(Long id, String name, String email, String senha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Project> getProjects() {
        return projects;
    }

    @Override // Sobrescreve o método equals para comparar entidades por valor do campo id
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserAdm userAdm = (UserAdm) o;
        return Objects.equals(id, userAdm.id);
    }

    @Override // Sobrescreve o método hashCode para gerar um código hash baseado no campo id
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
