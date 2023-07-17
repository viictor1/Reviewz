## Reviewz

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

### Tecnologias utilizadas
  - Java 17
  - Spring Boot 3.1.1
  - Maven
  - PostgreSQL
  - Flyway
  - JUnit 5
  - Swagger 2.1.0
