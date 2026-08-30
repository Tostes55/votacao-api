# 🗳️ Votação API

Uma API REST para gerenciamento de pautas, abertura de sessões de votação, registro de votos por associados e apuração de resultados, desenvolvida com **Java** e **Spring Boot**.

---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 21 / 17** (Spring Boot 3.x)
- 🛠️ **Gradle**
- 🗄️ **H2 Database** (In-Memory / Arquivo)
- 🍃 **Spring Data JPA** & **Hibernate**
- 🗺️ **MapStruct** (Mapeamento DTO <-> Entidade)
- 📄 **Swagger / OpenAPI 3** (Documentação Interativa)
- 🧪 **JUnit 5 & Mockito** (Testes Unitários e de Integração)

---

## ⚙️ Pré-requisitos

- **Java Development Kit (JDK 17 ou 21)** instalado.
- **Gradle** (incluso via wrapper `./gradlew`).

---

## 🏃 Execução Local

1. Clone o repositório:
   ```bash
   git clone https://github.com/Tostes55/votacao-api
   cd votacao-api
   ```

2. Execute o projeto via Gradle Wrapper:
   ```bash
   ./gradlew bootRun
   ```

3. A API estará rodando em:

   `http://localhost:8080`

---

## 🗄️ Acesso ao Banco de Dados (H2 Console)

| Componente | Detalhes |
| ------------------ | ---------------------------------- |
| 🌐 **Console H2** | `http://localhost:8080/h2-console` |
| 🔗 **JDBC URL** | `jdbc:h2:file:./data/votacaodb` |
| 👤 **Usuário** | `admin` |
| 🔒 **Senha** | `admin` |

---

## 📚 Documentação da API (Swagger / OpenAPI)

Interaja e teste todos os endpoints diretamente pelo navegador:

👉 `http://localhost:8080/swagger-ui/index.html`

---

## 📊 Arquitetura e Diagramas

🔗 **Acesse o fluxograma completo no Figma**

---

## 🔄 Fluxo Completo de Uso da API

### 1. 📝 Cadastrar Pauta

**Endpoint:**

```http
POST /pauta/cadastrar
```

**Body:**

```json
{
  "tituloPauta": "Aprovação do Orçamento 2027",
  "descricaoPauta": "Votação para definir os investimentos do próximo ano.",
  "categoriaPauta": "FINANCEIRO"
}
```

---

### 2. ⏰ Abrir Sessão de Votação

**Endpoint:**

```http
POST /pauta/{idPauta}/sessao/abrir
```

**Body (opcional):**

Se omitido, assume o padrão de **5 minutos**.

```json
{
  "duracaoMinutos": 10
}
```

---

### 3. ✅ Registrar Voto

**Endpoint:**

```http
POST /votos/cadastrar
```

**Body:**

```json
{
  "idPauta": 1,
  "cpfAssociado": "123.456.789-00",
  "voto": "SIM"
}
```

**Regras:**

- O CPF é validado e formatado.
- Cada CPF pode votar apenas uma vez por pauta.
- A sessão deve estar dentro do prazo de validade.

---

### 4. 🔍 Consultar Votos por CPF

**Endpoint:**

```http
GET /votos/buscarVoto/{cpfAssociado}
```

Retorna todos os votos já registrados pelo CPF informado.

---

### 5. 🔒 Encerrar Sessão (Antecipadamente)

**Endpoint:**

```http
POST /pauta/{idPauta}/sessao/fechar
```

Encerra a sessão imediatamente e altera o status da pauta para `CONCLUIDA`.

---

### 6. 📊 Consultar Resultado da Votação

**Endpoint:**

```http
GET /pauta/{idPauta}/resultado
```

Retorna:

- Total de votos `SIM`;
- Total de votos `NAO`;
- Total acumulado;
- Parecer final:
    - `APROVADO`
    - `REPROVADO`
    - `EMPATE`
    - `SEM_VOTOS`

---

## 🧪 Executando os Testes Unitários

Para rodar a suíte de testes com **JUnit/Mockito**:

```bash
./gradlew test
```
