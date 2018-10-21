package org.geekhub.lesson3.task1.controlpanel;

import org.geekhub.lesson3.task1.drivable.Drivable;
import org.geekhub.lesson3.task1.drivable.cars.Hatchback3Doors;
import org.geekhub.lesson3.task1.drivable.cars.Hatchback5Doors;
import org.geekhub.lesson3.task1.drivable.cars.SUV;
import org.geekhub.lesson3.task1.drivable.cars.Sedan;
import org.geekhub.lesson3.task1.drivable.constituents.Accelerator;
import org.geekhub.lesson3.task1.drivable.constituents.BrakePedal;
import org.geekhub.lesson3.task1.drivable.constituents.Engine;
import org.geekhub.lesson3.task1.drivable.constituents.GasTank;
import org.geekhub.lesson3.task1.drivable.constituents.SteeringWheel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ControlPanelImplTest {
    private static final int ACCELERATE_STRENGTH = 10;
    private static final int TURN_ANGLE_STEP = 20;
    private static final int BRAKING_STRENGTH = 20;
    private static final int MAX_GAS_TANK_VOLUME = 100;
    private static final int ENGINE_CAPACITY = 2;
    private static final int MAX_SPEED = 120;
    private static final int MAX_TURN_ANGLE = 70;
    public static final int FUEL_VOLUME = 30;
    private ControlPanel controlPanel;

    @BeforeMethod
    public void setUp() {
        controlPanel = new ControlPanelImpl();
    }

    @DataProvider
    public Object[][] cars() {
        final Engine i10Engine = new Engine(ENGINE_CAPACITY, MAX_SPEED);
        final GasTank i10GasTank = new GasTank(MAX_GAS_TANK_VOLUME);
        final SteeringWheel i10Wheel = new SteeringWheel(MAX_TURN_ANGLE, TURN_ANGLE_STEP);
        i10GasTank.fill(FUEL_VOLUME);

        final Engine i20Engine = new Engine(ENGINE_CAPACITY, MAX_SPEED);
        final GasTank i20GasTank = new GasTank(MAX_GAS_TANK_VOLUME);
        final SteeringWheel i20Wheel = new SteeringWheel(MAX_TURN_ANGLE, TURN_ANGLE_STEP);
        i20GasTank.fill(FUEL_VOLUME);

        final Engine lancerEngine = new Engine(ENGINE_CAPACITY, MAX_SPEED);
        final GasTank lancerGasTank = new GasTank(MAX_GAS_TANK_VOLUME);
        final SteeringWheel lancerWheel = new SteeringWheel(MAX_TURN_ANGLE, TURN_ANGLE_STEP);
        lancerGasTank.fill(FUEL_VOLUME);

        final Engine pajeroEngine = new Engine(ENGINE_CAPACITY, MAX_SPEED);
        final GasTank pajeroGasTank = new GasTank(MAX_GAS_TANK_VOLUME);
        final SteeringWheel pajeroWheel = new SteeringWheel(MAX_TURN_ANGLE, TURN_ANGLE_STEP);
        pajeroGasTank.fill(FUEL_VOLUME);
        return new Object[][] {
                {
                        new Hatchback3Doors(
                                "i10", new Accelerator(ACCELERATE_STRENGTH), new BrakePedal(BRAKING_STRENGTH),
                                i10Engine, i10GasTank, i10Wheel
                        ), i10Engine, i10GasTank, i10Wheel
                },
                {
                        new Hatchback5Doors(
                                "i20", new Accelerator(ACCELERATE_STRENGTH), new BrakePedal(BRAKING_STRENGTH),
                                i20Engine, i20GasTank, i20Wheel
                        ), i20Engine, i20GasTank, i20Wheel
                },
                {
                        new Sedan(
                                "Lancer X", new Accelerator(ACCELERATE_STRENGTH), new BrakePedal(BRAKING_STRENGTH),
                                lancerEngine, lancerGasTank, lancerWheel
                        ), lancerEngine, lancerGasTank, lancerWheel
                },
                {
                        new SUV(
                                "Pajero", new Accelerator(ACCELERATE_STRENGTH), new BrakePedal(BRAKING_STRENGTH),
                                pajeroEngine, pajeroGasTank, pajeroWheel
                        ), pajeroEngine, pajeroGasTank, pajeroWheel
                }
        };
    }

    @Test(dataProvider = "cars")
    public void controlPanelTurnsSteeringWheelOfTheCarLeft_when_turnLeft(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        controlPanel.turnLeft(car);
        assertEquals(wheel.getTurnAngle(), -wheel.getTurnAngleStep());
    }

    @Test(dataProvider = "cars")
    public void controlPanelTurnsSteeringWheelOfTheCarRight_when_turnRight(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        controlPanel.turnRight(car);
        assertEquals(wheel.getTurnAngle(), wheel.getTurnAngleStep());
    }

    @Test(dataProvider = "cars")
    public void controlStartsTheEngineIfStopped_when_accelerate(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        assertFalse(engine.isStarted());
        controlPanel.accelerate(car);
        assertTrue(engine.isStarted());
    }

    @Test(dataProvider = "cars")
    public void controlStartsDoesNotDoAnythingWithEngine_when_accelerateWithAlreadyStartedEngine(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        car.startTheEngine();
        assertTrue(engine.isStarted());

        controlPanel.accelerate(car);
        assertTrue(engine.isStarted());
    }

    @Test(dataProvider = "cars")
    public void controlPanelIncreasesTheSpeedForStoppedCar_when_accelerate(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        assertEquals(car.speed(), 0);

        assertEquals(controlPanel.accelerate(car), 50);
        assertEquals(car.speed(), 50);
    }

    @Test(dataProvider = "cars")
    public void controlPanelIncreasesTheSpeedForMovingCar_when_accelerate(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        car.startTheEngine();
        car.accelerate();
        assertEquals(car.speed(), 50);

        assertEquals(controlPanel.accelerate(car), 100);
        assertEquals(car.speed(), 100);
    }

    @Test(dataProvider = "cars")
    public void controlPanelReducesTheSpeedAndStopsTheCarEngine_when_brake(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        controlPanel.accelerate(car);
        assertEquals(car.speed(), 50);

        assertEquals(controlPanel.brake(car), 30);
        assertEquals(controlPanel.brake(car), 10);
        assertEquals(controlPanel.brake(car), 0);
        assertFalse(engine.isStarted());
    }

    @Test(dataProvider = "cars")
    public void controlPanelReducesTheSpeedForMovingCar_when_brake(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        controlPanel.accelerate(car);

        assertEquals(controlPanel.brake(car), 30);
        assertEquals(car.speed(), 30);
    }

    @Test(dataProvider = "cars")
    public void carUsesFuel_when_acceleratedByControlPanel(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        assertEquals(tank.getCurrentVolume(), FUEL_VOLUME);
        controlPanel.accelerate(car);

        assertEquals(tank.getCurrentVolume(), 20);
    }

    @Test(dataProvider = "cars")
    public void carDoesNotUseFuel_when_slowedDownByControlPanel(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        assertEquals(tank.getCurrentVolume(), FUEL_VOLUME);
        controlPanel.accelerate(car);
        assertEquals(tank.getCurrentVolume(), 20);

        controlPanel.brake(car);
        assertEquals(tank.getCurrentVolume(), 20);
    }

    @Test(dataProvider = "cars")
    public void amountOfFuelIncreasesInTank_when_controlPanelFillTank(Drivable car, Engine engine, GasTank tank, SteeringWheel wheel) {
        assertEquals(tank.getCurrentVolume(), FUEL_VOLUME);
        controlPanel.fillTank(car, 10);
        assertEquals(tank.getCurrentVolume(), FUEL_VOLUME + 10);
    }
}