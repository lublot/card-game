package app;

import java.util.Scanner;

public class Keypad {
    private Scanner input;

    public Keypad(){
        input = new Scanner(System.in);
    }

    public int readInt(String text){
        System.out.print((text=="")? ">> ":text);
        try {
            return input.nextInt();
        } catch (Exception e) {
            input.next();
        }
        return -1;
    }
}
