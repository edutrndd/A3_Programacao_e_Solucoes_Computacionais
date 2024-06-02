// Adiciona um listener ao formulário com id 'loginForm' que irá executar uma função quando o formulário for submetido.
document.getElementById('loginForm').addEventListener('submit', function(event) {
    // Previna o comportamento padrão do formulário (que seria recarregar a página)
    event.preventDefault();

    // Cria um objeto FormData contendo os dados do formulário submetido
    const formData = new FormData(event.target);

    // Cria um objeto vazio para armazenar os dados do usuário
    const userData = {};

    // Preenche o objeto userData com os dados do formulário
    formData.forEach((value, key) => {
        userData[key] = value;
    });

    // Verifica se as senhas digitadas coincidem
    if (userData.senha !== userData['confirma-senha']) {
        // Se as senhas não coincidirem, exibe um alerta e retorna, interrompendo a execução
        alert('Senhas não coincidem. Tente novamente.');
        return;
    }

    // Faz uma requisição POST para o endpoint '/users' enviando os dados do usuário em formato JSON
    fetch('/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        // Verifica se a resposta da requisição é bem-sucedida
        .then(response => {
            if (response.ok) {
                // Se a resposta for positiva, exibe um alerta de sucesso e direciona para o login
                alert('Usuário criado com sucesso!');
                window.location.href = '../login/index.html';
            } else {
                // Se a resposta não for positiva, exibe um alerta de erro
                alert('Erro ao criar usuário. Por favor, tente novamente.');
            }
        })
        // Caso ocorra um erro na requisição, exibe um alerta de erro
        .catch(error => {
            console.error('Erro ao criar usuário:', error);
            alert('Erro ao criar usuário. Por favor, tente novamente.');
        });

    // Se a checkbox 'remember-me' estiver marcada, armazena os dados do usuário no localStorage
    if (document.getElementById('remember-me').checked) {
        localStorage.setItem('rememberMe', JSON.stringify(userData));
    }
});

// Adiciona um listener que será executado quando o conteúdo da página for carregado
window.addEventListener('DOMContentLoaded', () => {
    // Tenta recuperar os dados do usuário armazenados no localStorage
    const rememberedUserData = JSON.parse(localStorage.getItem('rememberMe'));
    // Se houver dados armazenados, preenche os campos do formulário com esses dados
    if (rememberedUserData) {
        for (const [key, value] of Object.entries(rememberedUserData)) {
            const input = document.querySelector(`[name="${key}"]`);
            if (input) {
                // Preenche o campo de input correspondente com o valor armazenado
                input.value = value;
            }
        }
    }
});
