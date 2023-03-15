package it.unicam.cs.pa;

import it.unicam.cs.pa.cli.CliMenuGames;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CliMenuGames cliGames = new CliMenuGames(scanner);
        cliGames.printMenu();
        cliGames.chooseGame();
        scanner.close();
    }
}