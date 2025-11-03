# 🎬 Projeto: Cinema

Projeto final da disciplina de Backend, que consiste no desenvolvimento de uma API voltada ao universo cinematográfico.
O sistema permitirá consultar informações sobre filmes, criar playlists personalizadas e gerenciar dados relacionados ao tema, oferecendo uma experiência completa para os amantes de cinema.

Equipe: Calebe Arlan, Gustavo Gonçalves e Matheus Araújo

## Descrição do Problema

Muitas aplicações sobre filmes dependem de várias fontes de dados e não oferecem uma forma simples de gerenciar informações sobre filmes e usuários em um só lugar.
A API Cinema busca resolver isso oferecendo uma interface que permite:

- Gerenciar filmes e usuários (CRUD completo);

- Criar e organizar playlists personalizadas;

- Consultar dados externos sobre filmes por meio de uma API pública;

- Centralizar e padronizar o acesso a essas informações para uso em diferentes aplicações.

Com isso, a API facilita o desenvolvimento de sistemas voltados ao tema de cinema, tornando o processo mais prático e organizado.

## TECNOLOGIAS UTILIZADAS: 
- Java Spring.
- MYSQL.
- Api de filmes.

## LIMITAÇÕES DO PROJETO: 
- Iniciando sem frontend.
- Limite de requisições por usuario.
- Filmes limitados aos dados na API e ao nosso pequeno banco de dados.


### Entidades
| Entidade          | Descrição                                                                                          | Principais Campos                                                                                                              |
| ----------------- | -------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| **Usuário**       | Representa quem interage com o sistema, podendo criar playlists e avaliar filmes.                  | `id (int)`, `nome (varchar)`, `email (varchar)`                                                                                |
| **Filme**         | Armazena informações sobre filmes cadastrados, que podem ser avaliados e adicionados em playlists. | `id (int)`, `titulo (varchar)`, `diretor (varchar)`, `genero (varchar)`, `ano (int)`, `sinopse (text)`, `nota_media (decimal)` |
| **Avaliação**     | Registra a nota e o comentário de um usuário sobre um filme.                                       | `id (int)`, `usuario_id (int)`, `filme_id (int)`, `nota (int)`, `comentario (text)`, `data_avaliacao (datetime)`               |
| **Playlist**      | Lista de filmes criada por um usuário.                                                             | `id (int)`, `nome (varchar)`, `descricao (text)`, `usuario_id (int)`                                                           |
| **PlaylistFilme** | Relaciona filmes às playlists (relação N:N).                                                       | `playlist_id (int)`, `filme_id (int)`
<img width="898" height="804" alt="DiagramaBancoDados_BackEnd" src="https://github.com/user-attachments/assets/779994fc-37f5-4bad-80fc-48b48cc74266" />

## Rotas
| Descrição                                       | URI                      | Método HTTP | Corpo                 | Resposta Esperada | Erros Esperados                                |
| ----------------------------------------------- | ------------------------ | ----------- | --------------------- | ----------------- | ---------------------------------------------- |
| Retornar todos os filmes                        | `/filmes`                | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Nenhum filme encontrado.     |
| Retornar um filme específico por id             | `/filme/:id`             | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Filme não encontrado.        |
| Cadastrar novo filme                            | `/filme`                 | `POST`      | `Dados do filme`      | `201 Created`     | `400 Bad Request` - Dados inválidos.           |
| Atualizar um filme                              | `/filme`                 | `UPDATE`    | `Dados do filme`      | `200 OK`          | `404 Not Found` - Filme não encontrado.        |
| Deletar um filme                                | `/filme/:id`             | `DELETE`    | Vazio                 | `200 OK`          | `404 Not Found` - Filme não encontrado.        |
| Procurar um filme na API Externa por queryParam | `/filme/buscar`          | `GET`       | `queryParam`          | `200 OK`          | `404 Not Found` - Filme não encontrado.        |
| Retornar todas as playlists                     | `/playlists`             | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Nenhuma playlist encontrada. |
| Retornar os itens dentro da playlist específica | `/playlist/:id`          | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Playlist não encontrada.     |
| Cadastrar uma nova playlist                     | `/playlist`              | `POST`      | `Dados da playlist`   | `201 Created`     | `400 Bad Request` - Dados inválidos.           |
| Atualizar uma playlist                          | `/playlist`              | `UPDATE`    | `Dados da playlist`   | `200 OK`          | `404 Not Found` - Playlist não encontrada.     |
| Deletar uma playlist                            | `/playlist/:id`          | `DELETE`    | Vazio                 | `200 OK`          | `404 Not Found` - Playlist não encontrada.     |
| Retornar todos os usuários                      | `/usuarios`              | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Nenhum usuário encontrado.   |
| Retornar um usuário específico por id           | `/usuario/:id`           | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Usuário não encontrado.      |
| Cadastrar um novo usuário                       | `/usuario`               | `POST`      | `Dados do usuário`    | `201 Created`     | `400 Bad Request` - Dados inválidos.           |
| Atualizar um usuário                            | `/usuario`               | `UPDATE`    | `Dados do usuário`    | `200 OK`          | `404 Not Found` - Usuário não encontrado.      |
| Deletar um usuário                              | `/usuario/:id`           | `DELETE`    | Vazio                 | `200 OK`          | `404 Not Found` - Usuário não encontrado.      |
| Retornar avaliações de um filme específico      | `/avaliacao/:filmeId`    | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Filme não encontrado.        |
| Retornar uma avaliação específica               | `/avaliacao/:id`         | `GET`       | Vazio                 | `200 OK`          | `404 Not Found` - Avaliação não encontrada.    |
| Cadastrar uma nova avaliação                    | `/avaliacao/:id/filmeId` | `POST`      | `Dados da avaliação`  | `201 Created`     | `400 Bad Request` - Dados inválidos.           |
| Atualizar uma avaliação                         | `/avaliacao/:id`         | `UPDATE`    | `Dados da avaliação`  | `200 OK`          | `404 Not Found` - Avaliação não encontrada.    |
| Deletar uma avaliação                           | `/avaliacao/:id`         | `DELETE`    | Vazio                 | `200 OK`          | `404 Not Found` - Avaliação não encontrada.    |



## [Excalidraw](https://excalidraw.com/#json=XndpJUALurBa1lXttf3lO,RtldGjyZGuKDhNZ2-2XQhg)
