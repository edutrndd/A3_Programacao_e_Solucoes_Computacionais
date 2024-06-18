document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const userData = {};

    formData.forEach((value, key) => {
        userData[key] = value;
    });

    if (userData.senha !== userData['confirma-senha']) {
        alert('Senhas não coincidem. Tente novamente.');
        return;
    }

    fetch('http://127.0.0.1:8080/users', {  // URL absoluta
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => response.json().then(data => ({ status: response.status, body: data })))
        .then(({ status, body }) => {
            if (status === 201) { // 201 Created
                alert('Usuário criado com sucesso!');
                window.location.href = '../login/index.html';
            } else {
                alert(`Erro ao criar usuário: ${body.message || 'Por favor, tente novamente.'}`);
            }
        })
        .catch(error => {
            console.error('Erro ao criar usuário:', error);
            alert('Erro ao criar usuário. Por favor, tente novamente.');
        });

    if (document.getElementById('remember-me').checked) {
        localStorage.setItem('rememberMe', JSON.stringify(userData));
    }
});

window.addEventListener('DOMContentLoaded', () => {
    const rememberedUserData = JSON.parse(localStorage.getItem('rememberMe'));
    if (rememberedUserData) {
        for (const [key, value] of Object.entries(rememberedUserData)) {
            const input = document.querySelector(`[name="${key}"]`);
            if (input) {
                input.value = value;
            }
        }
    }
});
