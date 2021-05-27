package app.gui;

import app.CardGame;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
  private StackPane stack_1;

  public void move () {
    ImageView img = new ImageView(new Image("/app/assets/board/hidden.png"));
    img.setFitWidth(50);
    img.setFitHeight(80);
    img.setCursor(javafx.scene.Cursor.HAND);
    stack_1.getChildren().add(img);
    StackPane.setAlignment(img, Pos.TOP_CENTER);
  }

  @FXML
  private void initialize () {
    this.move();
  }

  @FXML
  @Override
  public void quit () {
    this.running = false;
    System.out.println("Programa encerrado!");
    System.exit(0);
  }
}
