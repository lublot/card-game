package app;

import java.util.ArrayList;
import java.util.List;

public class Solitaire extends CardGame{
    private Deck deck;
    public List<Pile> piles;

    
    public Solitaire(){
    	piles = new ArrayList<Pile>();
    	deck  = new Deck();
    	createPiles();
    }
    
    private void createPiles() {
    	piles.add(new Pile(1, "ESTOQUE", deck.getCards(24)));
    	piles.add(new Pile(2, "DESCARTE"));
    	piles.add(new Pile(3, "FUNDACAO1"));
    	piles.add(new Pile(4, "FUNDACAO2"));
    	piles.add(new Pile(5, "FUNDACAO3"));
    	piles.add(new Pile(6, "FUNDACAO4"));
    	
    	//Inicia as pilhas de fileiras com cartas aleatorias
    	for(int i=0; i<7; i++) {
    		String name = String.format("TABLEAU%d", i+1);
    		Pile pile = new Pile(piles.size()+1, name, deck.getCards(i+1));
    		pile.turnUpCard();
    		piles.add(pile);
    	}
    }
    
    public void start() {
    	
    }
    
    public void showPile(int indexPile) {
    	piles.get(indexPile-1).draw();
    }
    
    
    
}
