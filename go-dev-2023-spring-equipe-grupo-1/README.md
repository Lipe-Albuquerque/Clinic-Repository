# 🏫 Escola GoDev 🎒


O nosso projeto consiste em um sistema que visa facilitar o gerenciamento de matrículas da nossa escola e também da atribuição dos nossos professores de cada disciplina de suas respectivas turmas das quais eles estão relacionados para poder ministrar as aulas.
  Cada aluno, assim que cadastrado recebe um e-mail de Boas Vindas confirmando a matrícula e também informando os dados de sua turma e o período que ocorrerá as aulas.

### Tecnologias : <br> 
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) 
![GitLab](https://img.shields.io/badge/GitLab-330F63?style=for-the-badge&logo=gitlab&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Postgre](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)
&nbsp; 	
	
<br>

Os pacotes foram estruturados seguindo o padrão MVC (Model, View e Controller). Criamos e implantamos uma API REST (Representational State Transfer) usando SpringBoot e Postgres, aplicando JPA (Java Persistence API) e Hibernate (uma ferramenta de mapeamento objeto/relacional para ambientes Java).

A camada REST - onde implementamos a lógica de negócio - e a camada de serviço, têm o poder de acessar a camada de dados e os repositórios. Serviços e repositórios são os que se comunicam com a classe de domínio do aplicativo.

<br>

## Requisitos de instalação do projeto

## **Spring**

1. Para executar o projeto corretamente, algumas ferramentas de desenvolvimento devem ser instaladas. Java JDK e Maven.
2. Para instalar o Java JDK
    - Baixe o JDK [aqui](https://www.oracle.com/java/technologies/downloads/#jdk19-windows)
    - Instale o arquivo JDK.exe
    - Verifique no Diretório ``C:\Arquivos de Programas\Java\jdk-xxx`` se existe a pasta jdk;
3. Para instalar o Maven (ele irá compilar a aplicação)
    - Baixe o Maven [aqui](https://maven.apache.org/download.cgi);
    - Descompacte o arquivo;
    - Crie uma pasta chamada maven em ``C:\`` ;
    - Pressione a tecla Windows no teclado e digite: "editar variáveis ​​do sistema". Clique na opção que aparece.
    - Na tela "Variáveis ​​de Ambiente" que se abrirá, clique no botão "Novo" logo abaixo da parte da tela que diz "Variáveis ​​de Ambiente".
    - Nos campos nome e valor da variável, preencha: ``MAVEN_HOME`` e ``C:\maven\bin``, respectivamente. Pressione o botão "Confirmar".
    - Verifique se funcionou abrindo um novo prompt e digitando ``mvn -version``. Se funcionou, um erro aparecerá.
4. Para instalar o Eclipse
    - Baixe o Eclipse [aqui](https://www.eclipse.org/downloads/)
5. Faça uma cópia do projeto para sua máquina:
      primeiro:
     `git@git.senior.com.br:treinamento/go-dev-2023-spring-equipe.git `,
      após clonar o repositório, entre na pasta do projeto e altere para a branch do nosso time no gitbash ou cmd:
     `git pull origin grupo-1`
6. Importe o projeto para o seu Eclipse.
    - Arquivo -> Importar -> Maven -> Projetos Maven existentes -> Avançar -> Procurar -> Selecionar passado do projeto -> Concluir
7. É necessária uma mudança no arquivo application.properties.
    - É necessário definir seu endereço, nome de usuário e senha.
    - O arquivo ficará assim:
    ```` 
    spring.datasource.url=jdbc:postgresql://localhost:5432/escola
    spring.datasource.username="seu username do banco postgres"
    spring.datasource.password="sua senha do banco postgres"
    ````

## **Postgre**
> Para executar o projeto, um banco de dados chamado `escola` deve ser criado. Flyway é responsável por criar as tabelas, não se preocupe, ele já está no projeto e os arquivos estão prontos para rodar se o banco de dados tiver sido criado com o nome correto mencionado anteriormente.

1. Para criar o banco de dados, abra postgres. Caso não tenha, instale [aqui.](https://www.postgresql.org/download/)
2. Para criar um esquema.
    >Crie um esquema ``escola``, se você digitar errado, o spring não reconhecerá o banco de dados.

<br>

## **Testando as requisições**
> O Postman foi o software que escolhemos para testar as requisições do aplicativo. Ele pode ser acessado no navegador ou baixado [aqui.](https://www.postman.com/downloads/).

[aqui](https://git.senior.com.br/treinamento/go-dev-2023-spring-equipe/-/tree/grupo-1/assets) requisições do postman.

para importar os pedidos do postman, vá em:

import -> upload -> selecione o arquivo `Escola GoDev.postman_collection` que está na pasta `assets` do projeto.

## **Testes unitários**
> Testes unitários realizados utilizando a biblioteca Mockito, todos CRUD seguindo o padrão de teste unitário.

## **Equipe**

## Equipe

<br>
<table align="center">
  <tr>
     <td align="center">
      <a href="https://www.linkedin.com/in/anacarolinehenschel/">
        <img src="https://media.licdn.com/dms/image/C4E03AQHp9f-gr1UJMg/profile-displayphoto-shrink_800_800/0/1642184775846?e=1679529600&v=beta&t=bWK3MlrDtfu9FTgbrKkkfY19ShVktw2NRQ_nqxDwOl0"
     alt="Ana Caroline Henschel" width="250px;" height="250px"/>
      </a>
      <br />
      <a href="https://www.linkedin.com/in/anacarolinehenschel">Ana Caroline Henschel</a>
    </td>                
    <td align="center">  
      <a href="https://www.linkedin.com/in/filipe-albuquerque/">
        <img src="https://avatars.githubusercontent.com/u/98127981?s=400&u=ef1069ab56dbce000d7bbd088b4bfa14910750dd&v=4"
     alt="Filipe Albuquerque" width="200px;" height="250px"/>
      </a>
      <br />
      <a href="https://www.linkedin.com/in/filipe-albuquerque/">Filipe Albuquerque</a>
    </td>                 
    <td align="center">   
      <a href="https://www.linkedin.com/in/geovani-da-silva-brustolin-278600235/">
        <img src="https://media.licdn.com/dms/image/C4D03AQF3HXi7H5L7-g/profile-displayphoto-shrink_800_800/0/1655064594059?e=1679529600&v=beta&t=cjz7cKeVFZFyEvsWScv8sZ-HBQJPtF2x1rV4srW4Tdc"
     alt="Geovani da Silva Brustolin" width="250px;" height="250px"/>
      <br />
      <a href="https://www.linkedin.com/in/geovani-da-silva-brustolin-278600235/">Geovani da Silva Brustolin</a>
    </td>           
   </tr>
 </table>

 <table align="center">
  <tr>             
    <td align="center">  
      <a href="https://www.linkedin.com/in/joao-puel/">
        <img src="https://media.licdn.com/dms/image/C4E03AQFsCl0Os7h7uQ/profile-displayphoto-shrink_800_800/0/1628680413660?e=1679529600&v=beta&t=nZbIy5rPkW8t57BQYWfhSZKiFXDQHrab9Dzd9hD1n5Q"
     alt="João Ricardo Tasca Puel" width="200px;" height="250px"/>
      </a>
      <br />
      <a href="https://www.linkedin.com/in/joao-puel/">João Ricardo Tasca Puel</a>
    </td>
    <td align="center">
      <a href="https://www.linkedin.com/in/wellingtonklinkowski/">
        <img src="https://media.licdn.com/dms/image/C4D03AQFr_o2xnoSmbA/profile-displayphoto-shrink_800_800/0/1657215454749?e=1679529600&v=beta&t=A02-3nABH4GQ2orkO7o_Fk-p5gmukyzQ6K_kiAlOtNM"
     alt="Wellington Klinkowski" width="250px;" height="250px"/>
      </a>
      <br />
      <a href="https://www.linkedin.com/in/wellingtonklinkowski/">Wellington Klinkowski</a>
    </td>                 
            
   </tr>
 </table>

