package it.unicam.cs.pa.games;

import lombok.Setter;

@Setter
public record Position(int x, int y){

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position p){
            return p.x == this.x && p.y == this.y;
        }
        return false;
    }
}
