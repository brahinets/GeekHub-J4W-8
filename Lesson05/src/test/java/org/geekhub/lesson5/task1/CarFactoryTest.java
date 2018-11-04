package org.geekhub.lesson5.task1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CarFactoryTest {
    @Test
    public void twoCarsWithSameNamePowerModelColor_shouldBeEqual() {
        Car car1 = CarFactory.create("Scorch", 800, "Dodge", Color.RED);
        Car car2 = CarFactory.create("Scorch", 800, "Dodge", Color.RED);

        Assert.assertEquals(car1, car2);
    }

    @Test
    public void twoCarsWithDifferentNameAndSamePowerModelColor_shouldBeEqual() {
        Car car1 = CarFactory.create("Scorch", 800, "Dodge", Color.RED);
        Car car2 = CarFactory.create("Swoosh", 800, "Dodge", Color.RED);

        Assert.assertEquals(car1, car2);
    }

    @Test
    public void twoCarsWithDifferentPower_shouldBeNotEqual() {
        Car car1 = CarFactory.create("Racer", 700, "Dodge", Color.RED);
        Car car2 = CarFactory.create("Racer", 800, "Dodge", Color.RED);

        Assert.assertNotEquals(car1, car2);
    }

    @Test
    public void twoCarsWithDifferentModel_shouldBeNotEqual() {
        Car car1 = CarFactory.create("Screech", 700, "Dodge", Color.RED);
        Car car2 = CarFactory.create("Screech", 700, "BMW", Color.RED);

        Assert.assertNotEquals(car1, car2);
    }

    @Test
    public void twoCarsWithDifferentColor_shouldBeNotEqual() {
        Car car1 = CarFactory.create("Scorch", 700, "BMW", Color.GREEN);
        Car car2 = CarFactory.create("Scorch", 700, "BMW", Color.RED);

        Assert.assertNotEquals(car1, car2);
    }
}