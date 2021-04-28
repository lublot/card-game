package app;

public class Card {
    private String naipe;
    private boolean faceDown;

    public Card(String naipe){
        this.naipe    = naipe;
        this.faceDown = true;
    }

    public void turnUp(){
        this.faceDown = !this.faceDown;
    }

    public boolean isFaceDown(){
        return this.faceDown;
    }

    public String draw(){
        if(isFaceDown()) return "[<>]";
        return String.format("[%s]", this.naipe);
    }
    
}
