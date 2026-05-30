// ====================== CONFIGURAÇÃO DA API ======================
const API_BASE_URL = 'http://localhost:8081';

// ====================== MÉTODO 1: CARREGAR TIPOS DE SUPERFÍCIE ======================
// Busca os tipos de superfície no endpoint: /surface-type
async function carregarTiposSuperficie() {
    const select = document.getElementById("superficie");
    
    try {
        const response = await fetch(`${API_BASE_URL}/surface-type`);
        
        if (!response.ok) {
            throw new Error('Falha ao buscar superfícies');
        }

        const dados = await response.json();

        // Reseta o campo com a opção padrão inicial
        select.innerHTML = '<option value="" disabled selected>Selecione a superfície...</option>';

        console.log(dados)
        if (Array.isArray(dados)) {
            dados.forEach(item => {
                // Ignora o mock genérico "string" se ele vier da API
                if (item.type) {
                    const option = document.createElement("option");
                    
                    // Configuração padrão dos formatos aceitos
                    if (typeof item === 'string') {
                        option.value = item;
                        option.text = item.charAt(0).toUpperCase() + item.slice(1);
                    } else if (item.value && item.label) {
                        option.value = item.value;
                        option.text = item.label;
                    } else {
                        option.value = item.type;
                        option.text = item.type.charAt(0).toUpperCase() + item.type.slice(1);
                        
                        // Guarda o ID da superfície no atributo customizado do HTML
                        if (item.id) {
                            option.setAttribute("data-id", item.id);
                        }
                    }
                    
                    select.appendChild(option);
                }
            });
        }

    } catch (error) {
        console.error('Erro ao carregar superfícies:', error);
        select.innerHTML = '<option value="" disabled selected>Erro ao carregar superfícies</option>';
        alert("Não foi possível conectar com a API. Verifique se o backend está rodando.");
    }
}

// ====================== MÉTODO 2: CARREGAR ESTADOS DA SUPERFÍCIE ======================
// Busca os estados cadastrados no endpoint: /surface
async function carregarEstadosPorSuperficie() {
    const selectSuperficie = document.getElementById("superficie");
    
    // Obtém o "data-id" injetado pela Função 1 na opção que o usuário selecionou
    const superficieId = selectSuperficie.options[selectSuperficie.selectedIndex]?.getAttribute("data-id");
    const selectEstado = document.getElementById("estado");

    // Exibe o feedback de carregamento e desativa o campo temporariamente
    selectEstado.innerHTML = '<option value="" disabled selected>Carregando estados...</option>';
    selectEstado.disabled = true;
    
    if (!selectSuperficie.value) return;

    try {
        const response = await fetch(`${API_BASE_URL}/surface`);
        
        if (!response.ok) {
            throw new Error('Falha ao buscar estados da superfície');
        }

        const dados = await response.json();

        selectEstado.innerHTML = '<option value="" disabled selected>Selecione o estado...</option>';
        selectEstado.disabled = false;

        if (Array.isArray(dados)) {
            // Filtra a lista comparando o id capturado do HTML com o surfaceTypeId retornado do back-end
            const dadosFiltrados = dados.filter(item => String(item.surfaceTypeId) === String(superficieId));

            dadosFiltrados.forEach(item => {
                // Remove o valor mockado "string" da listagem de estados
                if (item.description) {
                    const option = document.createElement("option");
                    
                    // Atribuição idêntica à sua lógica original para value e text
                    if (item.description) {
                        option.value = item.id || item.description.toLowerCase().replace(/ /g, "_"); 
                        option.text = item.description; 
                    } else if (typeof item === 'string') {
                        option.value = item;
                        option.text = item;
                    } else {
                        option.value = item.value || JSON.stringify(item);
                        option.text = item.text || JSON.stringify(item);
                    }
                    
                    selectEstado.appendChild(option);
                }
            });
        }

    } catch (error) {
        console.error('Erro ao carregar estados:', error);
        selectEstado.innerHTML = '<option value="" disabled selected>Erro ao carregar estados</option>';
    }
}

// ====================== MÉTODO 3: CARREGAR MÉTODOS DE PINTURA ======================
// async function carregarMetodosPintura() {
//     const selectMetodo = document.getElementById("metodo");
    
//     try {
//         const response = await fetch(`${API_BASE_URL}/painting-method`);
//         if (!response.ok) throw new Error('Falha ao buscar métodos de pintura');

//         const dados = await response.json();
//         selectMetodo.innerHTML = '<option value="" disabled selected>Selecione o método...</option>';

//         if (Array.isArray(dados)) {
//             dados.forEach(item => {
//                 const option = document.createElement("option");
                
//                 // if (typeof item === 'string') {
//                 //     option.value = item.toLowerCase();
//                 //     option.text = item.charAt(0).toUpperCase() + item.slice(1);
//                 // } else if (item.description) {
//                     option.value = item.description.toLowerCase();
//                     option.text = item.description;
//                 // } else if (item.value && item.label) {
//                 //     option.value = item.value.toLowerCase();
//                 //     option.text = item.label;
//                 // }
                
//                 selectMetodo.appendChild(option);
//             });
//         }
//     } catch (error) {
//         console.error('Erro ao carregar métodos de pintura:', error);
//         selectMetodo.innerHTML = '<option value="" disabled selected>Erro ao carregar métodos</option>';
//     }
// }

// ====================== MÉTODO 3: CARREGAR MÉTODOS DE PINTURA ======================
async function carregarMetodosPintura() {
    const selectMetodo = document.getElementById("metodo");
    
    try {
        const response = await fetch(`${API_BASE_URL}/painting-method`);
        if (!response.ok) throw new Error('Falha ao buscar métodos de pintura');

        const dados = await response.json();
        selectMetodo.innerHTML = '<option value="" disabled selected>Selecione o método...</option>';

        if (Array.isArray(dados)) {
            dados.forEach(item => {
                const option = document.createElement("option");
                
                option.value = item.description.toLowerCase();
                option.text = item.description;
                
                // NOVO: Guarda o ID numérico do método de pintura no HTML
                if (item.id) {
                    option.setAttribute("data-id", item.id);
                }
                
                selectMetodo.appendChild(option);
            });
        }
    } catch (error) {
        console.error('Erro ao carregar métodos de pintura:', error);
        selectMetodo.innerHTML = '<option value="" disabled selected>Erro ao carregar métodos</option>';
    }
}

function obterCookie(nome) {
    const cookies = document.cookie.split('; ');
    const cookieEncontrado = cookies.find(row => row.startsWith(nome + '='));
    return cookieEncontrado ? decodeURIComponent(cookieEncontrado.split('=')[1]) : null;
}

// ====================== MÉTODO 4: SALVAR HISTÓRICO DE CONSULTA ======================
async function salvarHistoricoPintura(surfaceId, paintingMethodId, resultadoTexto) {
    const token = obterCookie('auth_token');
    const email = obterCookie('user_email');

    // Se não houver token ou e-mail, ignora silenciosamente (usuário não logado)
    if (!token || !email) {
        console.log("Usuário não autenticado. O histórico não será salvo.");
        return;
    }

    const historicoPayload = {
        email: email,
        surfaceId: parseInt(surfaceId),
        paintingMethodId: parseInt(paintingMethodId),
        result: resultadoTexto
    };

    console.log(historicoPayload);

    try {
        // Envia a requisição POST com a rota contendo barra dupla conforme especificado
        const response = await fetch(`${API_BASE_URL}/paint-history`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Padrão recomendado para APIs com JWT
            },
            body: JSON.stringify(historicoPayload)
        });

        if (!response.ok) {
            throw new Error('Não foi possível gravar o registro no histórico.');
        }

        console.log('Histórico de pintura salvo com sucesso no banco de dados!');
    } catch (error) {
        console.error('Erro ao salvar histórico:', error);
    }
}

    // Função que gera o texto de acordo com as escolhas
    function gerarInstrucoes() {
        const superficie = document.getElementById("superficie").value;
        const estado = document.getElementById("estado").value;
        const metodo = document.getElementById("metodo").value;
        const textarea = document.getElementById("resultado");

        if (!superficie || !estado || !metodo) {
            alert("Por favor, selecione todas as 3 opções antes de gerar o passo a passo.");
            return;
        }

        let texto = "--- PREPARAÇÃO GERAL ---\nProteja o piso, use equipamentos de proteção (luvas/óculos) e leia as instruções da embalagem para a diluição correta.\n\n--- PREPARAÇÃO DA SUPERFÍCIE ---\n";

        // Lógica da Superfície e Estado (Baseado no Manual)
        if (superficie === "madeira") {
            if (estado === "Madeira recém lixada e sem pintura/verniz") {
                texto += "1. Limpe o pó da madeira.\n2. Aplique um selador ou fundo branco fosco (o fundo branco evita que pregos enferrujem e melhora a aderência).\n3. Corrija imperfeições com massa própria para madeira, se houver.\n4. Após secar, aplique o acabamento (Esmalte Sintético ou Verniz).";
            } else { //Madeira pintada/envernizada
                texto += "1. Remova a pintura/verniz antigo lixando com lixa média.\n2. Faça o acabamento com lixa fina, lixando SEMPRE no sentido dos veios da madeira.\n3. Limpe bem o pó.\n4. Aplique a nova pintura (Esmalte ou Verniz).";
            }
        } 
        else if (superficie === "alvenaria") {
            if (estado === "Parede de reboco sem pintura e sem selante") {
                texto += "1. Aguarde a cura do reboco (cerca de 30 dias).\n2. Lixe com lixa vermelha para nivelar e limpe o pó com pano úmido.\n3. Aplique Selador Acrílico para melhorar a aderência e economizar tinta.\n4. Aplique a tinta Acrílica.";
            } else if (estado === "Massa corrida") {
                texto += "1. Lixe a superfície com lixa vermelha para deixá-la bem lisa.\n2. Retire todo o pó com um pano úmido.\n3. Se a parede estiver soltando muito pó, aplique Fundo Preparador.\n4. Aplique a tinta Acrílica.";
            } else if (estado === "Parede de reboco pintada/selada") {
                texto += "1. Lixe levemente para quebrar o brilho da tinta antiga e remover sujeiras.\n2. Limpe com pano úmido.\n3. Se houver partes descascando ou esfarelando, raspe a área e aplique Fundo Preparador de Paredes.\n4. Aplique a nova tinta Acrílica.";
            } else { //Parede com mofo/infiltração
                texto += "1. Lave o local afetado com água sanitária e água (partes iguais).\n2. Deixe agir, enxágue e aguarde secar completamente.\n3. Aplique Fundo Preparador se a parede ficar porosa/arenosa.\n4. Pinte com tinta Acrílica, de preferência com proteção antimofo.";
            }
        } 
        else if (superficie === "metal") {
            if (estado === "Metal novo, sem pintura e sem ferrugem") {
                texto += "1. Limpe a superfície usando Thinner ou solução desengraxante para remover óleos de fábrica.\n2. Aplique um fundo especial para metais (Primer).\n3. Após secar, aplique o Esmalte Sintético.";
            } else {//Metal enferrujado ou pintado
                texto += "1. Remova a ferrugem e tintas soltas usando escova de aço, palha de aço ou lixa para ferro.\n2. Limpe a superfície com solução desengraxante.\n3. Aplique um Convertedor de Ferrugem nos pontos mais críticos.\n4. Aplique o fundo protetor (Primer) e depois o Esmalte Sintético.";
            }
        }

        texto += "\n\n--- DICAS SOBRE O MÉTODO (" + metodo.toUpperCase() + ") ---\n";

        // Lógica do Método
        if (metodo === "pincel") {
            texto += "Excelente para cantos, recortes (como teto/parede), portas, janelas e pequenos detalhes. Use trinchas de cerdas escuras para esmaltes/vernizes e cerdas gris para tintas à base de água.";
        } else if (metodo === "rolo") {
            texto += "Ideal para grandes superfícies planas. \n- Alvenaria: Use rolo de lã (mista ou natural). Pinte fazendo movimentos em 'W' para melhor cobertura.\n- Madeira e Metal: Use rolinho de espuma próprio para esmaltes e vernizes, garantindo um acabamento liso.";
        } else if (metodo === "pistola") {
            texto += "Garante um acabamento extremamente liso e profissional, mas exige cuidado. Você precisará diluir a tinta um pouco mais do que para rolo (siga rigorosamente a embalagem). Isole muito bem o ambiente, pois o spray cria uma névoa que pode sujar tudo ao redor.";
        }

        texto += "\n\nLembre-se: O solvente usado varia da tinta. Tintas Acrílicas usam Água; Esmaltes e Vernizes Sintéticos usam Aguarrás para diluição (para não perder o brilho) e Thinner para limpeza.";

        textarea.value = texto;

        const selectEstado = document.getElementById("estado");
        const selectMetodo = document.getElementById("metodo");

        const surfaceId = selectEstado.value; 
        const paintingMethodId = selectMetodo.options[selectMetodo.selectedIndex]?.getAttribute("data-id");

        if (surfaceId && paintingMethodId) {
            salvarHistoricoPintura(surfaceId, paintingMethodId, texto);
        }
    }
// ====================== INICIALIZAÇÃO ======================
// Executa o método de carregar superfícies assim que a página abre
window.onload = function() {
    carregarTiposSuperficie();
    carregarMetodosPintura();
};