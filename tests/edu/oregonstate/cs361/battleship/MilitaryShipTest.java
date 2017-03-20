package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MilitaryShipTest {
    @Test
    void MilitaryShip() {
        MilitaryShip x = new MilitaryShip("Submarine",3,7,7);
        assertEquals(x,new MilitaryShip("Submarine",3,7,7));
    }
	
    @Test
    void testsScan() {
        BattleshipModel model = new BattleshipModel();
        model.scan(2,2);
        assertEquals(true,model.getScanResult());

        model.scan(6,6);
        assertEquals(false,model.getScanResult());

        model.shootAtComputer(1,1) ;
        assertEquals(true, model.computerHits.isEmpty());
        
        model.shootAtComputer(2,3) ;
        assertEquals(2, model.computerHits.get(0).getAcross());
        assertEquals(3, model.computerHits.get(0).getDown());
        
        model.shootAtComputer(6,8) ;
        assertEquals(6, model.computerHits.get(1).getAcross());
        assertEquals(8, model.computerHits.get(1).getDown());
        
        model.shootAtComputer(4,4) ;
        assertEquals(4, model.computerHits.get(2).getAcross());
        assertEquals(4, model.computerHits.get(2).getDown());
        
        model.shootAtComputer(7,3) ;
        assertEquals(7, model.computerHits.get(3).getAcross());
        assertEquals(3, model.computerHits.get(3).getDown());
        
        model.shootAtComputer(9,6) ;
        assertEquals(9, model.computerHits.get(4).getAcross());
        assertEquals(6, model.computerHits.get(4).getDown());
  }

}
