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
	public void checkWinner(){
		int foundationIndexes[] = {2, 3, 4, 5};
		for(int index: foundationIndexes)
			if(piles.get(index).size()!=13) return;
		System.out.println("[Parabens voce completou todas as FUNDACOES!]");
		quit();
	}

	@Override
	public void gameLoop(){
		showPiles();
		showMenu();
		int option = input.readInt("");
		try {
			switch(option){
				case 1: moveCardFromTo();			break;
				case 2: moveCardFromStockToWaste(); break;
				case 3: turnUpTableauCard();		break;
				case 4: moveOneOrMoreCards();		break;
				case 5: quit();						break;
				default: System.out.println("[Informe uma opcao valida!]");
			}
		}
		catch(NullPointerException e) { System.out.println("[Informe uma valor valido!]"); }
		catch(Exception e) { System.out.println(e.getMessage()); }
	}

	@Override
	public boolean isValidMove(int fromIndex, int toIndex){
		Pile fromPile = piles.get(fromIndex-1);
		Pile toPile   = piles.get(toIndex-1);

		if(fromPile.name().equals("ESTOQUE")  && toPile.name().equals("DESCARTE")) return true;
		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("FUNDACAO")) return true;
		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("FUNDACAO") && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("FUNDACAO")) return true;
		return false;
	}

	@Override
	public void showMenu() {
		System.out.println("====================================================================================");
		System.out.println("Escolha uma opcao:");
		//System.out.println("1 - Reiniciar");
		System.out.println("1 - Mover carta");
		System.out.println("2 - Virar carta do estoque");
		System.out.println("3 - Virar carta da fileira");
		System.out.println("4 - Mover cartas entre fileiras");
		System.out.println("5 - Encerrar");
	}

	/**
     * Compara 2 cartas das pilhas de origem e destino e verifica se ela pode ser adicionada 
	 * na pilha destino em ordem descendente de valores Ex: K,Q,J,10. 
     * @param fromPile - Pilha de origem
     * @param toPile   - Pilha de destino
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

		if(fromPile.isEmpty()) throw new Exception("[Pilha de origem esta vazia!]\n");			
		if(!isValidMove(fromIndex, toIndex)) throw new Exception("[Jogada Invalida!]\n");

		if(toPile.name().equals("FUNDACAO") && !isStackableOnFoundation(fromPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a FUNDACAO!]\n");
	
		if(toPile.name().equals("TABLEAU")  && !isStackableOnTableau(fromPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a TABLEAU!]\n");

		moveCard(fromIndex, toIndex);
	}

	private void moveCardFromStockToWaste() throws Exception{
		if(piles.get(0).isEmpty()) throw new Exception("[Pilha estoque esta vazia!]\n");
		moveCard(1, 2);
	}

	private void turnUpTableauCard() throws Exception{
		int pileIndex = input.readInt("Escolha um TABLEAU: ")-1;
		if(pileIndex < 7 || pileIndex > 13) throw new Exception("[Opcao invalida!\n]");
		piles.get(pileIndex).turnUpCard();
	}

	private void moveOneOrMoreCards() throws Exception{
		int fromIndex = input.readInt("Pilha de origem: ");
		int cardsQty  = input.readInt("Quantidade de cartas: ");
		int toIndex   = input.readInt("Pilha de destino: ");
		Pile fromPile = piles.get(fromIndex-1);
		Pile toPile   = piles.get(toIndex-1);
		Pile newPile  = fromPile.pickLastCards(cardsQty);

		if(!fromPile.name().equals("TABLEAU") || !toPile.name().equals("TABLEAU"))
			throw new Exception("[Mover mais de uma carta so e permitido entre fileiras]\n");
		
		if(newPile.isEmpty()) return;
		
		if(!isStackableOnTableau(newPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a TABLEAU!]\n");
		
		while(!newPile.isEmpty()){
			fromPile.removeLastCard();
			Card cardFromPile = newPile.removeLastCard();
			toPile.addCard(cardFromPile);
		}
	}
}
