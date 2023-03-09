package it.unicam.cs.pa.games;

import lombok.Setter;

@Setter
public record Position(int x, int y){
//    public Position{
////        if(x < 0 || y < 0)
////            System.err.println("Posizione x: "+x+" y: "+y+" non valida");
//    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position p){
            return p.x == this.x && p.y == this.y;
        }
        return false;
    }
}
