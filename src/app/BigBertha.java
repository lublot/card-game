// package app;

// public class BigBertha extends CardGame{
//   private Deck deck;
    
// 	public BigBertha(){
//     super();
//     turnCardsFromStock = 1;
//   }
    
// 	@Override
//     public void createPiles() {
// 		deck  = new Deck();
//     piles.add(new Pile(1, "ESTOQUE", deck.getCards(24)));
//     piles.add(new Pile(2, "DESCARTE"));
//     piles.add(new Pile(3, "FUNDACAO 1"));
//     piles.add(new Pile(4, "FUNDACAO 2"));
//     piles.add(new Pile(5, "FUNDACAO 3"));
//     piles.add(new Pile(6, "FUNDACAO 4"));
    
//     //Inicia as pilhas de fileiras com cartas aleatorias
//     for(int i=0; i<7; i++) {
//       String name = String.format("TABLEAU %d", i+1);
//       Pile pile = new Pile(piles.size()+1, name, deck.getCards(i+1));
//       pile.turnUpCard();
//       piles.add(pile);
//     }
//   }

// 	@Override
// 	public void checkWinner(){
// 		int foundationIndexes[] = {2, 3, 4, 5};
// 		for(int index: foundationIndexes)
// 			if(piles.get(index).size()!=13) return;
// 		System.out.println("[Parabens voce completou todas as FUNDACOES!]");
// 		quit();
// 	}

// 	@Override
// 	public void gameLoop(){
// 		showPiles();
// 		showMenu();
// 		int option = input.readInt("");
// 		try {
// 			switch(option){
// 				case 1: moveCardFromTo();			break;
// 				case 2: moveCardFromStockToWaste(); break;
// 				case 3: turnUpTableauCard();		break;
// 				case 4: moveOneOrMoreCards();		break;
// 				case 5: turnCardsFromStock = turnCardsFromStock==1? 3:1; break;
// 				case 6: restart();					break;
// 				case 7: quit();						break;
// 				default: System.out.println("[Informe uma opcao valida!]");
// 			}
// 		}
// 		catch(NullPointerException e) { System.out.println("[Informe um valor valido!]"); }
// 		catch(Exception e) { System.out.println(e.getMessage()); }
// 	}

// 	@Override
// 	public boolean isValidMove(int fromIndex, int toIndex){
// 		Pile fromPile = piles.get(fromIndex-1);
// 		Pile toPile   = piles.get(toIndex-1);

// 		if(fromPile.name().equals("ESTOQUE")  && toPile.name().equals("DESCARTE")) return true;
// 		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("FUNDACAO")) return true;
// 		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("TABLEAU"))  return true;
// 		if(fromPile.name().equals("FUNDACAO") && toPile.name().equals("TABLEAU"))  return true;
// 		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("TABLEAU"))  return true;
// 		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("FUNDACAO")) return true;
// 		return false;
// 	}

// 	@Override
// 	public void showMenu() {
// 		System.out.println("====================================================================================");
// 		System.out.println("Escolha uma opcao:");
// 		System.out.println("1 - Mover carta");
// 		System.out.println("2 - Virar carta do estoque");
// 		System.out.println("3 - Virar carta da fileira");
// 		System.out.println("4 - Mover cartas entre fileiras");
// 		System.out.println("5 - Virar " + (turnCardsFromStock==1? "3 cartas":"1 carta") + " do estoque");
// 		System.out.println("6 - Reiniciar");
// 		System.out.println("7 - Encerrar");
// 	}

// 	@Override
// 	public void restart(){
// 		piles.clear();
// 		createPiles();
// 	}
// }
