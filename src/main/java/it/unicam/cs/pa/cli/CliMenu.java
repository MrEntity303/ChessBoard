package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.players.Player;

public interface CliMenu {
    /**
     * This method prints the menu of the game.
     */
    void printMenu();

    /**
     * This method causes the user to do some input for game.
     */
    void chooseGame();
    /**
     * This method causes the user to do some input for the number of players.
     * @return the Player[] of players.
     */
    Player[] createPlayers(int numberOfPlayers);

    /**
     * This method causes the user to do some input for the name of the player.
     * @param numPlayer the number of the player.
     * @return the String of the name of the player.
     */
    String chooseName(int numPlayer);

}
