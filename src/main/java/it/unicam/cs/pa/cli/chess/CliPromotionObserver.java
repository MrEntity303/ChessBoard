package it.unicam.cs.pa.cli.chess;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.chess.ChessPieceType;

import java.util.Scanner;
public class CliPromotionObserver implements PromotionObserver {
    private final Scanner scanner;
    public CliPromotionObserver(Scanner scanner) {
            this.scanner = scanner;
        }
    @Override
        public ChessPieceType handlePromotion(Color color) {
            System.out.println("Your pawn has reached the last rank! Choose the type of piece to promote to (Knight, Bishop, Rook, Queen):");

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
                            System.err.println("Invalid input. Choose the type of piece to promote to (Knight, Bishop, Rook, Queen):");
                }
            }
        }
    }
