package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.games.chess.ChessGame;
import it.unicam.cs.pa.players.chess.ChessPlayer;

import java.util.Scanner;

public class CliMenuGames implements CliMenu {
    private final Scanner scanner;
    public CliMenuGames(Scanner scanner){
        this.scanner = scanner;
    }
    @Override
    public void printMenu(){
        System.out.println("1- Chess");
        System.out.println("2- Checkers");
        System.out.println("3- Reverse");
    }

    @Override
    public void chooseGame() {
        System.out.print("Choose a game: ");
        switch (getIntInput()) {
            case 1 -> {
                ChessGame chessGame = new ChessGame(scanner, createPlayers(2));
                chessGame.play();
            }
            case 2 -> System.out.println("Checkers not implemented yet");
            //implementare il gioco delle dame
            //        CheckersGame checkersGame = new CheckersGame();
            //        checkersGame.play();
            case 3 -> System.out.println("Reverse not implemented yet");
            //implementare il gioco del tris
            //        ReverseGame reverseGame = new ReverseGame();
            //        reverseGame.start();
            default -> {
                System.err.println("Invalid choice");
                printMenu();
            }
        }
    }

    /**
     * This method insert to number and return the input.
     * @return int input
     */
    public int getIntInput(){
        int input = 0;
        boolean valid = false;
        while(!valid) {
            try {
                input = scanner.nextInt();
                valid = true;
            } catch (Exception e) {
                scanner.nextLine();
                System.err.println("Invalid input, insert a number: ");
            }
        }
        return input;
    }

    @Override
    public ChessPlayer[] createPlayers(int numPlayers) {
        ChessPlayer[] players = new ChessPlayer[numPlayers];
        for (int i = 0; i < players.length ;i++){
            players[i] = new ChessPlayer(chooseName(i));
        }
        return players;
    }

    @Override
    public String chooseName(int numPlayer) {
        System.out.println("Choose a name for "+(numPlayer+1)+"st Player: ");
        return scanner.next();
    }
}
