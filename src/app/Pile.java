package app;

import java.util.List;
import java.util.Stack;

public class Pile {
    private Stack<Card> cards;
    private int id;
    private String name;
    
    public Pile() {
    	cards = new Stack<Card>();
    }
    
    public Pile(int id, String name) {
    	this.id   = id;
    	this.name = name;
    	cards = new Stack<Card>();
    }
    
    public Pile(int id, String name, List<Card> cardList) {
    	this(id, name);
    	this.init(cardList);
    }
    
    /**
     * Inicia a pilha com uma lista de cartas
     * @param cardList
     */
    private void init(List<Card> cardList) {
    	cardList.forEach(card->cards.push(card));
    }
    
    public void addCard(Card card){
    	this.cards.push(card);    
    }
    
    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void moveCard(int fromPile, int toPile){}
    
    public Card pickLastCard(){
        return cards.peek();
    }

    public Pile pickLastCards(int cardsQty){
        if(this.isEmpty()) return new Pile(0, "EMPTY");

        Stack<?> newStack = (Stack<?>) this.cards.clone();
        Pile cardsPile = new Pile(0, "NEW");

        while(cardsPile.size() < cardsQty){
            Card card = (Card) newStack.peek();
            if(card.isFaceDown()) break;
            cardsPile.addCard((Card) newStack.pop());
        }
        return cardsPile;
    }
    
    public Card removeLastCard(){
    	return cards.pop(); 
    }

    public void show() {
    	System.out.print(this.toString());
    	cards.forEach(card->System.out.print(card.show() + " "));
    	System.out.println("");
    }

    public int size() {
    	return cards.size();
    }
    
    public void turnUpCard(){
    	cards.peek().turnUp();
    }
    
    public String toString() {
    	return String.format("%2d - %10s == ", id, name);
    }

    public String name(){
        return name.split(" ")[0];
    }
}
