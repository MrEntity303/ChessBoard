package it.unicam.cs.pa.games;

public record Position(int x, int y){
    public Position{
        if(x < 0 || y < 0)
            throw new IllegalArgumentException("Position must be positive");
    }
}
