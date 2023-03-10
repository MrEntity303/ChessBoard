package it.unicam.cs.pa.cli;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.chess.ChessPieceType;

import java.util.Scanner;
public class CliPromotionObserver implements PromotionObserver{
        private final Scanner scanner;

        public CliPromotionObserver(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public ChessPieceType handlePromotion(Color color) {
            System.out.println("Il tuo pedone è arrivato all'ultima traversa! Scegli il tipo di pezzo in cui promuoverlo (Cavallo, Alfiere, Torre, Regina):");

            while (true) {
                String input = scanner.nextLine();
                switch (input.toLowerCase()) {
                    case "knight" -> {
                        return ChessPieceType.KNIGHT;
                    }
                    case "bishop" -> {
                        return ChessPieceType.BISHOP;
                    }
                    case "rook" -> {
                        return ChessPieceType.ROOK;
                    }
                    case "queen" -> {
                        return ChessPieceType.QUEEN;
                    }
                    default ->
                            System.err.println("Input non valido. Scegli il tipo di pezzo in cui promuoverlo (Cavallo, Alfiere, Torre, Regina):");
                }
            }
        }
    }
