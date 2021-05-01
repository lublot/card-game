package app;

public class Solitaire extends CardGame{
	private Deck deck;
    
	public Solitaire(){
    	super();
    }
    
	@Override
    public void createPiles() {
		deck  = new Deck();
    	piles.add(new Pile(1, "ESTOQUE", deck.getCards(24)));
    	piles.add(new Pile(2, "DESCARTE"));
    	piles.add(new Pile(3, "FUNDACAO 1"));
    	piles.add(new Pile(4, "FUNDACAO 2"));
    	piles.add(new Pile(5, "FUNDACAO 3"));
    	piles.add(new Pile(6, "FUNDACAO 4"));
    	
    	//Inicia as pilhas de fileiras com cartas aleatorias
    	for(int i=0; i<7; i++) {
    		String name = String.format("TABLEAU %d", i+1);
    		Pile pile = new Pile(piles.size()+1, name, deck.getCards(i+1));
    		pile.turnUpCard();
    		piles.add(pile);
    	}
    }

	@Override
	public boolean isValidMove(int fromIndex, int toIndex){
		Pile fromPile = piles.get(fromIndex-1);
		Pile toPile   = piles.get(toIndex-1);

		if(fromPile.name().equals("ESTOQUE")  && toPile.name().equals("DESCARTE")) return true;
		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("FUNDACAO")) return true;
		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("FUNDACAO") && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("TABLEAU"))   return true;
		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("FUNDACAO")) return true;
		return false;
	}

	@Override
    public void start() {
		this.running = true;
		
		while(running){	
			showPiles();
			showMenu();
			int option = input.readInt("");
			try {
				switch(option){
					case 1: moveCardFromTo(); 			break;
					case 2: moveCardFromStockToWaste(); break;
					case 3: turnUpTableauCard(); 		break;
					case 4: moveCards(); 				break;
					case 5: quit(); 					break;
					default: System.out.println("Informe uma opcao valida!");
				}
			} catch (Exception e) { System.out.println(e.getMessage()); }
		}
    }

	@Override
	public void showMenu() {
		System.out.println("====================================================================================");
		System.out.println("Escolha uma opcao:");
		//System.out.println("1 - Reiniciar");
		System.out.println("1 - Mover carta");
		System.out.println("2 - Virar carta do estoque");
		System.out.println("3 - Virar carta da fileira");
		System.out.println("4 - Mover cartas");
		System.out.println("5 - Encerrar");
	}

	private boolean indexIsOutOfBound(int number, int range){
		return (number < 0 || number > range);
	}

	/**
     * Compara 2 cartas e verifica se ela pode ser adicionada a uma pilha 
     * construida em ordem descendente.
     * @param card - Carta que sera adicionada na pilha
     * @param lastCard - Ultima carta da pilha
     * @return true se as cartas possuem cores diferentes e valores em ordem descendente
     * (card possui um valor menor), caso contrario retorna false.
     */
    private boolean isStackableOnTableau(Pile fromPile, Pile toPile){
		Card card = fromPile.pickLastCard();
		if(toPile.isEmpty()){
			if(!card.value().equals("K")) return false;
		}
		else{
			Card lastCard = toPile.pickLastCard();
			if(deck.hasSameColor(card, lastCard)) 		   return false;
			if(!deck.hasSameNextCardValue(card, lastCard)) return false;
		}
        return true;
    }

	private boolean isStackableOnFoundation(Pile fromPile, Pile toPile){
		Card card = fromPile.pickLastCard();
		if(toPile.isEmpty()){
			if(!card.value().equals("A")) return false;
		}	
		else{
			Card lastCard = toPile.pickLastCard();
			if(!deck.hasSameSuit(card, lastCard)) 			return false;
			if(!deck.hasSamePriorCardValue(card, lastCard)) return false;
		}
		return true;
	}

	private void moveCardFromTo() throws Exception{
		int fromIndex = input.readInt("Pilha de origem: ");
		int toIndex   = input.readInt("Pilha de destino: ");
		Pile fromPile = piles.get(fromIndex-1);
		Pile toPile   = piles.get(toIndex-1);

		if(indexIsOutOfBound(fromIndex, piles.size())) return;
		if(indexIsOutOfBound(toIndex,   piles.size())) return;
		if(fromPile.isEmpty()) throw new Exception("[Pilha de origem esta vazia!]\n");			
		if(!isValidMove(fromIndex, toIndex)) throw new Exception("[Jogada Invalida!]\n");

		if(toPile.name().equals("FUNDACAO") && !isStackableOnFoundation(fromPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a FUNDACAO!]\n");
	
		if(toPile.name().equals("TABLEAU")  && !isStackableOnTableau(fromPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a TABLEAU!]\n");

		moveCard(fromIndex, toIndex);
	}

	private void moveCardFromStockToWaste(){
		moveCard(1, 2);
	}

	private void turnUpTableauCard(){
		int pileIndex = input.readInt("Escolha um TABLEAU: ")-1;
		if(pileIndex < 7 || pileIndex > 13) {
			System.out.println("Opcao invalida!");
			return;
		}
		piles.get(pileIndex).turnUpCard();
	}

	private void moveCards(){
		System.out.println("Not implemented yet!");
	}

}
