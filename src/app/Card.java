package app;

import java.nio.file.Paths;

public class Card {
    private String color;
    private String value;
    private String suit; //Naipe
    private boolean faceDown;

    public Card(String value, String suit){
        this.value    = value;
        this.suit     = suit;
        this.color    = (suit=="paus" || suit=="espadas")? "black":"red";
        this.faceDown = true;
    }

    public void turnUp(){
        this.faceDown = false;
    }
    
    public void turnDown(){
        this.faceDown = true;
    }

    public boolean isFaceDown(){
        return this.faceDown;
    }

    public String show(){
        if(isFaceDown()) return "[<>]";
        return String.format("[%s %s]", this.value, this.suit);
    }

    public String getAssetPath() {
        if (this.faceDown) {
            return "/app/assets/board/hidden.png";
        }
        return Paths
            .get("/app/assets/decks", this.suit, this.value + ".png")
            .toString();
    }
    
    public String color(){ return this.color; } 
    public String value(){ return this.value; }
    public String suit(){ return this.suit; }

}
