package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CivilianShipTest {
    @Test
    void CivilianShip() {
        CivilianShip x = new CivilianShip("Dingy",1,7,7);
        assertEquals(x,new CivilianShip("Dingy",1,7,7));
    }

}
