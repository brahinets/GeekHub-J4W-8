package org.geekhub.lesson5.task1;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

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
        Car car2 = CarFactory.create("Furiosa", 420, "BMW", new Color(0, 255, 0));

        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car1);
    }

    @Test
    public void equals_shouldBeTransitive() {
        Car car1 = CarFactory.create("Clockwork", 560, "Ferrari", Color.RED);
        Car car2 = CarFactory.create("Zinger", 560, "Ferrari", new Color(255, 0, 0));
        Car car3 = CarFactory.create("Apollo", 560, "Ferrari", new Color(255, 0, 0));

        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car3);
        Assert.assertEquals(car3, car1);
    }


    @Test
    public void sameCars_shouldHaveSameHashCode() {
        Car car1 = CarFactory.create("SpeedX", 700, "Tesla", Color.BLUE);
        Car car2 = CarFactory.create("Torch", 700, "Tesla", new Color(0, 0, 255));

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        Assert.assertEquals(cars.size(), 1);
    }

    @Test
    public void differentCars_shouldHaveDifferentHashCode() {
        Car car1 = CarFactory.create("SpeedX", 700, "Tesla", Color.BLUE);
        Car car2 = CarFactory.create("Torch", 600, "Tesla", new Color(0, 0, 255));

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        Assert.assertEquals(cars.size(), 2);
    }
}