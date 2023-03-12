package it.unicam.cs.pa.players;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Move;

public abstract class Player {
    private String name;
    private Color color;

    public Player(String name, Color color)
    {
        this.name = name;
        this.color = color;
    }
    public Player(String name) {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Color getColor()
    {
        return this.color;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public abstract boolean makeMove(Move move);
}
