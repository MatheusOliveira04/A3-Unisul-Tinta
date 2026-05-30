// ====================== CONFIGURAÇÃO DA API ======================
const API_BASE_URL = 'http://localhost:8080';

// ====================== INICIALIZAÇÃO E EVENTOS ======================
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("registroForm");
    if (form) {
        form.addEventListener("submit", cadastrarUsuario);
    }
});

// ====================== MÉTODO: CADASTRAR USUÁRIO ======================
// Envia os dados do formulário de registro para o endpoint /user via POST
async function cadastrarUsuario(event) {
    // Impede o recarregamento automático da página
    event.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value;

    // Validação básica de segurança (seguindo o padrão @NotEmpty do backend)
    if (!nome || !email || !senha) {
        alert("Por favor, preencha todos os campos obrigatoriamente.");
        return;
    }

    // Montagem do payload conforme esperado pela API de destino
    const usuarioPayload = {
        name: nome,
        email: email,
        password: senha,
        roles: "USER" // Valor fixo obrigatório especificado
    };

    try {
        const response = await fetch(`${API_BASE_URL}/user`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuarioPayload)
        });

        if (!response.ok) {
            throw new Error('Falha ao registrar o usuário na API');
        }

        // Sucesso no cadastro
        alert("Cadastro realizado com sucesso!");
        
        // Limpa os campos do formulário após o sucesso
        document.getElementById("registroForm").reset();

        // Opcional: Redireciona o usuário para a tela de login
        window.location.href = "../login/login.html";

    } catch (error) {
        console.error('Erro ao realizar o cadastro:', error);
        alert("Não foi possível realizar o cadastro. Verifique a conexão com o servidor ou se o e-mail já existe.");
    }
}