package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Position;
import it.unicam.cs.pa.games.chess.ChessMove;

import java.util.Scanner;

public class CliMakeTurn implements CliTurn {

    private final Scanner scanner;

    public CliMakeTurn(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Move inputTurn() {
        System.out.println("Insert your move origin: EXAMPLE(<A1>, <A2>");
        Position origin = isValidInput(scanner.next());
        while(origin == null)
        {
            System.out.println("Invalid origin coordinate");
            origin = isValidInput(scanner.next());
        }
        System.out.println("Insert your move destination: EXAMPLE(<A1>, <A2>");
        Position destination = isValidInput(scanner.next());
        while(destination == null)
        {
            System.out.println("Invalid destination coordinate");
            destination = isValidInput(scanner.next());
        }
        return new ChessMove(origin, destination);
    }

    @Override
    public Position isValidInput(String input) {
        String regex = "[A-H][1-8]";
            if (input.matches(regex)) {
                char lettera = input.substring(0, 1).charAt(0);
                int numero = Integer.parseInt(input.substring(1));
                return new Position(lettera-'A', numero-1);
            }
        System.out.println("The coordinate is not valid");
        return null;
    }
}
