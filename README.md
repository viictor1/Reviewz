## Reviewz

A ideia do projeto é você criar uma conta para guardar as suaus opiniões sobre as obras que você consome, como músicas, filmes, séries, etc.


### Projeto para aprender mais sobre alguns padrões e tecnologias como:
  - Clean Architecture
  - Clean Code
  - Git Flow
  - Commits semânticos
  - Testes Unitários

### Funcionalidades
  - Criação de usuário
    - Exceção personalizada caso tente usar um email ja cadastrado.
    - Exceção caso não informe um campo obrigatório.
  - Login
    - Exceção caso não informe um campo obrigatório.
    - Exceção caso informe login ou senha incorretos.
  - Get All Users
    - Exceção caso usuário não seja admin.
  - Get User By Login
    - Exceção caso usuário não seja admin.
    - Exceção caso o usuário que está sendo buscado não exista.
  - Delete Account
    - Deleta a conta relacionada ao token JWT passado no header da requisição após confirmar a senha.
    - Exceção caso o usuário não seja encontrado
    - Exceção caso a senha não seja passada.
  - Update Account
    - É possível alterar name, oldPassword, newPassword, sendo que o único obrigatório é o oldPassword, para fazer a validação.
    - Se não passar name ou newPassword será mantido os antigos.
    - Exceção caso a oldPassword não seja a senha verdadeira.
   
  - Criação de Review
    - A review possui, titulo, categoria, quem fez, estrelas, a review em si, data de publicação da obra, data review e é necessariamente vinculado a um usuário.
    - Get All Reviews:
      - Mostrará todas as reviews do usuário autenticado.
      - Exceção caso o usuário não esteja autenticado.
    - Get Review By id
      - Exceção caso usuário não esteja autenticado.
      - Exceção ao tentar acessar review de outro usuário.
    - Delete Review
      - Exceção caso usuário não esteja autenticado.
      - Exceção ao tentar deletar review de outro usuário.
    - Update Review
      - Exceção caso usuário não esteja autenticado.
      - Exceção ao tentar atualizar review de outro usuário.
      - Caso alguma informalçao não for passada, será mantida a antiga.

### Tecnologias utilizadas
  - Java 17
  - Spring Boot 3.1.1
  - Maven
  - PostgreSQL
  - Flyway
  - JUnit 5
  - Swagger 2.1.0
  - Docker

### Como executar
1. Clone ou instale o repositório
2. ```./mvnw clean package -DskipTests ``` para instalar o .jar do projeto
3. ```docker-compose up -d``` para rodar o projeto e o banco de dados
4. ```docker ps``` para verificar se o projeto e o banco estão rodando corretamente
