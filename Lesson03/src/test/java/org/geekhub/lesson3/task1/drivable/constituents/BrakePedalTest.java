package org.geekhub.lesson3.task1.drivable.constituents;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BrakePedalTest {
    @Test
    public void brakeCreatedWithStrengthEq25_when_usedStrengthGtThan25() {
        final BrakePedal brake = new BrakePedal(100);
        assertEquals(brake.getBrakingStrength(), 25);
    }

    @Test
    public void brakeCreatedWithStrengthEq5_when_usedStrengthLessThan5() {
        final BrakePedal brake = new BrakePedal(-5);
        assertEquals(brake.getBrakingStrength(), 5);
    }

    @Test
    public void brakeCreatedWithStrengthEq17_when_usedStrengthEq17() {
        final BrakePedal brake = new BrakePedal(17);
        assertEquals(brake.getBrakingStrength(), 17);
    }
}