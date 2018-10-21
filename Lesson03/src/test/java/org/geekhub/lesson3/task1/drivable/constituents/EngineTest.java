package org.geekhub.lesson3.task1.drivable.constituents;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EngineTest {
    private Engine engine;

    @BeforeMethod
    public void setUp() {
        engine = new Engine(2, 97);
    }

    @Test
    public void engineCreatedWithCapacityEq6_when_usedCapacityGtThan6() {
        final Engine engine = new Engine(100, 100);
        assertEquals(engine.getCapacity(), 6);
    }

    @Test
    public void engineCreatedWithCapacityEq05_when_usedCapacityLessThan05() {
        final Engine engine = new Engine(0.01, 100);
        assertEquals(engine.getCapacity(), 0.5);
    }

    @Test
    public void engineCreatedWithCapacityEq2_when_usedCapacityEq2() {
        final Engine engine = new Engine(2, 100);
        assertEquals(engine.getCapacity(), 2);
    }

    @Test
    public void engineCreatedWithMaxSpeedEq400_when_usedMaxSpeedGtThan400() {
        final Engine engine = new Engine(2, 500);
        assertEquals(engine.getMaxSpeed(), 400);
    }

    @Test
    public void engineCreatedWithMaxSpeedEq60_when_usedMaxSpeedLessThan60() {
        final Engine engine = new Engine(2, 10);
        assertEquals(engine.getMaxSpeed(), 60);
    }

    @Test
    public void engineCreatedWithMaxSpeedEq97_when_usedMaxSpeedEq97() {
        final Engine engine = new Engine(2, 97);
        assertEquals(engine.getMaxSpeed(), 97);
    }

    @Test
    public void engineCreatesStopped() {
        assertFalse(engine.isStarted());
    }

    @Test
    public void engineBecomesStarted_when_startIt() {
        engine.start();
        assertTrue(engine.isStarted());
        assertEquals(engine.status(), "started");
    }

    @Test
    public void engineStaysStarted_when_startItTwice() {
        engine.start();
        engine.start();
        assertTrue(engine.isStarted());
        assertEquals(engine.status(), "started");
    }

    @Test
    public void engineBecomesStopped_when_stopStarted() {
        engine.start();
        engine.stop();
        assertFalse(engine.isStarted());
        assertEquals(engine.status(), "stopped");
    }

    @Test
    public void engineStaysStopped_when_stopAlreadyStopped() {
        engine.stop();
        assertFalse(engine.isStarted());
        assertEquals(engine.status(), "stopped");
    }

    @Test
    public void engineUsesFuelFromTheTank_when_workStarted() {
        engine.start();
        final int volume = 10;
        final GasTank tank = new GasTank(20);
        tank.fill(volume);

        engine.work(tank);
        assertEquals(tank.getCurrentVolume(), volume - engine.getCapacity());
    }

    @Test
    public void engineDoesNotUseFuelFromTheTank_when_workStopped() {
        final int volume = 10;
        final GasTank tank = new GasTank(20);
        tank.fill(volume);

        engine.work(tank);
        assertEquals(tank.getCurrentVolume(), volume);
    }
}