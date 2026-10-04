# Projeto Escola

Projeto Java local para controle de alunos.

## Descrição

Este repositório contém um projeto Java para gerenciamento de informações escolares, com foco no cadastro e controle de alunos. A estrutura foi organizada para praticar conceitos de programação orientada a objetos, separação por camadas e uso de boas práticas de modelagem.

O projeto foi desenvolvido em Java e usa Maven para gerenciamento do build, além de um ambiente com PostgreSQL e pgAdmin via Docker Compose para facilitar a configuração local do banco de dados.

## Tecnologias e ferramentas

- Java
- Maven
- PostgreSQL
- pgAdmin
- Docker
- Docker Compose

## Visão geral da aplicação

A aplicação está organizada em torno de entidades de domínio e repositórios para acesso aos dados. O modelo principal inclui:

- Pessoa: classe base com dados gerais
- Aluno: classe que herda de Pessoa e acrescenta informações específicas
- PessoaRepository: abstração para acesso de dados relacionado à Pessoa
- AlunoRepository: abstração para acesso de dados relacionado ao Aluno
- Main: ponto de entrada da aplicação

## Estrutura do projeto

```text
projeto-escola/
├── src/
│   └── main/
│       └── java/
│           └── cotiinformatica/
│               ├── Main.java
│               ├── entities/
│               │   ├── Pessoa.java
│               │   └── Aluno.java
│               └── repositories/
│                   ├── PessoaRepository.java
│                   └── AlunoRepository.java
├── docker-compose.yml
├── pom.xml
├── .gitignore
├── README.md
└── .idea/
```

## Modelo de domínio

### Pessoa
A classe `Pessoa` representa os dados pessoais básicos, como:

- id
- nome
- cpf
- dataNascimento

Ela encapsula esses atributos com métodos getter e setter, seguindo o padrão Java Bean.

### Aluno
A classe `Aluno` herda da classe `Pessoa` e adiciona atributos específicos do contexto escolar, como:

- id
- matricula

Esse relacionamento demonstra o uso de herança em Java para reutilizar atributos e comportamentos comuns.

## Padrões e boas práticas aplicados

### 1. Herança
A classe `Aluno` estende `Pessoa`, permitindo reaproveitar dados comuns como nome, CPF e data de nascimento.

### 2. Encapsulamento
Os atributos das classes são privados e acessados por meio de getters e setters, preservando o controle do estado dos objetos.

### 3. Arquitetura em camadas
O projeto separa:

- entidades: representam o domínio
- repositories: são responsáveis pela persistência e acesso aos dados
- aplicação: ponto de entrada e execução

### 4. Repository Pattern
Os repositórios ajudam a isolar a lógica de acesso a dados, tornando a aplicação mais organizada, escalável e fácil de evoluir.

## Configuração do ambiente local

### Requisitos

- Java 25
- Maven
- Docker
- Docker Compose

### Banco de dados
O projeto inclui um arquivo `docker-compose.yml` com os seguintes serviços:

- PostgreSQL 16
- pgAdmin 4

Os serviços são configurados para rodar em containers locais, com as credenciais abaixo:

- Banco: `bd-alunos`
- Usuário: `coti`
- Senha: `coti`
- Porta PostgreSQL: `5434`
- Porta pgAdmin: `5052`

### Subir o ambiente

```bash
docker-compose up -d
```

Isso será suficiente para iniciar o PostgreSQL e o pgAdmin localmente.

## Arquivo `pom.xml`

O projeto utiliza Maven e define a configuração básica de build com Java 25:

```xml
<properties>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

## Como executar

1. Clonar o repositório:

```bash
git clone https://github.com/Edmiranda099/projeto-escola.git
cd projeto-escola
```

2. Subir os containers com Docker Compose:

```bash
docker-compose up -d
```

3. Compilar o projeto:

```bash
mvn clean compile
```

4. Executar a aplicação:

```bash
mvn exec:java -Dexec.mainClass="cotiinformatica.Main"
```

## Observações de desenvolvimento

O código atual apresenta a estrutura inicial do projeto com classes de domínio e repositories já definidos. Ainda há espaço para evolução, incluindo:

- conexão real com banco de dados
- CRUD completo de alunos
- validações de dados
- persistência com JDBC ou JPA
- testes unitários
- camada de serviço
- interface para interação com o usuário

## Status do projeto

O projeto está em desenvolvimento inicial, com foco em organização e modelagem Java de um sistema escolar.

## Licença

Este projeto foi criado para fins educacionais e de estudo.

## Repositório

- GitHub: https://github.com/Edmiranda099/projeto-escola

## Resumo

O projeto `projeto-escola` é uma aplicação Java local para controle de alunos, utilizando arquitetura em camadas, classes de entidade, repositórios e containerização com PostgreSQL. Ele serve como base para aprendizado de Java, orientação a objetos, organização de código e uso de ferramentas como Maven e Docker.
