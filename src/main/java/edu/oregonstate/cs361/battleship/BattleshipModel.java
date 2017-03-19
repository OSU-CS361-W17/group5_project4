package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by michaelhilton on 1/4/17.
 */
public class BattleshipModel {

    private Random rand = new Random();

    private MilitaryShip aircraftCarrier = new MilitaryShip(false,"aircraftcarrier",5, new Coordinate(0,0),new Coordinate(0,0));
    private MilitaryShip battleship = new MilitaryShip(true,"battleship",4, new Coordinate(0,0),new Coordinate(0,0));
    private MilitaryShip submarine = new MilitaryShip(true,"submarine",2, new Coordinate(0,0),new Coordinate(0,0));
    private CivilianShip clipper = new CivilianShip("clipper", 3, new Coordinate(0, 0), new Coordinate(0, 0));
    private CivilianShip dinghy = new CivilianShip("dinghy", 1, new Coordinate(0, 0), new Coordinate(0, 0));

    private MilitaryShip computer_aircraftCarrier = new MilitaryShip(false,"Computer_AircraftCarrier",5, new Coordinate(0,0),new Coordinate(0,0));
    private MilitaryShip computer_battleship = new MilitaryShip(true,"Computer_Battleship",4, new Coordinate(0,0),new Coordinate(0,0));
    private MilitaryShip computer_submarine = new MilitaryShip(true,"Computer_Submarine",2, new Coordinate(0,0),new Coordinate(0,0));
    private CivilianShip computer_clipper = new CivilianShip("Computer_Clipper", 3, new Coordinate(0, 0), new Coordinate(0, 0));
    private CivilianShip computer_dinghy = new CivilianShip("Computer_Dinghy", 1, new Coordinate(0, 0), new Coordinate(0, 0));



    ArrayList<Coordinate> playerHits;
    private ArrayList<Coordinate> playerMisses;
    ArrayList<Coordinate> computerHits;
    private ArrayList<Coordinate> computerMisses;

    //adopted from group 20
    ArrayList<Coordinate> shipSquares;

    //global variables
    boolean scanResult = false;
    boolean overlapResult = false;
    boolean offBoard = false;
    //String level = "";


    public BattleshipModel() {
        //diffLevel = level;
        playerHits = new ArrayList<>();
        playerMisses= new ArrayList<>();
        computerHits = new ArrayList<>();
        computerMisses= new ArrayList<>();
        //adopted from group 20
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
        }else{
            return null;
        }
    }
    public Ship getComputerShip(String shipName) {
        if (shipName.equalsIgnoreCase("computer_aircraftcarrier")) {
            return this.computer_aircraftCarrier;
        }
        if (shipName.equalsIgnoreCase("computer_battleship")) {
            return this.computer_battleship;
        }
        if (shipName.equalsIgnoreCase("computer_submarine")) {
            return this.computer_submarine;
        }
        if (shipName.equalsIgnoreCase("computer_clipper")) {
            return this.computer_clipper;
        }
        if (shipName.equalsIgnoreCase("computer_dinghy")) {
            return this.computer_dinghy;
        } else {
            return null;
        }
    }

    public BattleshipModel placeComputerEasy(){

//        if(level.equals("easy")){
//            System.out.println("in place computer(bsmod): " + level);
//        }
        this.computer_aircraftCarrier.setLocation(new Coordinate(1,1), new Coordinate(1,5));
        shipSquares.addAll(this.computer_aircraftCarrier.getShipSquares());
        this.computer_battleship.setLocation(new Coordinate(2,1), new Coordinate(2,4));
        shipSquares.addAll(this.computer_battleship.getShipSquares());
        this.computer_submarine.setLocation(new Coordinate(3,1), new Coordinate(3,2));
        shipSquares.addAll(this.computer_submarine.getShipSquares());
        this.computer_clipper.setLocation(new Coordinate(4,1), new Coordinate(4,3));
        shipSquares.addAll(this.computer_clipper.getShipSquares());
        this.computer_dinghy.setLocation(new Coordinate(5,1), new Coordinate(5,1));
        shipSquares.addAll(this.computer_dinghy.getShipSquares());
        return this;
    }

    public BattleshipModel placeComputerHard(){
        int i, //Iterator
                j, //Iterator
                k, //Iterator for shipName array of Strings
                rowInt,
                colInt;
        boolean or; //Boolean for acquiring random orientation
        String[] shipName = new String[]{"Computer_AircraftCarrier", "Computer_Battleship", "Computer_Clipper", "Computer_Submarine", "Computer_Dinghy"};

        for(i=0, k=0; k<5; i++){
            overlapResult = false;
            offBoard = false;
            or = rand.nextBoolean();
            rowInt = rand.nextInt(10) + 1;
            colInt = rand.nextInt(10) + 1;
            System.out.println("I: " + i + ", rowInt: " + rowInt + ", colInt: " + colInt + ", or: " + or);
            if(or) { //horizontal
                System.out.println("2nd   I: " + i + ", rowInt: " + rowInt + ", colInt: " + colInt + ", or: " + or);
                this.getComputerShip(shipName[k]).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt, colInt + 4 - k));
            } else{ //vertical
                System.out.println("3rd   I: " + i + ", rowInt: " + rowInt + ", colInt: " + colInt + ", or: " + or);
                this.getComputerShip(shipName[k]).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt + 4 - k, colInt));
            }
            //this.getComputerShip(shipName[k]).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt, colInt + 4 - k));

            System.out.println("3rd   I: " + i + ", rowInt: " + rowInt + ", colInt: " + colInt + ", or: " + or);
            for(j = 0; j < this.getComputerShip(shipName[k]).getShipSquares().size(); j++){
                if(this.getComputerShip(shipName[k]).getShipSquares().get(j).getAcross() > 10 || this.getComputerShip(shipName[k]).getShipSquares().get(j).getDown() > 10) {
                    offBoard = true;
                    //"unplace" ship
                    this.getComputerShip(shipName[k]).setLocation(new Coordinate(0,0), new Coordinate(0, 0));
                    j = 1000;
                }
                // if master list already contains one of the new ship's squares, it's an overlap!
                if (shipSquares.contains(this.getComputerShip(shipName[k]).getShipSquares().get(j))) {
                    overlapResult = true;
                    //"unplace" ship
                    this.getComputerShip(shipName[k]).setLocation(new Coordinate(0,0), new Coordinate(0, 0));
                    j = 1000;
                }
                System.out.println("J: " + j + " I: " + i + ", rowInt: " + rowInt + ", colInt: " + colInt + ", or: " + or);
            }
            // if no overlap + not off the board, leave the ship alone and add its squares to the master list
            if(!(offBoard || overlapResult)) {
                System.out.println("k: " + k);
                this.shipSquares.addAll(this.getComputerShip(shipName[k]).getShipSquares());
                k++;
            }
        }
        System.out.println("This is being reached 2");
        return this;
    }

    public BattleshipModel placeShip(String shipName, String row, String col, String orientation) {
        int rowInt = Integer.parseInt(row);
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
                this.getShip(shipName).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt, colInt+1));
            }
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                this.getShip(shipName).setLocation(new Coordinate(rowInt,colInt),new Coordinate(rowInt,colInt+4));
            }
            if(shipName.equalsIgnoreCase("battleship")) {
                this.getShip(shipName).setLocation(new Coordinate(rowInt,colInt),new Coordinate(rowInt,colInt+3));
            }
            if(shipName.equalsIgnoreCase( "clipper")){
                this.getShip(shipName).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt, colInt+2));
            }
            if(shipName.equalsIgnoreCase("dinghy")){
                this.getShip(shipName).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt, colInt));
            }
        }else{
            //vertical
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                this.getShip(shipName).setLocation(new Coordinate(rowInt,colInt),new Coordinate(rowInt+4,colInt));
            }
            if(shipName.equalsIgnoreCase("battleship")) {
                this.getShip(shipName).setLocation(new Coordinate(rowInt,colInt),new Coordinate(rowInt+3,colInt));
            }
            if(shipName.equalsIgnoreCase("submarine")) {
                this.getShip(shipName).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt+1, colInt));
            }
            if(shipName.equalsIgnoreCase( "clipper")){
                this.getShip(shipName).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt+2, colInt));
            }
            if(shipName.equalsIgnoreCase("dinghy")){
                this.getShip(shipName).setLocation(new Coordinate(rowInt, colInt), new Coordinate(rowInt, colInt));
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

    //adopted from group 20
    void playerShot(Coordinate coor) {
        if(playerMisses.contains(coor)){
        }
        if(aircraftCarrier.covers(coor)){
            playerHits.add(coor);
        }else if (battleship.covers(coor)){
            playerHits.add(coor);
        }else if (submarine.covers(coor)){
            playerHits.add(coor);
        }else if (clipper.covers(coor)){
            playerHits.addAll(clipper.one_hit());
        }else if (dinghy.covers(coor)){
            playerHits.addAll(dinghy.one_hit());
        } else {
            playerMisses.add(coor);
        }
    }

    //adopted from group 20
    public void scan(int rowInt, int colInt) {
        Coordinate coor = new Coordinate(rowInt,colInt);
        scanResult = false;
        if(computer_aircraftCarrier.scan(coor)){
            scanResult = true;
        } else if (computer_battleship.scan(coor)){
            scanResult = true;
        }else if (computer_submarine.scan(coor)){
            scanResult = true;
        }else if (computer_clipper.scan(coor)){
            scanResult = true;
        }else if (computer_dinghy.scan(coor)) {
            scanResult = true;
        } else {
            scanResult = false;
        }
    }


//    public boolean getScanResult() {
//        return scanResult;
//    }
}
