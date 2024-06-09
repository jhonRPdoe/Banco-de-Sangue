# Banco de sangue

- [Apresentação](#apresentação)
- [Descrição do projeto](#descrição-do-projeto)
  - [Descrição dos usuários](#descrição-dos-usuários)
  - [Screenshots](#screenshots)
- [Meu processo](#meu-processo)
  - [Construido com](#construido-com)
- [Autor](#autor)

## Apresentação

Este é um sistema, ainda em desenvolvimento, para os hospitais que desejam um modo de gerenciar seu banco de sangue de forma mais simples e ágil. O projeto, em resumo, trata-se de um sistema de registro, consulta e doações de bolsas de sangue, um banco de sangue digital. Foi desenvolvido em Java no padrão de projeto MVC, inicialmente ele foi construido para desktop, mas um dos próximos passos é porta-lo para web, sua interface foi desenvolvida usando o Java Swing e sem nenhum framework e a parte de persistencia de dados é feita em MySQL.

## Descrição do projeto

O Sistema a princípio possui as funcionalidades de registrar as doações de sangue recebidas, doações feitas, tipos de sangue ainda em estoque e pacientes á espera de doação. Assim, agilizando e tornando mais preciso processos que necessitam de transfusões de sangue, como uma cirurgia por exemplo, evitando possíveis erros por falta de informação. O acesso ao sistema será exclusivo ao pessoal interno do hospital e a sua manipulação somente áqueles que possuírem autorização, que será feita através da autenticação de alguns dados do funcionário.

A primeira tela a ser apresentada é a tela de login onde o funcionário deverá informar seu nome e CPF para serem validados, neste momento também será verificada a quais rotinas do sistema o funcionário tem privilégio. Os tipos de usuários e privilégios serão apresentados mais a frente. O login não é muito complexo, pois, pensando em situações de emergencia o acesso a consulta dos sangues deve ser ágil e fácil.

Entáo, após o login, o usuário é apresentado ao menu principal do sistema e as rotinas as quais ele tem acesso. O sistema conta com telas para o cadastro de novas doações de sangue para o banco de sangue, para doação de sangue para os pacientes, para registro de novos funcionários e seu nivel de privilégio, assim como a alteração de funcionários já cadastrados.

Existe uma rotina que ainda está em desenvolvimento, esta é a "Relatar um Problema" que terá como objetivo facilitar a comunicação do usuário com o suporte e assim agilizar a resolução de possiveis problemas que surgirem.

## Descrição dos usuários

Para fazer consultas ou pesquisas simples todo funcionário que tiver o registro de trabalho ativo e associado ao hospital terá acesso ao sistema. Este usuário é classificado como usuário **Comum**.

Já para a manipulação e modificação dos registros só aqueles com permissão especial de acesso ao sistema poderão fazê-lo. Este usuário é classificado como **Especial**.

Também deverá haver pelo menos um funcionário com acesso de administrador, este que terá livre acesso a todas as funcionalidades do sistema, poderá fornecer acesso simples,  especial e de administrador á outros funcionários. Este usuário é classificado como **Administrador**.

## Screenshots

### Tela de login
![](./screenshots/readme-images/readme-images/telaLogin.png)

### Menu Principal (Acesso de Administrador)
![](./screenshots/readme-images/telaMenuPrincipal.png)

### Menu Principal (Acesso Especial)
![](./screenshots/readme-images/telaMenuPrincipalAcessoEspecial.png)

### Menu Principal (Acesso Comum)
![](./screenshots/readme-images/telaMenuPrincipalAcessoComum.png)

### Banco de Sangue (consulta)
![](./screenshots/readme-images/telaBancoSangue.png)

### Tela de cadastro de doações (para o banco)
![](./screenshots/readme-images/telaCadastroDoacao.png)

### Tela de cadastro de doações para pacientes (do banco para o paciente)
![](./screenshots/readme-images/telaDoarPaciente.png)

### Tela de consulta de funcionários cadastros no sistema e seus privilégios
![](./screenshots/readme-images/telaFuncionario.png)

### Tela para cadastro de funcionário
![](./screenshots/readme-images/telaCadastrarFuncionario.png)

### Tela para alteração de informações do funcionário
![](./screenshots/readme-images/telaAlterarFuncionario.png)


## Meu processo

O projeto foi pensando desde o inicio para ser simples, rápido e leve, Podemos ver claramente isto olhando para o menu principal, aqui a interface é simples e direta, para agilizar a consulta, tanto a interface quanto o back-end devem ser leves. A sua simplicidade também foi feita pensando na usabilidade, as informações na tela são sucintas, centralizadas e claras quanto a sua função, assim qualquer usuário que utilizar o sistema pela primeira vez dominará suas funcionalidades rapidamente. Esta ideia de clareza e simplicidade se estende por todas as telas do sistema. 

Neste projeto foi utilizado banco de dados relacional para deixar o acesso aos dados, durante e após o desenvolvimento, mais fluido e organizado.
A escolha do Java como linguagem de desenvolvimento dá-se ao fato dela ter diversas ferramentas para desenvolvimento, sua larga difusão entre desenvolvedores e sua flexibilidade de uso. 
A escolha do sistema operacional windows foi por conta dele ser o mais usado nesses ambientes.

Todas essas escolhas foram feitas pensando também em possíveis expansões do sistema, bem como a adição de novas funcionalidades, sendo uma delas a tela de doação de orgãos com as funcionalidades anteriores somadas a algumas novas que este update requer.

### Construido com

- Java
- Java Swing
- MySQL

## Autor

- Linkedin - [Ruan Pereira](https://www.linkedin.com/in/ruan-pereira-651523237/)