# Projeto Jogo de Cartas (Versão modificada)

Para ver o projeto em funcionamento, abra o código no VSCode e execute a classe Main


## Projeto Jogo de Cartas (Versão original)

### Autores:
```
Esaú Mascarenhas e Resemblinck Freitas
```

### Como o projeto implementa o jogo Paciência:
O jogo foi implementado na linguagem de programação JAVA (versão 8). As classes utilizadas e seus relacionamentos são apresentadas nas sessões seguintes, assim como os padrões de projetos utilizados na tentativa de se obter um código organizado, manutenível e aberto a possíveis adições no futuro.

Este jogo foi implementado pensando na sua utilização via terminal, sendo assim para rodar o jogo é necessário ter o [jdk 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html "jdk 8") instalado na máquina.

### Como jogar:

1. Baixe o projeto 
2. Abra o terminal na pasta /game do projeto e digite: java -jar CardGame.jar
3. O jogo se inicia com uma mesa já formada e um menu de opções para o usuário escolher a próxima jogada. À esquerda encontra-se a numeração referente as pilhas.
4. Para mover uma carta, basta selecionar a opção 1, em seguida é preciso informar a pilha de origem e a pilha de destino, seguindo as regras do jogo.
5. Para visualizar a próxima carta do estoque, basta selecionar a opção 2 do menu de opções.
6. Para visualizar uma carta no topo de uma Tableau, basta selecionar a opção 3 e informar o número da mesma.
7. A opção 4 permite a movimentação de mais de uma carta entre as tableaus. É necessário informar a pilha de origem, de destino e a quantidade de cartas.
8. A opção 5 alterna a quantidade (1 ou 3) de cartas do estoque a serem mostradas por vez.
9. É possível iniciar um novo jogo a qualquer momento selecionando a opção 6.
10. Para encerrar o jogo é só escolher a opção 7 do menu.

### O sistema:

#### Diagrama conceitual:

![ConceptualDiagram](https://user-images.githubusercontent.com/29510159/117089079-da70f680-ad2a-11eb-9137-4cfd97382a60.jpg)

#### Diagrama de classes:

![ClassDiagram](https://user-images.githubusercontent.com/29510159/117088468-dd6ae780-ad28-11eb-85d8-a2b1bb9bc291.jpg)

### Descrição das entidades:

#### Classe Main
Principal classe do sistema, é responsável por iniciar o jogo a ser jogado pelo usuário. Ela contém um relacionamento com a classe **CardGame**, a partir da qual o usuário poderá jogar um jogo de cartas. De momento, o único jogo presente é o Paciência. A classe main apenas instancia e inicia a classe **CardGame**.


#### Classe CardGame
Fazendo uso do padrão de projetos **Strategy**, a classe **CardGame** foi implementada como uma classe abstrata que será utilizada como um modelo a ser seguido, definindo uma estratégia em comum para jogos de cartas. Ela possui como atributos uma lista de Pilhas, um indicador de parada e um indicador da quantidade de cartas a serem exibidas do estoque. A partir da herança, todos esses atributos serão visíveis para os seus filhos. 

Esta classe possui os métodos *createPiles()*, *checkWinner()*, *gameLoop()*, *isValidMove()*, *restart()* e *showMenu()* que serão diferentes a depender do jogo em questão, portanto eles não são implementados. Além disso, contém também os métodos *start()*, *moveCard()*, *showPiles()* e *quit()* que são implementados e tem nomes autoexplicativos.

#### Classe Solitaire
Essa classe herda de **CardGame** e implementa os métodos não implementados anteriormente de acordo com as regras do jogo Paciência. Sendo assim, os métodos *createPiles()*, *checkWinner()*, *gameLoop()*, *isValidMove()*, *restart()* e *showMenu()* são implementados para satisfazer essas regras específicas. 

#### Classe Pile
Essa classe é utilizada para pilhas de fundação, estoque, descarte e fileiras. Ela possui como atributos uma pilha de cartas, um identificador que será usado para diferenciar os tipos de pilhas e um nome.

Além dos construtores, essa classe possui os métodos *init()* usado para iniciar a pilha através de uma lista de cartas recebidas, *addCard()*, *addCardInFinal()* adiciona no final da pila, *isEmpty()*, *moveCard()*, *pickLastCard()*, *pickLastCards()*, *removeLastCard()*, *removeBelow()* remove de baixo da pilha, *show()*, *size()*, *turnUpCard()* vira a carta para cima, *toString()*  e *name()*.

#### Classe Card
Esta classe representa uma carta do baralho e possui os atributos *cor*, *valor*, *naipe* e *faceDown* que indica se ela está virada ou não. 

#### Classe Deck
Nessa classe as cartas são embaralhadas e prontas para serem distribuídas para o jogo.  São criadas 52 cartas, sendo 4 de cada naipe.

### Padrões Utilizados
Visando encapsular os algoritmos de jogos de cartas, tornando-os intercambiáveis e permitindo a variação independentemente dos clientes que os utilizam, o padrão Strategy foi utilizado.

### Pontos Fortes
- Utilização de padrões de projeto pensando na organização do código e manutenibilidade.
- Separação da regra de negócio do algoritmo que deve ser aplicado (aplicando o princípio da responsabilidade única).
- Utilização dos fundamentos do Clean Code.

### Pontos Fracos
- Poderia ter sido utilizado um padrão MVC para melhor organização do projeto.
- Pode ser complexo criar toda uma hierarquia de classes para aplicar novos algoritmos, caso muitos jogos sejam adicionados.










