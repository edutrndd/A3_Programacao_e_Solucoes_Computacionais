document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const loginData = {};

    formData.forEach((value, key) => {
        loginData[key] = value;
    });

    const loginUrls = [
        'http://8080/auth/login',
        'http://localhost:8080/auth/login'
    ];

    function tryLogin(urlIndex) {
        if (urlIndex >= loginUrls.length) {
            console.error('Erro ao realizar login: Todos os endpoints falharam.');
            alert('Erro ao realizar login. Por favor, tente novamente.');
            return;
        }

        fetch(loginUrls[urlIndex], {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
            .then(response => response.json().then(data => ({ status: response.status, body: data })))
            .then(({ status, body }) => {
                if (status === 200 && body.success) {
                    alert('Login bem sucedido!');
                    if (body.userType === 'USER') {
                        window.location.href = '../home/index.html';
                    } else if (body.userType === 'ADMIN') {
                        window.location.href = '../homeAdm/index.html';
                    }
                } else {
                    alert(body.message || 'Erro ao realizar login. Por favor, tente novamente.');
                }
            })
            .catch(error => {
                console.error('Erro ao realizar login:', error);
                // Tenta o próximo URL
                tryLogin(urlIndex + 1);
            });
    }

    tryLogin(0); // Inicia a tentativa de login com o primeiro URL
});
