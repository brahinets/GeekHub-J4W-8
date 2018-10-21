package org.geekhub.lesson3.task1.drivable.constituents;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AcceleratorTest {
    private Accelerator accelerator;
    private GasTank gasTank;
    private Engine engine;

    @BeforeMethod
    public void setUp() {
        accelerator = new Accelerator(10);
        engine = new Engine(2, 70);
        gasTank = new GasTank(100);
    }

    @Test
    public void acceleratorCreatedWithStrengthEq25_when_usedStrengthGtThan25() {
        final Accelerator accelerator = new Accelerator(100);
        assertEquals(accelerator.getAccelerateStrength(), 25);
    }

    @Test
    public void acceleratorCreatedWithStrengthEq5_when_usedStrengthLessThan5() {
        final Accelerator accelerator = new Accelerator(-5);
        assertEquals(accelerator.getAccelerateStrength(), 5);
    }

    @Test
    public void acceleratorCreatedWithStrengthEq17_when_usedStrengthEq17() {
        final Accelerator accelerator = new Accelerator(17);
        assertEquals(accelerator.getAccelerateStrength(), 17);
    }

    @Test
    public void acceleratorDoesNotWork_when_gasTankIsEmpty() {
        assertEquals(accelerator.accelerate(engine, gasTank), 0);
    }

    @Test
    public void acceleratorChangesSpeedAccordingToItStrength_when_accelerate() {
        gasTank.fill(100);
        engine.start();
        assertEquals(accelerator.accelerate(engine, gasTank), 50);
    }

    @Test
    public void acceleratorChangesSpeedAccordingToItStrengthWhileTankIsNotEmpty_when_accelerate() {
        gasTank.fill(4);
        engine.start();
        assertEquals(accelerator.accelerate(engine, gasTank), 20);
    }

    @Test
    public void acceleratorDoesNotChangeSpeed_when_engineIsNotStarted() {
        gasTank.fill(10);
        assertEquals(accelerator.accelerate(engine, gasTank), 0);
    }
}