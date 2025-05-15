# 🧩 Jogo de Tabuleiro - Programação Orientada a Objetos (POO)

Este é um projeto de um jogo de tabuleiro desenvolvido em Java para a disciplina de **Programação Orientada a Objetos (POO)**. O jogo simula um tabuleiro com 40 casas e permite até **6 jogadores** com comportamentos distintos: **sortudo**, **azarado** e **normal**. Cada jogador é representado por uma cor e avança conforme o lançamento de dois dados.

## 🎮 Como jogar

1. **Execute o arquivo `Main.java`** 
2. Escolha a quantidade de jogadores (mínimo 2, máximo 6).
3. Atribua uma cor e um tipo para cada jogador.
4. O jogo começa! A cada rodada:
   - Os jogadores lançam dois dados.
   - A soma determina o avanço no tabuleiro.
   - Algumas casas têm efeitos especiais.

---

## 🧠 Regras do jogo

- O tabuleiro possui **40 casas**.
- O primeiro jogador a **chegar ou ultrapassar a casa 40 vence**.
- Se o jogador **tirar dois dados iguais**, ele joga novamente.
- Os efeitos especiais das casas são:

| Tipo de Casa      | Casas             | Efeito |
|-------------------|-------------------|--------|
| 🛑 Perde rodada    | 10, 25, 38         | O jogador perde a próxima rodada. |
| ❓ Surpresa        | 13                | O jogador muda aleatoriamente de tipo (azarado, sortudo ou normal). |
| 🍀 Sorte           | 5, 15, 30         | O jogador (exceto azarado) avança 3 casas extras. |
| 🔁 Retroceder outro| 17, 27            | O jogador escolhe um adversário para voltar à casa 0. |
| 🪄 Casa mágica     | 20, 35            | Troca de posição com o jogador que estiver mais atrás (se não for o último). |

---

## 🧬 Tipos de Jogadores

- **JogadorSortudo**: sempre tira soma **≥ 7**.
- **JogadorAzarado**: sempre tira soma **≤ 6**.
- **JogadorNormal**: tira qualquer valor possível (2 a 12).

---

## ⚙️ Requisitos Técnicos

- Projeto feito em **Java** com uso de:
  - **Herança** e **Polimorfismo**
  - **Encapsulamento**
- Arquitetura separada por pacotes (`jogo`, `jogador`, etc).
- Utilização de **`ArrayList`** para armazenar jogadores.
- Suporte a modo **Debug**: permite inserir manualmente a casa que o jogador irá para testes.

---

## 🧪 Modo Debug

Para fins de testes e correções, existe um modo especial chamado **Debug**, onde o usuário pode informar a casa desejada para cada jogador, ao invés de usar o valor dos dados. Isso facilita a verificação de efeitos especiais e comportamentos do jogo.

---

## 🏁 Final do jogo

- É mostrado o vencedor (primeiro a atingir ou ultrapassar a casa 40).
- Exibe a quantidade de jogadas de cada jogador.
- Exibe a posição final de todos os jogadores.

---


