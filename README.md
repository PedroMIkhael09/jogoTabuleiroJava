🎲 Jogo de Tabuleiro - Projeto de POO em Java
Esse projeto é um jogo de tabuleiro simples que desenvolvi para a disciplina de Programação Orientada a Objetos (POO). A ideia é simular um tabuleiro com 40 casas e até 6 jogadores, cada um com um estilo diferente: sortudo, azarado ou normal. Os jogadores avançam conforme o resultado da soma de dois dados.

Como jogar
Rode o arquivo Main.java.

Escolha quantos jogadores vão participar (de 2 a 6).

Defina a cor e o tipo de cada jogador.

O jogo começa! A cada rodada:

Cada jogador joga dois dados.

A soma dos dados define quantas casas ele avança.

Algumas casas especiais aplicam efeitos diferentes.

Regras do jogo
O tabuleiro tem 40 casas.

Quem chegar ou passar da casa 40 primeiro vence.

Se o jogador tirar dois dados iguais, ele joga de novo.

Algumas casas têm efeitos especiais:

Casa	Número	Efeito
Perde rodada	10, 25, 38	O jogador perde a próxima rodada.
Surpresa	13	O jogador muda de tipo aleatoriamente (sortudo, azarado ou normal).
Sorte	5, 15, 30	Jogadores que não são azarados avançam 3 casas extras.
Retroceder adversário	17, 27	O jogador escolhe um adversário para voltar para a casa 0.
Casa mágica	20, 35	Troca a posição com o jogador que está mais atrás (se não for o último).

Tipos de jogadores
Sortudo: Sempre tira soma dos dados maior ou igual a 7.

Azarado: Sempre tira soma dos dados menor ou igual a 6.

Normal: Sorteia qualquer valor possível (2 a 12).

Detalhes técnicos
O projeto foi feito em Java, usando:

Herança e polimorfismo para modelar os diferentes tipos de jogador.

Encapsulamento para proteger os dados.

Os jogadores são guardados numa lista (ArrayList).

Tem um modo Debug que permite escolher manualmente a casa de destino, para facilitar testes.

Modo Debug
Nesse modo especial, você pode informar manualmente a casa para onde o jogador vai, ao invés de jogar os dados, o que ajuda muito a testar os efeitos das casas e garantir que tudo está funcionando.

Final do jogo
Mostra quem venceu (quem chegou ou passou da casa 40 primeiro).

Exibe quantas jogadas cada jogador fez.

Mostra a posição final de todos.
