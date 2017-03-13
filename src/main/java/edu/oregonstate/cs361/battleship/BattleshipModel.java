package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by michaelhilton on 1/4/17.
 */
public class BattleshipModel {

    private Ship aircraftCarrier = new Ship("aircraftcarrier",5, new Coordinate(0,0),new Coordinate(0,0), false);
    private Ship battleship = new Ship("battleship",4, new Coordinate(0,0),new Coordinate(0,0), true);
    private Ship submarine = new Ship("submarine",2, new Coordinate(0,0),new Coordinate(0,0), true);
    private Ship clipper = new CivilianShip( "clipper", 3, new Coordinate( 0, 0), new Coordinate( 0, 0), false);
    private Ship dinghy = new CivilianShip( "dinghy", 1, new Coordinate ( 0, 0), new Coordinate( 0, 0), false);

    private Ship computer_aircraftCarrier = new Ship("Computer_AircraftCarrier",5, new Coordinate(2,2),new Coordinate(2,6), false);
    private Ship computer_battleship = new Ship("Computer_Battleship",4, new Coordinate(2,8),new Coordinate(5,8), true);
    private Ship computer_submarine = new Ship("Computer_Submarine",2, new Coordinate(9,6),new Coordinate(9,7), true);
    private Ship computer_clipper = new CivilianShip( "Computer_Clipper", 3, new Coordinate( 1, 1), new Coordinate( 3, 1), false);
    private Ship computer_dinghy = new CivilianShip( "Computer_Dinghy", 1, new Coordinate ( 10, 10), new Coordinate( 10, 10), false);

    ArrayList<Coordinate> playerHits;
    private ArrayList<Coordinate> playerMisses;
    ArrayList<Coordinate> computerHits;
    private ArrayList<Coordinate> computerMisses;
    ArrayList<Coordinate> shipSquares;

    boolean scanResult = false;
    boolean overlapResult = false;
    boolean offBoard = false;



    public BattleshipModel() {
        playerHits = new ArrayList<>();
        playerMisses= new ArrayList<>();
        computerHits = new ArrayList<>();
        computerMisses= new ArrayList<>();
        shipSquares = new ArrayList<>();
    }



    public Ship getShip(String shipName) {
        if (shipName.equalsIgnoreCase("aircraftcarrier")) {
            return this.aircraftCarrier;
        } if(shipName.equalsIgnoreCase("battleship")) {
            return this.battleship;
        }
        if(shipName.equalsIgnoreCase("submarine")) {
            return this.submarine;
        }
        if (shipName.equalsIgnoreCase( "clipper" )) {
            return this.clipper;
        }
        if(shipName.equalsIgnoreCase("dinghy" )) {
            return this.dinghy;
        }
        else {
            return null;
        }
    }

    public BattleshipModel placeShip(String shipName, String row, String col, String orientation) {
        int rowint = Integer.parseInt(row);
        int colInt = Integer.parseInt(col);

        /*adopted from Group 20*/
        /*resets overlap flag and off board flag*/
        overlapResult = false;
        offBoard = false;
        if(this.getShip(shipName).alreadyPlaced()){

            shipSquares.removeAll(this.getShip(shipName).getShipSquares());
        }
        if(orientation.equals("horizontal")){
            if(shipName.equalsIgnoreCase("submarine")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt+1));
            }
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt+4));
            }
            if(shipName.equalsIgnoreCase("battleship")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt+3));
            }
            if(shipName.equalsIgnoreCase( "clipper")){
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt+2));
            }
            if(shipName.equalsIgnoreCase("dinghy")){
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt));
            }
        }else{
            //vertical
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+4,colInt));
            }
            if(shipName.equalsIgnoreCase("battleship")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+3,colInt));
            }
            if(shipName.equalsIgnoreCase("submarine")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint+1, colInt));
            }
            if(shipName.equalsIgnoreCase( "clipper")){
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint+2, colInt));
            }
            if(shipName.equalsIgnoreCase("dinghy")){
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt));
            }
        }

        /*adopted from group 20*/
        for(int i = 0; i < this.getShip(shipName).getShipSquares().size(); i++){
            if(this.getShip(shipName).getShipSquares().get(i).getAcross() > 10 || this.getShip(shipName).getShipSquares().get(i).getDown() > 10) {
                offBoard = true;
                //unplace ship
                this.getShip(shipName).setLocation(new Coordinate(0,0), new Coordinate(0, 0));
                return this;
            }
            // if master list already contains one of the new ship's squares, it's an overlap!
            if (shipSquares.contains(this.getShip(shipName).getShipSquares().get(i))) {
                overlapResult = true;
                // "un-place" ship
                this.getShip(shipName).setLocation(new Coordinate(0,0), new Coordinate(0, 0));
                return this;
            }
        }
        // if no overlap + not off the board, leave the ship alone and add its squares to the master list
        shipSquares.addAll(this.getShip(shipName).getShipSquares());
        return this;
    }

//    public void shootAtComputer(int row, int col) {
//        Coordinate coor = new Coordinate(row,col);
//        if(computer_aircraftCarrier.covers(coor)){
//            computerHits.add(coor);
//        }
//        else if (computer_battleship.covers(coor)){
//            computerHits.add(coor);
//        }
//        else if (computer_submarine.covers(coor)){
//            computerHits.add(coor);
//        }
//        else if (computer_clipper.covers(coor)) {
//            computerHits.add(computer_clipper.start);
//            computerHits.add(computer_clipper.end);
//            //add code to sink entire civilian ship
//            Coordinate begin = computer_clipper.start;
//            Coordinate end = computer_clipper.end;
//            if (begin.getDown() == end.getDown()) {
//                switch (begin.getAcross()) {
//                    case '1':
//                        begin.setAcross(2);
//                        break;
//                    case '2':
//                        begin.setAcross(3);
//                        break;
//                    case '3':
//                        begin.setAcross(4);
//                        break;
//                    case '4':
//                        begin.setAcross(5);
//                        break;
//                    case '5':
//                        begin.setAcross(6);
//                        break;
//                    case '6':
//                        begin.setAcross(7);
//                        break;
//                    case '7':
//                        begin.setAcross(8);
//                        break;
//                    case '8':
//                        begin.setAcross(9);
//                        break;
//                    case '9':
//                        begin.setAcross(10);
//                        break;
//                }
//
//            }
//            if (begin.getAcross() == end.getAcross()) {
//                switch (begin.getDown()) {
//                    case '1':
//                        begin.setDown(2);
//                        break;
//                    case '2':
//                        begin.setDown(3);
//                        break;
//                    case '3':
//                        begin.setDown(4);
//                        break;
//                    case '4':
//                        begin.setDown(5);
//                        break;
//                    case '5':
//                        begin.setDown(6);
//                        break;
//                    case '6':
//                        begin.setDown(7);
//                        break;
//                    case '7':
//                        begin.setDown(8);
//                        break;
//                    case '8':
//                        begin.setDown(9);
//                        break;
//                    case '9':
//                        begin.setDown(10);
//                        break;
//                }
//                computerHits.add(begin);
//            }
//        }
//        else if (computer_dinghy.covers(coor)){
//            computerHits.add(coor);
//        }
//        else {
//            computerMisses.add(coor);
//        }
//    }

    //adopted from group 20
    public void shootAtComputer(int row, int col) {
        overlapResult = false;
        offBoard = false;
        Coordinate coor = new Coordinate(row,col);
        if(computer_aircraftCarrier.covers(coor)){
            computerHits.add(coor);
        }else if (computer_battleship.covers(coor)){
            computerHits.add(coor);
        }else if (computer_submarine.covers(coor)){
            computerHits.add(coor);
        }else if (computer_clipper.covers(coor)){
            computerHits.add(coor);
        }else if (computer_dinghy.covers(coor)){
            computerHits.add(coor);
        } else {
            computerMisses.add(coor);
        }
    }

    public void shootAtPlayer() {
        int max = 10;
        int min = 1;
        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;

        Coordinate coor = new Coordinate(randRow,randCol);
        playerShot(coor);
    }

    void playerShot(Coordinate coor) {
        if(playerMisses.contains(coor)){
            //System.out.println("Dupe");
        }
        if(aircraftCarrier.covers(coor)){
            playerHits.add(coor);
        }
        else if (battleship.covers(coor)){
            playerHits.add(coor);
        }
        else if (submarine.covers(coor)){
            playerHits.add(coor);
        }
        else if (clipper.covers(coor)){
            playerHits.add(coor);
        }
        else if (dinghy.covers(coor)){
            playerHits.add(coor);
        }
        else {
            playerMisses.add(coor);
        }
    }


    public void scan(int rowInt, int colInt) {
        Coordinate coor = new Coordinate(rowInt,colInt);
        scanResult = false;
        if(computer_aircraftCarrier.scan(coor)) {
            scanResult = true;
        }else if (computer_battleship.scan(coor)){
            scanResult = false; /*changed to false, so battleship is undetectable by scan*/
        }else if (computer_clipper.scan(coor)){
            scanResult = true;
        }else if (computer_dinghy.scan(coor)){
            scanResult = true;
	      }else if (computer_submarine.scan(coor)){
	          scanResult = false; /* Changed to false, so the submarine is undetectable by the scan feature*/
        }else {
            scanResult = false;
        }
    }

    public boolean getScanResult() {
        return scanResult;
    }
}
