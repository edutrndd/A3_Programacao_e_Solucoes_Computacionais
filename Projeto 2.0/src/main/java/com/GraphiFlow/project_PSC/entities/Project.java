package com.GraphiFlow.project_PSC.entities;

import com.GraphiFlow.project_PSC.entities.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity // Anota a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Table(name = "tb_project") // Especifica o nome da tabela no banco de dados que esta entidade representa
public class Project implements Serializable {
    private static final long serialVersionUID = 1L; // Define uma constante para versionamento da classe, usada na serialização

    @Id // Anota o campo como chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o valor da chave primária será gerado automaticamente pelo banco de dados
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")  // O fuso horário GMT ao ser serializado para JSON.
    private Instant moment; // Formata o campo 'moment' para ser representado como uma string no formato ISO 8601

    private Integer projectStatus; // Armazena o status do projeto como um valor inteiro que representa um código do enum 'ProjectStatus'

    @ManyToOne // Define uma relação muitos-para-um com a entidade 'UserAdm'
    @JoinColumn(name = "client_id") // mapeando a coluna 'client_id' na tabela 'tb_project' como a chave estrangeira.
    private UserAdm client;

    public Project() {
    }

    public Project(Long id, Instant moment, ProjectStatus projectStatus,UserAdm client) {
        super();
        this.id = id;
        this.moment = moment;
        setProjectStatus(projectStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public ProjectStatus getProjectStatus() {
        return ProjectStatus.valueOf(projectStatus);
    }

    public void setProjectStatus(ProjectStatus projectStatus) { // Inclui lógica para conversão do enum 'ProjectStatus' para seu código inteiro correspondente.
        if (projectStatus != null) {
            this.projectStatus = projectStatus.getCode();
        }
    }

    public UserAdm getClient() {
        return client;
    }

    public void setClient(UserAdm client) {
        this.client = client;
    }

    @Override // Sobrescreve o método equals para comparar entidades por valor do campo id
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override // Sobrescreve o método hashCode para gerar um código hash baseado no campo id
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
