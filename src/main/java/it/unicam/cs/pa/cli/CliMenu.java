package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.players.Player;

public interface CliMenu {
    void printMenu();
    void chooseGame();
    Player[] createPlayers(int numberOfPlayers);

}
