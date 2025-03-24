# 📱 Screen-Match

<img src = "img/Demonstração.gif" alt = "GIF da demonstração do projeto"/>

## 📰 Descrição

Projeto trabalhado durante a primeira e a segunda formação de Java na Alura

Esse projeto simula um serviço de streaming de filmes e séries, que **exibem suas respectivas informações (nome do título, ano de lançamento, duração, nota...)**

> Esse projeto utiliza da <a href = "https://www.omdbapi.com/">API da "OMDb"</a>, que permite uma **pesquisa mais precisa e "real"** dos filmes e séries 
> * Utilizando agora da **biblioteca JSON "Jackson"**, o projeto apresenta conceitos de **lambdas e streams** para gerar fluxos de dados
> * O projeto agora também utiliza da <a href = "https://mymemory.translated.net/">API da "MyMemory"</a>, uma API que **traduz a sinopse vinda da "OMDb"**
> * Adicionalmente, o projeto apresenta **conexão com banco de dados Postgres e consultas utilizando Spring Data JPA** 
> * Agora, também, o projeto apresenta **uma aplicação front-end (utilizando do Spring Web)** 
>   * Em adição a isso, foram feitas algumas **alterações opcionais às páginas,** como: **página de "Categorias" exibe o seu título de acordo com a categoria,** melhor organização do código, diferentes links de redirecionamento...

## 💻 Tecnologias Utilizadas
`Trabalhado durante o curso:`

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-plain.svg" height = "40" alt = "Ícone Java"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" height = "40"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg" height = "40"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/json/json-plain.svg" height = "40"/> 

`Modificado para novas implementações OPCIONAIS:`

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/html5/html5-original.svg" height = "40" alt = "Ícone HTML"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/css3/css3-original.svg" height = "40" alt = "Ícone CSS"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/javascript/javascript-original.svg" height = "40" alt = "Ícone Java"/> 

`Desenvolvido anteriormente pela Alura:`

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/html5/html5-original.svg" height = "40" alt = "Ícone HTML"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/css3/css3-original.svg" height = "40" alt = "Ícone CSS"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/javascript/javascript-original.svg" height = "40" alt = "Ícone Java"/> 

## 🛠️ Como executar o programa 

* Passo 1: Instale <a href = "https://www.enterprisedb.com/downloads/postgres-postgresql-downloads">a versão mais recente do Postgres</a>

* Passo 2: Durante a instalação, **coloque a senha *"123" (ou outra senha fácil)***
  * Você pode **desmarcar a opção de inicializar o "Stack Builder" no final da instalação**
<img src = "img/Passo1.1.jpg">
<img src = "img/Passo1.2.jpg">

- Passo 3: Baixe o ZIP do projeto ***(escolha a versão em "Releases" ou apenas clique em "Code")*** e descompacte a pasta
<img src = "img/Passo0_1.jpg">
<img src = "img/Passo0_2.jpg">

- Passo 4: Abra o projeto utilizando a <a href = "https://www.jetbrains.com/pt-br/idea/">IDE do Intellij</a> e clique em **"Setup SDK"**
<img src = "img/Passo1.5.jpg">
<img src = "img/Passo1.jpg">

- Passo 5: Clique em **"Download JDK"**
<img src = "img/Passo2.jpg">

- Passo 6: Instale a versão mais recente (ou, em caso de problemas, ***instale a versão demonstrada na imagem***)
<img src = "img/Passo3.jpg">

- Passo 7: Agora, dentro de ***src/main/resources/application.properties,*** copie o nome do banco de dados *(e mude a senha, caso tenha alterado)*
<img src = "img/Passo1.6.jpg">

* Passo 8: Abrindo o **Postgres**, **insira a senha** e, clicando com o botão direito nos "databases", crie um novo banco de dados **com o nome copiado anteriormente**
<img src = "img/Passo12.jpg">
<img src = "img/Passo1.3.jpg">
<img src = "img/Passo1.4.jpg">

- Passo 9: Após isso, abra o projeto com o <a href = "https://code.visualstudio.com/">Visual Studio Code</a> e **instale a extensão do "Live Preview"**
<img src = "img/Passo10.jpg">

- Passo 10: Abra o **"index.html"** com o Live Preview *(demonstração na imagem)*
<img src = "img/Passo11.jpg">

- Passo 11: Após isso, volte ao Intellij e **procure a classe principal para executar o programa**
<img src = "img/Passo4.jpg">

## 🏅 Certificado de Conclusão 

<img src = "img/Certificado.jpg" alt = "Certificado de Conclusão da Alura"/> 

## 🙋 Autores
[<img loading="lazy" src="https://avatars.githubusercontent.com/u/136634888?v=4" width=80 alt = "Ícone da Foto de Perfil"> <br> <sub> Gabriel Possato </sub>](https://github.com/possatogabriel)
<br>
<br>
<p align = "center"> <img alt="Badge de Concluído" src="https://img.shields.io/badge/STATUS%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20-concluído-green?style=for-the-badge"> <br/> <img src = "img/alura1.png" height = "50" alt = "Logo da Alura"></p>