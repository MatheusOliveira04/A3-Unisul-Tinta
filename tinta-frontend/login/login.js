// ====================== CONFIGURAÇÃO DA API ======================
const API_BASE_URL = 'http://localhost:8080';

// ====================== INICIALIZAÇÃO E EVENTOS ======================
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");
    if (form) {
        form.addEventListener("submit", efetuarLogin);
    }
});

// ====================== MÉTODO: EFETUAR LOGIN ======================
// Autentica o usuário no endpoint /auth/token e armazena o token nos cookies
async function efetuarLogin(event) {
    // Impede o recarregamento automático da página
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value;

    // Validação básica antes de disparar a requisição
    if (!email || !senha) {
        alert("Por favor, preencha o e-mail e a senha.");
        return;
    }

    // Estrutura do payload conforme especificado: email e password
    const loginPayload = {
        email: email,
        password: senha
    };

    try {
        const response = await fetch(`${API_BASE_URL}/auth/token`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginPayload)
        });

        if (!response.ok) {
            throw new Error('E-mail ou senha incorretos.');
        }

        // Captura o token retornado como string pura no corpo da resposta
        const responseJson = await response.json();
        const token = responseJson.token;
        const id = responseJson.userId;

        // Armazena o token nos cookies com validade de 30 minutos (1800 segundos)
        // O path=/ deixa o token visível para a pasta /guia e outras rotas do projeto
        document.cookie = `auth_token=${token}; max-age=1800; path=/; SameSite=Strict`;
        document.cookie = `auth_id=${id}; max-age=1800; path=/; SameSite=Strict`;
        document.cookie = `user_email=${email}; max-age=1800; path=/; SameSite=Strict`;

        alert("Login efetuado com sucesso!");

        // Redireciona o usuário autenticado para a tela do guia prático
        window.location.href = "../guia/guia.html";

    } catch (error) {
        console.error('Erro ao efetuar login:', error);
        alert("Falha na autenticação. Verifique suas credenciais e se o servidor está rodando.");
    }
}