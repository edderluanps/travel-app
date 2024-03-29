# Trvael App

### Projeto Versão Web
Link do projeto em produção <a href="http://travel-io.netlify.app">aqui</a>.


### Projeto Versão App Mobile
<img src="https://github.com/edderluanps/travel-app/blob/25cef94bd9b023d994778c72e9dd9815cf3575ae/documentos/imgs/telas1.png">
<br>
<img src="https://github.com/edderluanps/travel-app/blob/25cef94bd9b023d994778c72e9dd9815cf3575ae/documentos/imgs/telas2.png">

## Objetivo
 
O projeto Travel App é um projeto pessoal de caracterizado como uma plataforma de agencia de viagens. O projeto foi criado com o objetivo de praticar e fixar o que foi visto nesses ultimos anos de estudo em Java/Spring, MySQL, Angular e Ionic, bem como iniciar em novas tecnologias. (Lista de tecnologias utilizadas no projeto a seguir)



## Funções principais

### Cliente

* Compra de pacotes turísticos com voos e hospedagem inclusos.
* Acesso ao blog do site com dicas de viagens, curiosidades e reviews postados pela equipe de admins.

### Admin

* Adicionar, listar, editar e remover pacotes turísticos e postagens no blog.
* Aefinir outros usuários como Admins, bem como excluir o cadastro dos mesmos.



## Detalhes

O projeto contará com área de clientes e de admins, onde o cliente poderá selecionar passagens, voos, pacotes completos e avaliar os serviços obtidos, e os admins poderão ter acesso completo aos dados referente a voos, pacotes e afins, além de fazer publicações no Blog do site, com dicas e indicações aos leitores.

<img src="https://github.com/edderluanps/travel-app/blob/b7a90bc65351851c513c0c63024d644b392e2090/documentos/Diagrama.jpg">



## Tecnologias

### Backend

* Astah (Modelagem UML)
* Java 17
* Maven
* Spring Framework
* Spring Web
* Spring Data
* Spring Security
* Lombok
* JUnit
* Mockito
* OpenPDF
* JWT

### Banco de dados

* H2 Database
* MySQL

### Frontend

* TypeScript
* Angular 14.1.0
* Material Design
* Font Awesome
* Google Fonts
* SweetAlert2
* Pixabay (Banco de imagens)
* Freepik (Banco de imagens)
* FreeLogoDesign

### App

* Ionic 6.20.8
* IonIcons V5+
* Date FNS



### Deploy

* Backend: Railway
* DB: Railway
* Frotend: Netlify
* App: APK file (Implementação Futura)



## Instalação Passo a passo

* Obs: O processo descrito priorizará o MS Windows.

### Java e Spring
* Baixe e instale o Java, recomendo a versão 17. Clique <a href="https://www.oracle.com/br/java/technologies/downloads/#jdk17-windows" target="_blank" rel="noopener noreferrer"> aqui </a> para baixar;
* Agora, utilize uma IDE para abrir o projeto, sugiro a mesma que utilizei: Apache NetBeans IDE 15. Baixe clicando <a href="https://netbeans.apache.org/download/nb15/" target="_blank" rel="noopener noreferrer"> aqui </a>;
* Caso queira optar por outra IDE, <a href="https://spring.io/tools" target="_blank" rel="noopener noreferrer"> Spring Tool Suite </a> e <a href="https://www.jetbrains.com/pt-br/idea/" target="_blank" rel="noopener noreferrer"> Intellij IDEA </a> são excelentes;
* Em caso de dúvidas na configuração das IDEs, consulte a documentação dos mesmos ou Google/YouTube;
* Com o ambiente pronto, baixe a pasta Backend como arquivo zip, entre em uma das IDEs selecionadas e importe o projeto;
* Caso não saiba importar, clique <a href="https://itsnatrivera.wordpress.com/2017/04/28/how-to-import-a-project-in-netbeans/" target="_blank" rel="noopener noreferrer">aqui</a> para aprender o passo a passo. Créditos ao autor;


### Banco de dados MySQL
* O projeto cria automaticamente as tabelas do banco de dados MySQL assim que iniciado, mas é preciso ter o SGBD instalado na máquina;
* Como sugestão, indico o <a href="https://www.apachefriends.org/pt_br/download.html" target="_blank" rel="noopener noreferrer"> Xampp </a>, rápido e prático, mas não há problema em utilizar outro, desde que seja MySQL;
* Ao finalizar a instalação, o Xampp Controll será aberto, precione 'start' nos serviços Apache e MySQL.
  <img src="https://devtuts.butlerccwebdev.net/testserver/xampp-control-panel.png" style="height:500px; width: 750px">
  * Créditos da imagem: Devtuts
* Agora, clique em 'admin' ao lado do serviço 'MySQL', vá até apágina inicial do Xampp e crie um novo banco com o nome da sua preferência.
* Perceba que esse banco deverá ter o mesmo nome apresentado na 1º linha do arquivo 'applications-dev.properties', no projeto.
* Ex: Se o banco se chama 'fullstack' sem aspas, a 1º linha do arquivo 'applications-dev.propperties' deve ser <code>spring.datasource.url=jdbc:mysql://localhost:3306/fullstack</code>.
* Volte a sua IDE, inicie o projeto e o banco será preenchido com as tabelas mapeadas pelo JPA nas classes de entidade.


### Angular
* Para instalar o Angular é necessário ter o <a href="https://nodejs.org/en/download/" target="_blank" rel="noopener noreferrer">  Node.js </a> instalado na máquina;
* Node instalado, hora de instalar o Angular:
* Abra um terminal (CMD ou Git Bash), e digite o comando <code>npm install -g @angular/cli</code>.
* Se preferir, pode instalar uma versão específica ou a mesma em que o projeto foi construido: <code>npm install -g @angular/cli@14.1.0</code>
* Para verificar, abra um terminal (CMD ou Git Bash) e digite <code>ng version </code>.
* Para codar, é necessário um editor de código, sugiro o <a href="https://code.visualstudio.com/download" target="_blank" rel="noopener noreferrer">VS Code</a>, mas o <a href="https://www.sublimetext.com/3" target="_blank" rel="noopener noreferrer">Sublime Text</a> também é uma boa opção.
* Com o editor instalado, baixe a pasta Frontend como arquivo zip, extraia a pasta para um diretório de sua preferência;
* Para abrir o projeto Frontend no editor, use a barra de opções ou entre na pasta do projeto, abra um terminal dentro da pasta, e digite <code>code .</code>.
* Pronto, projeto Frontend aberto no editor de código.
* Para iniciar o projeto, abra um novo terminal dentro do Editor (Procure na barra de ferramentas na parte de cima da tela);
  <img src="https://media.geeksforgeeks.org/wp-content/uploads/20220929190345/creatingfileusingterminalvscode2.png" style="height:500px; width: 1000px">
  * Créditos da imagem: Geeksforgeeks
* Digite <code>ng serve</code> ou <code>ng s</code> no terminal, e a aplicação irá iniciar. Para abrir no seu navegador, vá até o endereço: http://localhost:4200.


### Ionic
* A instalação do Ionic é semelhante a do Angular;
* Lembrando, o Node DEVE estar instalado na máquina.
* Na dúvida, verifique... abra um terminal em qualquer pasta vazia (CMD ou Git Bash) e digite <code>node -v</code>. Irá retornar a versão do Node presente na sua máquina.
* Ainda no terminal, digite o comando <code>npm install -g @ionic/cli</code> para instalar a versão mais recente do Ionic na sua máquina.
* Ou se preferir, instale a versão do Ionic utilizada no projeto: <code>npm install -g @ionic/cli@6.20.8</code>.
* Para verificar, abra um terminal (CMD ou Git Bash) e digite <code>ionic --v</code>.
* Se o passo a passo do tópico anterior (Angular) tiver sido seguido, presumo que ja tenha um editor de código, caso não tenha, recomendo voltar ao mesmo no tópico de editores.
* Para abrir o projeto Mobile no editor, use a barra de opções ou entre na pasta do projeto, abra um terminal dentro da pasta, e digite <code>code .</code>.
* Pronto, projeto Mobile aberto no editor de código.
* Para iniciar o projeto, abra um novo terminal dentro do Editor (veja o passo a passo no tópico acima);
* Digite <code>ionic serve</code> no terminal, e a aplicação irá abrir no seu navegador.


### Deploy
* É preciso ter o seu projeto em um repositório do Github.
* <a href="https://www.youtube.com/watch?v=aINs3ouaoJk">Link para tutorial de como subir um projeto para o github.</a> Créditos ao autor. 
* <a href="https://www.youtube.com/watch?v=5sVxvF47dcU">Link para o processo de deploy do Backend e Banco de dados detalhado no Railway.</a> Créditos ao autor.
* <a href="https://www.youtube.com/watch?v=HxeuNAXFGe8">Link para o processo de deploy do Frontend detalhado no Netlify.</a> Créditos ao autor.


### Atenção

* Ao criar o banco no Railway e adicionar as variáveis do banco no aqruivo <code>application-prod.properties</code> do projeto, as tabelas serão preenchidas automaticamente na plataforma quando o projeto for iniciado. Mas se prefereir, é possível fazer manualmente adicionando as queries e rodando uma por vêz.
* O projeto é monorepo, ou seja, backend e frontend no mesmo repositório. Sendo assim, preste atenção ao indicar o diretório raiz de cada parte do projeto na hora do deploy.
* Caso opte por manter o projeto como monorepo, indique o backend como <code>/backend</code> no Railway ou outra ferramenta, e o frontend como <code>/frontend</code> no Netlify ou outra ferramenta.
* Se utilizar repositórios separados, desconsidere a etapa acima.

<img src="https://global.discourse-cdn.com/netlify/optimized/3X/c/2/c2adb7f2d63f8f7531e70d0fb89b491f5eb7d98b_2_690x364.png" style="height:500px; width: 1000px">
  * Créditos da imagem: Suporte Netlify


## Licença

Copyright © 2022 <a href="https://github.com/edderluanps">Edder Luan</a>

Este projeto está sob licença MIT.

