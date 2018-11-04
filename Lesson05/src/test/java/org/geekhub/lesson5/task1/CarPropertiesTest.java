package org.geekhub.lesson5.task1;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;

public class CarPropertiesTest {

    @Test
    public void equals_shouldBeNullSafe() {
        Car car = CarFactory.create("Firebrand", 700, "BMW", Color.GREEN);

        Assert.assertNotEquals(car, null);
    }

    @Test
    public void equals_shouldBeReflexive() {
        Car car = CarFactory.create("Pacer", 700, "BMW", Color.GREEN);

        Assert.assertEquals(car, car);
    }

    @Test
    public void equals_shouldBeSymmetric() {
        Car car1 = CarFactory.create("Bam Bam", 420, "BMW", Color.GREEN);
        Car car2 = CarFactory.create("Furiosa", 420, "BMW", Color.GREEN);

        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car1);
    }

    @Test
    public void equals_shouldBeTransitive() {
        Car car1 = CarFactory.create("Clockwork", 560, "Ferrari", Color.RED);
        Car car2 = CarFactory.create("Zinger", 560, "Ferrari", Color.RED);
        Car car3 = CarFactory.create("Apollo", 560, "Ferrari", Color.RED);

        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car3);
        Assert.assertEquals(car3, car1);
    }


    @Test
    public void sameCars_shouldHaveSameHashCode() {
        Car car1 = CarFactory.create("SpeedX", 700, "Tesla", Color.BLUE);
        Car car2 = CarFactory.create("Torch", 700, "Tesla", Color.BLUE);

        HashSet<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        Assert.assertEquals(cars.size(), 1);
    }

    @Test
    public void differentCars_shouldHaveDifferentHashCode() {
        Car car1 = CarFactory.create("SpeedX", 700, "Tesla", Color.BLUE);
        Car car2 = CarFactory.create("Torch", 600, "Tesla", Color.BLUE);

        HashSet<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        Assert.assertEquals(cars.size(), 2);
    }
}