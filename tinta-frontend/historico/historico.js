// ====================== CONFIGURAÇÃO DA API ======================
const API_BASE_URL = 'http://localhost:8081';

/**
 * Função utilitária para capturar valores de dentro dos Cookies salvos no navegador.
 */
function obterCookie(nome) {
    const cookies = document.cookie.split('; ');
    const cookieEncontrado = cookies.find(row => row.startsWith(nome + '='));
    return cookieEncontrado ? decodeURIComponent(cookieEncontrado.split('=')[1]) : null;
}

// ====================== CARREGAR HISTÓRICO DO USUÁRIO ======================
async function carregarUltimoHistorico() {
    const token = obterCookie('auth_token');
    const userId = obterCookie('auth_id');
    const email = obterCookie('user_email');
    
    const tabelaCorpo = document.getElementById("historicoCorpo");
    const labelUsuario = document.querySelector(".user-name");

    // Atualiza visualmente o e-mail do usuário logado no menu lateral
    if (email && labelUsuario) {
        labelUsuario.textContent = email;
    }

    // Regra de segurança front-end: Se o cookie 'auth_id' não existir, interrompe sem chamar a API
    if (!userId || !token) {
        console.log("Usuário não autenticado ou ID ausente nos cookies. Requisição abortada.");
        tabelaCorpo.innerHTML = `
            <tr>
                <td colspan="4" class="table-loading" style="color: #e74c3c; font-weight: bold;">
                    Por favor, faça login para visualizar seu histórico de pintura.
                </td>
            </tr>`;
        return;
    }

    try {
        // Executa a busca dinâmica passando o userId do cookie na rota da URL
        const response = await fetch(`${API_BASE_URL}/paint-history/user/${userId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        // Caso o back-end não possua registros salvos ainda (Status 404 ou 204)
        if (response.status === 404 || response.status === 204) {
            tabelaCorpo.innerHTML = `
                <tr>
                    <td colspan="4" class="table-loading">Você ainda não gerou nenhum passo a passo técnico.</td>
                </tr>`;
            return;
        }

        if (!response.ok) {
            throw new Error('Falha na comunicação com o servidor ao coletar o histórico.');
        }

        const historico = await response.json();

        // Limpa a linha inicial de carregamento técnico
        tabelaCorpo.innerHTML = "";

        // Instancia a nova linha estruturada
        const tr = document.createElement("tr");

        // 1. Mapeamento do campo: surface.surfaceType.type
        const tdTipoSuperficie = document.createElement("td");
        tdTipoSuperficie.textContent = historico.surface?.surfaceType?.type 
            ? historico.surface.surfaceType.type.toUpperCase() 
            : "N/A";

        // 2. Mapeamento do campo: surface.description
        const tdDescricaoSuperficie = document.createElement("td");
        tdDescricaoSuperficie.textContent = historico.surface?.description || "Não informada";

        // 3. Mapeamento do campo: paintingMethod.description
        const tdMetodo = document.createElement("td");
        tdMetodo.textContent = historico.paintingMethod?.description 
            ? historico.paintingMethod.description.charAt(0).toUpperCase() + historico.paintingMethod.description.slice(1)
            : "Não informado";

        // 4. Mapeamento do campo: result
        const tdResultado = document.createElement("td");
        tdResultado.textContent = historico.result || "Sem dados gerados.";

        // Vincula de forma ordenada os elementos filhos (tds) à linha correspondente (tr)
        tr.appendChild(tdTipoSuperficie);
        tr.appendChild(tdDescricaoSuperficie);
        tr.appendChild(tdMetodo);
        tr.appendChild(tdResultado);
        
        // Insere a linha montada no corpo da tabela
        tabelaCorpo.appendChild(tr);

    } catch (error) {
        console.error('Erro de conexão com a API de histórico:', error);
        tabelaCorpo.innerHTML = `
            <tr>
                <td colspan="4" class="table-loading" style="color: #e74c3c; font-weight: bold;">
                    Não foi possível carregar os seus dados. Verifique a conexão com o banco de dados.
                </td>
            </tr>`;
    }
}

// ====================== INICIALIZAÇÃO DA PÁGINA ======================
document.addEventListener("DOMContentLoaded", carregarUltimoHistorico);