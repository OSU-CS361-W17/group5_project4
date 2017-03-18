package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;

/**
 * Created by williamstribyjr on 2/23/17.
 */
class CivilianShip extends Ship {

    public CivilianShip(String n, int l, Coordinate s, Coordinate e) {
        super(n, l, s, e);
    }
    //adopted from group 20
    ArrayList <Coordinate> one_hit() {
        return this.getShipSquares();
    }

}
