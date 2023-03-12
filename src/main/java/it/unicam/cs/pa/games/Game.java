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
            while (!endOfGame()) {
                makeTurn();
                countTurns++;
            }
            printResult();
        }

        //hook methods - possono essere ridefiniti dalle sottoclassi
        protected void initialize() {
            System.out.println("Inizializzazione del gioco...");
        }

        protected abstract void makeTurn();
        
        protected abstract void printBoard();

        protected boolean endOfGame() {
            return false;//TODO aggiungere condizione di fine gioco
        }

    protected void printResult(){
        System.out.println("Il gioco è finito, turni passati: " + countTurns);
    }
}
