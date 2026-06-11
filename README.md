# Anotação

## Pré-requisitos

- Java 17+
- Docker (apenas para o modo container)

---

## Configuração

Crie um arquivo `.env` na raiz do projeto com:

```env
DB_USERNAME=Seu_login
DB_PASSWORD=Sua_senha
DB_NAME=Nome_do_seu_banco
NVIDIA_API_KEY=sua_chave_aqui
```

O Spring Boot carrega o `.env` automaticamente.

---

## Rodar com MySQL local

**Você precisa ter o MySQL instalado e rodando manualmente na sua máquina na porta `3306`.**

```bash
Rodar a API 
Colocar comando no aplication.properties e roda no AnotacaoApplication
Comando:   spring.profiles.active=local    
```

---

## Rodar com MySQL no Docker

**Você não precisa ter o MySQL instalado na máquina.** O Docker Compose já sobe o MySQL automaticamente dentro de um container na porta `3307`.

```bash
Rodar API
# Sobe o container do MySQL (automatico)
docker compose up -d

# Roda a aplicação
colocar comando no Application.properties e roda API pelo AnotacaoApplication
Comando:  spring.profiles.active=docker 
```

> O container do MySQL precisa estar rodando para API iniciar 
> E se for rodar com MySQL local o MySQL precisa estar rodando também.

---

## Perfis

| Profile | Arquivo | Banco |
|---|---|---|
| `local` | `application-local.properties` | `localhost:3306` (MySQL standalone — você precisa iniciar manualmente) |
| `docker` | `application-docker.properties` | `localhost:3307` (MySQL container — sobe automaticamente com `docker compose up -d`) |

## Sobre o Projeto

Essa não foi uma dor real minha. Criei esse projeto com a ideia de um amigo meu que fez um projeto parecido. Então pensei: será que consigo consumir uma API de IA pra fazer um agente assim? Foi aí que me desafiei.

O projeto **front-end**: https://github.com/gustavomazur/Front-end-anota-o

### Funcionalidades

- Criar anotações com **título** e **descrição**
- **LocalDateTime criacao** — data de criação
- **LocalDateTime atualizacao** — data de atualização (caso edite a anotação)
- Listagem **paginada de 10 anotações por vez**
- **Busca inteligente por IA** — se você esquecer o título, pergunta pra IA e ela busca anotações relacionadas

---

Criado por **Gustavo Bueno Mazur** 
