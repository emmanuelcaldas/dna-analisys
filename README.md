# dna-analisys
endpoints: 
* Análise de DNA
POST - https://dna-analisys.herokuapp.com/mutant <br/>
json body
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

* Estatísticas de dnas mutantes encontrados
GET - https://dna-analisys.herokuapp.com/stats

## Maven Springboot Application + Spring Data MongoDB

* Para executar esta aplicação, faça o clone deste repositório, <br/>
* Tendo o Maven instalado em sua máquina, execute o comando "mvn clean install" na pasta raiz do projeto<br/>
* O pacote magneto-1.0-SNAPSHOT.jar  do projeto será criado na pasta target<br/>
* Tendo JRE (8 ou maior) instalada, execute o comando "java -jar magneto-1.0-SNAPSHOT.jar"<br/>
* Ou pelo comando maven "mvn spring-boot:run"<br/>

## Gravando as consultas de dnas na base do mongo db

* Instale o mongodb seguindo o tutorial básico e reinicie a aplicação <br/>
https://docs.mongodb.com/manual/administration/install-community/
* Altere a propriedade spring.data.mongodb.uri para mongo://hostDB:port/databaseName que vocẽ criar
* Uma collection no banco será criada com o nome de verifiedDna e as análises de dna serão
gravadas nesta collection para a consulta posterior no endpoint "/stats"

