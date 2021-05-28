package app.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

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
  @FXML
  private GridPane grid;

  @FXML
  private ImageView card2;

  @FXML
  private TextField move_from;

  @FXML
  private TextField move_to;

  public void move () {
    ImageView img = new ImageView(new Image("/app/assets/board/hidden.png"));
    img.setFitWidth(50);
    img.setFitHeight(80);
    img.setCursor(javafx.scene.Cursor.HAND);
    StackPane sp = ((StackPane) grid.lookup("#stack_1"));
    sp.getChildren().add(img);
    StackPane.setAlignment(img, Pos.TOP_CENTER);
  }

  @FXML
  private void initialize () {
    this.createPiles();
  }

  @Override
  public void createPiles () {
    this.piles = new ArrayList<Pile>();
    Deck deck = new Deck(2);
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
    this.piles.add(new Pile(11, "PILHA 1"));
    this.piles.add(new Pile(12, "PILHA 2"));
    this.piles.add(new Pile(13, "PILHA 3"));
    this.piles.add(new Pile(14, "PILHA 4"));
    this.piles.add(new Pile(15, "PILHA 5"));
    this.piles.add(new Pile(16, "PILHA 6"));
    this.piles.add(new Pile(17, "PILHA 7"));
    this.piles.add(new Pile(18, "PILHA 8"));
    this.piles.add(new Pile(19, "PILHA 9"));
    this.piles.add(new Pile(20, "PILHA 10"));
    this.piles.add(new Pile(21, "PILHA 11"));
    this.piles.add(new Pile(22, "PILHA 12"));
    this.piles.add(new Pile(23, "PILHA 13"));
    this.piles.add(new Pile(24, "PILHA 14"));
    this.piles.add(new Pile(25, "PILHA 15"));
  }

  @FXML
  public void executeMove() {
    int from = Integer.parseInt(move_from.getText()) + 1;
    int to = Integer.parseInt(move_to.getText()) + 1;
    System.out.println(this.piles.get(to - 1));
    this.moveCard(from, to);
    this.showPiles();
  }

  @Override
  public void showPiles() {
    for (int pileIndex = 0; pileIndex < this.piles.size(); pileIndex++) {
    // this.piles.forEach(pile -> {
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
        case "PILHA": {
          System.out.println(pile.id() - 10);
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

  private ImageView prepareCard(String path) {
    ImageView img = new ImageView(new Image(path));
    img.setFitWidth(50);
    img.setFitHeight(80);
    return img;
  }

  @FXML
  @Override
  public void quit () {
    this.running = false;
    System.out.println("Programa encerrado!");
    System.exit(0);
  }
}
