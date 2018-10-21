package org.geekhub.lesson3.task1.drivable.cars;

import org.geekhub.lesson3.task1.drivable.constituents.Accelerator;
import org.geekhub.lesson3.task1.drivable.constituents.BrakePedal;
import org.geekhub.lesson3.task1.drivable.constituents.Engine;
import org.geekhub.lesson3.task1.drivable.constituents.GasTank;
import org.geekhub.lesson3.task1.drivable.constituents.SteeringWheel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SedanTest {
    private Sedan car;

    @BeforeMethod
    public void setUp() {
        car = new Sedan(
                "Lancer X", new Accelerator(10),
                new BrakePedal(10), new Engine(2, 100),
                new GasTank(50), new SteeringWheel(50, 20)
        );
    }

    @Test
    public void carHas3Doors() {
        assertEquals(car.getNumberOfDoors(), 4);
    }

    @Test
    public void carCanContain5Passengers() {
        assertEquals(car.getMaxNumberOfPassengers(), 5);
    }

    @Test
    public void carHasSpecifiedName() {
        assertEquals(car.getName(), "Lancer X");
    }
}