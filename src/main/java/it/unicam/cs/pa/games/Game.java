package it.unicam.cs.pa.games;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class Game {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private int countTurns;
        //template method
        public final void play() {
            initialize();
            while (countTurns==0 || !endOfGame()) {
                makeTurn();
                countTurns++;
            }
            printResult();
        }
    //hook methods - ridefiniti dalle sottoclassi
    /**
     * Initialize the game
     */
     protected void initialize() {
        System.out.println("Inizializzazione del gioco...");
     }
     /**
      * Make a turn
      */
     protected abstract void makeTurn();

     /**
      * Print the board
      */
     protected abstract void printBoard();
     /**
      * Check if the game is ended
      * @return true if the game is ended, false otherwise
      */
     protected boolean endOfGame() {
         return false;}

    /**
     * Print the result of the game
     */
    protected void printResult(){
        System.out.println("Il gioco è finito, turni passati: " + countTurns);
    }
}
