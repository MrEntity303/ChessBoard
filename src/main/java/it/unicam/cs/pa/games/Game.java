package it.unicam.cs.pa.games;

import it.unicam.cs.pa.games.chess.PieceChess;
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
            }
            printResult();
        }

        //hook methods - possono essere ridefiniti dalle sottoclassi
        protected void initialize() {
            //this.setCountTurns(0);
            System.out.println("Inizializzazione del gioco");
        }

        protected abstract void makeTurn();

        protected boolean endOfGame() {
            //this.setCountTurns(this.getCountTurns() + 1);
            return false;//TODO aggiungere condizione di fine gioco
        }

    protected abstract boolean validator(PieceChess pieceChess, int x, int y);

    protected void printResult(){
//        System.out.println("Il gioco è finito, turni passati: " + (this.getCountTurns()-1));
    }
}
