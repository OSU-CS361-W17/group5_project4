package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;

/**
 * Created by michaelhilton on 1/5/17.
 */
public class Ship {
    protected String name;
    protected int length;
    protected Coordinate start;
    protected Coordinate end;

    public Ship(){
        name = "";
        length = 0;
        start = new Coordinate(0,0);
        end = new Coordinate(0, 0);

    }

    public Ship(String n, int l,Coordinate s, Coordinate e) {
        name = n;
        length = l;
        start = s;
        end = e;
//        stealth = b;
    }

    public void setLocation(Coordinate s, Coordinate e) {
        start = s;
        end = e;
    }

    //aquired from group 20
    /* returns true if this ship is horizontal... it can be assumed that neither the start nor end coordinates of the
     * ship contain 0s since this funct is only called after the [user] ship has been placed */
    public boolean isHorizontal() {
        // if ship's start and end coordinates share the same Across (row), must be horizontal
        if(start.getAcross() == end.getAcross()){
            return true;
        }
        // if ship's start and end coordinates share the same Down (col), must be vertical
        else {
            return false;
        }
    }

    /* this funct will only be called when the user attempts to assign a ship a position with a starting coordinate
     * guaranteed to be between 1 and 10, inclusive... therefore, it is impossible for ``this`` ship to have a start or
     * end coordinate that is (0,0) */
    public ArrayList<Coordinate> getShipSquares() {
        ArrayList<Coordinate> shipSquares = new ArrayList<>();      // will hold all the placed ship's squares

        // if this ship is horizontal, loop horizontally to grab all its squares
        if (this.isHorizontal()) {
            for (int i = start.getDown(); i < start.getDown() + length; i++) {
                Coordinate square = new Coordinate(start.getAcross(), i);
                shipSquares.add(square);
            }
        }
        // otherwise if this ship is vertical, loop vertically to grab all its squares
        else {
            for (int i = start.getAcross(); i < start.getAcross() + length; i++) {
                Coordinate square = new Coordinate(i, start.getDown());
                shipSquares.add(square);
            }

        }
        return shipSquares;
    }

    public boolean covers(Coordinate test) {
        //horizontal
        if(start.getAcross() == end.getAcross()){
            if(test.getAcross() == start.getAcross()){
                if((test.getDown() >= start.getDown()) &&
                        (test.getDown() <= end.getDown()))
                    return true;
            } else {
                return false;
            }
        }
        //vertical
        else{
            if(test.getDown() == start.getDown()){
                if((test.getAcross() >= start.getAcross()) &&
                        (test.getAcross() <= end.getAcross()))
                    return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public String getName() {
        return name;
    }

    public boolean alreadyPlaced() {
        if (this.start.getAcross() == 0) {
            return false;
        }
        return true;
    }

    public boolean scan(Coordinate coor) {
        if (covers(coor)) {
            return true;
        }
        if(covers(new Coordinate(coor.getAcross()-1,coor.getDown()))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross()+1,coor.getDown()))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross(),coor.getDown()-1))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross(),coor.getDown()+1))){
            return true;
        }
        return false;
    }
}