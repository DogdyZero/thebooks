# thebooks

Projeto The Books

O projeto esta em desenvolvimento afim de aplicar novos conhecimentos, tais como framework e padrões de projeto.
Tecnologias utilizadas (Java 8, Maven, JSF, PrimeFaces, Hibernate)

View - utilizado o framework PrimeFaces (JSF)

ViewController - JSF

Controller - utilizado o padrão Facede para implementação de uma camada intermediária entre a View e o DAO

Foi implementada uma AbstractDAO, que é responsável pela criação das sessões do hibernate, junto com o insert, update e Select


Para funcionar o select é necessário criar uma classe que implementa a IFactoryQuery, onde nelas devem ser implementadas todas
as HQL, com o tipo da consulta como uma chave.


Para implementar o projeto é necessário

criar o banco de dados "thebooks" (utilizado o postgres no projeto original) 

em hibernate.cfg.xml, alterar o nome usuário e senha e para DDL create (para criar as tabelas)

feito isso, fazer o git clone (caminho aqui do github)

