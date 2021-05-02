package app;

import java.util.ArrayList;
import java.util.List;

public abstract class CardGame {
    protected Keypad input;
    protected List<Pile> piles;
    protected boolean running = false;

    public CardGame(){
        input = new Keypad();
        piles = new ArrayList<>();
        createPiles();
    }

    public void createPiles(){}

    public void checkWinner(){}

    public void gameLoop(){}

    public boolean isValidMove(int fromIndex, int toIndex){
        return false;
    }

    public void showMenu(){}

    public void start() {
		this.running = true;
		while(running){	
			gameLoop();
			checkWinner();
		}
    }

	public void moveCard(int fromIndex, int toIndex){
		Pile fromPile = piles.get(fromIndex-1);
		Card cardFromPile = fromPile.removeLastCard();
		cardFromPile.turnUp();
		piles.get(toIndex-1).addCard(cardFromPile);
	}

    public void showPiles(){
        System.out.println("====================================================================================");
		this.piles.forEach(pile->pile.show());
    }
    
    public void turnUpCard(int indexPile){
        piles.get(indexPile).turnUpCard();
    }
    
    public void quit() {
		this.running = false;
		System.out.println("Programa encerrado!");
	}
}
