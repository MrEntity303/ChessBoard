package it.unicam.cs.pa.games;

public abstract class Move {
    private final Position origin;
    private final Position destination;
    public Move(Position origin, Position destination) {
//        if(origin.x() < 0 || origin.x() > 7 || origin.y() < 0 || origin.y() > 7 || destination.x() < 0 || destination.x() > 7 || destination.y() < 0 || destination.y() > 7)
//            throw new IllegalArgumentException("Origin and destination must be between 0 and 7");
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
