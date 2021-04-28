package app;

public class Main {

    public static void main(String[] args) throws Exception {
        CardGame game = new Solitaire();
        
        for(Pile pile: ((Solitaire) (game)).piles) {
        	pile.draw();
        }
        
    }
}