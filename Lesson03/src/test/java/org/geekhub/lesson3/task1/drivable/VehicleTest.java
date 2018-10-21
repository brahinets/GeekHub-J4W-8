package org.geekhub.lesson3.task1.drivable;

import org.geekhub.lesson3.task1.drivable.constituents.Accelerator;
import org.geekhub.lesson3.task1.drivable.constituents.BrakePedal;
import org.geekhub.lesson3.task1.drivable.constituents.Engine;
import org.geekhub.lesson3.task1.drivable.constituents.GasTank;
import org.geekhub.lesson3.task1.drivable.constituents.SteeringWheel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VehicleTest {
    private Vehicle vehicle;
    private Engine engine;
    private GasTank gasTank;
    private SteeringWheel steeringWheel;

    @BeforeMethod
    public void setUp() {
        final Accelerator accelerator = new Accelerator(10);
        final BrakePedal brakePedal = new BrakePedal(20);
        engine = new Engine(4, 70);
        gasTank = new GasTank(100);
        steeringWheel = new SteeringWheel(70, 20);
        vehicle = new Vehicle(
                "vehicle", 4, 4,
                accelerator, brakePedal, engine, gasTank, steeringWheel
        ) {};
    }

    @Test
    public void vehicleHas0Speed_when_created() {
        assertEquals(vehicle.speed(), 0);
    }

    @Test
    public void vehicleDoesNotStarEngine_when_created() {
        assertFalse(engine.isStarted());
    }

    @Test
    public void vehicleDoesNotFillTheTank_when_created() {
        assertTrue(gasTank.isEmpty());
    }

    @Test
    public void vehicleTurnsSteeringWheelLeft_when_turnLeft() {
        vehicle.turnLeft();
        assertEquals(steeringWheel.getTurnAngle(), -steeringWheel.getTurnAngleStep());
    }

    @Test
    public void vehicleTurnsSteeringWheelRight_when_turnRight() {
        vehicle.turnRight();
        assertEquals(steeringWheel.getTurnAngle(), steeringWheel.getTurnAngleStep());
    }

    @Test
    public void vehicleStartsEngine_when_startTheEngine() {
        vehicle.startTheEngine();
        assertTrue(engine.isStarted());
    }

    @Test
    public void vehicleStopsEngine_when_stopTheEngine() {
        vehicle.startTheEngine();
        assertTrue(engine.isStarted());

        vehicle.stopTheEngine();
        assertFalse(engine.isStarted());
    }

    @Test
    public void vehicleFillsTankBySpecifiedAmountOfFuel_when_fillTank() {
        assertTrue(gasTank.isEmpty());

        vehicle.fillTank(10);
        assertEquals(gasTank.getCurrentVolume(), 10);
    }

    @Test
    public void vehicleIncreasesSpeed_when_accelerateWithStartedEngineAndNonEmptyTank() {
        vehicle.fillTank(10);
        vehicle.startTheEngine();

        vehicle.accelerate();
        assertEquals(vehicle.speed(), 30);
    }

    @Test
    public void vehicleIncreasesSpeedToMaxAllowed_when_accelerateWithStartedEngineAndNonEmptyTank() {
        vehicle.fillTank(100);
        vehicle.startTheEngine();

        vehicle.accelerate();
        vehicle.accelerate();
        assertEquals(vehicle.speed(), 70);
    }

    @Test
    public void vehicleDoesNotChangeSpeed_when_accelerateWithStartedEngineAndEmptyTank() {
        vehicle.startTheEngine();

        vehicle.accelerate();
        assertEquals(vehicle.speed(), 0);
    }

    @Test
    public void vehicleDoesNotChangeSpeed_when_accelerateWithStoppedEngineAndNonEmptyTank() {
        vehicle.fillTank(10);

        vehicle.accelerate();
        assertEquals(vehicle.speed(), 0);
    }

    @Test
    public void vehicleReducesSpeed_when_brake() {
        vehicle.fillTank(10);
        vehicle.startTheEngine();
        vehicle.accelerate();

        assertEquals(vehicle.speed(), 30);

        vehicle.brake();
        assertEquals(vehicle.speed(), 10);
    }

    @Test
    public void vehicleDoesNotChangeSpeed_when_brakeWith0Speed() {
        vehicle.brake();
        assertEquals(vehicle.speed(), 0);
    }
}