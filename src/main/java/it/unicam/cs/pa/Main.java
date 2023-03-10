package it.unicam.cs.pa;

import it.unicam.cs.pa.cli.CliPromotionObserver;
import it.unicam.cs.pa.cli.PromotionObserver;
import it.unicam.cs.pa.games.chess.ChessBoard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        PromotionObserver promotionObserver = new CliPromotionObserver(scanner);
        ChessBoard.getInstance().setPromotionObserver(promotionObserver);
    }
}