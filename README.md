# dna-analisys
## Maven Springboot Application + Spring Data MongoDB

* Para executar esta aplicação, faça o clone deste repositório, <br/>
* Tendo o Maven instalado em sua máquina, execute o comando "mvn clean install" na pasta raiz do projeto<br/>
* O pacote magneto-1.0-SNAPSHOT.jar  do projeto será criado na pasta target<br/>
* Tendo JRE (8 ou maior) instalada, execute o comando "java -jar magneto-1.0-SNAPSHOT.jar"<br/>
* Ou pelo comando maven "mvn spring-boot:run"<br/>

## Gravando as consultas de dnas na base do mongo db

* Instale o mongodb seguindo o tutorial básico e reinicie a aplicação <br/>
https://docs.mongodb.com/manual/administration/install-community/
* Uma collection no banco será criada com o nome de mutant e as pesquisas serão
gravadas nesta collection para a consulta do endpoint "/stats"

