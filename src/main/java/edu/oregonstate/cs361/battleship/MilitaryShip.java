package edu.oregonstate.cs361.battleship;

/**
 * Created by williamstribyjr on 3/14/17.
 */

//adopted from group 20
public class MilitaryShip extends Ship{
    private boolean stealth;

    public MilitaryShip(boolean b, String n, int l,Coordinate s, Coordinate e) {
        super(n,l,s,e);
        stealth = b;
    }

    //New scan function that implements the stealth feature, original scan function is in Ship.java --Rebecca
    public boolean scan(Coordinate coor) {
        if (stealth == true) {
            return false;
        } else {
            if (covers(coor)) {
                return true;
            }
            if (covers(new Coordinate(coor.getAcross() - 1, coor.getDown()))) {
                return true;
            }
            if (covers(new Coordinate(coor.getAcross() + 1, coor.getDown()))) {
                return true;
            }
            if (covers(new Coordinate(coor.getAcross(), coor.getDown() - 1))) {
                return true;
            }
            if (covers(new Coordinate(coor.getAcross(), coor.getDown() + 1))) {
                return true;
            }
        }

        return false;
    }


}
