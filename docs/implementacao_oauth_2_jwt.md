# Implementação: Autenticação JWT com Google OAuth2 (apenas usuários pré-cadastrados)
Leia tudo com atenção e implemente exatamente as restrições e requisitos abaixo.

## Objetivo

Implementar autenticação baseada em JWT que suporte:

- Login clássico com email e senha (credenciais armazenadas no banco de dados).
- Login via Google OAuth2, porém aceitar apenas usuários que já existam no banco (ou seja: não permitir criação automática de conta ao usar o login do Google).
- Não deve existir rota pública de registro (signup). Todas as contas são criadas apenas via rota administrativa existente.

## Requisitos funcionais

1. Endpoints mínimos:
   - POST /api/auth/login
     - Recebe email e senha.
     - Retorna um JWT válido com informações básicas do usuário (id,nome, email, roles, campus_id).
   - GET /api/auth/oauth2/google
     - Inicia o fluxo de OAuth2 do Google (ou rota equivalente que o Spring Security sugira).
   - Callback de OAuth2 que valida o usuário autenticado pelo Google e, se o email não existir no banco, deve rejeitar o login com erro 403 e mensagem clara.

2. Fluxo Google OAuth2 restrito:
   - Ao receber a informação do Google, procurar o usuário pelo email no banco.
   - Se usuario existir e estiver ativado, emitir JWT e permitir acesso.
   - Se usuario não existir ou estiver desativado, negar acesso e não criar registro automaticamente.

3. JWT e sessão:
   - Gerar JWTs assinados com chave segura (assimétrica).
   - JWT deve conter id do usuário, nome, email, campus_id, roles e uma claim de emissão e expiração.
   - Implementar filtro ou configuração do Spring Security que valide o JWT para rotas protegidas.
   - Implementar renovação de token conforme política do time (se desejado), mas ao menos documentar como renovar.

4. Segurança de credenciais e senhas:
   - Senhas devem ser armazenadas com hashing BCrypt.
   - Comparação de senha deve usar o encoder adequado do Spring Security.

5. Autorização:
   - Usar roles básico: adapte as roles do sistema.
   - Proteger a rota de criação de usuários apenas para a role de ADMIN.


## Requisitos não-funcionais e boas práticas

- Usar Spring Security moderno e configuração por Java config (sem XML).
- Centralizar configuração de segurança em uma classe bem documentada.
- Fazer tratamento apropriado de exceções com respostas JSON padronizadas para falhas de autenticação e autorização.
- Logar eventos importantes (login bem sucedido, tentativa de login negada por Google por inexistência do email, etc.) sem logar senhas.
- Documentar claramente quais propriedades precisam ser adicionadas ao environment ou application-test.properties para que o fluxo do Google funcione em ambientes de teste.

## Inicialização automática de dados para development/test

Ao iniciar a aplicação em profile de teste (por exemplo profile test ou quando o application-test.properties estiver ativo), garantir que os seguintes usuários existam no banco:

- Email: devprates@gmail.com
  - Senha: teste123
  - Roles: ROLE_ADMIN, ROLE_PROFESSOR
  - Ativo: true

- Email: gabriel.bitencourt@estudante.ifms.edu.br
  - Senha: teste123
  - Roles: ROLE_ADMIN, ROLE_PROFESSOR
  - Ativo: true

As senhas devem ser armazenadas já hashed com BCrypt no banco ao inserir os registros iniciais.

## application-test.properties

No profile de teste (arquivo application-test.properties) adicionar as variáveis necessárias para configurar a integração com o Google OAuth2. Não listar as variáveis aqui; apenas assegurar que o arquivo contenha as propriedades do client do Google para os ambientes de teste.

## Mensagens e erros

- Quando um usuário tenta logar via Google e o email não está cadastrado, retornar HTTP 403 com mensagem JSON clara explicando que a conta não foi encontrada e que o cadastro deve ser feito por um administrador.
- Quando credenciais inválidas no login por email/senha, retornar HTTP 401 com mensagem apropriada.

## Observações finais

- Não adicionar rota pública de criação de conta. Apenas rotas administrativas existentes podem criar usuários.
- Garantir que o fluxo OAuth2 do Google nunca crie registros automaticamente — sempre exigir correspondência por email no banco existente.
- Não utilizar crases no markdown deste prompt.

Obrigado. Implemente de forma clara, testável e segura.