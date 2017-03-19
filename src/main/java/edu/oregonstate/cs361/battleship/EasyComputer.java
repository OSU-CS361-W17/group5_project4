package edu.oregonstate.cs361.battleship;

/**
 * Created by williamstribyjr on 3/14/17.
 */
public class EasyComputer extends BattleshipModel{

    public EasyComputer(){


    }
    /*public BattleshipModel placeComputerEasy(){

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
    }*/
    public class global {
    	public static int x = 1;
    	public static int y = 1;
    }
    public void shootAtPlayer() {
	if(global.x == 10){
		global.x = 1;
		global.y = global.y +1; 
	}
	Coordinate coor = new Coordinate(global.x,global.y);
	playerShot(coor);
	global.x = global.x + 1;
	

	/* OLD CODE
        int max = 10;
        int min = 1;
        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;

        Coordinate coor = new Coordinate(randRow,randCol);
        playerShot(coor);*/
    }

    public BattleshipModel computerPlaceShip() {
        this.placeShip("aircraftcarrier", "1", "1", "horizontal");
        this.placeShip("battleship", "3", "7", "vertical");
        this.placeShip("clipper", "5", "3", "horizontal");
        this.placeShip("submarine", "8", "2", "vertical");
        this.placeShip("dinghy", "9", "7", "horizontal");
        return this;
    }
}
