package org.geekhub.lesson6.task1;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class GarageTest {

    @Test
    public void shouldReturnEmptyList_whenNoCars() {
        Garage garage = new GarageImpl();

        List<Car> cars = garage.getCars();

        Assert.assertTrue(cars.isEmpty());
    }

    @Test
    public void shouldReturnCopyOfCarsList_whenCarsPresent() {
        Garage garage = new GarageImpl(
            new Car("Dodge", 700, Color.RED)
        );

        List<Car> cars = garage.getCars();
        cars.clear();

        Assert.assertEquals(garage.getCars().size(), 1);
        Assert.assertTrue(garage.getCars().contains(new Car("Dodge", 700, Color.RED)));
    }

    @Test
    public void shouldReturnSortableCars() {
        Garage garage = new GarageImpl(
            new Car("Dodge", 700, Color.RED),
            new Car("BMW", 800, new Color(255, 0, 0)),
            new Car("Toyota", 800, new Color(255, 0, 0)),
            new Car("Audi", 800, new Color(255, 0, 0))
        );

        List<Car> cars = garage.getCars();

        Assert.assertEquals(cars.stream().sorted().collect(Collectors.toList()), cars);
    }

    @Test
    public void shouldReturnCarsSortedByBrandInAlphabeticalOrderByDefault() {
        Garage garage = new GarageImpl(
            new Car("Dodge", 700, Color.RED),
            new Car("BMW", 800, new Color(255, 0, 0)),
            new Car("Toyota", 800, new Color(255, 0, 0)),
            new Car("Audi", 800, new Color(255, 0, 0))
        );

        List<Car> cars = garage.getCars();

        Assert.assertEquals(cars.size(), 4);
        Assert.assertEquals(cars.get(0), new Car("Audi", 800, new Color(255, 0, 0)));
        Assert.assertEquals(cars.get(1), new Car("BMW", 800, new Color(255, 0, 0)));
        Assert.assertEquals(cars.get(2), new Car("Dodge", 700, Color.RED));
        Assert.assertEquals(cars.get(3), new Car("Toyota", 800, new Color(255, 0, 0)));
    }

    @Test
    public void shouldReturnCarsSortedByPowerInDescendingOrder_whenBrandEquals() {
        Garage garage = new GarageImpl(
            new Car("BMW", 700, Color.RED),
            new Car("BMW", 800, new Color(255, 0, 0)),
            new Car("BMW", 810, new Color(255, 0, 0)),
            new Car("BMW", 790, new Color(255, 0, 0))
        );

        List<Car> cars = garage.getCars();

        Assert.assertEquals(cars.size(), 4);
        Assert.assertEquals(cars.get(0), new Car("BMW", 810, new Color(255, 0, 0)));
        Assert.assertEquals(cars.get(1), new Car("BMW", 800, new Color(255, 0, 0)));
        Assert.assertEquals(cars.get(2), new Car("BMW", 790, new Color(255, 0, 0)));
        Assert.assertEquals(cars.get(3), new Car("BMW", 700, Color.RED));
    }

    /**
     * @see <a href="https://en.wikipedia.org/wiki/Relative_luminance">Relative luminance</a>
     */
    @Test
    public void shouldReturnCarsSortedByColorRelativeLuminosityAscending_whenBrandAndPowerEquals() {
        Garage garage = new GarageImpl(
            new Car("BMW", 800, new Color(255, 0, 0)),
            new Car("BMW", 800, new Color(10, 255, 0)),
            new Car("BMW", 800, new Color(10, 255, 10)),
            new Car("BMW", 800, new Color(0, 255, 10)),
            new Car("BMW", 800, new Color(0, 0, 255))
        );

        List<Car> cars = garage.getCars();

        Assert.assertEquals(cars.size(), 5);
        Assert.assertEquals(cars.get(0), new Car("BMW", 800, new Color(0, 0, 255)));
        Assert.assertEquals(cars.get(1), new Car("BMW", 800, new Color(255, 0, 0)));
        Assert.assertEquals(cars.get(2), new Car("BMW", 800, new Color(0, 255, 10)));
        Assert.assertEquals(cars.get(3), new Car("BMW", 800, new Color(10, 255, 0)));
        Assert.assertEquals(cars.get(4), new Car("BMW", 800, new Color(10, 255, 10)));
    }
}