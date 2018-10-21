package org.geekhub.lesson3.task1.drivable.constituents;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GasTankTest {
    private GasTank tank;

    @BeforeMethod
    public void setUp() {
        tank = new GasTank(5);
    }

    @Test
    public void gasTankCreatedWithMaxVolume5_when_usedMaxVolumeLessThan5() {
        final GasTank tank = new GasTank(2);
        assertEquals(tank.getMaxVolume(), 5);
    }

    @Test
    public void gasTankCreatedWithMaxVolume100_when_usedMaxVolumeGtThan100() {
        final GasTank tank = new GasTank(200);
        assertEquals(tank.getMaxVolume(), 100);
    }

    @Test
    public void gasTankCreatedWithMaxVolume5_when_usedMaxVolumeLessThan0() {
        final GasTank tank = new GasTank(-100);
        assertEquals(tank.getMaxVolume(), 5);
    }

    @Test
    public void gasTankCreatedWithMaxVolume5_when_usedMaxVolumeEqTo5() {
        final GasTank tank = new GasTank(5);
        assertEquals(tank.getMaxVolume(), 5);
    }

    @Test
    public void gasTankCreatedWithMaxVolume6_when_usedMaxVolumeEqTo6() {
        final GasTank tank = new GasTank(6);
        assertEquals(tank.getMaxVolume(), 6);
    }

    @Test
    public void gasTankCreatedWithCurrentVolumeEqTo0() {
        assertEquals(tank.getCurrentVolume(), 0);
    }

    @Test
    public void currentVolumeStay0_when_tryToUseFuelFromEmptyTank() {
        tank.use(100);
        assertEquals(tank.getCurrentVolume(), 0);
    }

    @Test
    public void currentVolumeBecomeMaxAllowedValue_when_tryToFillTankWithBiggerVolumeThatAllowed() {
        tank.fill(100);
        assertEquals(tank.getCurrentVolume(), 5);
    }

    @Test
    public void currentVolumeBecome0_when_tryToUseBiggerAmountOfFuelThanExists() {
        tank.fill(5);

        tank.use(100);
        assertEquals(tank.getCurrentVolume(), 0);
    }

    @Test
    public void currentVolumeChanges_when_tryToFillTankWithAnyAmountOfFuel() {
        tank.fill(2);
        assertEquals(tank.getCurrentVolume(), 2);
    }

    @Test
    public void currentVolumeChanges_when_tryToUseSomeAmountOfFuel() {
        tank.fill(4);
        assertEquals(tank.getCurrentVolume(), 4);

        tank.use(2);
        assertEquals(tank.getCurrentVolume(), 2);
    }

    @Test
    public void currentVolumeStayUnchanged_when_tryToFillTankWithNegativeAmountOfFuel() {
        tank.fill(2);
        assertEquals(tank.getCurrentVolume(), 2);

        tank.fill(-5);
        assertEquals(tank.getCurrentVolume(), 2);
    }

    @Test
    public void currentVolumeStayUnchanged_when_tryToUseNegativeAmountOfFuel() {
        tank.fill(2);
        assertEquals(tank.getCurrentVolume(), 2);

        tank.use(-5);
        assertEquals(tank.getCurrentVolume(), 2);
    }

    @Test
    public void exist0PerCentOfFuel_when_tankIsEmpty() {
        assertEquals(tank.status(), "0.0% of fuel exist");
    }

    @Test
    public void exist20PerCentOfFuel_when_tankContains2LitresOf5() {
        tank.fill(2);
        assertEquals(tank.status(), "40.0% of fuel exist");
    }

    @Test
    public void exist100PerCentOfFuel_when_tankIsFull() {
        tank.fill(5);
        assertEquals(tank.status(), "100.0% of fuel exist");
    }

    @Test
    public void gasTankEmpty_when_tankDoesNotFilled() {
        assertTrue(tank.isEmpty());
    }

    @Test
    public void gasTankEmpty_when_currentVolumeLessThan0001() {
        tank.fill(0.0005);
        assertTrue(tank.isEmpty());
    }

    @Test
    public void gasTankNotEmpty_when_currentVolumeGtThan0001() {
        tank.fill(0.002);
        assertFalse(tank.isEmpty());
    }
}