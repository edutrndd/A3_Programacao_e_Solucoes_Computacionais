// Adiciona um listener que será executado quando o conteúdo da página for completamente carregado.
document.addEventListener('DOMContentLoaded', function() {

    // Seleciona os elementos do DOM: o botão de alternância do menu e o próprio menu.
    const menuToggle = document.getElementById('menu-toggle');
    const menu = document.getElementById('menu');

    // Adiciona um listener ao botão de alternância do menu que será executado quando o botão for clicado.
    menuToggle.addEventListener('click', function() {
        // Verifica o estado atual do menu e alterna entre visível ('flex') e oculto ('none').
        if (menu.style.display === 'none' || menu.style.display === '') {
            menu.style.display = 'flex';
        } else {
            menu.style.display = 'none';
        }
    });

    // Faz uma requisição para obter as tarefas agrupadas por categoria.
    fetch('/tasks/groupedByCategory')
        .then(response => response.json())  // Converte a resposta da requisição para JSON.
        .then(data => {
            // Seleciona o contêiner onde as tarefas serão inseridas.
            const tasksContainer = document.querySelector('.section:nth-child(2) ul');
            tasksContainer.innerHTML = '';  // Limpa o contêiner de tarefas.

            // Itera sobre os dados recebidos, que estão agrupados por categoria.
            for (const [category, tasks] of Object.entries(data)) {
                // Cria e adiciona um cabeçalho para cada categoria.
                const categoryHeader = document.createElement('h3');
                categoryHeader.textContent = category;
                tasksContainer.appendChild(categoryHeader);

                // Cria uma lista para as tarefas da categoria.
                const taskList = document.createElement('ul');

                // Adiciona cada tarefa como um item na lista.
                tasks.forEach(task => {
                    const taskItem = document.createElement('li');
                    taskItem.textContent = task.name;
                    taskList.appendChild(taskItem);
                });

                // Adiciona a lista de tarefas ao contêiner de tarefas.
                tasksContainer.appendChild(taskList);
            }
        })
        // Caso ocorra um erro na requisição, exibe uma mensagem de erro no console.
        .catch(error => console.error('Error fetching task data:', error));

    // Faz uma requisição para obter os dados dos projetos.
    fetch('/projects')
        .then(response => response.json())  // Converte a resposta da requisição para JSON.
        .then(data => {
            // Inicializa um objeto para contar os status dos projetos.
            const statuses = {
                CANCELED: 0,
                WAITING_DELIVERY: 0,
                DELIVERED: 0
            };

            // Itera sobre os dados dos projetos e conta cada status.
            data.forEach(project => {
                statuses[project.projectStatus]++;
            });

            // Seleciona o contexto do canvas onde o gráfico será desenhado.
            const ctx = document.getElementById('taskChart').getContext('2d');
            // Cria um gráfico de pizza usando os dados de status dos projetos.
            const taskChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ['Cancelado', 'Aguardando Entrega', 'Entregue'],
                    datasets: [{
                        data: [statuses.CANCELED, statuses.WAITING_DELIVERY, statuses.DELIVERED],
                        backgroundColor: ['#dc3545', '#ffc107', '#28a745']
                    }]
                },
                options: {
                    responsive: true
                }
            });
        })
        // Caso ocorra um erro na requisição, exibe uma mensagem de erro no console.
        .catch(error => console.error('Error fetching project data:', error));
});
