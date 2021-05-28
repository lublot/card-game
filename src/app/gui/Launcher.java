package app.gui;

import javax.swing.JOptionPane;

import app.CardGame;
import app.Solitaire;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application{
  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app/gui/Board.fxml"));
    Parent root = loader.load();
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    String[] options = {"Paciência", "Paciência Big Bertha"};
    switch (JOptionPane.showOptionDialog(null, 
      "O que deseja jogar?", 
      "Escolha um jogo", 
      JOptionPane.DEFAULT_OPTION,
      JOptionPane.INFORMATION_MESSAGE,
      null,
      options,
      options[0]
    )) {
      case 0:
        CardGame game = new Solitaire();
        game.start();
        break;
      case 1:
        launch(args);
        break;
      default:
        break;
    }
  }
}
