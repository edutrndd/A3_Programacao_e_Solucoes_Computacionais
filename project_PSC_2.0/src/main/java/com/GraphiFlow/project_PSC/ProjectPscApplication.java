package com.GraphiFlow.project_PSC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// Classe principal que inicia a aplicação Spring Boot
@SpringBootApplication
public class ProjectPscApplication {

	private static ConfigurableApplicationContext context;

	// Método principal que inicia a aplicação
	public static void main(String[] args) {
		// Configurar a propriedade headless para false
		System.setProperty("java.awt.headless", "false");

		// Inicia o contexto do Spring Boot
		context = SpringApplication.run(ProjectPscApplication.class, args);

		// Inicia a aplicação Swing
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new com.GraphiFlow.project_PSC.CadastroForm(context);
			}
		});
	}

	// Método para obter o contexto do Spring
	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
