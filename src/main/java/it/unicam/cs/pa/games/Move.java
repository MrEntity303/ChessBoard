package it.unicam.cs.pa.games;

public abstract class Move {
    private final Position origin;
    private final Position destination;
    public Move(Position origin, Position destination) {
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Get the origin of the move
     * @return the origin of the move
     */
    public Position getOrigin() {
        return origin;
    }

    /**
     * Get the destination of the move
     * @return the destination of the move
     */
    public Position getDestination() {
        return destination;
    }

     /**
      * Check if the move is valid
      * @return type of Move
      */
    public abstract Move isValid();
}
