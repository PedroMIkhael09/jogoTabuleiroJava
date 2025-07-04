# 🎲 Jogo de Tabuleiro — Projeto de Programação Orientada a Objetos (POO)

Este projeto é uma implementação em Java de um **jogo de tabuleiro** desenvolvido para a disciplina de **Programação Orientada a Objetos**. A versão atual passou por uma **refatoração completa**, incorporando **boas práticas de desenvolvimento** e **padrões de projeto**, o que melhorou significativamente sua **estrutura, legibilidade e manutenibilidade**.

---

## 🧩 Funcionalidades

- 🔧 Criação de um **tabuleiro totalmente personalizável**, com definição do número de casas e tipos de efeito.
- 🎮 Suporte para **até 6 jogadores**, com **cores** e **tipos personalizados**.
- 🎲 **Sistema de movimentação com regras especiais**, como jogadas extras ao tirar dados iguais.
- 🏠 **7 tipos de casas especiais** com efeitos diversos que alteram a dinâmica do jogo.
- 🧪 Modo de jogo **normal** e **debug** (para testes e validações).

---

## 📜 Regras do Jogo

- O **objetivo** é ser o **primeiro jogador** a **alcançar ou ultrapassar a última casa** do tabuleiro.
- Ao tirar **dois dados iguais**, o jogador ganha **uma jogada extra**.
- As casas podem conter **efeitos especiais**, conforme a configuração inicial:

| Tipo da Casa           | Efeito                                                                 |
|------------------------|------------------------------------------------------------------------|
| **Perde Rodada**       | Jogador perde a próxima rodada.                                        |
| **Surpresa**           | Jogador muda aleatoriamente seu tipo (Sortudo, Azarado ou Normal).     |
| **Sorte**              | Jogadores (exceto Azarados) avançam 3 casas extras.                    |
| **Retroceder Adversário** | Jogador escolhe um adversário para voltar à casa 0.               |
| **Casa Mágica**        | Troca de posição com o jogador que estiver mais atrás (exceto se já for o último). |
| **Jogar de Novo**      | O jogador tem direito a uma nova jogada.                               |
| **Azar**               | O jogador volta 3 casas.                                               |

---

## 🧠 Tipos de Jogadores

| Tipo      | Comportamento ao lançar os dados                       |
|-----------|--------------------------------------------------------|
| **Sortudo**  | Soma dos dados ≥ 7                                    |
| **Azarado** | Soma dos dados ≤ 6                                    |
| **Normal**  | Soma dos dados varia normalmente entre 2 e 12         |

---

## 🏗️ Arquitetura e Padrões de Projeto

O projeto foi cuidadosamente estruturado com base nos **princípios SOLID** e utilização dos seguintes **padrões de projeto**:

- **Facade**: A classe `Jogo` atua como fachada, centralizando a lógica do jogo e simplificando a `Main`.
- **Singleton**: A classe `Tabuleiro` garante uma única instância durante toda a execução.
- **Factory Method**: Usado para criar dinamicamente os objetos `Jogador` e `Casa`, desacoplando lógica de construção.
- **Strategy (Polimorfismo)**: Cada `Casa` implementa seu comportamento através do método abstrato `aplicarRegra()`.

---

## ✅ Qualidade do Código

Utilizamos o **SonarQube** para garantir a qualidade do código. Os principais resultados após a refatoração:

- 🔁 **Redução de duplicações**.
- 🧹 **Diminuição significativa de "code smells"**.
- 🐞 **Nenhum bug ou vulnerabilidade identificados**.
- ✅ Aprovado no **Quality Gate**.

---

## 🚀 Como Executar o Projeto

1. **Clone** este repositório:
   ```bash
   git clone https://github.com/seu-usuario/jogo-tabuleiro.git
   ```
2. **Abra** o projeto em sua IDE Java de preferência (ex: IntelliJ, Eclipse).
3. **Compile** todos os arquivos.
4. **Execute** a classe `Main.java` para iniciar o jogo.

---

## 👥 Autores

- **Pedro Mikhael Maia de Souza**
- **Victor Araújo Silva**
