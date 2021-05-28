package app.gui;

import java.util.ArrayList;
import java.util.Iterator;

import app.Pile;
import app.Card;
import app.CardGame;
import app.Deck;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class UIController extends CardGame{
  private Deck deck;
  @FXML
  private GridPane grid;

  @FXML
  private TextField move_from;

  @FXML
  private TextField move_to;

  @FXML
  private void initialize () {
    this.createPiles();
    this.showPiles();
  }

  @Override
  public void createPiles () {
    this.piles = new ArrayList<Pile>();
    deck = new Deck(2);
    this.piles.add(new Pile(0, "ESTOQUE", deck.getCards(18)));
    this.piles.add(new Pile(1, "DESCARTE"));
    this.piles.add(new Pile(2, "FUNDACAO 1"));
    this.piles.add(new Pile(3, "FUNDACAO 2"));
    this.piles.add(new Pile(4, "FUNDACAO 3"));
    this.piles.add(new Pile(5, "FUNDACAO 4"));
    this.piles.add(new Pile(6, "FUNDACAO 5"));
    this.piles.add(new Pile(7, "FUNDACAO 6"));
    this.piles.add(new Pile(8, "FUNDACAO 7"));
    this.piles.add(new Pile(9, "FUNDACAO 8"));
    this.piles.add(new Pile(10, "REIS"));
    this.piles.add(new Pile(11, "TABLEAU 1", deck.getCards(6)));
    this.piles.add(new Pile(12, "TABLEAU 2", deck.getCards(6)));
    this.piles.add(new Pile(13, "TABLEAU 3", deck.getCards(6)));
    this.piles.add(new Pile(14, "TABLEAU 4", deck.getCards(6)));
    this.piles.add(new Pile(15, "TABLEAU 5", deck.getCards(6)));
    this.piles.add(new Pile(16, "TABLEAU 6", deck.getCards(6)));
    this.piles.add(new Pile(17, "TABLEAU 7", deck.getCards(6)));
    this.piles.add(new Pile(18, "TABLEAU 8", deck.getCards(6)));
    this.piles.add(new Pile(19, "TABLEAU 9", deck.getCards(6)));
    this.piles.add(new Pile(20, "TABLEAU 10", deck.getCards(6)));
    this.piles.add(new Pile(21, "TABLEAU 11", deck.getCards(6)));
    this.piles.add(new Pile(22, "TABLEAU 12", deck.getCards(6)));
    this.piles.add(new Pile(23, "TABLEAU 13", deck.getCards(6)));
    this.piles.add(new Pile(24, "TABLEAU 14", deck.getCards(6)));
    this.piles.add(new Pile(25, "TABLEAU 15", deck.getCards(6)));
  }

  @FXML
  public void handleMove() throws Exception {
    int from = Integer.parseInt(move_from.getText()) + 1;
    int to = Integer.parseInt(move_to.getText()) + 1;
    Pile fromPile = piles.get(from-1);
		Pile toPile   = piles.get(to-1);

		if(fromPile.isEmpty()) throw new Exception("[TABLEAU de origem esta vazia!]\n");			
		if(!isValidMove(from, to)) throw new Exception("[Jogada Invalida!]\n");

		if(toPile.name().equals("FUNDACAO") && !isStackableOnFoundation(fromPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a FUNDACAO!]\n");
	
		if(toPile.name().equals("TABLEAU")  && !isStackableOnTableau(fromPile, toPile))
			throw new Exception("[Essa carta nao pode ser adicionada a TABLEAU!]\n");

    if(toPile.name().equals("REIS")  && !isStackableOnKeyFoundation(fromPile))
			throw new Exception("[Essa carta nao pode ser adicionada a FUNDACAO!]\n");
		moveCard(from, to);
    this.showPiles();
  }

  @FXML
  @Override
  public void restart() {
    this.createPiles();
    this.showPiles();
  }

  @Override
  public boolean isValidMove(int fromIndex, int toIndex) {
    Pile fromPile = piles.get(fromIndex-1);
		Pile toPile   = piles.get(toIndex-1);

    if(fromPile.name().equals("ESTOQUE")  && toPile.name().equals("DESCARTE")) return true;
    if(fromPile.name().equals("DESCARTE") && toPile.name().equals("ESTOQUE")) return true;
		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("FUNDACAO")) return true;
		if(fromPile.name().equals("DESCARTE") && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("FUNDACAO") && toPile.name().equals("TABLEAU"))  return true;
		if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("TABLEAU"))  return true;
    if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("FUNDACAO")) return true;
    if(fromPile.name().equals("TABLEAU")  && toPile.name().equals("REIS")) return true;
    if(fromPile.name().equals("DESCARTE")  && toPile.name().equals("REIS")) return true;
    if(fromPile.name().equals("FUNDACAO")  && toPile.name().equals("REIS")) return true;
    
		return false;
  }

  @Override
  public void showPiles() {
    for (int pileIndex = 0; pileIndex < this.piles.size(); pileIndex++) {
      Pile pile = this.piles.get(pileIndex);
      switch (pile.name()) {
        case "ESTOQUE": {
          ImageView stock = (ImageView) grid.lookup("#stock");
          if (pile.size() == 0) {
            stock.setImage(new Image("/app/assets/board/pile.png"));
          } else {
            stock.setImage(new Image("/app/assets/board/hidden.png"));
          }
          break;
        }
        case "DESCARTE": {
          ImageView waste = (ImageView) grid.lookup("#waste");
          if (pile.isEmpty()) {
            waste.setImage(new Image("/app/assets/board/pile.png"));
          } else {
            waste.setImage(new Image(pile.pickLastCard().getAssetPath()));
          }
          break;
        }
        case "FUNDACAO": {
          ImageView foundation = (ImageView) grid.lookup("#foundation_" + (pile.id() - 1));
          if (pile.isEmpty()) {
            foundation.setImage(new Image("/app/assets/board/pile.png"));
          } else {
            foundation.setImage(new Image(pile.pickLastCard().getAssetPath()));
          }
          break;
        }
        case "REIS": {
          ImageView keys = (ImageView) grid.lookup("#foundation_keys");
          if (pile.isEmpty()) {
            keys.setImage(new Image("/app/assets/board/pile.png"));
          } else {
            keys.setImage(new Image(pile.pickLastCard().getAssetPath()));
          }
          break;
        }
        case "TABLEAU": {
          StackPane sp = (StackPane) grid.lookup("#stack_" + (pile.id() - 10));
          sp.getChildren().clear();
          Iterator<Card> it = pile.getCards().iterator();
          int i = 0;
          while (it.hasNext()) {
            Card card = it.next();
            ImageView image = new ImageView(new Image(card.getAssetPath()));
            image.setFitWidth(50);
            image.setFitHeight(80);
            sp.getChildren().add(image);
            StackPane.setAlignment(image, Pos.TOP_CENTER);
            StackPane.setMargin(image, new Insets(30 * i, 0, 0, 0));
            i++;
          }
          break;
        }
        default:
          break;
      }
    }
  }

  /**
     * Compara 2 cartas das TABLEAUs de origem e destino e verifica se ela pode ser adicionada 
	 * na TABLEAU destino em ordem descendente de valores Ex: K,Q,J,10. 
     * @param fromPile - TABLEAU de origem
     * @param toPile   - TABLEAU de destino
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

  private boolean isStackableOnKeyFoundation(Pile fromPile){
		Card card = fromPile.pickLastCard();
		return card.value().equals("K");
  }
  
  @Override
	public void checkWinner(){
		int foundationIndexes[] = {2, 3, 4, 5, 6, 7, 8, 9};
		for(int index: foundationIndexes)
			if(piles.get(index).size()!=13) return;
		System.out.println("[Parabens voce completou todas as FUNDACOES!]");
  }

  @FXML
  @Override
  public void quit () {
    this.running = false;
    System.out.println("Programa encerrado!");
    System.exit(0);
  }
}
