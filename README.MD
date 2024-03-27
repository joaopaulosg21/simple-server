# SIMPLE SERVER

<p align="center">
  <img alt="Static Badge" src="https://img.shields.io/badge/status-desenvolvimento-green?style=for-the-badge">

</p>

> Uma implementação de um servidor web em java usando sockets.

### Funcionalidades

- [x] Possibilidade de adicionar rotas no server

- [x] Criação de funções para as rotas através da funcional interface [RouteRunner](https://github.com/joaopaulosg21/simple-server/blob/main/src/main/java/projeto/server/interfaces/RouteRunner.java)

- [x] Criação de uma Response padrão no caso de a rota solicitada na request não existir no server

- [x] Nova feature para criação de rotas atravês da abstract class [Route](https://github.com/joaopaulosg21/simple-server/blob/main/src/main/java/projeto/server/interfaces/Route.java)

- [x] Feature para identificar rotas com path variables e adiciona-las no objeto de Request

- [ ] Feature para identificar query params e adicionar no objeto de Request