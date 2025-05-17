# Jogo de Tabuleiro — Projeto de Programação Orientada a Objetos (POO)

Este projeto é uma implementação em Java de um jogo de tabuleiro desenvolvido como parte da disciplina de Programação Orientada a Objetos. O jogo simula um tabuleiro com 40 casas e permite até seis jogadores, que possuem diferentes perfis de comportamento: sortudo, azarado e normal. Cada jogador avança conforme a soma obtida no lançamento de dois dados.

## Funcionalidades

- Tabuleiro composto por 40 casas.
- Até 6 jogadores simultâneos, cada um com cor e tipo definidos pelo usuário.
- Regras específicas para movimentação, incluindo jogadas extras ao tirar dados iguais.
- Casas com efeitos especiais que alteram a dinâmica do jogo.
- Modo debug para facilitar testes e validação dos comportamentos.

## Regras do jogo

- O objetivo é ser o primeiro a alcançar ou ultrapassar a casa 40.
- Se um jogador tirar dois dados iguais, ganha uma jogada extra.
- Algumas casas no tabuleiro aplicam efeitos específicos:

| Tipo de Casa          | Casas      | Descrição                                            |
|-----------------------|------------|-----------------------------------------------------|
| Perde rodada          | 10, 25, 38 | Jogador perde a próxima rodada.                      |
| Surpresa              | 13         | Jogador muda aleatoriamente seu tipo (sortudo, azarado, normal). |
| Sorte                 | 5, 15, 30  | Jogadores (exceto azarados) avançam 3 casas extras. |
| Retroceder adversário | 17, 27     | Jogador escolhe um adversário para voltar à casa 0. |
| Casa mágica           | 20, 35     | Troca de posição com o jogador que estiver mais atrás (exceto se estiver em último). |

## Tipos de jogadores

- **Sortudo**: Sempre obtém soma dos dados igual ou maior que 7.
- **Azarado**: Sempre obtém soma dos dados igual ou menor que 6.
- **Normal**: Soma dos dados varia livremente entre 2 e 12.

## Aspectos técnicos

- Implementação orientada a objetos, com uso de herança e polimorfismo para diferenciar comportamentos dos jogadores.
- Controle de estado e regras encapsulados para garantir integridade dos dados.
- Utilização de `ArrayList` para gerenciamento dos jogadores.
- Sistema de debug que permite controle manual do avanço dos jogadores para facilitar testes específicos.

## Finalização

Ao final do jogo, o sistema apresenta:

- O vencedor da partida.
- A quantidade de jogadas realizadas por cada jogador.
- A posição final de todos os participantes.

---

Esse projeto serviu para consolidar conceitos de POO e práticas de desenvolvimento em Java, além de proporcionar uma experiência prática na implementação de regras complexas de jogos de tabuleiro.
