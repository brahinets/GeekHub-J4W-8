package org.geekhub.lesson3.task1.drivable.constituents;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SteeringWheelTest {
    @Test
    public void wheelCreatedWithMaxTurnAngleEq78_when_usedMaxTurnAngleGtThan78() {
        final SteeringWheel wheel = new SteeringWheel(100, 10);
        assertEquals(wheel.getMaxTurnAngle(), 78);
    }

    @Test
    public void wheelCreatedWithMaxTurnAngleEq40_when_usedMaxTurnAngleLessThan40() {
        final SteeringWheel wheel = new SteeringWheel(-5, 10);
        assertEquals(wheel.getMaxTurnAngle(), 40);
    }

    @Test
    public void wheelCreatedWithMaxTurnAngleEq65_when_usedMaxTurnAngleEq65() {
        final SteeringWheel wheel = new SteeringWheel(65, 10);
        assertEquals(wheel.getMaxTurnAngle(), 65);
    }

    @Test
    public void wheelCreatedWithTurnAngleStepEq30_when_usedTurnAngleStepGtThan30() {
        final SteeringWheel wheel = new SteeringWheel(50, 100);
        assertEquals(wheel.getTurnAngleStep(), 30);
    }

    @Test
    public void wheelCreatedWithTurnAngleStepEq10_when_usedTurnAngleStepLessThan10() {
        final SteeringWheel wheel = new SteeringWheel(50, -50);
        assertEquals(wheel.getTurnAngleStep(), 10);
    }

    @Test
    public void wheelCreatedWithTurnAngleStepEq25_when_usedTurnAngleStepEq25() {
        final SteeringWheel wheel = new SteeringWheel(50, 25);
        assertEquals(wheel.getTurnAngleStep(), 25);
    }

    @Test
    public void wheelCreatedWithTurnAngleEq0() {
        final SteeringWheel wheel = new SteeringWheel(50, 20);
        assertEquals(wheel.getTurnAngle(), 0);
    }

    @Test
    public void wheelTurns20DegreesLeft_when_tryToTurnLeftWheelWithTurnStepEq30() {
        final SteeringWheel wheel = new SteeringWheel(50, 30);
        wheel.turnLeft();

        assertEquals(wheel.getTurnAngle(), -30);
    }

    @Test
    public void wheelTurns50DegreesLeft_when_tryToTurnLeftWheelWithTurnStepEq30Twice() {
        final SteeringWheel wheel = new SteeringWheel(50, 30);
        wheel.turnLeft();
        wheel.turnLeft();

        assertEquals(wheel.getTurnAngle(), -50);
    }

    @Test
    public void wheelTurns20DegreesRight_when_tryToTurnRightWheelWithTurnStepEq30() {
        final SteeringWheel wheel = new SteeringWheel(50, 30);
        wheel.turnRight();

        assertEquals(wheel.getTurnAngle(), 30);
    }

    @Test
    public void wheelTurns50DegreesRight_when_tryToTurnRightWheelWithTurnStepEq30Twice() {
        final SteeringWheel wheel = new SteeringWheel(50, 30);
        wheel.turnRight();
        wheel.turnRight();

        assertEquals(wheel.getTurnAngle(), 50);
    }

    @Test
    public void wheelStatusShowTurnAngle_when_turnLeftAndCheckStatus() {
        final SteeringWheel wheel = new SteeringWheel(50, 30);

        assertEquals(wheel.status(), "turn angle = 0");
        wheel.turnLeft();
        assertEquals(wheel.status(), "turn angle = -30");
    }

    @Test
    public void wheelStatusShowTurnAngle_when_turnRightAndCheckStatus() {
        final SteeringWheel wheel = new SteeringWheel(50, 30);

        assertEquals(wheel.status(), "turn angle = 0");
        wheel.turnRight();
        assertEquals(wheel.status(), "turn angle = 30");
    }
}