package app;

import java.util.List;
import java.util.Stack;

public class Pile {
    public Stack<Card> cards;
    private int id;
    private String name;
    
    public Pile(int id, String name) {
    	this.id   = id;
    	this.name = name;
    	cards = new Stack<Card>();
    }
    
    public Pile(int id, String name, List<Card> cardList) {
    	this(id, name);
    	this.init(cardList);
    }
    
    
    private void init(List<Card> cardList) {
    	cardList.forEach(card->cards.push(card));
    }
    
    public void addCard(Card card){
    	this.cards.push(card);    
    }
    
    public void draw() {
    	System.out.print(this.toString());
    	cards.forEach(card->System.out.print(card.draw() + " "));
    	System.out.println("");
    }
    
    public void moveCard(int fromPile, int toPile){}
    
    public Card pickCard(){
    	return cards.pop(); 
    }
    
    public int size() {
    	return cards.size();
    }
    
    public void turnUpCard(){
    	cards.peek().turnUp();
    }
    
    public String toString() {
    	return String.format("%2d - %9s == ", id, name);
    }

}
