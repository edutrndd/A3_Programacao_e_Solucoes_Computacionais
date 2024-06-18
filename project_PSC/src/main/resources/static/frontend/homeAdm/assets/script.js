document.addEventListener('DOMContentLoaded', function() {
    window.electron.receive('fetch-data', (data) => {
        console.log('Data received:', data);
        updateTasks(data.tasks);
        updateChart(data.projects);
        loadCategories(data.categories);
    });

    window.electron.sendData('fetch-data');

    const projectButton = document.getElementById('project-button');
    const projectFormModal = document.getElementById('project-form-modal');
    const closeModal = document.querySelector('.project-form-content .close');
    const projectForm = document.getElementById('project-form');

    projectButton.addEventListener('click', function() {
        projectFormModal.style.display = 'block';
    });

    closeModal.addEventListener('click', function() {
        projectFormModal.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
        if (event.target === projectFormModal) {
            projectFormModal.style.display = 'none';
        }
    });

    projectForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const taskName = document.getElementById('taskName').value;
        const taskDescription = document.getElementById('taskDescription').value;
        const taskUrlImg = document.getElementById('taskUrlImg').value;
        const categoryId = document.getElementById('categorySelect').value;

        // Construindo o objeto task
        const task = {
            taskName: taskName,
            taskDescription: taskDescription,
            taskUrlImg: taskUrlImg,
            categoryId: categoryId
        };

        console.log('Objeto task antes de enviar:', task); // Debugging

        // Adiciona uma verificação para garantir que categoryId é um número válido
        if (isNaN(categoryId) || categoryId === '') {
            alert('Categoria inválida.');
            return;
        }

        fetch('http://127.0.0.1:8080/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(task)
        })
            .then(response => {
                console.log('Resposta recebida do servidor:', response);
                return response.text(); // Alterado para text() para logar a resposta bruta
            })
            .then(text => {
                console.log('Resposta bruta:', text); // Log da resposta bruta
                const data = JSON.parse(text); // Tenta fazer o parse manualmente
                console.log('Task and Project created:', data);
                fetchDataAndUpdate();
                projectFormModal.style.display = 'none';
                alert('Tarefa criada com sucesso!');
            })
            .catch((error) => {
                console.error('Erro ao enviar a requisição:', error);
                alert('Erro ao criar a tarefa e o projeto. Por favor, tente novamente.');
            });
    });

    function fetchDataAndUpdate() {
        window.electron.sendData('fetch-data');
    }

    function loadCategories(categories) {
        const categorySelect = document.getElementById('categorySelect');
        categorySelect.innerHTML = '';

        // Verifica se há categories
        if (!categories) {
            console.error('No categories data available');
            return;
        }

        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.id;
            option.textContent = category.name;
            categorySelect.appendChild(option);
        });
    }

    function updateTasks(tasksData) {
        const tasksContainer = document.querySelector('.section:nth-child(2) ul');
        tasksContainer.innerHTML = '';

        if (!tasksData) {
            console.error('No tasks data available');
            return;
        }

        // Itera sobre as chaves do objeto de tasksData (categorias)
        Object.keys(tasksData).forEach(category => {
            // Cria um elemento de lista para a categoria
            const categoryItem = document.createElement('li');
            categoryItem.textContent = category;
            categoryItem.classList.add('category-item');
            categoryItem.style.fontSize = '20px';
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
        if (!projects) {
            console.error('No projects data available');
            return;
        }

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
