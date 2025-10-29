
# 🗳️ Votação API

Uma API para gerenciamento de sessões de votação desenvolvida em Java.

## 🚀 Tecnologias Utilizadas

- ☕ **Java 21.0.8 LTS**
- 🛠️ **Gradle 8.14.3**
- 🗄️ **H2 Database**
- 📚 **Spring Boot**
- 📄 **Swagger/OpenAPI**

## ⚙️ Pré-requisitos

- Java 21.0.8 LTS
- Gradle 8.14.3
- Windows 11 (ou sistema compatível)

## 🏃 Execução Local

bash
>./gradlew bootRun

## 🗄️ Acesso ao Banco de Dados
Componente	Detalhes  
🌐 Console H2:	http://localhost:8080/h2-console  
🔗 JDBC URL:	jdbc:h2:file:./data/votacaodb  
👤 Usuário:	admin  
🔒 Senha:	admin
## 📚 Documentação da API
Swagger UI: http://localhost:8080/swagger-ui/index.html#/

## 📊 Fluxograma do Sistema
🔗 [Acesse o fluxograma completo](https://www.figma.com/board/JJALfemn8tZQkkyvc99a6M/votacao-api?node-id=0-1&p=f&t=6a85kaO7aB14mAkJ-0)

## 🔄 Fluxo de Votação  
1. 📝 Criar Pauta  
Endpoint: POST /api/pautas  
Cria uma nova pauta para votação. 


2. ⏰ Abrir Sessão  
Endpoint: POST **/api/sessoes**  
Abre uma sessão de votação para uma pauta específica.


3. ✅ Registrar Votos  
Endpoint: POST **/api/votos**  
Permite que associados registrem seus votos durante a sessão aberta.


4. 🔒 Encerrar Sessão  
Endpoint: PUT **/api/sessoes/{id}/fechar**  
Automático: Encerra automaticamente após 60 minutos  
Manual: Pode ser encerrada manualmente se necessário


5. 📊 Consultar Resultados  
Endpoint: GET **/api/resultados/sessao/{id}**  
Consulta o resultado final da votação de uma sessão específica.