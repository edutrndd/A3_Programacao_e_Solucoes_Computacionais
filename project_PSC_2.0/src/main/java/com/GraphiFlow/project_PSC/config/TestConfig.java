package com.GraphiFlow.project_PSC.config;

import com.GraphiFlow.project_PSC.entities.*;
import com.GraphiFlow.project_PSC.entities.enums.ProjectStatus;
import com.GraphiFlow.project_PSC.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

// Configuração da classe como uma configuração do Spring e com perfil "test"
@Configuration
@Profile("test") // Só será ativada quando o perfil "test" estiver ativo.
public class TestConfig implements CommandLineRunner { // implementa a interface CommandLineRunner, o que significa que ela terá um método run que será executado após a inicialização da aplicação.

    // Injetando os repositórios necessários para acessar o banco de dados.
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserAdmRepository userAdmRepository;

    // Método que será executado ao iniciar a aplicação
    @Override // Sobrescrevendo o método 'run' da interface (CommandLineRunner).
    public void run(String... args) throws Exception {

        // Criação das categorias para teste
        Category cat1 = new Category(null, "Área Técnica 01");
        Category cat2 = new Category(null, "Área Técnica 02");
        Category cat3 = new Category(null, "Área Técnica 03");

        // Criação das tarefas para teste
        Task t1 = new Task(null, "Análise de Dados de Vendas Mensais", "Esta tarefa envolve a análise dos dados de vendas mensais para identificar padrões, tendências e insights úteis para a tomada de decisões estratégicas.", "");
        Task t2 = new Task(null, "Desenvolvimento de Conteúdo para Mídias Sociais", "Nesta tarefa, você será responsável por criar conteúdo envolvente e relevante para as mídias sociais da empresa.", "");
        Task t3 = new Task(null, "Teste de Software de Aplicativo Móvel", "Este trabalho envolve a execução de testes de software em um aplicativo móvel para garantir sua funcionalidade, usabilidade e desempenho.", "");
        Task t4 = new Task(null, "Implementação de Estratégia de Marketing Digital", "Nesta tarefa, você será encarregado de implementar uma estratégia de marketing digital para promover produtos ou serviços da empresa.", "");
        Task t5 = new Task(null, "Desenvolvimento de Protótipo de Produto", "Nesta tarefa, você será responsável por criar protótipos de produtos para validar ideias, conceitos ou funcionalidades antes da produção em larga escala.", "");

        // Salvando as categorias no banco de dados
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        // Salvando as tarefas no banco de dados
        taskRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

        // Associando categorias às tarefas
        t1.getCategories().add(cat2);
        t2.getCategories().add(cat1);
        t3.getCategories().add(cat3);
        t4.getCategories().add(cat3);
        t5.getCategories().add(cat2);

        // Salvando as tarefas novamente após associá-las às categorias
        taskRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

        // Criação de usuários para teste
        User u1 = new User(null, "Pedro", 43, 'M', "pedro098@gmail.com", "111.111.111.12", "Senha");
        User u2 = new User(null, "João", 23, 'M', "joão098@gmail.com", "111.111.111.13", "Senha");

        // Criação de um administrador de usuário para teste
        UserAdm uadm1 = new UserAdm(null, "Adm", "adm@gmail.com","Admin");
        UserAdm uadm2 = new UserAdm(null, "Adm2", "Teste@gmail.com", "Senha");

        // Criação de projetos para teste
        Project p1 = new Project(null, Instant.parse("2024-06-20T19:53:07Z"), ProjectStatus.CANCELED, uadm1);
        Project p2 = new Project(null, Instant.parse("2024-07-21T03:42:10Z"), ProjectStatus.WAITING_DELIVERY, uadm1);
        Project p3 = new Project(null, Instant.parse("2024-07-22T15:21:22Z"), ProjectStatus.DELIVERED, uadm1);
        Project p4 = new Project(null, Instant.parse("2024-07-22T15:21:22Z"), ProjectStatus.DELIVERED, uadm1);
        Project p5 = new Project(null, Instant.parse("2024-07-22T15:21:22Z"), ProjectStatus.DELIVERED, uadm1);

        // Salvando os administradors de usuário no banco de dados
        userAdmRepository.saveAll(Arrays.asList(uadm1, uadm2));

        // Salvando os usuários no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));

        // Salvando os projetos no banco de dados
        projectRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }
}
