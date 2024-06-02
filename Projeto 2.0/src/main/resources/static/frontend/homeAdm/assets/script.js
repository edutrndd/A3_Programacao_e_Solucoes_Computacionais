document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.getElementById('menu-toggle');
    const menu = document.getElementById('menu');

    const projectButton = document.getElementById('project-button');
    const projectFormModal = document.getElementById('project-form-modal');
    const projectCloseModal = document.querySelector('#project-form-modal .close');
    const projectForm = document.getElementById('project-form');

    const editButton = document.getElementById('edit-button');
    const editFormModal = document.getElementById('edit-form-modal');
    const editCloseModal = document.querySelector('#edit-form-modal .close');
    const editForm = document.getElementById('edit-form');

    const collaboratorButton = document.getElementById('collaborator-button');
    const collaboratorFormModal = document.getElementById('collaborator-form-modal');
    const collaboratorCloseModal = document.querySelector('#collaborator-form-modal .close');
    const collaboratorForm = document.getElementById('collaborator-form');

    const settingsButton = document.getElementById('settings-button');
    const settingsFormModal = document.getElementById('settings-form-modal');
    const settingsCloseModal = document.querySelector('#settings-form-modal .close');
    const settingsForm = document.getElementById('settings-form');

    // Função para abrir o modal
    function openModal(modal) {
        modal.style.display = 'block';
    }

    // Função para fechar o modal
    function closeModal(modal) {
        modal.style.display = 'none';
    }

    // Alternar visibilidade do menu
    menuToggle.addEventListener('click', function() {
        menu.style.display = (menu.style.display === 'none' || menu.style.display === '') ? 'flex' : 'none';
    });

    // Evento para abrir o modal do projeto
    projectButton.addEventListener('click', function() {
        openModal(projectFormModal);
        loadCategories();
    });

    // Evento para fechar o modal do projeto
    projectCloseModal.addEventListener('click', function() {
        closeModal(projectFormModal);
    });

    // Evento para abrir o modal de editar tarefas
    editButton.addEventListener('click', function() {
        openModal(editFormModal);
    });

    // Evento para fechar o modal de editar tarefas
    editCloseModal.addEventListener('click', function() {
        closeModal(editFormModal);
    });

    // Evento para abrir o modal de colaboradores
    collaboratorButton.addEventListener('click', function() {
        openModal(collaboratorFormModal);
    });

    // Evento para fechar o modal de colaboradores
    collaboratorCloseModal.addEventListener('click', function() {
        closeModal(collaboratorFormModal);
    });

    // Evento para abrir o modal de configurações de perfil
    settingsButton.addEventListener('click', function() {
        openModal(settingsFormModal);
    });

    // Evento para fechar o modal de configurações de perfil
    settingsCloseModal.addEventListener('click', function() {
        closeModal(settingsFormModal);
    });

    // Fechar modais ao clicar fora deles
    window.addEventListener('click', function(event) {
        if (event.target === projectFormModal) {
            closeModal(projectFormModal);
        } else if (event.target === editFormModal) {
            closeModal(editFormModal);
        } else if (event.target === collaboratorFormModal) {
            closeModal(collaboratorFormModal);
        } else if (event.target === settingsFormModal) {
            closeModal(settingsFormModal);
        }
    });

    // Eventos de envio dos formulários
    projectForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const taskName = document.getElementById('taskName').value;
        const taskDescription = document.getElementById('taskDescription').value;
        const taskUrlImg = document.getElementById('taskUrlImg').value;
        const categoryId = document.getElementById('categorySelect').value;

        const task = {
            taskName: taskName,
            taskDescription: taskDescription,
            taskUrlImg: taskUrlImg,
            categoryId: categoryId
        };

        fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(task)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Task created:', data);
                createProject(taskName, taskDescription, taskUrlImg);
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('Erro ao criar a tarefa. Por favor, tente novamente.');
            });
    });

    editForm.addEventListener('submit', function(event) {
        event.preventDefault();
        // Adicionar lógica de envio do formulário de editar tarefas
        closeModal(editFormModal);
    });

    collaboratorForm.addEventListener('submit', function(event) {
        event.preventDefault();
        // Adicionar lógica de envio do formulário de colaboradores
        closeModal(collaboratorFormModal);
    });

    settingsForm.addEventListener('submit', function(event) {
        event.preventDefault();
        // Adicionar lógica de envio do formulário de configurações de perfil
        closeModal(settingsFormModal);
    });

    function createProject(taskName, taskDescription, taskUrlImg) {
        const params = new URLSearchParams({
            taskName: taskName,
            taskDescription: taskDescription,
            taskUrlImg: taskUrlImg
        });

        fetch(`/projects?${params.toString()}`, {
            method: 'POST'
        })
            .then(response => response.json())
            .then(data => {
                console.log('Project created:', data);
                projectFormModal.style.display = 'none';
                projectForm.reset();
                alert('Projeto e tarefa criados com sucesso');
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('Erro ao criar o projeto. Por favor, tente novamente.');
            });
    }

    function loadCategories() {
        fetch('/categories')
            .then(response => response.json())
            .then(categories => {
                const categorySelect = document.getElementById('categorySelect');
                categorySelect.innerHTML = '';
                categories.forEach(category => {
                    const option = document.createElement('option');
                    option.value = category.id;
                    option.textContent = category.name;
                    categorySelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error fetching categories:', error));
    }

    fetch('/tasks/groupedByCategory')
        .then(response => response.json())
        .then(data => {
            const tasksContainer = document.querySelector('.section:nth-child(2) ul');
            tasksContainer.innerHTML = '';

            for (const [category, tasks] of Object.entries(data)) {
                const categoryHeader = document.createElement('h3');
                categoryHeader.textContent = category;
                tasksContainer.appendChild(categoryHeader);

                const taskList = document.createElement('ul');
                tasks.forEach(task => {
                    const taskItem = document.createElement('li');
                    taskItem.textContent = task.name;
                    taskList.appendChild(taskItem);
                });

                tasksContainer.appendChild(taskList);
            }
        })
        .catch(error => console.error('Error fetching task data:', error));

    fetch('/projects')
        .then(response => response.json())
        .then(data => {
            const statuses = {
                CANCELED: 0,
                WAITING_DELIVERY: 0,
                DELIVERED: 0
            };

            data.forEach(project => {
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
        })
        .catch(error => console.error('Error fetching project data:', error));
});
