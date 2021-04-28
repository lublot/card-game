package app;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Deck {
    public List<Card> cards;
    
    public Deck(){
        this.cards = create();
    }

    private List<Card> create(){
        List<Card> array = new ArrayList<>(52);
        String naipes[]  = {"espadas", "copas", "paus", "ouros"};
        String values[]  = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for(String naipe: naipes){
            for(String value: values){
                array.add(new Card(value+' '+naipe));
            }
        }
        return array;
    }

    public List<Card> getCards(int cardsQty){
        List<Card> array = new ArrayList<>(cardsQty);
        while(array.size() < cardsQty){  	
        	int index = randomInt(cards.size());
            Card card = cards.remove(index);
            array.add(card);  
        }
        return array;
    }

    private int randomInt(int bound){
        return new Random().nextInt(bound);
    }
    
    public boolean isEmpty() {
    	return cards.isEmpty();
    }
    
    //Metodo usado apenas para debug
    public void show(){
        for(int i=0; i<cards.size(); i++){
            System.out.print(cards.get(i).draw() + " ");
            if(i==12 || i==25 || i==38) System.out.println("");
        }
    }

}
