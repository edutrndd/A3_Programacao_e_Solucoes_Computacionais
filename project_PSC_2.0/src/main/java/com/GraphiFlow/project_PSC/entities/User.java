package com.GraphiFlow.project_PSC.entities;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

@Entity // Anota a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Table(name = "tb_user") // Especifica o nome da tabela no banco de dados que esta entidade representa
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // Anota o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o valor da chave primária será gerado automaticamente pelo banco de dados
    private Long id;
    private String name;
    private Integer idade;
    private String sexo;
    private String email;
    private String cpf;
    private String senha;

    public User() {
    }

    public User(Long id, String name, Integer idade, Character sexo, String email, String cpf, String senha) {
        this.id = id;
        this.name = name;
        this.idade = idade;
        this.sexo = String.valueOf(sexo);
        this.email = email;
        this.cpf = cpf;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override // Sobrescreve o método equals para comparar entidades por valor do campo id
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override // Sobrescreve o método hashCode para gerar um código hash baseado no campo id
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
