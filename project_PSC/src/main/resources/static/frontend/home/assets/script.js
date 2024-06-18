document.addEventListener('DOMContentLoaded', function() {
    fetchAndUpdateData();

    function fetchAndUpdateData() {
        fetch('http://127.0.0.1:8080/tasks/groupedByCategory')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao obter tarefas');
                }
                return response.json();
            })
            .then(data => {
                console.log('Data received:', data);
                updateTasks(data);
            })
            .catch(error => {
                console.error('Erro ao obter tarefas:', error);
                alert('Erro ao obter tarefas. Por favor, tente novamente.');
            });

        fetch('http://127.0.0.1:8080/projects')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao obter projetos');
                }
                return response.json();
            })
            .then(data => {
                updateChart(data);
            })
            .catch(error => {
                console.error('Erro ao obter projetos:', error);
                alert('Erro ao obter projetos. Por favor, tente novamente.');
            });
    }

    function updateTasks(tasksData) {
        const tasksContainer = document.querySelector('.section:nth-child(2) ul');
        tasksContainer.innerHTML = '';

        // Itera sobre as chaves do objeto de tasksData (categorias)
        Object.keys(tasksData).forEach(category => {
            // Cria um elemento de lista para a categoria
            const categoryItem = document.createElement('li');
            categoryItem.textContent = category;
            categoryItem.classList.add('category-item');
            tasksContainer.appendChild(categoryItem);

            // Itera sobre cada tarefa da categoria
            tasksData[category].forEach(task => {
                const taskItem = document.createElement('li');
                taskItem.textContent = `${task.name} - ${category}`;
                taskItem.classList.add('task-item');
                tasksContainer.appendChild(taskItem);
            });
        });
    }

    function updateChart(projects) {
        const statuses = {
            CANCELED: 0,
            WAITING_DELIVERY: 0,
            DELIVERED: 0
        };

        projects.forEach(project => {
            statuses[project.projectStatus]++;
        });

        const ctx = document.getElementById('taskChart').getContext('2d');
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
    }
});
